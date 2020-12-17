package hu.gov.allamkincstar.javatanf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Kosar {

    private List<Termek> kosar = new ArrayList<Termek>();

    public Kosar(List<Termek> kosar) {
        this.kosar = kosar;
    }

    public List<Termek> getKosar() {
        return kosar;
    }

    @Override
    public String toString() {
        return "Kosar{" +
                "kosar=" + kosar +
                '}';
    }

    public void kosarbaHelyez(String termektipus, Raktar raktar) {
        Boolean kosarbaHelyezve = Boolean.FALSE;

        if (termektipus.isEmpty()) {
            throw new RuntimeException("Nincs megadva a kosárba helyezendő termék típusa.");
        }

        for (Termek termek : raktar.getTermekList()) {
            if ((termek.getTermektipus() == termektipus) && (termek.getStatus() == Termek.CikkStatus.eladhato) && (! kosarbaHelyezve)) {
                termek.setStatus(Termek.CikkStatus.rendelesAlatt);
                this.kosar.add(termek);
                kosarbaHelyezve = Boolean.TRUE;
            }
        }

        if (! kosarbaHelyezve) {
            throw new RuntimeException("Nincs eladható termék ebből a terméktipusból.");
        }
    }

    public void kosarbolKivesz(String cikkszam) {

        Termek termekKivenni = null;

        if (cikkszam.isEmpty()) {
            throw new RuntimeException("Nincs megadva a kosárból törlendő termék cikkszáma.");
        }

        if (this.getKosar().isEmpty()) {
            throw new RuntimeException("Ez a kosár üres.");
        }

        for (Termek termek : this.getKosar()) {
            if (termek.getCikkszam() == cikkszam ) {
                termek.setStatus(Termek.CikkStatus.eladhato);
                termekKivenni = termek;
            }
        }

        if (termekKivenni == null ) {
            throw new RuntimeException("A kosárban nincs termék a megadott cikkszámmal.");
        } else {
            this.kosar.remove(termekKivenni);
        }

    }

}
