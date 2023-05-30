import org.example.Mage;
import org.example.MageController;
import org.example.MageRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void findTest() {
        MageRepository mageRepository = mock(MageRepository.class);
        Mage mamaszDziubale = new Mage("Mamasz Dziubale", 80);
        when(mageRepository.find("Mamasz Dziubale")).thenReturn(Optional.of(mamaszDziubale));
        when(mageRepository.find("Magik z Paktofoniki")).thenReturn(Optional.empty());
        MageController mageController = new MageController(mageRepository);

        assertEquals(mamaszDziubale.toString(), mageController.find("Mamasz Dziubale"));
        assertEquals("not found", mageController.find("Magik z Paktofoniki"));
    }
    @Test
    public void Should_ReturnDone_WhenDeletingExistingMage() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Magik z Paktofoniki");
        MageController mageController = new MageController(mageRepository);

        assertEquals("done", mageController.delete("Mamasz Dziubale"));
    }

    @Test
    public void Should_ReturnNotFound_WhenDeletingNewMage() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Magik z Paktofoniki");
        MageController mageController = new MageController(mageRepository);

        assertEquals("not found", mageController.delete("Magik z Paktofoniki"));
    }
    @Test
    public void Should_ReturnBadRequest_WhenSavingExistingMage() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository)
                .save(any(Mage.class));
        MageController mageController = new MageController(mageRepository);

        assertEquals("bad request", mageController.save("Mamasz Dziubale", "80"));
    }

    @Test
    public void Should_ReturnDone_WhenSavingNewMage() {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(mageRepository)
                .save(new Mage("Mamasz Dziubale", 80));
        MageController mageController = new MageController(mageRepository);

        assertEquals("done", mageController.save("Magik z Paktofoniki", "22"));
    }
}