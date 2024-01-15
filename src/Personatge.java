import java.util.Random;
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
        Random random = new Random();
        return (int) (v.getVelocitatMaxima() + v.getAdherencia() + 1 + random.nextDouble() * _nom.length());
    }

    @Override
    public String toString() {
        return _nom;
    }
}
