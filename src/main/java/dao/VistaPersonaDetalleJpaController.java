/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaPersonaDetalle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author USER
 */
public class VistaPersonaDetalleJpaController implements Serializable {

    public VistaPersonaDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaPersonaDetalle vistaPersonaDetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaPersonaDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaPersonaDetalle(vistaPersonaDetalle.getCodiPers()) != null) {
                throw new PreexistingEntityException("VistaPersonaDetalle " + vistaPersonaDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaPersonaDetalle vistaPersonaDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaPersonaDetalle = em.merge(vistaPersonaDetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaPersonaDetalle.getCodiPers();
                if (findVistaPersonaDetalle(id) == null) {
                    throw new NonexistentEntityException("The vistaPersonaDetalle with id " + id + " no longer exists.");
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
            VistaPersonaDetalle vistaPersonaDetalle;
            try {
                vistaPersonaDetalle = em.getReference(VistaPersonaDetalle.class, id);
                vistaPersonaDetalle.getCodiPers();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaPersonaDetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaPersonaDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaPersonaDetalle> findVistaPersonaDetalleEntities() {
        EntityManager em = null;
        try {
             em = getEntityManager();
            TypedQuery<VistaPersonaDetalle> query = em.createQuery(
                    "SELECT v FROM VistaPersonaDetalle v", VistaPersonaDetalle.class);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return  query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        //return findVistaPersonaDetalleEntities(true, -1, -1);
    }

    public List<VistaPersonaDetalle> findVistaPersonaDetalleEntities(int maxResults, int firstResult) {
        return findVistaPersonaDetalleEntities(false, maxResults, firstResult);
    }

    private List<VistaPersonaDetalle> findVistaPersonaDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaPersonaDetalle.class));
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

    public VistaPersonaDetalle findVistaPersonaDetalle(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaPersonaDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaPersonaDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaPersonaDetalle> rt = cq.from(VistaPersonaDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
