package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String sorting = args[0];
        if(sorting == null) {
            System.out.println("No argument");
            return;
        }

        Mage[] mage = new Mage[10];

        mage[0] = new Mage("Severus", 38, 4.0, sorting);
        mage[1] = new Mage("Tytus Pikies", 50, 5.0, sorting);
        mage[2] = new Mage("Marek Kubale", 100, 10.0, sorting);
        mage[3] = new Mage("Gandalf", 70, 10.5, sorting);
        mage[4] = new Mage("J'zargo", 65, 9.5, sorting);
        mage[5] = new Mage("Morgana", 45, 4.5, sorting);
        mage[6] = new Mage("Manus", 55, 7.5, sorting);
        mage[7] = new Mage("Install Wizard", 40, 3.0, sorting);
        mage[8] = new Mage("Savos Aren", 30, 2.0, sorting);
        mage[9] = new Mage("Harry Pot", 35, 3.5, sorting);

        mage[2].AddApprentice(mage[0]);
        mage[2].AddApprentice(mage[6]);
        mage[6].AddApprentice(mage[7]);
        mage[7].AddApprentice(mage[9]);
        mage[6].AddApprentice(mage[8]);
        mage[8].AddApprentice(mage[4]);
        mage[4].AddApprentice(mage[3]);
        mage[4].AddApprentice(mage[1]);

        mage[2].PrintApprentices("-");
    }
}