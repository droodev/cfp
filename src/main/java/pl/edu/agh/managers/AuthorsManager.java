package pl.edu.agh.managers;

import pl.edu.agh.model.Author;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class AuthorsManager {

    @PersistenceContext(unitName = "domain")
    private EntityManager em;

    @EJB
    CorrespondencyDataManager correspondencyDataManager;

    public void addAuthor(Author author) {
        if(author.getCorrespondencyData()!=null){
            System.out.print("Correspondency data exists!");
            correspondencyDataManager.addCorrespondencyData(author.getCorrespondencyData());
        }
        em.persist(author);
    }

    public void removeAuthor(long authorID) {
        Author author = getAuthorById(authorID);
        if (author != null) {
            em.remove(author);
        }
    }

    private Author getAuthorById(long id) {
        Query q = em.createQuery("SELECT author FROM Author author WHERE author.id = :id");
        q.setParameter("id", id);
        try {
            return (Author) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
