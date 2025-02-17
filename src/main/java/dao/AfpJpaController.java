/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.planilla.exceptions.NonexistentEntityException;
import com.mycompany.planilla.exceptions.PreexistingEntityException;
import dto.Afp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author USER
 */
public class AfpJpaController implements Serializable {

    public AfpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Afp afp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(afp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAfp(afp.getCodiAFP()) != null) {
                throw new PreexistingEntityException("Afp " + afp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Afp afp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            afp = em.merge(afp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = afp.getCodiAFP();
                if (findAfp(id) == null) {
                    throw new NonexistentEntityException("The afp with id " + id + " no longer exists.");
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
            Afp afp;
            try {
                afp = em.getReference(Afp.class, id);
                afp.getCodiAFP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The afp with id " + id + " no longer exists.", enfe);
            }
            em.remove(afp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Afp> findAfpEntities() {
        return findAfpEntities(true, -1, -1);
    }

    public List<Afp> findAfpEntities(int maxResults, int firstResult) {
        return findAfpEntities(false, maxResults, firstResult);
    }

    private List<Afp> findAfpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Afp.class));
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

    public Afp findAfp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Afp.class, id);
        } finally {
            em.close();
        }
    }

    public int getAfpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Afp> rt = cq.from(Afp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
