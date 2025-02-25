/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.PaisEmisor;
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
public class PaisEmisorJpaController implements Serializable {

    public PaisEmisorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaisEmisor paisEmisor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paisEmisor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaisEmisor(paisEmisor.getCodiPaisEmis()) != null) {
                throw new PreexistingEntityException("PaisEmisor " + paisEmisor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaisEmisor paisEmisor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paisEmisor = em.merge(paisEmisor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = paisEmisor.getCodiPaisEmis();
                if (findPaisEmisor(id) == null) {
                    throw new NonexistentEntityException("The paisEmisor with id " + id + " no longer exists.");
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
            PaisEmisor paisEmisor;
            try {
                paisEmisor = em.getReference(PaisEmisor.class, id);
                paisEmisor.getCodiPaisEmis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paisEmisor with id " + id + " no longer exists.", enfe);
            }
            em.remove(paisEmisor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaisEmisor> findPaisEmisorEntities() {
        return findPaisEmisorEntities(true, -1, -1);
    }

    public List<PaisEmisor> findPaisEmisorEntities(int maxResults, int firstResult) {
        return findPaisEmisorEntities(false, maxResults, firstResult);
    }

    private List<PaisEmisor> findPaisEmisorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaisEmisor.class));
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

    public PaisEmisor findPaisEmisor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaisEmisor.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisEmisorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaisEmisor> rt = cq.from(PaisEmisor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
