package pl.edu.agh.model;

import pl.edu.agh.utils.GSONExclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String financialDisclosure;
    private String IPAddress;
    private Date signingDate;
    private String signature;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Author> authors = new ArrayList<Author>();

    @ManyToOne
    @GSONExclude
    private Journal journal;

    protected Paper() {}

    public Paper(String name, String financialDisclosure, String signature) {
        this.name = name;
        this.financialDisclosure = financialDisclosure;
        this.signature = signature;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFinancialDisclosure() {
        return financialDisclosure;
    }

    public String getSignature() {
        return signature;
    }

    public Collection<Author> getAuthors() {
        return Collections.unmodifiableCollection(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public Date getSigningDate() {
        return signingDate;
    }
}