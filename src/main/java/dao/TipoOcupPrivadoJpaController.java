/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoOcupPrivado;
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
public class TipoOcupPrivadoJpaController implements Serializable {

    public TipoOcupPrivadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoOcupPrivado tipoOcupPrivado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoOcupPrivado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoOcupPrivado(tipoOcupPrivado.getCodiTipoOcupPriv()) != null) {
                throw new PreexistingEntityException("TipoOcupPrivado " + tipoOcupPrivado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoOcupPrivado tipoOcupPrivado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoOcupPrivado = em.merge(tipoOcupPrivado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoOcupPrivado.getCodiTipoOcupPriv();
                if (findTipoOcupPrivado(id) == null) {
                    throw new NonexistentEntityException("The tipoOcupPrivado with id " + id + " no longer exists.");
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
            TipoOcupPrivado tipoOcupPrivado;
            try {
                tipoOcupPrivado = em.getReference(TipoOcupPrivado.class, id);
                tipoOcupPrivado.getCodiTipoOcupPriv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoOcupPrivado with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoOcupPrivado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoOcupPrivado> findTipoOcupPrivadoEntities() {
        return findTipoOcupPrivadoEntities(true, -1, -1);
    }

    public List<TipoOcupPrivado> findTipoOcupPrivadoEntities(int maxResults, int firstResult) {
        return findTipoOcupPrivadoEntities(false, maxResults, firstResult);
    }

    private List<TipoOcupPrivado> findTipoOcupPrivadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoOcupPrivado.class));
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

    public TipoOcupPrivado findTipoOcupPrivado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoOcupPrivado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoOcupPrivadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoOcupPrivado> rt = cq.from(TipoOcupPrivado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
