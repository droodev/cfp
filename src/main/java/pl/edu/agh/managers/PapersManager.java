package pl.edu.agh.managers;

import pl.edu.agh.model.Author;
import pl.edu.agh.model.Paper;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
public class PapersManager {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private AuthorsManager authorManger;

    public long addPaper(Paper paper) {
        em.persist(paper);
        for (Author a : paper.getAuthors()) {
            authorManger.addAuthor(a);
        }
        return paper.getId();
    }

    public void removePaper(long paperID) {
        Paper paperToRemove = getPaperById(paperID);
        if (paperToRemove != null) {
            em.remove(paperToRemove);
        }
        for (Author author : paperToRemove.getAuthors()) {
            authorManger.removeAuthor(author.getId());
        }
    }

    public Collection<Author> getAllAuthors(long id) {
        Paper paper = getPaperById(id);
        if (paper != null) {
            return paper.getAuthors();
        }
        return new ArrayList<Author>();
    }

    public long addAuthor(long paperID, Author author) {
        Paper paper = getPaperById(paperID);
        if (paper != null) {
            paper.addAuthor(author);
        }
        long newAuthorID = authorManger.addAuthor(author);
        em.persist(paper);
        return newAuthorID;
    }

    public Paper getPaper(long id){ return getPaperById(id);}

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
