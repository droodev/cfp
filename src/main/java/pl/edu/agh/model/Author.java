package pl.edu.agh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String affiliation;
    private String contribution;
    private int contributionValue;

    protected Author() {
    }

    public Author(String name, String surname, String affiliation, String contribution, int contributionValue) {
        this.name = name;
        this.surname = surname;
        this.affiliation = affiliation;
        this.contribution = contribution;
        this.contributionValue = contributionValue;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getContribution() {
        return contribution;
    }

    public int getContributionValue() {
        return contributionValue;
    }

    public long getId() {
        return id;
    }

}
