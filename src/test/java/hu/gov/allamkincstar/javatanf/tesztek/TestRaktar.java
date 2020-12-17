package hu.gov.allamkincstar.javatanf.tesztek;
import hu.gov.allamkincstar.javatanf.Raktar;
import hu.gov.allamkincstar.javatanf.Termek;
import java.util.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestRaktar {

    Exception exception;
    Raktar raktar = new Raktar(new ArrayList<>());

    @Test
    public void test1() {
        raktar.bevetelez(new Termek("0000001", "CERUZA"));
        raktar.bevetelez(new Termek("0000002", "CERUZA"));
        raktar.bevetelez(new Termek("0000003", "RADIR"));
        raktar.bevetelez(new Termek("0000004", "CERUZA"));
        raktar.bevetelez(new Termek("0000005", "RADIR"));
        assertEquals(5, raktar.getTermekList().size());

        exception = assertThrows(RuntimeException.class, ()-> raktar.bevetelez(new Termek("0000005","RADIR")));
        assertEquals("Már létezik termék a megadott cikkszámmal.", exception.getMessage());

    }

}
