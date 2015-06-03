package pl.edu.agh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Journal {

    protected Journal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String logoURL;
    private String name;
    private String constentToPublish;

    public Journal(String logoURL, String name, String constentToPublish) {
        this.logoURL = logoURL;
        this.name = name;
        this.constentToPublish = constentToPublish;
    }

    public long getId() {
        return id;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getName() {
        return name;
    }

    public String getConstentToPublish() {
        return constentToPublish;
    }


}
