/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Concepto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class ConceptoDAO extends ConceptoJpaController {

    public ConceptoDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Concepto> listaXPlantilla(int codiPlant) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Concepto.findByCodiPlant");
            q.setParameter("codiPlant", codiPlant);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Concepto> findConceptosByPlantilla(Integer plantillaId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Concepto c WHERE c.codiPlant = :plantilla", Concepto.class)
                    .setParameter("plantilla", plantillaId) // Aquí plantillaId es un Integer
                    .getResultList();
        } finally {
            em.close();
        }
    }
    

    public Concepto getFormulaXNombConcepto(String nombConc) {

        for (Concepto concepto : findConceptoEntities()) {
            if (concepto.getNombConc().equalsIgnoreCase(nombConc)) {
                return concepto;
            }
        }
        return null;
    }
}
