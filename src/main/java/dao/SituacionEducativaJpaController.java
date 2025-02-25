/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.SituacionEducativa;
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
public class SituacionEducativaJpaController implements Serializable {

    public SituacionEducativaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SituacionEducativa situacionEducativa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(situacionEducativa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSituacionEducativa(situacionEducativa.getCodiSituEduc()) != null) {
                throw new PreexistingEntityException("SituacionEducativa " + situacionEducativa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SituacionEducativa situacionEducativa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            situacionEducativa = em.merge(situacionEducativa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = situacionEducativa.getCodiSituEduc();
                if (findSituacionEducativa(id) == null) {
                    throw new NonexistentEntityException("The situacionEducativa with id " + id + " no longer exists.");
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
            SituacionEducativa situacionEducativa;
            try {
                situacionEducativa = em.getReference(SituacionEducativa.class, id);
                situacionEducativa.getCodiSituEduc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The situacionEducativa with id " + id + " no longer exists.", enfe);
            }
            em.remove(situacionEducativa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SituacionEducativa> findSituacionEducativaEntities() {
        return findSituacionEducativaEntities(true, -1, -1);
    }

    public List<SituacionEducativa> findSituacionEducativaEntities(int maxResults, int firstResult) {
        return findSituacionEducativaEntities(false, maxResults, firstResult);
    }

    private List<SituacionEducativa> findSituacionEducativaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SituacionEducativa.class));
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

    public SituacionEducativa findSituacionEducativa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SituacionEducativa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSituacionEducativaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SituacionEducativa> rt = cq.from(SituacionEducativa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
