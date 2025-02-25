/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoDoc;
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
public class TipoDocJpaController implements Serializable {

    public TipoDocJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDoc tipoDoc) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoDoc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoDoc(tipoDoc.getCodiTipoDoc()) != null) {
                throw new PreexistingEntityException("TipoDoc " + tipoDoc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDoc tipoDoc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoDoc = em.merge(tipoDoc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoDoc.getCodiTipoDoc();
                if (findTipoDoc(id) == null) {
                    throw new NonexistentEntityException("The tipoDoc with id " + id + " no longer exists.");
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
            TipoDoc tipoDoc;
            try {
                tipoDoc = em.getReference(TipoDoc.class, id);
                tipoDoc.getCodiTipoDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDoc with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoDoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDoc> findTipoDocEntities() {
        return findTipoDocEntities(true, -1, -1);
    }

    public List<TipoDoc> findTipoDocEntities(int maxResults, int firstResult) {
        return findTipoDocEntities(false, maxResults, firstResult);
    }

    private List<TipoDoc> findTipoDocEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDoc.class));
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

    public TipoDoc findTipoDoc(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDoc.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDoc> rt = cq.from(TipoDoc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
