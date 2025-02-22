/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaPersonaMarcacion;
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
public class VistaPersonaMarcacionJpaController implements Serializable {

    public VistaPersonaMarcacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaPersonaMarcacion vistaPersonaMarcacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaPersonaMarcacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaPersonaMarcacion(vistaPersonaMarcacion.getCodiMarc()) != null) {
                throw new PreexistingEntityException("VistaPersonaMarcacion " + vistaPersonaMarcacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaPersonaMarcacion vistaPersonaMarcacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaPersonaMarcacion = em.merge(vistaPersonaMarcacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaPersonaMarcacion.getCodiMarc();
                if (findVistaPersonaMarcacion(id) == null) {
                    throw new NonexistentEntityException("The vistaPersonaMarcacion with id " + id + " no longer exists.");
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
            VistaPersonaMarcacion vistaPersonaMarcacion;
            try {
                vistaPersonaMarcacion = em.getReference(VistaPersonaMarcacion.class, id);
                vistaPersonaMarcacion.getCodiMarc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaPersonaMarcacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaPersonaMarcacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaPersonaMarcacion> findVistaPersonaMarcacionEntities() {
        return findVistaPersonaMarcacionEntities(true, -1, -1);
    }

    public List<VistaPersonaMarcacion> findVistaPersonaMarcacionEntities(int maxResults, int firstResult) {
        return findVistaPersonaMarcacionEntities(false, maxResults, firstResult);
    }

    private List<VistaPersonaMarcacion> findVistaPersonaMarcacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaPersonaMarcacion.class));
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

    public VistaPersonaMarcacion findVistaPersonaMarcacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaPersonaMarcacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaPersonaMarcacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaPersonaMarcacion> rt = cq.from(VistaPersonaMarcacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
