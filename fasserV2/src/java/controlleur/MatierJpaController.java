/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import controlleur.exceptions.NonexistentEntityException;
import controlleur.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Matier;

/**
 *
 * @author zayoud_mohanned
 */
public class MatierJpaController implements Serializable {

    public MatierJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matier matier) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(matier);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatier(matier.getId()) != null) {
                throw new PreexistingEntityException("Matier " + matier + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matier matier) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            matier = em.merge(matier);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = matier.getId();
                if (findMatier(id) == null) {
                    throw new NonexistentEntityException("The matier with id " + id + " no longer exists.");
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
            Matier matier;
            try {
                matier = em.getReference(Matier.class, id);
                matier.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matier with id " + id + " no longer exists.", enfe);
            }
            em.remove(matier);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matier> findMatierEntities() {
        return findMatierEntities(true, -1, -1);
    }

    public List<Matier> findMatierEntities(int maxResults, int firstResult) {
        return findMatierEntities(false, maxResults, firstResult);
    }

    private List<Matier> findMatierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matier.class));
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

    public Matier findMatier(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matier.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatierCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matier> rt = cq.from(Matier.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
