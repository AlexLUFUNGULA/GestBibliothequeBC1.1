/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author Alex LUFUNGULA
 */
public abstract class ModelDAO<T> {
    //
    public abstract boolean ajoutB(T obj);
    //
    public abstract boolean modifB(T obj);
    //
    public abstract boolean supprimer(T obj);
    //
    public abstract Object[][] trouverTout();
    //
    public abstract Object[][] trouverToutParNom(Object nom);
    //
    public abstract T trouverID(T obj);
}
