public class VehicleEnCursa {

    private int _posicioEnCursa;
    private int _distanciaRecorreguda;
    private int _voltesActuals;
    private Jugador _jugador;
    private Personatge _personatge;
    private Vehicle _vehicle;

    public VehicleEnCursa(Jugador j, Vehicle v, Personatge p){
        _posicioEnCursa = 0;
        _voltesActuals = 1;
        _distanciaRecorreguda = 0;
        _jugador = j;
        _vehicle = v;
        _personatge = p;
    }

    public int posicio() {
        return _posicioEnCursa;
    }

    public int getDistanciaRecorreguda() {
        return _distanciaRecorreguda;
    }

    public int getVoltesActuals() {
        return _voltesActuals - 1;
    }

    public void avança(int velocidad, Cursa cursa) {

        // Lógica para avanzar el vehículo con la velocidad dada
        _distanciaRecorreguda += velocidad;
        actualizarPosicionEnCursa(cursa);

        // Verificar si se ha completado una vuelta
        if (_distanciaRecorreguda >= cursa.getDistancia()) {
            // Incrementar una vuelta
            incrementarVueltas();
            // Resetear la distancia con el remanente
            _distanciaRecorreguda = 0;
        }
    }

    private void incrementarVueltas() {
        // Lógica para incrementar el contador de vueltas
        _voltesActuals++;
    }

    private void actualizarPosicionEnCursa(Cursa cursa) {
        // Actualizar posición en la cursa
        _posicioEnCursa = (_distanciaRecorreguda + cursa.getDistancia() * (_voltesActuals - 1)) / cursa.getDistancia();
    }

    public void frenar(int velocidad, Cursa cursa) {
        // Lógica para frenar el vehículo con la velocidad dada
        _distanciaRecorreguda -= velocidad;
        _posicioEnCursa = _distanciaRecorreguda / cursa.getDistancia(); // Actualizar posición en la cursa

        // Verificar si la posición es menor a cero (por frenar)
        if (_distanciaRecorreguda < 0) {
            //resetearPosicion();
        }
    }

    public int calcularVelocidad() {
        int velocidadBase = _vehicle.getVelocitatMaxima();
        int adaptacionPersonatge = _personatge.nivellAdaptacio(_vehicle);
        return velocidadBase + adaptacionPersonatge;
    }

    public int calcularFrenado() {
        int adherenciaBase =  _vehicle.getAdherencia();
        int adaptacionPersonatge = _personatge.nivellAdaptacio(_vehicle);
        return adherenciaBase + adaptacionPersonatge;
    }



    @Override
    public String toString() {
        return " [Vehicle = " + _vehicle + "] [Personatge = " + _personatge + "]\n";
    }
}
