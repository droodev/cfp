package pl.edu.agh.services;

import pl.edu.agh.model.Journal;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class JournalService {

    @PersistenceContext(unitName = "testName")
    EntityManager em;

    public long addJournal(Journal journal) {
        em.persist(journal);
        return journal.getId();
    }

    public void deleteJournal(Journal journal) {
        em.remove(journal);
    }

    public Journal getJournal(long id) {
        Query q = em.createQuery("SELECT journal FROM Journal journal WHERE journal.id = :id");
        q.setParameter("id", id);
        try {
            return (Journal) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
