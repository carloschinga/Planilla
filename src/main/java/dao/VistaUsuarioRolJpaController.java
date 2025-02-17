/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.VistaUsuarioRol;
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
public class VistaUsuarioRolJpaController implements Serializable {

    public VistaUsuarioRolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VistaUsuarioRol vistaUsuarioRol) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistaUsuarioRol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistaUsuarioRol(vistaUsuarioRol.getCodiUsua()) != null) {
                throw new PreexistingEntityException("VistaUsuarioRol " + vistaUsuarioRol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VistaUsuarioRol vistaUsuarioRol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistaUsuarioRol = em.merge(vistaUsuarioRol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vistaUsuarioRol.getCodiUsua();
                if (findVistaUsuarioRol(id) == null) {
                    throw new NonexistentEntityException("The vistaUsuarioRol with id " + id + " no longer exists.");
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
            VistaUsuarioRol vistaUsuarioRol;
            try {
                vistaUsuarioRol = em.getReference(VistaUsuarioRol.class, id);
                vistaUsuarioRol.getCodiUsua();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistaUsuarioRol with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistaUsuarioRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VistaUsuarioRol> findVistaUsuarioRolEntities() {
        return findVistaUsuarioRolEntities(true, -1, -1);
    }

    public List<VistaUsuarioRol> findVistaUsuarioRolEntities(int maxResults, int firstResult) {
        return findVistaUsuarioRolEntities(false, maxResults, firstResult);
    }

    private List<VistaUsuarioRol> findVistaUsuarioRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VistaUsuarioRol.class));
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

    public VistaUsuarioRol findVistaUsuarioRol(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VistaUsuarioRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistaUsuarioRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VistaUsuarioRol> rt = cq.from(VistaUsuarioRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
