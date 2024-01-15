import javax.naming.ldap.Control;
import java.util.Map;

public class Cursa {
    private int _numVoltes;
    private String _nom;

    public Cursa(){
        _nom = "Default";
        _numVoltes = 0;
    }
    public Cursa(int _numVoltes, String _nom) {
        this._numVoltes = _numVoltes;
        this._nom = _nom;
    }


    @Override
    public String toString() {
        return "[" + _nom + "]";
    }
}
