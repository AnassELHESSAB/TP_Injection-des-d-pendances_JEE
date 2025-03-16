package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {

        DaoImpl dao = new DaoImpl();

        /*
            Injection des dépendances par Instanciation Statique avec le Setter
        */
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println("Result: " + metier.calcul());

        /*
            Injection des dépendances par Instanciation Statique avec le Constructeur
        */
        MetierImpl metier1 = new MetierImpl(dao);
        System.out.println("Result: " + metier1.calcul());

    }
}
