/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Auxiliar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class AuxiliarDAO extends AuxiliarJpaController {

    public AuxiliarDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Auxiliar> findAuxiliarByPeriodo(Integer periodoId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Auxiliar c WHERE c.codiPeri= :periodo", Auxiliar.class)
                    .setParameter("periodo", periodoId) // Aquí plantillaId es un Integer
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
