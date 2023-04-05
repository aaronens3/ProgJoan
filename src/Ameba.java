import java.util.ArrayList;

public class Ameba extends Esser {
    static int totalAmebes = 0;

    private Aliment aliment;

    private boolean reproduccio;

    public Ameba() {
        super("AMEBA", Alimentacio.PES_AMEBA);
        this.aliment = Aliment.TOT;
        this.reproduccio = true;
        totalAmebes++;
    }

    public static int dirPoblacio(){
        return totalAmebes;
    }

    @Override
    public String mostrarEstat() {
        return "@ " + this.dirNom() + " => PES: " + this.dirPes();
    }

    @Override
    public String mostrarDetall() {
        return "@ " + this.dirNom() +
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

        this.canviaPes(this.dirPes() + victima.dirPes());
        victima.reduirPoblacio();

        essersList.remove(victima);

        System.out.println("ALIMENTACIÓ **** " + this.dirNom() + ": m'he menjat a " + victima.dirNom() + ". Ara pese " + this.dirPes());
    }

    @Override
    public void reproduir() {
        try {
            if (this.dirPes() >= (Alimentacio.PES_AMEBA * Poblacio.PES_REPRODUCCIO)) {
                Ameba nova = new Ameba();
                this.canviaPes(this.dirPes() - nova.dirPes());
                System.out.println("REPRODUCCIÓ **** " + this.dirNom() + " m'he reproduït i he creat a " + nova.dirNom() + ". Ara pese " + this.dirPes());
            } else {
                throw new Exception("REPRODUCCIÓ **** " + this.dirNom() + " amb un pes de " + this.dirPes() + " no em puc reproduir");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
