package pl.edu.agh.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String financialDisclosure;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Author> authors = new ArrayList<Author>();

    protected Paper() {
    }

    public Paper(String name, String financialDisclosure) {
        this.name = name;
        this.financialDisclosure = financialDisclosure;
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

    public Collection<Author> getAuthors() {
        return Collections.unmodifiableCollection(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
