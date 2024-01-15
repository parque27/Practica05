public class Personatge {

    private String _nom;
    private boolean _estaEscollit = false;

    public Personatge(String nom){
        _nom = nom;
    }

    public void set_estaEscollit(boolean _estaEscollit) {
        this._estaEscollit = _estaEscollit;
    }

    public boolean is_estaEscollit(){ return _estaEscollit;}

    public int nivellAdaptacio(Vehicle v){
        return 0;
    }

    @Override
    public String toString() {
        return _nom;
    }
}
