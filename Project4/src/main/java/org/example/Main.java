package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
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
                System.out.println("6. Browary tanie");
                System.out.println("7. Piwa tańsze niż");
                System.out.println("8. Piwa droższe niż");
                System.out.println("0. Wyjdź z programu");

                int option = scanner.nextInt();
                scanner.nextLine(); // Pobierz znak nowej linii po wprowadzeniu liczby

                switch (option) {
                    case 1:
                        System.out.println("Nazwa [enter] cena [enter] nazwa browaru");

                        String name = scanner.nextLine();
                        long cena = scanner.nextLong();
                        scanner.nextLine();
                        String browarName = scanner.nextLine();

                        Browar browar = (Browar)em.find(Browar.class, browarName);

                        Piwo piwo = new Piwo(name, cena, browar);
                        db.add(piwo);
                        break;
                    case 2:
                        db.deletePiwo(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nazwa [enter] wartosc");
                        String name1 = scanner.nextLine();
                        long cena1 = scanner.nextLong();
                        scanner.nextLine();

                        db.add(new Browar(name1, cena1));
                        break;
                    case 4:
                        db.deleteBrowar(scanner.nextLine());
                        break;
                    case 5:
                        db.print();
                        break;
                    case 6:
                        long cena2 = scanner.nextLong();
                        List<Browar> browaryTanie = db.getBrowarsWithCheaperThan(cena2);
                        System.out.println("\nBrowary z tanimi piwami: ");
                        for (Browar browarTani : browaryTanie) {
                            System.out.println("Browar: " + browarTani.getName());
                        }
                        break;
                    case 7:
                        long cena3 = scanner.nextLong();
                        List<Piwo> piwa = db.pobierzPiwaZCenaNizsza(em, cena3);
                        System.out.println("\nTanie piwa: ");
                        for (Piwo piiwo : piwa) {
                            System.out.println(piiwo);
                        }
                        break;
                    case 8:
                        String name8 = scanner.nextLine();
                        long cena8 = scanner.nextLong();
                        scanner.nextLine();
                        List<Piwo> piwa4 = db.pobierzPiwaZCenaWiekszaDlaBrowaru(em, name8, cena8);
                        for (Piwo piwo4 : piwa4) {
                            System.out.println(piwo4);
                        }

                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja. Wybierz ponownie.");
                }
            }
            em.close();
        }
    }

    private static void addStartingData(Model db) {
        Browar browar1 = new Browar("browar w Elblągu", 27);
        Browar browar2 = new Browar("Browar 2", 200);
        Browar browar3 = new Browar("Browar 3", 135);
        Browar browar4 = new Browar("Browar 4", 2465);
        Browar browar5 = new Browar("Browar 5", 6542);

        Piwo piwo1 = new Piwo("Żubr", 300, browar1);
        Piwo piwo2 = new Piwo("Kasztelan", 350, browar1);
        Piwo piwo3 = new Piwo("Perła", 410, browar2);
        Piwo piwo4 = new Piwo("Tyskie", 520, browar2);
        Piwo piwo5 = new Piwo("Lech", 510, browar3);
        Piwo piwo6 = new Piwo("Dębowe mocne", 210, browar4);


        db.add(browar1);
        db.add(browar2);
        db.add(browar3);
        db.add(browar4);
        db.add(browar5);
        db.add(piwo1);
        db.add(piwo2);
        db.add(piwo3);
        db.add(piwo4);
        db.add(piwo5);
        db.add(piwo6);

    }

}