package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Eliksir implements Konzumabilno, Comparable<Eliksir> {
    private final TipEliksira tipEliksira;
    private final ArrayList<Biljka> biljke;
    private final int jacina;
    private String naziv;
    public Eliksir(String naziv, ArrayList<Biljka> biljke) {
        this.naziv = naziv;
        this.biljke = biljke;
        if (naziv == null || naziv.isBlank()) {
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez naziva");
        }
        if (biljke == null || biljke.size() == 0) {
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez biljaka");
        }
        Map<Class<? extends Biljka>, Integer> jacine = new HashMap<>(Map.of(
                AromaticnaBiljka.class, 0,
                LjekovitaBiljka.class, 0,
                OtrovnaBiljka.class, 0
        ));
        for (Biljka biljka : biljke) {
            jacine.put(biljka.getClass(), jacine.get(biljka.getClass()) + biljka.getJacina());
        }
        long brojRazlicitih = jacine.values().stream().distinct().count();
        long jacinaAromaticnih = jacine.get(AromaticnaBiljka.class),
                jacinaLjekovitih = jacine.get(LjekovitaBiljka.class),
                jacinaOtrovnih = jacine.get(OtrovnaBiljka.class);
        if (brojRazlicitih == 1) {
            tipEliksira = TipEliksira.BOOSTER;
            jacina = (int) (jacinaAromaticnih + jacinaOtrovnih + jacinaLjekovitih);
        } else if (jacinaAromaticnih > jacinaLjekovitih && jacinaAromaticnih > jacinaOtrovnih) {
            tipEliksira = TipEliksira.PARFEM;
            jacina = (int) jacinaAromaticnih;
        } else if (jacinaLjekovitih > jacinaAromaticnih && jacinaLjekovitih > jacinaOtrovnih) {
            tipEliksira = TipEliksira.LIJEK;
            jacina = (int) jacinaLjekovitih;
        } else {
            tipEliksira = TipEliksira.OTROV;
            jacina = (int) jacinaOtrovnih;
        }
    }

    public String getNaziv() {
        return naziv;
    }

    public TipEliksira getTipEliksira() {
        return tipEliksira;
    }

    public ArrayList<Biljka> getBiljke() {
        return biljke;
    }

    public int getJacina() {
        return jacina;
    }

    @Override
    public String konzumiraj() {
        return "{" + tipEliksira.toString() + getJacina() + "}";
    }

    @Override
    public int compareTo(Eliksir o) {
        return getNaziv().compareTo(o.getNaziv());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eliksir eliksir = (Eliksir) o;

        return Objects.equals(naziv, eliksir.naziv);
    }

    @Override
    public int hashCode() {
        return naziv != null ? naziv.hashCode() : 0;
    }

    public enum TipEliksira {
        PARFEM("Miris +"),
        LIJEK("Zdravlje +"),
        OTROV("Zdravlje -"),
        BOOSTER("Sve +");

        private final String value;

        TipEliksira(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }
}
