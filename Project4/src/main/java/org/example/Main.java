package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("rpgPu")) {
            EntityManager em = factory.createEntityManager();
            Model db = new Model(em);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            addStartingData(db);
            db.print();

            while (running) {
                System.out.println("Wybierz opcję:");
                System.out.println("1. Dodaj piwo");
                System.out.println("2. Usuń piwo");
                System.out.println("3. Dodaj browar");
                System.out.println("4. Usuń browar");
                System.out.println("5. Wyświetl");
                System.out.println("6. Browary z piwem tańszym niż..");
                System.out.println("7. Piwa tańsze niż...");
                System.out.println("8. Piwa droższe niż... w browarze...");
                System.out.println("0. Wyjdź z programu");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> {
                        System.out.println("Nazwa [enter] cena [enter] nazwa browaru");
                        String name = scanner.nextLine();
                        long price = scanner.nextLong();
                        scanner.nextLine();
                        Brewery brewery = db.getBrewery(scanner.nextLine());
                        if (brewery != null) {
                            Beer beer = new Beer(name, price, brewery);
                            db.add(beer);
                        }
                    }
                    case 2 -> {
                        System.out.println("Nazwa [enter]");
                        db.deletePiwo(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.println("Nazwa [enter] wartosc");
                        String name1 = scanner.nextLine();
                        long cena1 = scanner.nextLong();
                        scanner.nextLine();
                        db.add(new Brewery(name1, cena1));
                    }
                    case 4 -> {
                        System.out.println("Nazwa [enter]");
                        db.deleteBrowar(scanner.nextLine());
                    }
                    case 5 -> {
                        db.print();
                    }
                    case 6 -> {
                        System.out.println("Cena [enter]");
                        long cena2 = scanner.nextLong();
                        List<Brewery> browaryTanie = db.getBrowarsWithCheaperThan(cena2);
                        System.out.println("\nBrowary z tanimi piwami: ");
                        for (Brewery breweryTani : browaryTanie) {
                            System.out.println("Browar: " + breweryTani.getName());
                        }
                    }
                    case 7 -> {
                        System.out.println("Cena [enter]");
                        long cena3 = scanner.nextLong();
                        List<Beer> piwa = db.pobierzPiwaZCenaNizsza(em, cena3);
                        System.out.println("\nTanie piwa: ");
                        for (Beer piiwo : piwa) {
                            System.out.println(piiwo);
                        }
                    }
                    case 8 -> {
                        System.out.println("Nazwa [enter] cena");
                        String name8 = scanner.nextLine();
                        long cena8 = scanner.nextLong();
                        scanner.nextLine();
                        List<Beer> piwa4 = db.pobierzPiwaZCenaWiekszaDlaBrowaru(em, name8, cena8);
                        for (Beer beer4 : piwa4) {
                            System.out.println(beer4);
                        }
                    }
                    case 0 -> {
                        running = false;
                    }
                    default -> System.out.println("Nieprawidłowa opcja. Wybierz ponownie.");
                }
            }
            em.close();
        }
    }

    private static void addStartingData(Model db) {
        Brewery brewery1 = new Brewery("browar w Elblągu", 27);
        Brewery brewery2 = new Brewery("Brewery 2", 200);
        Brewery brewery3 = new Brewery("Brewery 3", 135);
        Brewery brewery4 = new Brewery("Brewery 4", 2465);
        Brewery brewery5 = new Brewery("Brewery 5", 6542);

        Beer beer1 = new Beer("Żubr", 300, brewery1);
        Beer beer2 = new Beer("Kasztelan", 350, brewery1);
        Beer beer3 = new Beer("Perła", 410, brewery2);
        Beer beer4 = new Beer("Tyskie", 520, brewery2);
        Beer beer5 = new Beer("Lech", 510, brewery3);
        Beer beer6 = new Beer("Dębowe mocne", 210, brewery4);


        db.add(brewery1);
        db.add(brewery2);
        db.add(brewery3);
        db.add(brewery4);
        db.add(brewery5);
        db.add(beer1);
        db.add(beer2);
        db.add(beer3);
        db.add(beer4);
        db.add(beer5);
        db.add(beer6);

    }

}