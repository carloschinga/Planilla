/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.CategoriaOcupacional;
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
public class CategoriaOcupacionalJpaController implements Serializable {

    public CategoriaOcupacionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaOcupacional categoriaOcupacional) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaOcupacional);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoriaOcupacional(categoriaOcupacional.getCodiCateOcup()) != null) {
                throw new PreexistingEntityException("CategoriaOcupacional " + categoriaOcupacional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaOcupacional categoriaOcupacional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaOcupacional = em.merge(categoriaOcupacional);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = categoriaOcupacional.getCodiCateOcup();
                if (findCategoriaOcupacional(id) == null) {
                    throw new NonexistentEntityException("The categoriaOcupacional with id " + id + " no longer exists.");
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
            CategoriaOcupacional categoriaOcupacional;
            try {
                categoriaOcupacional = em.getReference(CategoriaOcupacional.class, id);
                categoriaOcupacional.getCodiCateOcup();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaOcupacional with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaOcupacional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaOcupacional> findCategoriaOcupacionalEntities() {
        return findCategoriaOcupacionalEntities(true, -1, -1);
    }

    public List<CategoriaOcupacional> findCategoriaOcupacionalEntities(int maxResults, int firstResult) {
        return findCategoriaOcupacionalEntities(false, maxResults, firstResult);
    }

    private List<CategoriaOcupacional> findCategoriaOcupacionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaOcupacional.class));
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

    public CategoriaOcupacional findCategoriaOcupacional(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaOcupacional.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaOcupacionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaOcupacional> rt = cq.from(CategoriaOcupacional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
