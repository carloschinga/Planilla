/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.PlanillaConcepto;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class PlanillaConceptoDAO extends PlanillaConceptoJpaController {

    public PlanillaConceptoDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<PlanillaConcepto> findConceptosByPlantilla(Integer plantillaId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Concepto c WHERE c.codiPlant = :plantilla", PlanillaConcepto.class)
                    .setParameter("plantilla", plantillaId) // Aquí plantillaId es un Integer
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarValorPorCodiConc(Integer codiConc, BigDecimal nuevoValor) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            // Iniciar una transacción
            transaction = em.getTransaction();
            transaction.begin();

            // Consulta de actualización con parámetros posicionales (?)
            String query = "UPDATE PlanillaConcepto SET valor = ? WHERE codiDeta = ?";

            // Ejecutar la consulta con parámetros posicionales
            em.createNativeQuery(query)
                    .setParameter(1, nuevoValor) // Primer parámetro para 'valor'
                    .setParameter(2, codiConc) // Segundo parámetro para 'codiConc'
                    .executeUpdate();

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception ex) {
            // Si ocurre una excepción, hacer rollback de la transacción
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            String mensaje = ex.getMessage();
        } finally {
            em.close();
        }
    }

    public int anularPlanillaConceptoGenerada(String codigosPlanilla) {
        EntityManager em = getEntityManager();
        if (codigosPlanilla == null || codigosPlanilla.trim().isEmpty()) {
            return 0; // No realizar actualizaciones si el string está vacío
        }

        try {
            em.getTransaction().begin();

            // Construir la consulta nativa directamente usando codigosPlanilla
            String sql = "UPDATE PlanillaConcepto SET actvDeta = 0 WHERE codiPlani IN " + codigosPlanilla + "";
            Query query = em.createNativeQuery(sql);

            int rowsUpdated = query.executeUpdate();
            
            sql = "UPDATE Planilla SET actiPlani = 0 WHERE codiPlani IN " + codigosPlanilla + "";
            query = em.createNativeQuery(sql);

            rowsUpdated = query.executeUpdate();
            
            em.getTransaction().commit();
            return rowsUpdated;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return -1;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
