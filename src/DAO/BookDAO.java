/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import librarymanager1.Entities.Book;
import librarymanager1.pkg1.ConnectionSQLite;

/**
 *
 * @author Alex LUFUNGULA
 */
public class BookDAO extends ModelDAO<Book> {

    @Override
    public boolean ajoutB(Book obj) {
        //
        boolean etatAjout = false;
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "INSERT INTO book(name_book, author_book) VALUES ('" + obj.getNameBook() + "', '" + obj.getAuthorBook() + "')";
        //
        if (connSQLite.UpDatetEXE(requett)) {
            etatAjout = true;
        }
        return etatAjout;
    }

    @Override
    public boolean modifB(Book obj) {
        //
        boolean etaModif = false;
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "UPDATE book SET name_book='" + obj.getNameBook() + "',author_book='" + obj.getAuthorBook() + "' WHERE id_book ='" + obj.getIdBk() + "'";
        //
        if (connSQLite.UpDatetEXE(requett)) {
            etaModif = true;
        }
        return etaModif;
    }

    @Override
    public boolean supprimer(Book obj) {
        //
        boolean etatSupr = false;
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "DELETE FROM book WHERE id_book ='" + obj.getIdBk() + "'";
        //
        if (connSQLite.UpDatetEXE(requett)) {
            etatSupr = true;
        }
        return etatSupr;
    }

    @Override
    public Object[][] trouverTout() {
        Object[][] listBook;
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "SELECT * FROM book";
        //
        listBook = connSQLite.SelectEXE(requett);
        //
        return listBook;
    }

    @Override
    public Object[][] trouverToutParNom(Object nom) {
        Object[][] listBook;
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "SELECT * FROM book WHERE name_book LIKE '%"+nom+"%'";
        //
        listBook = connSQLite.SelectEXE(requett);
        //
        return listBook;
    }

    @Override
    public Book trouverID(Book obj) {
        //
        Book bk = new Book();
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "SELECT * FROM book WHERE name_book ='"+obj.getIdBk()+"'";
        //
        bk = connSQLite.SelectEXE1(requett);
        //
        return bk;
    }

}
