/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dto.AuxiliarDetalle;
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
public class AuxiliarDetalleJpaController implements Serializable {

    public AuxiliarDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuxiliarDetalle auxiliarDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(auxiliarDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuxiliarDetalle auxiliarDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            auxiliarDetalle = em.merge(auxiliarDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auxiliarDetalle.getCodiDetaAux();
                if (findAuxiliarDetalle(id) == null) {
                    throw new NonexistentEntityException("The auxiliarDetalle with id " + id + " no longer exists.");
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
            AuxiliarDetalle auxiliarDetalle;
            try {
                auxiliarDetalle = em.getReference(AuxiliarDetalle.class, id);
                auxiliarDetalle.getCodiDetaAux();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auxiliarDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(auxiliarDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuxiliarDetalle> findAuxiliarDetalleEntities() {
        return findAuxiliarDetalleEntities(true, -1, -1);
    }

    public List<AuxiliarDetalle> findAuxiliarDetalleEntities(int maxResults, int firstResult) {
        return findAuxiliarDetalleEntities(false, maxResults, firstResult);
    }

    private List<AuxiliarDetalle> findAuxiliarDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuxiliarDetalle.class));
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

    public AuxiliarDetalle findAuxiliarDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuxiliarDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuxiliarDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuxiliarDetalle> rt = cq.from(AuxiliarDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
