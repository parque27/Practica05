import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, Jugador> jugadors = new HashMap<>();
        Map<String, Personatge> personatges = new HashMap<>();
        Map<Jugador,VehicleEnCursa> vehiclesEnCursa = new HashMap<>();

        Map<String, ControlCursa> curses = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        //Carregar Jugadors i Personatges des d'un txt
        llegirArxiuPersonatges(jugadors,personatges, "src/carrega/personatges.txt");
        System.out.println("[Jugadors i Personatges carregats OK!]");

        llegirArxiuCurses(curses, "src/carrega/curses.txt");
        System.out.println("[Curses carregades OK!]");

        //Preparar participació dels jugadors
        prepararParticipacio(curses, jugadors, personatges, scanner, vehiclesEnCursa);

        int opcio;
        do {
            opcio = demanarOpcio(scanner);
            switch (opcio) {
                // Iniciar cursa
                case 1:
                    List<String> cursasListasParaIniciar = new ArrayList<>();

                    curses.forEach((nom, control) -> {
                        if (control.getEstado() == ControlCursa.EstatCursa.LISTA_PARA_INICIAR) {
                            cursasListasParaIniciar.add(nom);
                        }
                    });

                    // Mostrar las cursas listas para iniciar
                    System.out.println("Curses listas para iniciar:");
                    for (int i = 0; i < cursasListasParaIniciar.size(); i++) {
                        System.out.println("[" + i + "] " + cursasListasParaIniciar.get(i));
                    }

                    // Pedir al usuario que elija una cursa
                    System.out.print("Elige una cursa para iniciar: ");
                    int eleccionCursa = scanner.nextInt();

                    // Validar la elección del usuario
                    if (eleccionCursa < 0 || eleccionCursa >= cursasListasParaIniciar.size()) {
                        System.out.println("Selección no válida. Elige nuevamente.");
                        break;
                    }

                    menuCursa(curses.get(cursasListasParaIniciar.get(eleccionCursa)));

                    break;
                // Mostrar curses amb vehicles
                case 2:
                    curses.forEach((nom,cursa)->{
                        System.out.println(cursa);
                    });
                    break;
                default: break;
            }
        }
        while (opcio != 0);
    }

    private static void menuCursa(ControlCursa control){

        Scanner scanner = new Scanner(System.in);
        int opcio = 1;

        List<Jugador> jugadores = control.llistaJugadors();

        do {
            for (Jugador jugador : jugadores) {
                if(!control.isCursaAcabada()){
                    System.out.println("Turno de " + jugador + ":");
                    System.out.println("[1] Avanzar");
                    System.out.println("[2] Frenar");
                    System.out.print("Selecciona una opción: ");
                    opcio = scanner.nextInt();

                    switch (opcio) {
                        case 1:
                            // Avanzar
                            control.avança(jugador);
                            System.out.println("Avanzando...");
                            break;
                        case 2:
                            // Frenar
                            control.frenar(jugador);
                            System.out.println("Frenando...");
                            break;
                        default:
                            System.out.println("Opción no válida. Elige nuevamente.");
                            break;
                    }

                    control.classificacio();
                }

            }

        } while (!control.isCursaAcabada());

    }

    private static void prepararParticipacio(Map<String, ControlCursa> curses, Map<String, Jugador> jugadors, Map<String, Personatge> personatges, Scanner scanner, Map<Jugador,VehicleEnCursa> vehiclesEnCursa){
        jugadors.forEach((nom,jugador) -> {
            System.out.println("Torn del jugador: " + jugador);

            ControlCursa control = escollirCursa(curses, scanner);

            String nomPersonatge = escollirPersonatge(personatges, scanner);
            Vehicle vehicle = escollirVehicle(scanner);
            VehicleEnCursa vehicleEnCursa = new VehicleEnCursa(jugador,vehicle, personatges.get(nomPersonatge));

            vehiclesEnCursa.put(jugador,vehicleEnCursa);

            control.afegirVehicles(vehiclesEnCursa);
        });
    }

    private static ControlCursa escollirCursa(Map<String,ControlCursa> curses, Scanner scanner) {

        ControlCursa cursa = null;
        List<String> cursesDisponibles = new ArrayList<>();

        int i = 0;
        for (Map.Entry<String, ControlCursa> entry : curses.entrySet()) {
            ControlCursa cursaActual = entry.getValue();
            if (!cursaActual.isCursaAcabada()) {
                System.out.println("[" + i + "] " + entry.getKey());
                cursesDisponibles.add(entry.getKey());
                i++;
            }
        }

        System.out.print("Escull una cursa: ");
        int eleccion = scanner.nextInt();

        // Validar la elección del usuario
        if (eleccion < 0 || eleccion >= cursesDisponibles.size()) {
            System.out.println("Selección no válida. Elige nuevamente.");
            escollirCursa(curses, scanner);  // Recursión para repetir la selección
        }

        String nomCursaSeleccionada = cursesDisponibles.get(eleccion);
        cursa = curses.get(nomCursaSeleccionada);

        return cursa;
    }

    private static Vehicle escollirVehicle(Scanner scanner) {
        int opcio;
        Vehicle v;

        System.out.println("Selecciona un vehicle:");
        System.out.println("[0] Biga");
        System.out.println("[1] Cavall");
        System.out.println("[2] Quadriga");

        System.out.print("Escull un vehicle: ");
        opcio = scanner.nextInt();

        switch (opcio) {
            case 0:
                v  = new Biga();
                return v;
            case 1:
                v  = new Cavall();
                return v;
            case 2:
                v  = new Quadriga();
                return v;
            default:
                v  = new Biga();
                System.out.println("Opción no válida. Seleccionando Biga por defecto.");
                return v; // O puedes manejarlo de otra manera, como lanzar una excepción.
        }
    }

    private static String escollirPersonatge(Map<String, Personatge> personatges, Scanner scanner) {
        List<String> personatgesDisponibles = new ArrayList<>();

        String nomPersonatge = "";

        // Mostrar personajes disponibles y almacenar sus nombres en una lista
        int i = 0;
        System.out.println("Personajes disponibles:");
        for (Map.Entry<String, Personatge> entry : personatges.entrySet()) {
            Personatge personatge = entry.getValue();
            if (!personatge.is_estaEscollit()) {
                nomPersonatge = entry.getKey();
                System.out.println("[" + i + "] " + nomPersonatge);
                personatgesDisponibles.add(nomPersonatge);
                i++;
            }
        }

        // Pedir al usuario que elija un personaje
        System.out.print("Elige un personaje (ingresa el número correspondiente): ");
        int eleccion = scanner.nextInt();

        // Validar la elección del usuario
        if (eleccion < 0 || eleccion >= personatgesDisponibles.size()) {
            System.out.println("Selección no válida. Elige nuevamente.");
            return escollirPersonatge(personatges, scanner);  // Recursión para repetir la selección
        }
        else
            personatges.get(personatgesDisponibles.get(eleccion)).set_estaEscollit(true);

        return personatgesDisponibles.get(eleccion);
    }

    private static int demanarOpcio(Scanner scanner) {
        mostrarMenu();
        return scanner.nextInt();
    }

    static void mostrarMenu() {
        System.out.println("-- Introdueix una opció --");
        System.out.println("[0] Sortir");
        System.out.println("[1] Iniciar cursa");
        System.out.println("[2] Mostrar curses disponibles");
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
    public static void llegirArxiuCurses(Map<String, ControlCursa> curses, String nomArxiu){
        try (BufferedReader br = new BufferedReader(new FileReader(nomArxiu))) {
            String linea;
            String nom;
            int nVoltes;
            int distancia;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en nombre de la carrera y número de vueltas
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    nom = partes[0].trim();
                    nVoltes = Integer.parseInt(partes[1].trim());
                    distancia = Integer.parseInt(partes[2].trim());
                    // Crear instancia de Cursa y agregarla al ControlCursa
                    Cursa cursa = new Cursa(nVoltes, nom, distancia);
                    ControlCursa controlCursa = new ControlCursa(cursa);
                    curses.put(nom,controlCursa);
                } else {
                    System.out.println("Error en el formato de línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: No s'ha pogut llegir l'arxiu " + nomArxiu);
        }
    }
}