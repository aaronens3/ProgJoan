import java.util.ArrayList;
import java.util.Random;

public abstract class Esser implements Alimentacio, Poblacio {

    protected static int totalEssers = 0;
    private static int consecutiu = 1;

    private String nom;

    private int pes;

    public Esser(String subclasse, int pes) {
        this.nom = subclasse + consecutiu;
        this.pes = pes;
        consecutiu++;
        totalEssers++;
    }

    public static int getTotalEssers() {
        return totalEssers;
    }

    public static void setTotalEssers(int totalEssers) {
        Esser.totalEssers = totalEssers;
    }

    public static int getConsecutiu() {
        return consecutiu;
    }

    public static void setConsecutiu(int consecutiu) {
        Esser.consecutiu = consecutiu;
    }

    @Override
    public void reduirPoblacio() {
        totalEssers--;

        if (this instanceof Ameba) Ameba.totalAmebes--;
        else if(this instanceof Alga) Alga.totalAlgues--;
        else if(this instanceof Bacteri) Bacteri.totalBacteris--;
    }

    public String dirNom() {
        return nom;
    }

    public int dirPes() {
        return this.pes;
    }

    public final void canviaPes(int pes) {
        this.pes = pes;
    }

    protected static int generaAleatori(int inicial, int quantitat) {
        Random r = new Random();
        int aleatori = r.nextInt(quantitat) + inicial;
        return aleatori;

    }

    public abstract String mostrarEstat();

    public abstract String mostrarDetall();
}
