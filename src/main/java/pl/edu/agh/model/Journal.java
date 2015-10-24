package pl.edu.agh.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Journal {

    protected Journal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition="TEXT") //MySQL specific
    private String base64Logo;
    private String name;
    @Column(columnDefinition="TEXT") //MySQL specific
    private String consentToPublish;

    @OneToMany(mappedBy = "journal")
    private Collection<Paper> papers = new ArrayList<Paper>();

    public Journal(String name, String consentToPublish, String base64Logo) {
        this.base64Logo = base64Logo;
        this.name = name;
        this.consentToPublish = consentToPublish;
    }

    public Collection<Paper> getPapers() {
        return Collections.unmodifiableCollection(papers);
    }

    public long getId() {
        return id;
    }

    public String getBase64Logo() {
        return base64Logo;
    }

    public String getName() {
        return name;
    }

    public String getConsentToPublish() {
        return consentToPublish;
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public String toString() {
        return String.format("Journal:{\n%s\n%s\n%s\n}", getName(), getConsentToPublish(), getBase64Logo());
    }


}
