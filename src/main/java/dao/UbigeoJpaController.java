/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.Ubigeo;
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
public class UbigeoJpaController implements Serializable {

    public UbigeoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ubigeo ubigeo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ubigeo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUbigeo(ubigeo.getCodiUbig()) != null) {
                throw new PreexistingEntityException("Ubigeo " + ubigeo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ubigeo ubigeo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ubigeo = em.merge(ubigeo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ubigeo.getCodiUbig();
                if (findUbigeo(id) == null) {
                    throw new NonexistentEntityException("The ubigeo with id " + id + " no longer exists.");
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
            Ubigeo ubigeo;
            try {
                ubigeo = em.getReference(Ubigeo.class, id);
                ubigeo.getCodiUbig();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubigeo with id " + id + " no longer exists.", enfe);
            }
            em.remove(ubigeo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ubigeo> findUbigeoEntities() {
        return findUbigeoEntities(true, -1, -1);
    }

    public List<Ubigeo> findUbigeoEntities(int maxResults, int firstResult) {
        return findUbigeoEntities(false, maxResults, firstResult);
    }

    private List<Ubigeo> findUbigeoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ubigeo.class));
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

    public Ubigeo findUbigeo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ubigeo.class, id);
        } finally {
            em.close();
        }
    }

    public int getUbigeoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ubigeo> rt = cq.from(Ubigeo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
