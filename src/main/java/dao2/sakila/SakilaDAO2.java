package dao2.sakila;

import dao.beans.Actor;

import java.util.List;

public interface SakilaDAO2 extends SakilaDAO{

    List<Actor> selectByConditions();
}
