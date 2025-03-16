package pres;

import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresentationV2 {

    public static void main(String[] args) {
        try {
            /*
                Injection des dépendances par Instanciation Dynamique
            */
            Scanner scanner = new Scanner(new File("config.txt"));
            String daoClassname = scanner.nextLine();
            Class cDao = Class.forName(daoClassname);
            IDao dao = (IDao) cDao.getConstructor().newInstance();

            // Via constructeur
            String metierClassname = scanner.nextLine();
            Class cMetier = Class.forName(metierClassname);
            IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);

            // Via setter
            IMetier metier2 = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);
            Method setDao = cMetier.getDeclaredMethod("setDao", IDao.class);
            setDao.invoke(metier2, dao);

            System.out.println("Resultat: " + metier.calcul());
            System.out.println("Resultat: " + metier2.calcul());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
 