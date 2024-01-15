import java.util.*;

public class ControlCursa {
    public enum EstatCursa {
        APUNTANDO_JUGADORES,
        LISTA_PARA_INICIAR,
        ACABADA
    }

    private Map<Jugador,VehicleEnCursa> _vehiclesEnCursa;
    private Cursa _cursa;

    private EstatCursa estatCursa;

    public ControlCursa(Cursa cursa){
        _vehiclesEnCursa = new HashMap<>();
        estatCursa = EstatCursa.APUNTANDO_JUGADORES;
        this._cursa = cursa;
    }

    public EstatCursa getEstado() {
        return estatCursa;
    }

    public void classificacio(){
        List<VehicleEnCursa> vehiclesOrdenados = new ArrayList<>(_vehiclesEnCursa.values());

        // Ordenar los vehiclesEnCursa por posición
        vehiclesOrdenados.sort(Comparator.comparingInt(VehicleEnCursa::posicio).reversed());

        // Mostrar la clasificación
        System.out.println("Clasificación de la carrera:");

        for (int i = 0; i < vehiclesOrdenados.size(); i++) {
            VehicleEnCursa vehicleEnCursa = vehiclesOrdenados.get(i);
            System.out.println("Posición " + (i + 1) + ": " + vehicleEnCursa +
                    " - Distancia Recorrida: " + vehicleEnCursa.getDistanciaRecorreguda() +
                    " - Vueltas: " + vehicleEnCursa.getVoltesActuals());
        }
    }

    public void avança(Jugador jugador) {
        VehicleEnCursa vehicleEnCursa = _vehiclesEnCursa.get(jugador);
        if (vehicleEnCursa != null) {
            int velocidad = vehicleEnCursa.calcularVelocidad();
            vehicleEnCursa.avança(velocidad, _cursa);

            if (vehicleEnCursa.getVoltesActuals() == _cursa.getNumVoltes()) {
                // Se ha completado la distancia total, la cursa ha acabado
                estatCursa = EstatCursa.ACABADA;
                System.out.println("¡La cursa ha terminado!");
                System.out.println("Ganador: " + jugador);
            }
        }
    }

    public void frenar(Jugador jugador) {
        VehicleEnCursa vehicleEnCursa = _vehiclesEnCursa.get(jugador);
        if (vehicleEnCursa != null) {
            int frenado = vehicleEnCursa.calcularFrenado();
            vehicleEnCursa.frenar(frenado, _cursa);
        }
    }

    public boolean isCursaAcabada(){ return estatCursa == EstatCursa.ACABADA;}

    public void afegirVehicles(Map<Jugador,VehicleEnCursa> vehiclesEnCursa){
        this._vehiclesEnCursa.putAll(vehiclesEnCursa);
        if(_vehiclesEnCursa.size() >= 2)
            estatCursa = EstatCursa.LISTA_PARA_INICIAR;
    }

    public List<Jugador> llistaJugadors(){

        return new ArrayList<>(_vehiclesEnCursa.keySet());
    }

    @Override
    public String toString() {
        return _cursa + "\n" + _vehiclesEnCursa.toString().replace(',',' ').replace('{',' ').replace('}',' ');
    }
}
