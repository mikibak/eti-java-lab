package org.example;

public class Tower {
    @Id
    private String name;
    private int height;
    @OneToMany
    private List<Mage> mages;
}