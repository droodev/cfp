package pl.edu.agh.managers;

import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Singleton
public class JournalsManager {

    @PersistenceContext(unitName = "testName")
    EntityManager em;

    @EJB
    PapersManager papersManager;

    public long addJournal(Journal journal) {
        em.persist(journal);
        return journal.getId();
    }

    public void deleteJournal(long id) {
        Journal journalToRemove = getJournalById(id);
        if (journalToRemove != null) {
            em.remove(journalToRemove);
        }
    }

    public Journal getJournal(long id) {
        return getJournalById(id);
    }

    @SuppressWarnings("unchecked")
    public List<Long> getAll() {
        Query q = em.createQuery("SELECT journal.id FROM Journal journal");
        List results = q.getResultList();
        return (List<Long>) results;
    }

    private Journal getJournalById(long id) {
        Query q = em.createQuery("SELECT journal FROM Journal journal WHERE journal.id = :id");
        q.setParameter("id", id);
        try {
            return (Journal) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public long addPaper(long journalId, Paper paper) {
        Journal journal = getJournal(journalId);
        if (journal != null) {
            journal.addPaper(paper);
        }
        long newPaperId = papersManager.addPaper(paper);
        em.persist(journal);
        return newPaperId;
    }

    public Collection<Paper> getAllPapers(long journalId) {
        Journal journal = getJournal(journalId);
        if (journal != null) {
            return journal.getPapers();
        }
        return new ArrayList<Paper>();
    }


}
