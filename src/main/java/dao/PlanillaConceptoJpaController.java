/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.PlanillaConcepto;
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
public class PlanillaConceptoJpaController implements Serializable {

    public PlanillaConceptoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanillaConcepto planillaConcepto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(planillaConcepto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanillaConcepto(planillaConcepto.getCodiDeta()) != null) {
                throw new PreexistingEntityException("PlanillaConcepto " + planillaConcepto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanillaConcepto planillaConcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            planillaConcepto = em.merge(planillaConcepto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planillaConcepto.getCodiDeta();
                if (findPlanillaConcepto(id) == null) {
                    throw new NonexistentEntityException("The planillaConcepto with id " + id + " no longer exists.");
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
            PlanillaConcepto planillaConcepto;
            try {
                planillaConcepto = em.getReference(PlanillaConcepto.class, id);
                planillaConcepto.getCodiDeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planillaConcepto with id " + id + " no longer exists.", enfe);
            }
            em.remove(planillaConcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanillaConcepto> findPlanillaConceptoEntities() {
        return findPlanillaConceptoEntities(true, -1, -1);
    }

    public List<PlanillaConcepto> findPlanillaConceptoEntities(int maxResults, int firstResult) {
        return findPlanillaConceptoEntities(false, maxResults, firstResult);
    }

    private List<PlanillaConcepto> findPlanillaConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanillaConcepto.class));
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

    public PlanillaConcepto findPlanillaConcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanillaConcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanillaConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanillaConcepto> rt = cq.from(PlanillaConcepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
