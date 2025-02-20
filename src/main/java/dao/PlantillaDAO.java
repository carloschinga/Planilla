/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Plantilla;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class PlantillaDAO extends PlantillaJpaController{
    
    public PlantillaDAO(EntityManagerFactory emf) {
        super(emf);
    }
     public List<Plantilla> listarActivos() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Plantilla.findByActvPlant");
            q.setParameter("actvPlant", true);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
