/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager1.pkg1;

import java.util.ArrayList;
import java.util.List;
import librarymanager1.Entities.Book;
import librarymanager1.IHM.LibManag1;

/**
 *
 * @author Alex LUFUNGULA
 */
public class LibraryManager11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LibManag1 formManager11= new LibManag1();
        formManager11.setVisible(true);
//        List<Book> listBook = new ArrayList<>();
        //
        ConnectionSQLite connSQLite = new ConnectionSQLite();
        String requett = "SELECT * FROM book";
        //
        Object[][] tab= connSQLite.SelectEXE(requett);
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.println(tab[i][j]);
            }
        }
    }

}
