package hu.gov.allamkincstar.javatanf;

public class Vevo {

    private String nev;
    private String lackim;
    private String telefon;
    private String email;

    public Vevo(String nev, String lackim, String telefon, String email) {
        this.nev = nev;
        this.lackim = lackim;
        this.telefon = telefon;
        this.email = email;
    }

    public String getNev() {
        return nev;
    }

    public String getLackim() {
        return lackim;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Vevo{" +
                "nev='" + nev + '\'' +
                ", lackim='" + lackim + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
