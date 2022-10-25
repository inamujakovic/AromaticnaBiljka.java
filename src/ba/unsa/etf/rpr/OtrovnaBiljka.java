package ba.unsa.etf.rpr;

public class OtrovnaBiljka extends Biljka {
    public OtrovnaBiljka(String naziv, int jacina) {
        super.setNaziv(naziv);
        super.setJacina(jacina);
    }

    @Override
    public String toString() {
        return "Otrov: " + getNaziv() + " - " + getJacina();
    }

    @Override
    public String konzumiraj() {
        return "[ZDRAVLJE -" + getJacina() + "]";
    }
}
