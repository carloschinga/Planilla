/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoOcupPublico;
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
public class TipoOcupPublicoJpaController implements Serializable {

    public TipoOcupPublicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoOcupPublico tipoOcupPublico) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoOcupPublico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoOcupPublico(tipoOcupPublico.getCodiTipoOcupPubl()) != null) {
                throw new PreexistingEntityException("TipoOcupPublico " + tipoOcupPublico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoOcupPublico tipoOcupPublico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoOcupPublico = em.merge(tipoOcupPublico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoOcupPublico.getCodiTipoOcupPubl();
                if (findTipoOcupPublico(id) == null) {
                    throw new NonexistentEntityException("The tipoOcupPublico with id " + id + " no longer exists.");
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
            TipoOcupPublico tipoOcupPublico;
            try {
                tipoOcupPublico = em.getReference(TipoOcupPublico.class, id);
                tipoOcupPublico.getCodiTipoOcupPubl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoOcupPublico with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoOcupPublico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoOcupPublico> findTipoOcupPublicoEntities() {
        return findTipoOcupPublicoEntities(true, -1, -1);
    }

    public List<TipoOcupPublico> findTipoOcupPublicoEntities(int maxResults, int firstResult) {
        return findTipoOcupPublicoEntities(false, maxResults, firstResult);
    }

    private List<TipoOcupPublico> findTipoOcupPublicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoOcupPublico.class));
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

    public TipoOcupPublico findTipoOcupPublico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoOcupPublico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoOcupPublicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoOcupPublico> rt = cq.from(TipoOcupPublico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
