/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoVia;
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
public class TipoViaJpaController implements Serializable {

    public TipoViaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoVia tipoVia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoVia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoVia(tipoVia.getCodiTipoVia()) != null) {
                throw new PreexistingEntityException("TipoVia " + tipoVia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoVia tipoVia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoVia = em.merge(tipoVia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoVia.getCodiTipoVia();
                if (findTipoVia(id) == null) {
                    throw new NonexistentEntityException("The tipoVia with id " + id + " no longer exists.");
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
            TipoVia tipoVia;
            try {
                tipoVia = em.getReference(TipoVia.class, id);
                tipoVia.getCodiTipoVia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoVia with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoVia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoVia> findTipoViaEntities() {
        return findTipoViaEntities(true, -1, -1);
    }

    public List<TipoVia> findTipoViaEntities(int maxResults, int firstResult) {
        return findTipoViaEntities(false, maxResults, firstResult);
    }

    private List<TipoVia> findTipoViaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoVia.class));
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

    public TipoVia findTipoVia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoVia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoViaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoVia> rt = cq.from(TipoVia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
