/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dto.MacHabilitadas;
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
public class MacHabilitadasJpaController implements Serializable {

    public MacHabilitadasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MacHabilitadas macHabilitadas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(macHabilitadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MacHabilitadas macHabilitadas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            macHabilitadas = em.merge(macHabilitadas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = macHabilitadas.getCodiMacHabi();
                if (findMacHabilitadas(id) == null) {
                    throw new NonexistentEntityException("The macHabilitadas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MacHabilitadas macHabilitadas;
            try {
                macHabilitadas = em.getReference(MacHabilitadas.class, id);
                macHabilitadas.getCodiMacHabi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The macHabilitadas with id " + id + " no longer exists.", enfe);
            }
            em.remove(macHabilitadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MacHabilitadas> findMacHabilitadasEntities() {
        return findMacHabilitadasEntities(true, -1, -1);
    }

    public List<MacHabilitadas> findMacHabilitadasEntities(int maxResults, int firstResult) {
        return findMacHabilitadasEntities(false, maxResults, firstResult);
    }

    private List<MacHabilitadas> findMacHabilitadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MacHabilitadas.class));
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

    public MacHabilitadas findMacHabilitadas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MacHabilitadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMacHabilitadasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MacHabilitadas> rt = cq.from(MacHabilitadas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
