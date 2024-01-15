public class Vehicle {
    private String _nom;
    private int _velocitatMaxima;
    private int _adherencia;

    public Vehicle(String nom, int velocitatMaxima, int adherencia) {
        _nom = nom;
        _velocitatMaxima = velocitatMaxima;
        _adherencia = adherencia;
    }

    @Override
    public String toString() {
        return _nom;
    }

    public int getVelocitatMaxima() {
        return _velocitatMaxima;
    }

    public int getAdherencia() {
        return _adherencia;
    }
}
