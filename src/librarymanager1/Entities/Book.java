/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager1.Entities;

/**
 *
 * @author Alex LUFUNGULA
 */
public class Book {
    //
    private int IdBk;
    private String nameBook;
    private String authorBook;
    //

    public int getIdBk() {
        return IdBk;
    }

    public void setIdBk(int IdBk) {
        this.IdBk = IdBk;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }
    
    
}
