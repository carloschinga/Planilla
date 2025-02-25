/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoZona;
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
public class TipoZonaJpaController implements Serializable {

    public TipoZonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoZona tipoZona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoZona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoZona(tipoZona.getCodiTipoZona()) != null) {
                throw new PreexistingEntityException("TipoZona " + tipoZona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoZona tipoZona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoZona = em.merge(tipoZona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoZona.getCodiTipoZona();
                if (findTipoZona(id) == null) {
                    throw new NonexistentEntityException("The tipoZona with id " + id + " no longer exists.");
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
            TipoZona tipoZona;
            try {
                tipoZona = em.getReference(TipoZona.class, id);
                tipoZona.getCodiTipoZona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoZona with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoZona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoZona> findTipoZonaEntities() {
        return findTipoZonaEntities(true, -1, -1);
    }

    public List<TipoZona> findTipoZonaEntities(int maxResults, int firstResult) {
        return findTipoZonaEntities(false, maxResults, firstResult);
    }

    private List<TipoZona> findTipoZonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoZona.class));
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

    public TipoZona findTipoZona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoZona.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoZonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoZona> rt = cq.from(TipoZona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
