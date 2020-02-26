package frc.robot;


public class BasculeActivation  {
    private boolean _derniereEntree;
    private boolean _derniereSortie;

    public BasculeActivation() {
    }
    // ...

    
    public boolean compute(final boolean entree) {
        // La valeur de sortie c'est la précédente valeur de l'entrée
        boolean sortie;
        boolean resultat1;

        if ((entree) && (!_derniereEntree )) {
        
            resultat1 = true;
        } else {
            
            resultat1 = false;
        }

        if (resultat1) {
            sortie = !_derniereSortie;

        } else {
            sortie = _derniereSortie;
        }

        _derniereSortie = sortie;
        _derniereEntree = entree;
        return sortie;
    }
}
