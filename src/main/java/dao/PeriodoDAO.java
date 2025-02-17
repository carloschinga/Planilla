/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Periodo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


/**
 *
 * @author USER
 */
public class PeriodoDAO extends PeriodoJpaController{

    public PeriodoDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public Periodo findByActiPeri() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Periodo.findByActiPeri");
            q.setParameter("actiPeri", true);
            List<Periodo> lista= q.getResultList();
            return lista.get(0);
        }
        catch(Exception ex){
            return null;
        }
        finally {
            em.close();
        }
    }
    
}
