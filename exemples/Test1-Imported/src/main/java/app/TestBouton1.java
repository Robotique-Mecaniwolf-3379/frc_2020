package app;

import lab_robotique.BaseComputePeriodicBoolean;
//import sun.font.TrueTypeFont;

public class TestBouton1 extends BaseComputePeriodicBoolean {
    private boolean _derniereEntree;
    private boolean _derniereSortie;

    public TestBouton1() {
    }
    // ...

    @Override
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
