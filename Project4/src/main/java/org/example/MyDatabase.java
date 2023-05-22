package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Scanner;

public class MyDatabase {
    private EntityManager em;

    public MyDatabase(EntityManager em) {
        this.em = em;
    }

/*    public void add(Mage mage) {
        em.getTransaction().begin();
        em.persist(mage);
        em.getTransaction().commit();
        em.close();
    }*/

    public <T> void add(T object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    private static void deleteMage(Scanner input) {
        try (EntityManager em = emf.createEntityManager()) {
            System.out.println("Enter mage name:");
            String mageName = input.nextLine();
            em.getTransaction().begin();
            Mage mage = em.find(Mage.class, mageName);
            em.remove(mage);
            em.getTransaction().commit();
            System.out.println("SUCCESS: Mage deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
