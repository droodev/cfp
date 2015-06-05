package pl.edu.agh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String financialDisclosure;

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
}
