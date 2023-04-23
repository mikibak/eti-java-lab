package org.example;

import java.util.Set;

public class Mage {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

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
}
