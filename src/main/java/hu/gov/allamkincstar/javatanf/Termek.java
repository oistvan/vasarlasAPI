package hu.gov.allamkincstar.javatanf;

public class Termek {

    public enum CikkStatus {
        eladhato,
        rendelesAlatt,
        eladva
    }

    private String cikkszam;
    private String termektipus;
    private CikkStatus status;

    public Termek(String cikkszam, String termektipus) {
        this.cikkszam = cikkszam;
        this.termektipus = termektipus;
        this.status = CikkStatus.eladhato;
    }

    public String getCikkszam() {
        return cikkszam;
    }

    public String getTermektipus() {
        return termektipus;
    }

    public CikkStatus getStatus() {
        return status;
    }

    public void setStatus(CikkStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Termek{" +
                "cikkszam='" + cikkszam + '\'' +
                ", termektipus='" + termektipus + '\'' +
                ", status=" + status +
                '}';
    }
}

