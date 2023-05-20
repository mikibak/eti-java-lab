package org.example;

public class Mage {
    @Id
    private String name;
    private int level;
    @ManyToOne
    private Tower tower;
}