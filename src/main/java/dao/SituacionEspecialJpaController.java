/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.SituacionEspecial;
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
public class SituacionEspecialJpaController implements Serializable {

    public SituacionEspecialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SituacionEspecial situacionEspecial) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(situacionEspecial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSituacionEspecial(situacionEspecial.getCodiSituEspe()) != null) {
                throw new PreexistingEntityException("SituacionEspecial " + situacionEspecial + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SituacionEspecial situacionEspecial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            situacionEspecial = em.merge(situacionEspecial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = situacionEspecial.getCodiSituEspe();
                if (findSituacionEspecial(id) == null) {
                    throw new NonexistentEntityException("The situacionEspecial with id " + id + " no longer exists.");
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
            SituacionEspecial situacionEspecial;
            try {
                situacionEspecial = em.getReference(SituacionEspecial.class, id);
                situacionEspecial.getCodiSituEspe();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The situacionEspecial with id " + id + " no longer exists.", enfe);
            }
            em.remove(situacionEspecial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SituacionEspecial> findSituacionEspecialEntities() {
        return findSituacionEspecialEntities(true, -1, -1);
    }

    public List<SituacionEspecial> findSituacionEspecialEntities(int maxResults, int firstResult) {
        return findSituacionEspecialEntities(false, maxResults, firstResult);
    }

    private List<SituacionEspecial> findSituacionEspecialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SituacionEspecial.class));
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

    public SituacionEspecial findSituacionEspecial(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SituacionEspecial.class, id);
        } finally {
            em.close();
        }
    }

    public int getSituacionEspecialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SituacionEspecial> rt = cq.from(SituacionEspecial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
