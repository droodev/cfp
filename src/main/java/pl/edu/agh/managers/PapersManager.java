package pl.edu.agh.managers;

import pl.edu.agh.model.Paper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class PapersManager {

    @PersistenceContext
    private EntityManager em;

    public long addPaper(Paper paper) {
        em.persist(paper);
        return paper.getId();
    }

    public void removePaper(long paperID) {
        Paper paperToRemove = getPaperById(paperID);
        if (paperToRemove != null) {
            em.remove(paperToRemove);
        }
    }

    private Paper getPaperById(long id) {
        Query q = em.createQuery("SELECT paper FROM Paper paper WHERE paper.id = :id");
        q.setParameter("id", id);
        try {
            return (Paper) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
