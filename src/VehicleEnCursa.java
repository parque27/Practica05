public class VehicleEnCursa {

    private int _posicioEnCursa;
    private int _voltesActuals;
    private Jugador _jugador;
    private Personatge _personatge;
    private Vehicle _vehicle;

    public VehicleEnCursa(Jugador j, Vehicle v, Personatge p){
        _posicioEnCursa = 0;
        _voltesActuals = 0;
        _jugador = j;
        _vehicle = v;
        _personatge = p;
    }

    public int posicio() {
        return _posicioEnCursa;
    }

    @Override
    public String toString() {
        return " [Vehicle] -> " + _vehicle + " [Personatge] -> " + _personatge + "\n";
    }
}
