/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaHorarioDetalle;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author USER
 */
public class VistaHorarioDetalleJpaController implements Serializable {

    public VistaHorarioDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaHorarioDetalle vistaHorarioDetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaHorarioDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaHorarioDetalle(vistaHorarioDetalle.getCodiHoraDeta()) != null) {
                throw new PreexistingEntityException("VistaHorarioDetalle " + vistaHorarioDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaHorarioDetalle vistaHorarioDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaHorarioDetalle = em.merge(vistaHorarioDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaHorarioDetalle.getCodiHoraDeta();
                if (findVistaHorarioDetalle(id) == null) {
                    throw new NonexistentEntityException("The vistaHorarioDetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VistaHorarioDetalle vistaHorarioDetalle;
            try {
                vistaHorarioDetalle = em.getReference(VistaHorarioDetalle.class, id);
                vistaHorarioDetalle.getCodiHoraDeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaHorarioDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaHorarioDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<String> findUniqueNombres() {
        EntityManager em = getEntityManager();
        try {
            // Usamos DISTINCT para evitar duplicados
            String query = "SELECT DISTINCT v.nombHora FROM VistaHorarioDetalle v";
            return em.createQuery(query, String.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<VistaHorarioDetalle> findVistaHorarioDetalleByNombre(String nombHora) {
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT v FROM VistaHorarioDetalle v WHERE v.nombHora = :nombHora";
            return em.createQuery(query, VistaHorarioDetalle.class)
                    .setParameter("nombHora", nombHora)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<VistaHorarioDetalle> findVistaHorarioDetalleEntities() {
        return findVistaHorarioDetalleEntities(true, -1, -1);
    }

    public List<VistaHorarioDetalle> findVistaHorarioDetalleEntities(int maxResults, int firstResult) {
        return findVistaHorarioDetalleEntities(false, maxResults, firstResult);
    }

    private List<VistaHorarioDetalle> findVistaHorarioDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq
                    .select(cq.from(VistaHorarioDetalle.class
                    ));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VistaHorarioDetalle findVistaHorarioDetalle(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(VistaHorarioDetalle.class,
                     id);
        } finally {
            em.close();
        }
    }

    public int getVistaHorarioDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaHorarioDetalle> rt = cq.from(VistaHorarioDetalle.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
