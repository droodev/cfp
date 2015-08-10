package pl.edu.agh.managers;

import pl.edu.agh.model.CorrespondencyData;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class CorrespondencyDataManager {
    @PersistenceContext(unitName = "domain")
    private EntityManager em;

    public void addCorrespondencyData(CorrespondencyData data){
        em.persist(data);
    }
}
