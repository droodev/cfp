package pl.edu.agh.managers;

import pl.edu.agh.model.CorrespondencyData;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class CorrespondencyDataManager {
    @PersistenceContext
    private EntityManager em;

    public long addCorrespondencyData(CorrespondencyData data){
        em.persist(data);
        return data.getId();
    }
}
