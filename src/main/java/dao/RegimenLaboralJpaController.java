/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.RegimenLaboral;
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
public class RegimenLaboralJpaController implements Serializable {

    public RegimenLaboralJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegimenLaboral regimenLaboral) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(regimenLaboral);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegimenLaboral(regimenLaboral.getCodiRegiLabo()) != null) {
                throw new PreexistingEntityException("RegimenLaboral " + regimenLaboral + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegimenLaboral regimenLaboral) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            regimenLaboral = em.merge(regimenLaboral);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = regimenLaboral.getCodiRegiLabo();
                if (findRegimenLaboral(id) == null) {
                    throw new NonexistentEntityException("The regimenLaboral with id " + id + " no longer exists.");
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
            RegimenLaboral regimenLaboral;
            try {
                regimenLaboral = em.getReference(RegimenLaboral.class, id);
                regimenLaboral.getCodiRegiLabo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The regimenLaboral with id " + id + " no longer exists.", enfe);
            }
            em.remove(regimenLaboral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegimenLaboral> findRegimenLaboralEntities() {
        return findRegimenLaboralEntities(true, -1, -1);
    }

    public List<RegimenLaboral> findRegimenLaboralEntities(int maxResults, int firstResult) {
        return findRegimenLaboralEntities(false, maxResults, firstResult);
    }

    private List<RegimenLaboral> findRegimenLaboralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegimenLaboral.class));
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

    public RegimenLaboral findRegimenLaboral(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegimenLaboral.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegimenLaboralCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegimenLaboral> rt = cq.from(RegimenLaboral.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
