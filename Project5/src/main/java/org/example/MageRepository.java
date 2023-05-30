package org.example;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;
    public Optional<Mage> find(String name) {
        for(Mage mage : collection) {
            if(Objects.equals(mage.getName(), name)) {
                return Optional.of(mage);
            }
        }
        return  Optional.empty();
    }
    public void delete(String name) {
        if(!collection.removeIf(mage -> Objects.equals(mage.getName(), name))) {
            throw new IllegalArgumentException();
        }
    }
    public void save(Mage mage) {
        if(collection.stream().anyMatch(m -> Objects.equals(m.getName(), mage.getName()))) {
            throw new IllegalArgumentException();
        } else {
            collection.add(mage);
        }
    }
}