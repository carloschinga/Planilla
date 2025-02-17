/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaAuxiliarDetallePersona;
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
public class VistaAuxiliarDetallePersonaJpaController implements Serializable {

    public VistaAuxiliarDetallePersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaAuxiliarDetallePersona vistaAuxiliarDetallePersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaAuxiliarDetallePersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaAuxiliarDetallePersona(vistaAuxiliarDetallePersona.getCodiDetaAux()) != null) {
                throw new PreexistingEntityException("VistaAuxiliarDetallePersona " + vistaAuxiliarDetallePersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaAuxiliarDetallePersona vistaAuxiliarDetallePersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaAuxiliarDetallePersona = em.merge(vistaAuxiliarDetallePersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaAuxiliarDetallePersona.getCodiDetaAux();
                if (findVistaAuxiliarDetallePersona(id) == null) {
                    throw new NonexistentEntityException("The vistaAuxiliarDetallePersona with id " + id + " no longer exists.");
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
            VistaAuxiliarDetallePersona vistaAuxiliarDetallePersona;
            try {
                vistaAuxiliarDetallePersona = em.getReference(VistaAuxiliarDetallePersona.class, id);
                vistaAuxiliarDetallePersona.getCodiDetaAux();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaAuxiliarDetallePersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaAuxiliarDetallePersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaAuxiliarDetallePersona> findVistaAuxiliarDetallePersonaEntities() {
        return findVistaAuxiliarDetallePersonaEntities(true, -1, -1);
    }

    public List<VistaAuxiliarDetallePersona> findVistaAuxiliarDetallePersonaEntities(int maxResults, int firstResult) {
        return findVistaAuxiliarDetallePersonaEntities(false, maxResults, firstResult);
    }

    private List<VistaAuxiliarDetallePersona> findVistaAuxiliarDetallePersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaAuxiliarDetallePersona.class));
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

    public VistaAuxiliarDetallePersona findVistaAuxiliarDetallePersona(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaAuxiliarDetallePersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaAuxiliarDetallePersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaAuxiliarDetallePersona> rt = cq.from(VistaAuxiliarDetallePersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
