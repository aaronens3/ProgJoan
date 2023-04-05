import java.util.ArrayList;

public class Bacteri extends Esser {

    static int totalBacteris = 0;

    private Aliment aliment;

    private boolean reproduccio;

    public Bacteri() {
        super("BACTERI", Alimentacio.PES_BACTERI);
        this.aliment = Aliment.ALGA;
        this.reproduccio = true;
        totalBacteris++;
    }

    public static int dirPoblacio(){
        return totalBacteris;
    }

    @Override
    public String mostrarEstat() {
        return "/ " + this.dirNom() + " => PES: " + this.dirPes();
    }

    @Override
    public String mostrarDetall() {
        return "/ " + this.dirNom() +
                " => PES " + this.dirPes() +
                " - ALIMENTACIÓ: " + this.aliment +
                " - REPRODUCCIÓ: " + (this.reproduccio ? "SI" : "NO");
    }

    @Override
    public void menjar(ArrayList<Esser> essersList) {
        int posicioElegida;
        Esser victima;

        try {
            if (Alga.dirPoblacio() == 0) {
                throw new Exception("ALIMENTACIÓ **** " + this.dirNom() + " amb un pes de " + this.dirPes() + " no he pogut menjar perque no queden algues.");
            }

            do {
                posicioElegida = (int) (Math.random() * essersList.size());
                victima = essersList.get(posicioElegida);
            } while (!(victima instanceof Alga));

            this.canviaPes(this.dirPes() + victima.dirPes());
            victima.reduirPoblacio();

            essersList.remove(victima);

            System.out.println("ALIMENTACIÓ **** " + this.dirNom() + ": m'he menjat a " + victima.dirNom() + ". Ara pese " + this.dirPes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void reproduir() {
        try {
            if (this.dirPes() >= (2 * Alimentacio.PES_BACTERI * Poblacio.PES_REPRODUCCIO)) {
                Bacteri nou = new Bacteri();
                nou.canviaPes(this.dirPes()/2);
                this.canviaPes(this.dirPes()/2);
                System.out.println("REPRODUCCIÓ **** " + this.dirNom() + " m'he reproduït i he creat a " + nou.dirNom() + ". Ara pese " + this.dirPes());
            } else {
                throw new Exception("REPRODUCCIÓ **** " + this.dirNom() + " amb un pes de " + this.dirPes() + " no em puc reproduir");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
