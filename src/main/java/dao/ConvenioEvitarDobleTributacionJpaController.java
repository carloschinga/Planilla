/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.ConvenioEvitarDobleTributacion;
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
public class ConvenioEvitarDobleTributacionJpaController implements Serializable {

    public ConvenioEvitarDobleTributacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConvenioEvitarDobleTributacion convenioEvitarDobleTributacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(convenioEvitarDobleTributacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConvenioEvitarDobleTributacion(convenioEvitarDobleTributacion.getCodiConvDoblTrib()) != null) {
                throw new PreexistingEntityException("ConvenioEvitarDobleTributacion " + convenioEvitarDobleTributacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConvenioEvitarDobleTributacion convenioEvitarDobleTributacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            convenioEvitarDobleTributacion = em.merge(convenioEvitarDobleTributacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = convenioEvitarDobleTributacion.getCodiConvDoblTrib();
                if (findConvenioEvitarDobleTributacion(id) == null) {
                    throw new NonexistentEntityException("The convenioEvitarDobleTributacion with id " + id + " no longer exists.");
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
            ConvenioEvitarDobleTributacion convenioEvitarDobleTributacion;
            try {
                convenioEvitarDobleTributacion = em.getReference(ConvenioEvitarDobleTributacion.class, id);
                convenioEvitarDobleTributacion.getCodiConvDoblTrib();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenioEvitarDobleTributacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(convenioEvitarDobleTributacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConvenioEvitarDobleTributacion> findConvenioEvitarDobleTributacionEntities() {
        return findConvenioEvitarDobleTributacionEntities(true, -1, -1);
    }

    public List<ConvenioEvitarDobleTributacion> findConvenioEvitarDobleTributacionEntities(int maxResults, int firstResult) {
        return findConvenioEvitarDobleTributacionEntities(false, maxResults, firstResult);
    }

    private List<ConvenioEvitarDobleTributacion> findConvenioEvitarDobleTributacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConvenioEvitarDobleTributacion.class));
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

    public ConvenioEvitarDobleTributacion findConvenioEvitarDobleTributacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConvenioEvitarDobleTributacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioEvitarDobleTributacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConvenioEvitarDobleTributacion> rt = cq.from(ConvenioEvitarDobleTributacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
