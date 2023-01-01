package dao.factory;

import dao.Sakila.SakilaDAO;

public interface SakilaDAOFactory {

    SakilaDAO getInstance();
    <T extends SakilaDAO> T getInstance(Class<T> implClazz);
}
