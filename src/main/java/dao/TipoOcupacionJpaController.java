/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoOcupacion;
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
public class TipoOcupacionJpaController implements Serializable {

    public TipoOcupacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoOcupacion tipoOcupacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoOcupacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoOcupacion(tipoOcupacion.getCodiTipoOcup()) != null) {
                throw new PreexistingEntityException("TipoOcupacion " + tipoOcupacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoOcupacion tipoOcupacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoOcupacion = em.merge(tipoOcupacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoOcupacion.getCodiTipoOcup();
                if (findTipoOcupacion(id) == null) {
                    throw new NonexistentEntityException("The tipoOcupacion with id " + id + " no longer exists.");
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
            TipoOcupacion tipoOcupacion;
            try {
                tipoOcupacion = em.getReference(TipoOcupacion.class, id);
                tipoOcupacion.getCodiTipoOcup();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoOcupacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoOcupacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoOcupacion> findTipoOcupacionEntities() {
        return findTipoOcupacionEntities(true, -1, -1);
    }

    public List<TipoOcupacion> findTipoOcupacionEntities(int maxResults, int firstResult) {
        return findTipoOcupacionEntities(false, maxResults, firstResult);
    }

    private List<TipoOcupacion> findTipoOcupacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoOcupacion.class));
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

    public TipoOcupacion findTipoOcupacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoOcupacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoOcupacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoOcupacion> rt = cq.from(TipoOcupacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
