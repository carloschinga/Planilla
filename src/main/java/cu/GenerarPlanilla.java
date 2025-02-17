/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu;

import dao.ConceptoDAO;
import dao.PeriodoDAO;
import dao.PersonaDAO;
import dao.PlanillaConceptoDAO;
import dao.PlanillaDAO;
import dto.Concepto;
import dto.Periodo;
import dto.Persona;
import dto.Planilla;
import dto.PlanillaConcepto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class GenerarPlanilla {

    private final EntityManagerFactory emf;
    private final PeriodoDAO periodoDAO;
    private final PersonaDAO personaDAO;
    private final PlanillaDAO planillaDAO;
    private final ConceptoDAO conceptoDAO;
    private final PlanillaConceptoDAO planillaConceptoDAO;

    public GenerarPlanilla() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.periodoDAO = new PeriodoDAO(emf);
        this.personaDAO = new PersonaDAO(emf);
        this.planillaDAO = new PlanillaDAO(emf);
        this.conceptoDAO = new ConceptoDAO(emf);
        this.planillaConceptoDAO = new PlanillaConceptoDAO(emf);
    }

    public String generar() {
        JSONObject resultado = new JSONObject();
        int codiPlantTmp = 0;

        // Obtener el periodo actual
        Periodo periodoActual = periodoDAO.findByActiPeri();
        if (periodoActual == null) {
            System.out.println("No se encontró un periodo activo.");
            return resultado.put("resultado", "error").put("mensaje", "No se encontró un periodo activo.").toString();
        }

        // Obtener la lista de trabajadores activos
        List<Persona> listaPersona = personaDAO.listarActivos();
        if (listaPersona == null || listaPersona.isEmpty()) {
            System.out.println("No se encontraron trabajadores.");
            return resultado.put("resultado", "error").put("mensaje", "No se encontraron trabajadores.").toString();
        }

        List<Concepto> listaConcepto = null;
        // Transacción para generar planillas y sus conceptos
        EntityManager em = planillaDAO.getEntityManager();
        try {
            em.getTransaction().begin();

            for (Persona persona : listaPersona) {

                if (codiPlantTmp != persona.getCodiPlant()) {
                    // Obtener lista de conceptos segun persona
                    listaConcepto = conceptoDAO.listaXPlantilla(persona.getCodiPlant());
                    if (listaConcepto == null || listaConcepto.isEmpty()) {
                        System.out.println("No se encontraron conceptos.");
                        return resultado.put("resultado", "error").put("mensaje", "No se encontraron conceptos.").toString();
                    }
                }
                //verificar si existe planilla del periodoActual

                Planilla planilla = new Planilla(0, periodoActual.getCodiPeri(), persona.getCodiPers(), true);
                planillaDAO.create(planilla);

                for (Concepto concepto : listaConcepto) {
                    PlanillaConcepto planillaConcepto = new PlanillaConcepto(
                            0, planilla.getCodiPlani(), concepto.getCodiConc(), concepto.getNombConc(),concepto.getOrdnConc(),
                            concepto.getTipoConc(), concepto.getCateConc(), concepto.getValor(),
                            concepto.getFormula(), true, true
                    );
                    planillaConceptoDAO.create(planillaConcepto);
                }
            }

            em.getTransaction().commit();
            return resultado.put("resultado", "ok").put("mensaje", "Planillas generadas con éxito.").toString();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al crear las planillas: " + e.getMessage());
            return resultado.put("resultado", "error").put("mensaje", "Error al crear las planillas: " + e.getMessage()).toString();
        } finally {
            em.close();
        }
    }

    public String existePlanillaGeneradaXPeriodo(int codiPeri) {
        JSONObject resultado = new JSONObject();
        List<Planilla> listaPlanilla = planillaDAO.listaXPeriodo(codiPeri);
        if (listaPlanilla.isEmpty()) {
            return resultado.put("resultado", "no").toString();
        } else {
            String codigosPlanilla = obtenerCodiPlaniListFormat(listaPlanilla);
            return resultado.put("resultado", "si").put("codigos", codigosPlanilla).toString();

        }
    }

    public String anularPlanillaGenerada(int codiPeri, String codigosPlanilla) {
        JSONObject resultado = new JSONObject();
        int i = planillaDAO.anularPlanilla(codiPeri);
        if (i > 0) {
            i = planillaConceptoDAO.anularPlanillaConceptoGenerada(codigosPlanilla);
            if (i > 0) {
                return resultado.put("resultado", "ok").toString();
            }
        }
        return resultado.put("resultado", "error").toString();
    }

    public static String obtenerCodiPlaniListFormat(List<Planilla> planillas) {
        
        /*String result = planillas.stream()
                .map(planilla -> planilla.getCodiPlani().toString())
                .collect(Collectors.joining(", ", "(", ")"));
        return result;*/
        return "";
    } 

    public static void main(String[] args) {
        JSONObject resultado = null;
        GenerarPlanilla generarPlanilla = new GenerarPlanilla();
        String jsonString = generarPlanilla.existePlanillaGeneradaXPeriodo(1);
        resultado = new JSONObject(jsonString);
        if (resultado.getString("resultado").equals("si")) {
            if (JOptionPane.YES_OPTION == JOptionPane.showInternalConfirmDialog(null, "Ya existe, desea anular el existente y volver a generar")) {
                jsonString = generarPlanilla.anularPlanillaGenerada(1, resultado.getString("codigos"));
                resultado = new JSONObject(jsonString);
                if (resultado.getString("resultado").equals("ok")) {
                    jsonString = generarPlanilla.generar();
                    resultado = new JSONObject(jsonString);
                }
            }else{
                 System.out.println("Se cancelo la operacion");
            }
        } else {
            jsonString = generarPlanilla.generar();
            resultado = new JSONObject(jsonString);
        }
        System.out.println(resultado);
        //generarPlanilla.generar();

    }

}
