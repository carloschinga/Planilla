/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.Nacionalidad;
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
public class NacionalidadJpaController implements Serializable {

    public NacionalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nacionalidad nacionalidad) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nacionalidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNacionalidad(nacionalidad.getCodiNaci()) != null) {
                throw new PreexistingEntityException("Nacionalidad " + nacionalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nacionalidad nacionalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nacionalidad = em.merge(nacionalidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = nacionalidad.getCodiNaci();
                if (findNacionalidad(id) == null) {
                    throw new NonexistentEntityException("The nacionalidad with id " + id + " no longer exists.");
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
            Nacionalidad nacionalidad;
            try {
                nacionalidad = em.getReference(Nacionalidad.class, id);
                nacionalidad.getCodiNaci();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nacionalidad with id " + id + " no longer exists.", enfe);
            }
            em.remove(nacionalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nacionalidad> findNacionalidadEntities() {
        return findNacionalidadEntities(true, -1, -1);
    }

    public List<Nacionalidad> findNacionalidadEntities(int maxResults, int firstResult) {
        return findNacionalidadEntities(false, maxResults, firstResult);
    }

    private List<Nacionalidad> findNacionalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nacionalidad.class));
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

    public Nacionalidad findNacionalidad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nacionalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getNacionalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nacionalidad> rt = cq.from(Nacionalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
