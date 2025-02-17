/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AuxiliarDetalle;
import dto.VistaAuxiliarDetallePersona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class AuxiliarDetalleDAO extends AuxiliarDetalleJpaController{
    
    public AuxiliarDetalleDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<VistaAuxiliarDetallePersona> findAuxiliarByPeriodo(Integer auxiliarId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiAux = :auxiliar", VistaAuxiliarDetallePersona.class)
                    .setParameter("auxiliar", auxiliarId) // Aquí auxiliarId es un Integer
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
