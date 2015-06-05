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
    private String base64Logo;
    private String name;
    private String constentToPublish;

    @OneToMany
    private Collection<Paper> papers = new ArrayList<Paper>();

    public Journal(String name, String constentToPublish, String base64Logo) {
        this.base64Logo = base64Logo;
        this.name = name;
        this.constentToPublish = constentToPublish;
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

    public String getConstentToPublish() {
        return constentToPublish;
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public void removePaper(long paperID) {
        Paper paperToRemove = null;
        for (Paper p : papers) {
            if (p.getId() == paperID) {
                paperToRemove = p;
            }
        }
        if (paperToRemove != null) {
            papers.remove(paperToRemove);
        }
    }

    public String toString() {
        return String.format("Journal:{\n%s\n%s\n%s\n}", getName(), getConstentToPublish(), getBase64Logo());
    }


}
