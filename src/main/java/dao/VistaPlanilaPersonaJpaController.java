/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaPlanilaPersona;
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
public class VistaPlanilaPersonaJpaController implements Serializable {

    public VistaPlanilaPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaPlanilaPersona vistaPlanilaPersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaPlanilaPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaPlanilaPersona(vistaPlanilaPersona.getCodiPlani()) != null) {
                throw new PreexistingEntityException("VistaPlanilaPersona " + vistaPlanilaPersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaPlanilaPersona vistaPlanilaPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaPlanilaPersona = em.merge(vistaPlanilaPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaPlanilaPersona.getCodiPlani();
                if (findVistaPlanilaPersona(id) == null) {
                    throw new NonexistentEntityException("The vistaPlanilaPersona with id " + id + " no longer exists.");
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
            VistaPlanilaPersona vistaPlanilaPersona;
            try {
                vistaPlanilaPersona = em.getReference(VistaPlanilaPersona.class, id);
                vistaPlanilaPersona.getCodiPlani();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaPlanilaPersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaPlanilaPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaPlanilaPersona> findVistaPlanilaPersonaEntities() {
        return findVistaPlanilaPersonaEntities(true, -1, -1);
    }

    public List<VistaPlanilaPersona> findVistaPlanilaPersonaEntities(int maxResults, int firstResult) {
        return findVistaPlanilaPersonaEntities(false, maxResults, firstResult);
    }

    private List<VistaPlanilaPersona> findVistaPlanilaPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaPlanilaPersona.class));
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

    public VistaPlanilaPersona findVistaPlanilaPersona(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaPlanilaPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaPlanilaPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaPlanilaPersona> rt = cq.from(VistaPlanilaPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
