import java.util.ArrayList;

public interface Alimentacio {
    int PES_AMEBA = 20;

    int PES_BACTERI = 10;

    int PES_ALGA = 3;

    int PES_NUTRIENT = 5;

    void menjar(ArrayList<Esser> essers);
}
