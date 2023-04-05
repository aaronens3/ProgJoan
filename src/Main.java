import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static int NUM_MAX_ESSERS = 20;


    public static void main(String[] args) {


        ArrayList<Esser> essers = new ArrayList<>();


        System.out.println("Introdueix el nombre d'amebes (entre 1 i " + NUM_MAX_ESSERS + "):");
        int numAmebes = llegirNumero();
        System.out.println("Introdueix el nombre de bacteris (entre 1 i " + NUM_MAX_ESSERS + "):");
        int numBacteris = llegirNumero();
        System.out.println("Introdueix el nombre d'algues (entre 1 i " + NUM_MAX_ESSERS + "):");
        int numAlgues = llegirNumero();

        crearEssers(essers, numAmebes, numBacteris, numAlgues);
        processaMenu(essers);


    }

    private static int llegirNumero() {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        try {
            do {
                numero = sc.nextInt();
            } while (numero < 1 || numero > NUM_MAX_ESSERS);
        } catch (InputMismatchException e) {
            System.out.println("Chato, que no es un numero");
        }
        return numero;
    }

    public static void crearEssers(ArrayList<Esser> essers, int numAmebes, int numBacteris, int numAlgues) {
        NUM_MAX_ESSERS = numAmebes + numBacteris + numAlgues;
        for (int i = 0; i < NUM_MAX_ESSERS; i++) {
            int tipus = Esser.generaAleatori(0, 3);

            switch (tipus) {
                case 0:
                    if (numAmebes > 0) {
                        essers.add(new Ameba());
                        numAmebes--;
                    }
                    break;
                case 1:
                    if (numBacteris > 0) {
                        essers.add(new Bacteri());
                        numBacteris--;
                    }
                    break;
                case 2:
                    if (numAlgues > 0) {
                        essers.add(new Alga());
                        numAlgues--;
                    }
                    break;
                default:
                    break;
            }

        }
    }


    public static void processaMenu(ArrayList<Esser> essers) {

        Scanner scanner = new Scanner(System.in);
        int opcio = -1;
        while (opcio != 0) {
            System.out.println("Menu");
            System.out.println("1.-Una Interacció");
            System.out.println("2.-Deu Interaccions");
            System.out.println("3.-Llistat");
            System.out.println("4.-Detall");
            System.out.println("0.-Sortir");
            opcio = scanner.nextInt();
            switch (opcio) {
                case 1:
                    produeixInteraccio(essers);
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        produeixInteraccio(essers);
                    }
                    break;
                case 3:
                    mostrarLlistaEssers(essers);
                    break;
                case 4:
                    mostrarEsser(scanner, essers);
                    break;
                case 0:
                    System.out.println("Adéu");
                    break;
                default:
                    System.out.println("Opció incorrecta");
                    break;
            }
        }
        while (opcio != 0) ;

    }

    private static void produeixInteraccio(ArrayList<Esser> essers) {
        return;
    }

    public static void mostrarLlistaEssers(ArrayList<Esser> essers) {
        for (Esser e : essers) {
            System.out.println(e.toString());

            if (e instanceof Ameba) {
                Ameba.totalAmebes++;
            } else if (e instanceof Bacteri) {
                Bacteri.totalBacteris++;
            } else if (e instanceof Alga) {
                Alga.totalAlgues++;
            }

        }
        System.out.println("POBLACIÓ: TOTAL ESSERS=>" + Esser.totalEssers + ", AMEBES=>" + Ameba.totalAmebes + ", BACTERIES=>" +
                Bacteri.totalBacteris + ", ALGUES=>" + Alga.totalAlgues);
    }


    public static void mostrarEsser(Scanner scanner, ArrayList<Esser> essers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el detall de l'esser que vols mostrar");
        boolean trobat = false;
        for (Esser esser : essers) {
            if (esser.dirNom().equals(scanner.nextLine())) {
                System.out.println(esser);
                trobat = true;
                break;

            }
        }
        if (!trobat) {
            System.out.println("No existeix aquest esser");
        }
    }
}









    /*public static void crearEssers(ArrayList<Esser> essers, int numerAmebes, int numerBacteris, int numerAlges) {
        Random random = new Random();
        int TOTAL_ESERS = random.nextInt(3);
        if (TOTAL_ESERS == 0 && numerAmebes == 0) {
            essers.add(new Ameba());
            numerAmebes--;
        } else if (TOTAL_ESERS == 1 && numerBacteris == 0) {
            essers.add(new Bacteri());
            numerBacteris--;
        } else if (TOTAL_ESERS == 2 && numerAlges == 0) {
            essers.add(new Alga());
            numerAlges--;
        } else {
            crearEssers(essers, numerAmebes, numerBacteris, numerAlges);
        }
    }

    public static void processaMenu(ArrayList<Esser> essers){
        Scanner scanner = new Scanner(System.in);
        int opcio = -1;

        while (opcio != 0) {
            System.out.println("OPCIONS==>");
            System.out.println("1. Una interacció");
            System.out.println("2. 10 interaccions");
            System.out.println("3. Listar");
            System.out.println("4. Detall");
            System.out.println("0. Eixir");

            try {
                opcio = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opció incorrecta");
                opcio = -1;
            }

            switch (opcio) {
                case 1:
                    produeixInteraccio(essers);
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        produeixInteraccio(essers);
                    }
                    break;
                case 3:
                    mostrarLlistaEssers(essers);
                    break;
                case 4:
                    System.out.println("Introdueix el nom de l'esser a mostrar");
                    try {
                        int posicio = scanner.nextInt();
                        System.out.println(essers.get(posicio).toString());
                    } catch (Exception e) {
                        System.out.println("Opció incorrecta");
                    }
                    break;
                case 0:
                    System.out.println("Adéu");
                    break;
                default:
                    System.out.println("Opció incorrecta");
                    break;


        }

        }
    }

    private static void produeixInteraccio(ArrayList<Esser> essers) {
        Random random = new Random();
        Esser aleatori = essers.get(random.nextInt(essers.size()));
        int accio= random.nextInt(3);
        if (accio == 0) {
            ((Ameba) aleatori).menjar();
        } else if (accio == 1) {
            ((Ameba) aleatori).reproduir();
        } else if (aleatori instanceof Bacteri){
            int accio2 = random.nextInt(2);
            if (accio2 == 0) {
                ((Bacteri) aleatori).menjar();
            } else  (aleatori instanceof Alga){
                ((Alga) aleatori).menjar();
                if (!(Alga) aleatori).estaReproduint() && generarAleatori(essers) instanceof Alga) {
                    ((Alga) aleatori).reproduir();
                    essers.remove(aleatori);
                }
            }
        }

    }

    private static void mostrarLlistaEssers(ArrayList<Esser> essers) {
        int totalEssers = essers.size();
        int totalAmebes = 0;
        int totalBacteris = 0;
        int totalAlges = 0;



        System.out.println("Llista d'essers");
        for (int i = 0; i < essers.size(); i++) {
            System.out.println(i + ". " + essers.get(i).toString());
            if (Esser instanceof Ameba) {
                System.out.println("Ameba");
            } else if (Esser instanceof Bacteri) {
                System.out.println("Bacteri");
            } else if (Esser instanceof Alga) {
                System.out.println("Alga");
            }
        }

        System.out.println("Total d'essers: " + totalEssers + " Total d'amebes: " + totalAmebes + " Total de bacteris: " + totalBacteris + " Total d'alges: " + totalAlges);
    }*/







        /*
        Alga alga = new Alga();

        Alga alga2 = new Alga();
        //Bacteri bacteri = new Bacteri();
        Ameba ameba1 = new Ameba();
        Ameba ameba2 = new Ameba();
        Ameba ameba3 = new Ameba();
        Ameba ameba4 = new Ameba();
        Ameba ameba5 = new Ameba();
        Ameba ameba6 = new Ameba();
        Ameba ameba7 = new Ameba();

        Bacteri bacteri1 = new Bacteri();
        Bacteri bacteri2 = new Bacteri();
        Bacteri bacteri3 = new Bacteri();
        Bacteri bacteri4 = new Bacteri();
        Bacteri bacteri5 = new Bacteri();

        Alga alga3 = new Alga();
        Alga alga4 = new Alga();
        Alga alga5 = new Alga();


        ArrayList<Esser> essers = new ArrayList<>();

        essers.add(ameba1);
        essers.add(ameba2);
        essers.add(ameba3);
        essers.add(ameba4);
        essers.add(ameba5);
        essers.add(ameba6);
        essers.add(ameba7);

        essers.add(bacteri1);
        essers.add(bacteri2);
        essers.add(bacteri3);
        essers.add(bacteri4);
        essers.add(bacteri5);

        essers.add(alga);
        essers.add(alga2);
        essers.add(alga3);
        essers.add(alga4);
        essers.add(alga5);


        System.out.println("Total d'algues:" + Alga.dirPoblacio());
        System.out.println("Total d'bacteris:" + Bacteri.dirPoblacio());
        System.out.println("Total d'amebes:" + Ameba.dirPoblacio());
        System.out.println("Total d'essers:" + Esser.getTotalEssers());



        ameba4.menjar(essers);
        bacteri4.menjar(essers);
        alga4.menjar(essers);
        alga3.menjar(essers);

        System.out.println("Total d'algues:" + Alga.dirPoblacio());
        System.out.println("Total d'bacteris:" + Bacteri.dirPoblacio());
        System.out.println("Total d'amebes:" + Ameba.dirPoblacio());
        System.out.println("Total d'essers:" + Esser.getTotalEssers());

        */
