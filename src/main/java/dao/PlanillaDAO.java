/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.PlanillaJpaController;
import dto.Concepto;
import dto.Planilla;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class PlanillaDAO extends PlanillaJpaController {

    public PlanillaDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Planilla> listaXPeriodo(int codiPeri) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Planilla.findByCodiPeri");
            q.setParameter("codiPeri", codiPeri);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int anularPlanilla(int codiPeri) {
        EntityManager em = getEntityManager();
        try {
            // Crear la consulta nativa para actualizar actiPlani a false
            String sql = "UPDATE Planilla SET actiPlani = 0 WHERE codiPeri = ?";

            // Crear la query nativa
            Query query = em.createNativeQuery(sql);
           query.setParameter(1, codiPeri);

            // Ejecutar la actualización
            em.getTransaction().begin();
            int rowsUpdated = query.executeUpdate();
            em.getTransaction().commit();
            return rowsUpdated;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace(); // Para depuración
            return -1;
        } finally {
            if (em != null) {
                em.close(); // Asegurar que el EntityManager se cierra
            }
        }
    }

}
