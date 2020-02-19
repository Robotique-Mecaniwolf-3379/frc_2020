package app;

import lab_robotique.BaseComputePeriodicBoolean;

public class TestBouton1 extends BaseComputePeriodicBoolean
{
    private boolean _derniereEntree;

    public TestBouton1()
    {
        // ...
    }

    @Override
    public boolean compute(boolean entree) {
        // La valeur de sortie c'est la précédente valeur de l'entrée
        boolean sortie = _derniereEntree;

        _derniereEntree = entree;
        return sortie;
    }

}
