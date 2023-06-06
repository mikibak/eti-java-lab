import org.example.Mage;
import org.example.MageRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class MageRepositoryTest {
    @Test
    public void Should_ReturnOptionalMage_WhenMageInRepository() {
        Collection<Mage> collection = new ArrayList<>();
        collection.add(new Mage("Mamasz Dziubale", 80));
        MageRepository mageRepository = new MageRepository(collection);

        Optional<Mage> mage = mageRepository.find("Mamasz Dziubale");
        assertTrue(mage.isPresent());
    }

    @Test
    public void Should_ReturnEmptyOptional_WhenMageNotInRepository() {
        Collection<Mage> collection = new ArrayList<>();
        MageRepository mageRepository = new MageRepository(collection);

        Optional<Mage> nullMage = mageRepository.find("Magik z Paktofoniki");
        assertTrue(nullMage.isEmpty());
    }

    @Test
    public void Should_ReturnCorrectOptionalMage_WhenMageInRepository() {
        Collection<Mage> collection = new ArrayList<>();
        collection.add(new Mage("Mamasz Dziubale", 80));
        MageRepository mageRepository = new MageRepository(collection);

        Optional<Mage> mage = mageRepository.find("Mamasz Dziubale");
        assertEquals(mage.get().getLevel(), 80);
    }
    @Test
    public void deleteTest() {
        Collection<Mage> collection = new ArrayList<>();
        collection.add(new Mage("Mamasz Dziubale", 80));
        MageRepository mageRepository = new MageRepository(collection);

        assertDoesNotThrow(
                () -> mageRepository.delete("Mamasz Dziubale")
        );

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> mageRepository.delete("Magik z Paktofoniki")
        );
    }
    @Test
    public void saveTest() {
        MageRepository mageRepository = new MageRepository(new ArrayList<>());
        assertDoesNotThrow(
                () -> mageRepository.save(new Mage("Mamasz Dziubale", 100))
        );

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> mageRepository.save(new Mage("Mamasz Dziubale", 200))
        );
    }
}