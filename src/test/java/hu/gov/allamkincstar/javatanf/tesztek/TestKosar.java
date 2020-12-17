package hu.gov.allamkincstar.javatanf.tesztek;

import hu.gov.allamkincstar.javatanf.Kosar;
import hu.gov.allamkincstar.javatanf.Raktar;
import hu.gov.allamkincstar.javatanf.Termek;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestKosar {

    Exception exception;
    Raktar raktar = new Raktar(new ArrayList<>());

        @Test
        public void test1() {

            raktar.bevetelez(new Termek("0000001","LAPTOP"));
            raktar.bevetelez(new Termek("0000002","LAPTOP"));
            raktar.bevetelez(new Termek("0000003","PC"));
            raktar.bevetelez(new Termek("0000004","LAPTOP"));
            raktar.bevetelez(new Termek("0000005","PC"));

            Kosar kosar = new Kosar(new ArrayList<>());
            kosar.kosarbaHelyez("PC", raktar);
            kosar.kosarbaHelyez("PC", raktar);

            assertEquals(2, kosar.getKosar().size());
            exception = assertThrows(RuntimeException.class, ()-> kosar.kosarbaHelyez("PC", raktar));
            assertEquals("Nincs eladható termék ebből a terméktipusból.", exception.getMessage());

            kosar.kosarbolKivesz("0000003");
            assertEquals(1, kosar.getKosar().size());


        }

}
