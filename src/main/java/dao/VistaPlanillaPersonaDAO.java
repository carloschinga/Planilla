/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.VistaPlanilaPersona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class VistaPlanillaPersonaDAO extends VistaPlanilaPersonaJpaController{
    
    public VistaPlanillaPersonaDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<VistaPlanilaPersona> listaPlanillaPersonaXPeriodo(int codiPeri) {
        try {
            EntityManager em = getEntityManager();
            try {
                Query q = em.createNamedQuery("VistaPlanilaPersona.findByCodiPeri");
                q.setParameter("codiPeri", codiPeri);
                return q.getResultList();
            } finally {
                em.close();
            }
        } catch (Exception ex) {
            return null;
        }

    }
    
}
