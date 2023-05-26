package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Beer {
    @Id
    private String name;
    private long cena;
    @ManyToOne
    private Brewery brewery;

    public Beer(String name, long cena, Brewery brewery) {
        this.name = name;
        this.cena = cena;
        this.brewery = brewery;
    }

    public Beer() {

    }

    @Override
    public String toString() {
        return "Name: " + name + " cena: " + cena + " brewery: " + brewery.getName();
    }

    public Brewery getBrowar() {
        return brewery;
    }
}