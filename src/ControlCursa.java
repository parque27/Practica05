import java.util.*;

public class ControlCursa {

    public enum EstatCursa {
        APUNTANDO_JUGADORES,
        LISTA_PARA_INICIAR,
        EN_CURSO,
        ACABADA
    }

    private Map<Jugador,VehicleEnCursa> _vehiclesEnCursa;
    private Cursa cursa;

    private EstatCursa estatCursa;

    public ControlCursa(Cursa cursa){
        _vehiclesEnCursa = new HashMap<>();
        estatCursa = EstatCursa.APUNTANDO_JUGADORES;
        this.cursa = cursa;
    }

    public EstatCursa getEstado() {
        return estatCursa;
    }

    public void setEstado(EstatCursa estado) {
        this.estatCursa = estado;
    }

    public void classificacio(){
        List<VehicleEnCursa> vehiclesOrdenados = new ArrayList<>(_vehiclesEnCursa.values());

        // Ordenar los vehiclesEnCursa por posición
        vehiclesOrdenados.sort(Comparator.comparingInt(VehicleEnCursa::posicio));

        // Mostrar la clasificación
        System.out.println("Clasificación de la carrera:");

        for (int i = 0; i < vehiclesOrdenados.size(); i++) {
            VehicleEnCursa vehicleEnCursa = vehiclesOrdenados.get(i);
            System.out.println("Posición " + (i + 1) + ": " + vehicleEnCursa);
        }
    }

    public void acabarCursa() {
        estatCursa = EstatCursa.ACABADA;
    }

    public boolean isCursaAcabada(){ return estatCursa == EstatCursa.ACABADA;}

    public void afegirVehicles(Map<Jugador,VehicleEnCursa> vehiclesEnCursa){
        this._vehiclesEnCursa.putAll(vehiclesEnCursa);
        if(_vehiclesEnCursa.size() >= 2)
            estatCursa = EstatCursa.LISTA_PARA_INICIAR;
    }

    @Override
    public String toString() {
        return cursa + "\n" + _vehiclesEnCursa.toString().replace(',',' ').replace('{',' ').replace('}',' ');
    }
}
