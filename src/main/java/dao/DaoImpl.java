package dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class DaoImpl implements IDao {

    public DaoImpl() {
    }

    @Override
    public double getData() {
        System.out.println("Récuperation de la base de donnée");
        return 1.0;
    }
}
