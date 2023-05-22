package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("rpgPu")) {
            EntityManager em = factory.createEntityManager();

            em.getTransaction().begin();

            Tower tower1 = new Tower("Blok w Katowicach", 27);
            Tower tower2 = new Tower("White Tower", 200);

            Mage mage1 = new Mage("Magik z Paktofoniki", 22, tower1);
            Mage mage2 = new Mage("Sauron", 30, tower1);
            Mage mage3 = new Mage("Harry Potter", 205, tower2);

            em.persist(tower1);
            em.persist(tower2);
            em.persist(mage1);
            em.persist(mage2);
            em.persist(mage3);

            em.getTransaction().commit();
        }
    }

}