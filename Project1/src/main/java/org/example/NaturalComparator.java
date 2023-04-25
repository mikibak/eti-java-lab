package org.example;

import java.util.Comparator;
import java.util.Objects;

public class NaturalComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == o2)
            return 0;
        if (!(o1 instanceof Mage && o2 instanceof Mage))
            return 0;
        Mage thisMage = (Mage)o1;
        Mage other = (Mage)o2;
        if(thisMage.getName().compareTo(other.getName()) > 0) {
            return 1;
        } else if(thisMage.getName().compareTo(other.getName()) < 0) {
            return -1;
        } else {
            if(thisMage.getLevel() > other.getLevel()) {
                return 1;
            } else if(thisMage.getLevel() < other.getLevel()) {
                return -1;
            } else {
                if(thisMage.getPower() > other.getPower()) {
                    return 1;
                } else if(thisMage.getPower() < other.getPower()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
