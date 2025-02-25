/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.PeriocidadRemuneracion;
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
public class PeriocidadRemuneracionJpaController implements Serializable {

    public PeriocidadRemuneracionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PeriocidadRemuneracion periocidadRemuneracion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(periocidadRemuneracion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriocidadRemuneracion(periocidadRemuneracion.getCodiPeriRemu()) != null) {
                throw new PreexistingEntityException("PeriocidadRemuneracion " + periocidadRemuneracion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PeriocidadRemuneracion periocidadRemuneracion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            periocidadRemuneracion = em.merge(periocidadRemuneracion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = periocidadRemuneracion.getCodiPeriRemu();
                if (findPeriocidadRemuneracion(id) == null) {
                    throw new NonexistentEntityException("The periocidadRemuneracion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeriocidadRemuneracion periocidadRemuneracion;
            try {
                periocidadRemuneracion = em.getReference(PeriocidadRemuneracion.class, id);
                periocidadRemuneracion.getCodiPeriRemu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periocidadRemuneracion with id " + id + " no longer exists.", enfe);
            }
            em.remove(periocidadRemuneracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PeriocidadRemuneracion> findPeriocidadRemuneracionEntities() {
        return findPeriocidadRemuneracionEntities(true, -1, -1);
    }

    public List<PeriocidadRemuneracion> findPeriocidadRemuneracionEntities(int maxResults, int firstResult) {
        return findPeriocidadRemuneracionEntities(false, maxResults, firstResult);
    }

    private List<PeriocidadRemuneracion> findPeriocidadRemuneracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PeriocidadRemuneracion.class));
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

    public PeriocidadRemuneracion findPeriocidadRemuneracion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PeriocidadRemuneracion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriocidadRemuneracionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PeriocidadRemuneracion> rt = cq.from(PeriocidadRemuneracion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
