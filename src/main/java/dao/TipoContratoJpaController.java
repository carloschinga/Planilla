/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dto.TipoContrato;
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
public class TipoContratoJpaController implements Serializable {

    public TipoContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoContrato tipoContrato) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoContrato);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoContrato(tipoContrato.getCodiTipoCntr()) != null) {
                throw new PreexistingEntityException("TipoContrato " + tipoContrato + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoContrato tipoContrato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoContrato = em.merge(tipoContrato);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoContrato.getCodiTipoCntr();
                if (findTipoContrato(id) == null) {
                    throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.");
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
            TipoContrato tipoContrato;
            try {
                tipoContrato = em.getReference(TipoContrato.class, id);
                tipoContrato.getCodiTipoCntr();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoContrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoContrato> findTipoContratoEntities() {
        return findTipoContratoEntities(true, -1, -1);
    }

    public List<TipoContrato> findTipoContratoEntities(int maxResults, int firstResult) {
        return findTipoContratoEntities(false, maxResults, firstResult);
    }

    private List<TipoContrato> findTipoContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoContrato.class));
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

    public TipoContrato findTipoContrato(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoContrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoContrato> rt = cq.from(TipoContrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
