package ba.unsa.etf.rpr;

public class LjekovitaBiljka extends Biljka {
    public LjekovitaBiljka(String naziv, int jacina) {
        super.setNaziv(naziv);
        super.setJacina(jacina);
    }

    @Override
    public String toString() {
        return "Lijek: " + getNaziv() + " - " + getJacina();
    }

    @Override
    public String konzumiraj() {
        return "[ZDRAVLJE +" + getJacina() + "]";
    }
}
