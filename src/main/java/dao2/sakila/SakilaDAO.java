package dao2.sakila;

import dao.beans.Actor;
import dao2.CommonDAO;

import java.util.List;

public interface SakilaDAO extends CommonDAO<Actor> {
    List<Actor> selectByFirstName(String firstName);
    List<Actor> selectByLastName(String lastName);
}
