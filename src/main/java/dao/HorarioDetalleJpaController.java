/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dto.HorarioDetalle;
import java.io.Serializable;
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
public class HorarioDetalleJpaController implements Serializable {

    public HorarioDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HorarioDetalle horarioDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(horarioDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HorarioDetalle horarioDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            horarioDetalle = em.merge(horarioDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horarioDetalle.getCodiHoraDeta();
                if (findHorarioDetalle(id) == null) {
                    throw new NonexistentEntityException("The horarioDetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HorarioDetalle horarioDetalle;
            try {
                horarioDetalle = em.getReference(HorarioDetalle.class, id);
                horarioDetalle.getCodiHoraDeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horarioDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(horarioDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HorarioDetalle> findHorarioDetalleEntities() {
        return findHorarioDetalleEntities(true, -1, -1);
    }

    public List<HorarioDetalle> findHorarioDetalleEntities(int maxResults, int firstResult) {
        return findHorarioDetalleEntities(false, maxResults, firstResult);
    }

    private List<HorarioDetalle> findHorarioDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HorarioDetalle.class));
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

    public HorarioDetalle findHorarioDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HorarioDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HorarioDetalle> rt = cq.from(HorarioDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
