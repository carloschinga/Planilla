/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.VistaAuxiliarDetallePersona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class VistaAuxiliarDetallePersonaDAO extends VistaAuxiliarDetallePersonaJpaController {

    public VistaAuxiliarDetallePersonaDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<VistaAuxiliarDetallePersona> findVistaAuxiliarDetallePersonaByPeriodo(Integer periodoId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("VistaAuxiliarDetallePersona.findByCodiPeri");
            q.setParameter("codiPeri", periodoId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
