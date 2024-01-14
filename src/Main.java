import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Map<String, Jugador> jugadors = new HashMap<>();
        Map<String, Personatge> personatges = new HashMap<>();

        Map<Jugador,VehicleEnCursa> vehiclesEnCursa = new HashMap<>();

        llegirArxiuPersonatges(jugadors,personatges, "src/personatges.txt");

        Scanner scanner = new Scanner(System.in);
        int opcio;

        do {
            opcio = demanarOpcio(scanner);
            switch (opcio) {
                // Preparar la participació
                case 1:
                    // Identificar jugador
                    jugadors.forEach((nom,jugador) -> {
                        String nomPersonatge = mostraPersonatges(personatges);
                        Vehicle vehicle = mostraVehicles(scanner);
                        VehicleEnCursa vehicleEnCursa = new VehicleEnCursa(jugador,vehicle, personatges.get(nomPersonatge));
                        vehiclesEnCursa.put(jugador,vehicleEnCursa);
                    });
                    // Seleccionar personatge
                    // Mostrar inscrits a la cursa

                    vehiclesEnCursa.forEach((jugador,vehicle)->{
                        System.out.println(vehicle);
                    });

                    break;
                // Fer moure un vehicle
                case 2:
                    break;
                // Mostrar situació de la carrera
                case 3:
                    break;
                default: break;
            }
        }
        while (opcio != 0);
    }

    private static Vehicle mostraVehicles(Scanner scanner) {
        Vehicle v = new Vehicle();
        int opcio = 0;

        System.out.println("[0] Biga");
        System.out.println("[1] Cavall");
        System.out.println("[2] Quadriga");

        opcio = scanner.nextInt();

        switch (opcio){
            case 0: v = new Biga(); break;
            case 1: v = new Cavall(); break;
            case 2: v = new Quadriga(); break;
            default: System.out.println("Hoy es nocheee de seeexo - Wisin y Yandel"); break;
        }

        return v;
    }

    private static String mostraPersonatges(Map<String, Personatge> personatges) {
        int i = 0;
        String nomPersonatgeEscollit = "";
        personatges.forEach((nom, personatge)->{
            System.out.println("[" + i + "] " + nom);
        });
        return nomPersonatgeEscollit;
    }

    private static void seleccionarPersonatge() {

    }

    private static void identificarJugador(Scanner scanner) {
        System.out.print("Introdueix el nom: ");
        String nomJugador = scanner.next();
        // ???
        // Profit
    }
    private static int demanarOpcio(Scanner scanner) {
        mostrarMenu();
        return scanner.nextInt();
    }

    static void mostrarMenu() {
        System.out.println("-- Introdueix una opció --");
        System.out.println("[0] Sortir");
        System.out.println("[1] Preparar la participació");
        System.out.println("[2] Fer moure un vehicle");
        System.out.println("[3] Mostrar situació de la carrera");
        System.out.print("La teva elecció: ");
    }

    public static void llegirArxiuPersonatges(Map<String, Jugador> jugadors, Map<String, Personatge> personatges, String nomArxiu) {

        try (BufferedReader br = new BufferedReader(new FileReader(nomArxiu))) {
            String linia;
            boolean llegintJugadors = true;

            while ((linia = br.readLine()) != null) {
                if (linia.startsWith("#")) {
                    // Si la línea comienza con '#', es un encabezado y se debe omitir.
                    llegintJugadors = false;
                    continue;
                }
                if (llegintJugadors) {
                    Jugador jugador = new Jugador(linia);
                    jugadors.put(linia, jugador);
                } else {
                    Personatge personatge = new Personatge(linia);
                    personatges.put(linia, personatge);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: No s'ha pogut llegir l'arxiu " + nomArxiu);
        }
    }
}