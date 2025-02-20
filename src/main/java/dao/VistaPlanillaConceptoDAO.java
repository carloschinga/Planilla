/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.VistaPlanillaConcepto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class VistaPlanillaConceptoDAO extends VistaPlanillaConceptoJpaController {

    public VistaPlanillaConceptoDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<VistaPlanillaConcepto> listaPlanillaConceptoXPeriodo(int codiPeri) {
        try {
            EntityManager em = getEntityManager();
            try {
                Query q = em.createNamedQuery("VistaPlanillaConcepto.findByCodiPeriAndActvDeta");
                q.setParameter("codiPeri", codiPeri);
                q.setParameter("actvDeta", true);
                return q.getResultList();
            } finally {
                em.close();
            }
        } catch (Exception ex) {
            return null;
        }

    }
    public List<VistaPlanillaConcepto> findPlanillaConceptosByPeriodoAndPersona(Integer periodoId,Integer personaId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM VistaPlanillaConcepto v WHERE v.codiPeri = :codiPeri and v.codiPers = :codiPers and v.actvDeta=true", VistaPlanillaConcepto.class)
                    .setParameter("codiPeri", periodoId) // Aquí plantillaId es un Integer
                    .setParameter("codiPers", personaId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
