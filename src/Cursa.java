import javax.naming.ldap.Control;
import java.util.Map;

public class Cursa {
    private int _numVoltes;
    private int _distancia;
    private String _nom;

    public Cursa(){
        _nom = "Default";
        _distancia = 0;
        _numVoltes = 0;
    }
    public Cursa(int _numVoltes, String _nom, int _distancia) {
        this._numVoltes = _numVoltes;
        this._nom = _nom;
        this._distancia = _distancia;
    }

    public int getNumVoltes() {
        return _numVoltes;
    }

    public int getDistancia() {
        return _distancia;
    }

    public String getNom() {
        return _nom;
    }

    @Override
    public String toString() {
        return "[" + _nom + " - Distancia: " + _distancia + " - Vueltas: " + _numVoltes +"]";
    }
}
