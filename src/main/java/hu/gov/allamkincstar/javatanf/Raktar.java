package hu.gov.allamkincstar.javatanf;

import java.util.ArrayList;
import java.util.List;

public class Raktar {

    private List<Termek> termekList = new ArrayList<Termek>();

    public Raktar(List<Termek> termekList) {
        this.termekList = termekList;
    }

    public void bevetelez(Termek termekBevenni) {

        for (Termek termek : termekList) {
            if(termekBevenni.getCikkszam().equals(termek.getCikkszam())){
                throw new RuntimeException("Már létezik termék a megadott cikkszámmal.");
            }
        }

        this.termekList.add(termekBevenni);

    }

    public List<Termek> getTermekList() {
        return termekList;
    }
}
