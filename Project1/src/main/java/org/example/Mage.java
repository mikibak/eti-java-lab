package org.example;

import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable{
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;
    private String compareOptions = "natural";

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mage))
            return false;
        Mage other = (Mage)o;

        return (this.name == other.name
                && this.level == other.level
                && this.power == other.power
                && this.apprentices == other.apprentices);
    }

    @Override
    public final int hashCode() {
        int result = 17;
        result *= level * power * name.hashCode();

        for(Mage apprentice : apprentices) {
            result += apprentice.hashCode();
        }

        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (o == this)
            return 0;
        if (!(o instanceof Mage))
            return -1;
        Mage other = (Mage)o;
        if(Objects.equals(compareOptions, "natural")) {
            if(this.name.length() > other.name.length()) {
                return 1;
            } else if(this.name.length() < other.name.length()) {
                return -1;
            } else {
                if(this.level > other.level) {
                    return 1;
                } else if(this.level < other.level) {
                    return -1;
                } else {
                    if(this.power > other.power) {
                        return 1;
                    } else if(this.power < other.power) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}
