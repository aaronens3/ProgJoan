import java.util.ArrayList;

public class Alga extends Esser {

    static int totalAlgues = 0;

    private Aliment aliment;

    private boolean reproduccio;

    public Alga() {
        super("ALGA", Alimentacio.PES_ALGA);
        this.aliment = Aliment.NUTRIENT;
        this.reproduccio = true;
        totalAlgues++;
    }

    public static int dirPoblacio(){
        return totalAlgues;
    }

    @Override
    public String mostrarEstat() {
        return "# " + this.dirNom() + " => PES: " + this.dirPes();
    }

    @Override
    public String mostrarDetall() {
        return "# " + this.dirNom() +
                " => PES " + this.dirPes() +
                " - ALIMENTACIÓ: " + this.aliment +
                " - REPRODUCCIÓ: " + (this.reproduccio ? "SI" : "NO");
    }

    @Override
    public void menjar(ArrayList<Esser> essersList) {
        int posicioElegida;
        Esser victima;
        do {
            posicioElegida = (int) (Math.random() * essersList.size());
            victima = essersList.get(posicioElegida);
        } while (this == victima);

        this.canviaPes(this.dirPes() + Alimentacio.PES_NUTRIENT);
        victima.reduirPoblacio();

        essersList.remove(victima);

        System.out.println("ALIMENTACIÓ **** " + this.dirNom() + ": m'he menjat a " + victima.dirNom() + ". Ara pese " + this.dirPes());
    }

    @Override
    public void reproduir() {
        try {
            while (this.dirPes() >= (Alimentacio.PES_ALGA * Poblacio.PES_REPRODUCCIO)) {
                Alga nova = new Alga();
                this.canviaPes(this.dirPes() - nova.dirPes());
                System.out.println("REPRODUCCIÓ **** " + this.dirNom() + " m'he reproduït i he creat a " + nova.dirNom() + ". Ara pese " + this.dirPes());
            }
            throw new Exception("REPRODUCCIÓ **** " + this.dirNom() + " amb un pes de " + this.dirPes() + " no em puc reproduir");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
