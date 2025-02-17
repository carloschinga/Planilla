/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaPlanillaConcepto;
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
public class VistaPlanillaConceptoJpaController implements Serializable {

    public VistaPlanillaConceptoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaPlanillaConcepto vistaPlanillaConcepto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaPlanillaConcepto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaPlanillaConcepto(vistaPlanillaConcepto.getCodiDeta()) != null) {
                throw new PreexistingEntityException("VistaPlanillaConcepto " + vistaPlanillaConcepto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaPlanillaConcepto vistaPlanillaConcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaPlanillaConcepto = em.merge(vistaPlanillaConcepto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaPlanillaConcepto.getCodiDeta();
                if (findVistaPlanillaConcepto(id) == null) {
                    throw new NonexistentEntityException("The vistaPlanillaConcepto with id " + id + " no longer exists.");
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
            VistaPlanillaConcepto vistaPlanillaConcepto;
            try {
                vistaPlanillaConcepto = em.getReference(VistaPlanillaConcepto.class, id);
                vistaPlanillaConcepto.getCodiDeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaPlanillaConcepto with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaPlanillaConcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaPlanillaConcepto> findVistaPlanillaConceptoEntities() {
        return findVistaPlanillaConceptoEntities(true, -1, -1);
    }

    public List<VistaPlanillaConcepto> findVistaPlanillaConceptoEntities(int maxResults, int firstResult) {
        return findVistaPlanillaConceptoEntities(false, maxResults, firstResult);
    }

    private List<VistaPlanillaConcepto> findVistaPlanillaConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaPlanillaConcepto.class));
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

    public VistaPlanillaConcepto findVistaPlanillaConcepto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaPlanillaConcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaPlanillaConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaPlanillaConcepto> rt = cq.from(VistaPlanillaConcepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
