public class Vehicle {
    private String _nom;
    private int _velocitatMaxima;
    private int _adherencia;

    public Vehicle(){
        _nom = "Default";
        _velocitatMaxima = 0;
        _adherencia = 0;
    }

    public void avança(){

    }

    public void frena(){

    }

    @Override
    public String toString() {
        return _nom;
    }
}
