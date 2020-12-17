package hu.gov.allamkincstar.javatanf;

import java.time.LocalDateTime;

public class Vasarlas {

    public enum KezbesitesiModok {
        helyszini,
        kiszallitas
    }

    public enum FizetesiModok {
        keszpenz,
        bannkartya
    }

    public enum VasarlasAllapotok {
        BOOKED,
        IN_PROGRESS,
        FAILED_DELIVERY,
        DELIVERED
    }


    private Vevo vevo;
    private Kosar kosar;
    private Integer fizetendo;
    private KezbesitesiModok kezbesitesiMod;
    private FizetesiModok fizetesiMod;
    private VasarlasAllapotok vasarlasAllapot;
    private LocalDateTime megrendelesDatuma;
    private LocalDateTime atvetelDatuma;


    public Vasarlas(Vevo vevo, Kosar kosar, Integer fizetendo, KezbesitesiModok kezbesitesiMod, FizetesiModok fizetesiMod) {
        this.vevo = vevo;
        this.kosar = kosar;
        this.fizetendo = fizetendo;
        this.kezbesitesiMod = kezbesitesiMod;
        this.fizetesiMod = fizetesiMod;
        this.megrendelesDatuma = LocalDateTime.now();
        if (kezbesitesiMod == KezbesitesiModok.helyszini) {
            this.vasarlasAllapot = VasarlasAllapotok.DELIVERED;
            this.atvetelDatuma = LocalDateTime.now();
            for (Termek termek : kosar.getKosar()) {
                termek.setStatus(Termek.CikkStatus.eladva);
            }
        } else {
            this.vasarlasAllapot = VasarlasAllapotok.BOOKED;
            this.atvetelDatuma = null;
        }

    }


    public void boltFutarnakKiad() {

        if (!(this.kezbesitesiMod == KezbesitesiModok.kiszallitas && this.getVasarlasAllapot() == VasarlasAllapotok.BOOKED)) {
            throw new RuntimeException("Csak kiszállításra kért, megrendelt állapotú rendelés adható ki a futárnak");
        } else {
            this.vasarlasAllapot = VasarlasAllapotok.IN_PROGRESS;
        }
    }

    public void futarMegrednelonekAtad() {

        if (!(this.kezbesitesiMod == KezbesitesiModok.kiszallitas && this.getVasarlasAllapot() == VasarlasAllapotok.IN_PROGRESS)) {
            throw new RuntimeException("Csak kiszállításra kért, szállítás alatt lévő rendelés adható át a megrendelőnek");
        } else {
            this.vasarlasAllapot = VasarlasAllapotok.DELIVERED;
            this.atvetelDatuma = LocalDateTime.now();
            for (Termek termek : kosar.getKosar()) {
                termek.setStatus(Termek.CikkStatus.eladva);
            }
        }
    }

    public void futarBoltnakVisszaad() {

        if (!(this.kezbesitesiMod == KezbesitesiModok.kiszallitas && this.getVasarlasAllapot() == VasarlasAllapotok.IN_PROGRESS)) {
            throw new RuntimeException("Csak kiszállításra kért, szállítás alatt lévő rendelés adható vissza a boltnak");
        } else {
            this.vasarlasAllapot = VasarlasAllapotok.FAILED_DELIVERY;

            for (Termek termek : kosar.getKosar()) {
                termek.setStatus(Termek.CikkStatus.eladhato);
            }
        }
    }


    public Vevo getVevo() {
        return vevo;
    }

    public Kosar getKosar() {
        return kosar;
    }

    public Integer getFizetendo() {
        return fizetendo;
    }

    public KezbesitesiModok getKezbesitesiMod() {
        return kezbesitesiMod;
    }

    public FizetesiModok getFizetesiMod() {
        return fizetesiMod;
    }

    public VasarlasAllapotok getVasarlasAllapot() {
        return vasarlasAllapot;
    }

    public LocalDateTime getMegrendelesDatuma() {
        return megrendelesDatuma;
    }

    public LocalDateTime getAtvetelDatuma() {
        return atvetelDatuma;
    }

    @Override
    public String toString() {
        return "Vasarlas{" +
                "vevo=" + vevo.toString() +
                ", kosar=" + kosar.toString() +
                ", fizetendo=" + fizetendo +
                ", kezbesitesiMod=" + kezbesitesiMod +
                ", fizetesiMod=" + fizetesiMod +
                ", vasarlasAllapot=" + vasarlasAllapot +
                ", megrendelesDatuma=" + megrendelesDatuma +
                ", atvetelDatuma=" + atvetelDatuma +
                '}';
    }
}
