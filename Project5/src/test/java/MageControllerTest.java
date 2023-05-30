import org.example.Mage;
import org.example.MageController;
import org.example.MageRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void find() {
        MageRepository mageRepository = mock(MageRepository.class);
        Mage mamaszDziubale = new Mage("Mamasz Dziubale", 80);
        when(mageRepository.find("Mamasz Dziubale")).thenReturn(Optional.of(mamaszDziubale);
        when(mageRepository.find("Magik z Paktofoniki")).thenReturn(Optional.empty());
        MageController mageController = new MageController(mageRepository);

        assertEquals(mageController.find("Mamasz Dziubale"), mamaszDziubale.toString());
        assertEquals(mageController.find("Magik z Paktofoniki"), "not found");
    }
    @Test
    public void delete() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository.delete("Magik z Paktofoniki");
        MageController mageController = new MageController(mageRepository);

        assertEquals(mageController.delete("Mamasz Dziubale"), "done");
        assertEquals(mageController.delete("Magik z Paktofoniki"), "not found");
    }
    @Test
    public void save() {
        MageRepository mageRepository = mock(MageRepository.class);
        Mage mamaszDziubale = new Mage("Mamasz Dziubale", 80);
        when(mageRepository.save(mamaszDziubale)).thenReturn().thenThrow(IllegalArgumentException.class);
        MageController mageController = new MageController(mageRepository);

        try {
            repository.save(new Mage(name, Integer.parseInt(level)));
            return "done";
        } catch(IllegalArgumentException e) {
            return "bad request";
        }
    }
}