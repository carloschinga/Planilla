package cu;

import dao.PlanillaConceptoDAO;
import dao.PlanillaDAO;
import dao.VistaAuxiliarDetallePersonaDAO;
import dao.VistaPersonaDetalleDAO;
import dao.VistaPlanillaConceptoDAO;
import dto.Planilla;
import dto.VistaAuxiliarDetallePersona;
import dto.VistaPlanillaConcepto;
import org.apache.commons.jexl3.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.VistaPersonaDetalle;
import org.json.JSONObject;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcularPlanilla {

    private final EntityManagerFactory emf;

    private final PlanillaDAO planillaDAO;
    private final PlanillaConceptoDAO planillaConceptoDAO;
    private final VistaPlanillaConceptoDAO vistaPlanillaConceptoDAO;
    private final VistaPersonaDetalleDAO vistaPersonaAfpDAO;
    private final VistaAuxiliarDetallePersonaDAO vistaAuxiliarDetallePersonaDAO;

    public CalcularPlanilla() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");

        this.planillaDAO = new PlanillaDAO(emf);
        this.vistaPlanillaConceptoDAO = new VistaPlanillaConceptoDAO(emf);
        this.vistaPersonaAfpDAO = new VistaPersonaDetalleDAO(emf);
        this.vistaAuxiliarDetallePersonaDAO = new VistaAuxiliarDetallePersonaDAO(emf);
        this.planillaConceptoDAO = new PlanillaConceptoDAO(emf);
    }

    public String procesar(int codiPeri) {
        JSONObject resultado = new JSONObject();
        String jsonString = existePeriodoAProcesar(codiPeri);
        resultado = new JSONObject(jsonString);

        if ("si".equals(resultado.getString("resultado"))) {
            return calcularValores(codiPeri);
        }
        return resultado.put("resultado", "error").put("mensaje", "No existe el Periodo a Procesar").toString();
    }

    public String existePeriodoAProcesar(int codiPeri) {
        JSONObject resultado = new JSONObject();
        List<Planilla> lista = planillaDAO.listaXPeriodo(codiPeri);

        if (lista.isEmpty()) {
            return resultado.put("resultado", "no").toString();
        }
        return resultado.put("resultado", "si").toString();
    }

    private String calcularValores(int codiPeri) {
        JSONObject resultado = new JSONObject();
        List<VistaPersonaDetalle> listaPersona = vistaPersonaAfpDAO.findVistaPersonaDetalleEntities();
        List<VistaPlanillaConcepto> listaPlanillaConceptos = vistaPlanillaConceptoDAO.listaPlanillaConceptoXPeriodo(codiPeri);
        List<VistaAuxiliarDetallePersona> listaAuxiliarDetalle = vistaAuxiliarDetallePersonaDAO.findVistaAuxiliarDetallePersonaByPeriodo(codiPeri);

        Map<String, BigDecimal> valores = new HashMap<>();
        
        // Crear el motor de expresión JEXL
        JexlEngine jexl = new JexlBuilder().create();

        for (VistaPlanillaConcepto concepto : listaPlanillaConceptos) {
            if (concepto.getTipoConc() == Tipo.VALOR.getValor()) {
                valores.put(concepto.getNombConc(), concepto.getValor());
                planillaConceptoDAO.actualizarValorPorCodiConc(concepto.getCodiDeta(), concepto.getValor());
            } else if (concepto.getTipoConc() == Tipo.FORMULA.getValor()) {
                String formula = concepto.getFormula();

                // Reemplazo de variables en la fórmula
                VistaPersonaDetalle persona = buscarPorCodiPers(listaPersona, concepto.getCodiPers());
                formula = formula
                        .replace("BPERSONA(sueldo)", persona.getSuelPers().toString())
                        .replace("BPERSONA(asig_fami)", persona.getAsigFamiPers().toString())
                        .replace("BPERSONA(segu_afp)", persona.getSeguAFP().toString())
                        .replace("BPERSONA(comi_afp)", persona.getComiAFP().toString())
                        .replace("BPERSONA(mont_afp)", persona.getMontAFP().toString());

                if (formula.startsWith("BAUXILIAR")) {
                    String contenido;
                    Pattern pattern = Pattern.compile("\\((.*?)\\)");
                    Matcher matcher = pattern.matcher(formula);

                    if (matcher.find()) {
                        contenido = matcher.group(1);
                        formula = buscarAuxiliarPorCodiPers(listaAuxiliarDetalle, persona.getCodiPers(), contenido);
                    }
                }

                // Reemplazo de valores de conceptos previamente calculados
                for (Map.Entry<String, BigDecimal> entry : valores.entrySet()) {
                    formula = formula.replace(entry.getKey(), entry.getValue().toString());
                }

                try {
                    // Evaluar fórmula con JEXL
                    JexlExpression e = jexl.createExpression(formula);
                    JexlContext context = new MapContext();  // Contexto vacío por ahora, si necesitas puedes agregar variables

                    // Evaluar la expresión
                    Object result = e.evaluate(context);
                    BigDecimal valor = new BigDecimal(result.toString());
                    concepto.setValor(valor);
                    valores.put(concepto.getNombConc(), valor);

                    // Si es necesario, actualizar los valores en la base de datos
                     planillaConceptoDAO.actualizarValorPorCodiConc(concepto.getCodiDeta(), valor);
                } catch (Exception e) {
                    System.err.printf("Error al evaluar la fórmula '%s': %s%n", concepto.getNombConc(), e.getMessage());
                }
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(valores);
            return resultado.put("resultado", "ok").put("valores", valores).put("mensaje", "Cálculo realizado correctamente").toString();
        } catch (Exception e) {
            return resultado.put("resultado", "error").put("mensaje", "Error al procesar los valores").toString();
        }
    }

    public static VistaPersonaDetalle buscarPorCodiPers(List<VistaPersonaDetalle> lista, int codiPersBuscar) {
        for (VistaPersonaDetalle vistaPersonaAfp : lista) {
            if(vistaPersonaAfp.getCodiPers()==codiPersBuscar){
                return vistaPersonaAfp;
            }
        }
        return null;
    }

    public static String buscarAuxiliarPorCodiPers(List<VistaAuxiliarDetallePersona> lista, int codiPersBuscar, String contenido) {
        for (VistaAuxiliarDetallePersona vadp : lista) {
            if(vadp!=null && vadp.getNombAux().equals(contenido) && vadp.getCodiPers()==codiPersBuscar){
                return vadp.getMontDeta().toString();
            }
        }
        return "0";
    }

    public static void main(String[] args) {
        CalcularPlanilla c = new CalcularPlanilla();
        String s = c.procesar(1);
        System.out.println(s);
    }
}
