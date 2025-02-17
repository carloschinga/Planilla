/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu;

import dao.ConceptoDAO;
import dao.PlantillaDAO;
import dto.Concepto;
import dto.Plantilla;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class ClonarPlantilla {

    private final EntityManagerFactory emf;
    private final ConceptoDAO conceptoDAO;
    private final PlantillaDAO plantillaDAO;

    public ClonarPlantilla() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.conceptoDAO = new ConceptoDAO(emf);
        this.plantillaDAO = new PlantillaDAO(emf);
    }

    public String clonar(int codiPlantOrig, String nombPlanNuev) {
        JSONObject resultado = new JSONObject();
        try {

            Plantilla p = new Plantilla(nombPlanNuev);
            plantillaDAO.create(p);

            List<Concepto> lista = conceptoDAO.listaXPlantilla(codiPlantOrig);
            for (Concepto concepto : lista) {
                concepto.setCodiPlant(p.getCodiPlant());
                conceptoDAO.create(concepto);
            }
            return resultado.put("resultado", "ok").put("codigo",p.getCodiPlant()).put("mensaje", "Clono Plantilla correctamente.").toString();

        } catch (Exception ex) {
            return resultado.put("resultado", "error").put("mensaje", ex.getMessage()).toString();

        }
    }

    public static void main(String[] args) {
        ClonarPlantilla clonarPlantilla= new ClonarPlantilla();
        String resultado=clonarPlantilla.clonar(1, "PLANTILLA CLONADA 2");
        System.out.println(resultado);
      
    }
}
