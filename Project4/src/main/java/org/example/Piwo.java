package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Piwo {
    @Id
    private String name;
    private long cena;
    @ManyToOne
    private Browar browar;

    public Piwo(String name, long cena, Browar browar) {
        this.name = name;
        this.cena = cena;
        this.browar = browar;
    }

    public Piwo() {

    }

    @Override
    public String toString() {
        return "Name: " + name + " cena: " + cena + " browar: " + browar.getName();
    }

    public Browar getBrowar() {
        return browar;
    }
}