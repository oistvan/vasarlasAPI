package hu.gov.allamkincstar.javatanf.tesztek;

import hu.gov.allamkincstar.javatanf.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVasarlas {

    Exception exception;
    Raktar raktar = new Raktar(new ArrayList<>());

    @Test
    public void test1() {

        raktar.bevetelez(new Termek("0000001","CERUZA"));
        raktar.bevetelez(new Termek("0000002","TOLL"));
        raktar.bevetelez(new Termek("0000003","RADIR"));
        raktar.bevetelez(new Termek("0000004","CERUZA"));
        raktar.bevetelez(new Termek("0000005","CERUZA"));

        // CERUZA+RADIR helyszíni vásárlás
        Kosar kosar = new Kosar(new ArrayList<>());
        kosar.kosarbaHelyez("CERUZA", raktar);
        kosar.kosarbaHelyez("RADIR", raktar);
        Vasarlas vasarlas = new Vasarlas(
                new Vevo("Teszt Vevő", "Teszt lakcím", "20/999-888", "tesztvevo@gmail.com"),
                kosar,100000, Vasarlas.KezbesitesiModok.helyszini, Vasarlas.FizetesiModok.keszpenz
                );
        assertEquals("Teszt Vevő", vasarlas.getVevo().getNev());
        assertEquals(2, vasarlas.getKosar().getKosar().size());
        assertEquals(Vasarlas.KezbesitesiModok.helyszini, vasarlas.getKezbesitesiMod());
        assertEquals(Vasarlas.FizetesiModok.keszpenz, vasarlas.getFizetesiMod());
        assertEquals(100000, vasarlas.getFizetendo());
        assertEquals(Termek.CikkStatus.eladva,  vasarlas.getKosar().getKosar().get(0).getStatus());
        assertEquals(Termek.CikkStatus.eladva,  vasarlas.getKosar().getKosar().get(1).getStatus());


    }

    @Test
    public void test2() {

        raktar.bevetelez(new Termek("0000001","CERUZA"));
        raktar.bevetelez(new Termek("0000002","TOLL"));
        raktar.bevetelez(new Termek("0000003","RADIR"));
        raktar.bevetelez(new Termek("0000004","CERUZA"));
        raktar.bevetelez(new Termek("0000005","CERUZA"));

        // CERUZA+TOLL vásárlás kiszállítással
        Kosar kosar = new Kosar(new ArrayList<>());
        kosar.kosarbaHelyez("CERUZA", raktar);
        kosar.kosarbaHelyez("TOLL", raktar);
        Vasarlas vasarlas = new Vasarlas(
                new Vevo("Teszt2 Vevő", "Teszt2 lakcím", "20/000-111", "tesztvevo2@gmail.com"),
                kosar,5000, Vasarlas.KezbesitesiModok.kiszallitas, Vasarlas.FizetesiModok.keszpenz
        );
        assertEquals(Vasarlas.KezbesitesiModok.kiszallitas, vasarlas.getKezbesitesiMod());
        assertEquals(Vasarlas.VasarlasAllapotok.BOOKED, vasarlas.getVasarlasAllapot());
        assertEquals(Termek.CikkStatus.rendelesAlatt, vasarlas.getKosar().getKosar().get(0).getStatus());
        assertEquals(Termek.CikkStatus.rendelesAlatt, vasarlas.getKosar().getKosar().get(1).getStatus());

        exception = assertThrows(RuntimeException.class, ()-> vasarlas.futarMegrednelonekAtad());
        assertEquals("Csak kiszállításra kért, szállítás alatt lévő rendelés adható át a megrendelőnek", exception.getMessage());

        vasarlas.boltFutarnakKiad();
        assertEquals(Vasarlas.VasarlasAllapotok.IN_PROGRESS, vasarlas.getVasarlasAllapot());

        vasarlas.futarMegrednelonekAtad();
        assertEquals(Vasarlas.VasarlasAllapotok.DELIVERED, vasarlas.getVasarlasAllapot());
        assertEquals(Termek.CikkStatus.eladva, vasarlas.getKosar().getKosar().get(0).getStatus());
        assertEquals(Termek.CikkStatus.eladva, vasarlas.getKosar().getKosar().get(1).getStatus());

    }

}
