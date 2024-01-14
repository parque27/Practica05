public class Personatge {

    public String _nom;

    public Personatge(String nom){
        _nom = nom;
    }

    public int nivellAdaptacio(Vehicle v){
        return 0;
    }

    @Override
    public String toString() {
        return _nom;
    }
}
