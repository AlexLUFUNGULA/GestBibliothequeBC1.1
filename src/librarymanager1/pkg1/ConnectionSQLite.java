/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager1.pkg1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import librarymanager1.Entities.Book;

/**
 *
 * @author Alex LUFUNGULA
 */
public class ConnectionSQLite {

    private Connection conn = null;
    private Statement st = null;
    private ResultSet rst = null;

    public ConnectionSQLite() {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:gest_biblio_db.sqlite");
            //
        } catch (Exception e) {
            System.out.println("Erreur :: ConnectionSQLite:: " + e.getMessage());
        }
    }

    public Object[][] SelectEXE(String Requett) {
        Object[][] data = new Object[1][3];
        int nbrLign = 0;
        int comparisonResult = 0;
        try {
            st = conn.createStatement();
            rst = st.executeQuery(Requett);
            while (rst.next()) {
                nbrLign++;
            }
            data = new Object[nbrLign][3];
            //
            st = conn.createStatement();
            rst = st.executeQuery(Requett);
            //
            data = new String[nbrLign][3];
            String stgTampB = "";
            String stgTampA = "";
            String idBkStrg = "";
            //Recupere les elts de la BD et les met dans un tableau
            int compt = 0;
            while (rst.next()) {
                data[compt][0] = rst.getString("name_book");
                data[compt][1] = rst.getString("author_book");
                data[compt][2] = rst.getString("id_book");
                //
                compt++;
            }

            //Ordonne les elts du tableau
            for (int i = 1; i < data.length; i++) {
                //
                for (int j = i; j < data.length; j++) {
                    comparisonResult = data[j - 1][0].toString().compareTo((data[j][0]).toString());
                    //
                    if (comparisonResult > 0) {
                        stgTampB = data[j][0].toString();
                        stgTampA = data[j][1].toString();
                        idBkStrg = data[j][2].toString();
                        //
                        data[j][0] = data[j - 1][0];
                        data[j][1] = data[j - 1][1];
                        data[j][2] = data[j - 1][2];
                        //
                        data[j - 1][0] = stgTampB;
                        data[j - 1][1] = stgTampA;
                        data[j - 1][2] = idBkStrg;
                    }
                    //
                }
            }

        } catch (SQLException e) {

        }
        return data;
    }

    public boolean UpDatetEXE(String Requett) {
        //
        boolean testUpdt = false;
        //
        List<Book> listBOOK = new ArrayList<>();
        Book bk = new Book();
        //
        
        try {
            st = conn.createStatement();
            int intRes = st.executeUpdate(Requett);
            //
            //
            System.out.println("Ici == "+intRes);
            if (intRes > 0) {
                testUpdt = true;
            }//
            //
        } catch (SQLException e) {
            System.out.println("Ereur::UpDatetEXE :: " + e.getMessage());
        }
        //
        return testUpdt;
        //  
    }
    //

    public Book SelectEXE1(String Requett) {
        //
        Book bk = new Book();
        //
        try {
            st = conn.createStatement();
            rst = st.executeQuery(Requett);
            //
            st = conn.createStatement();
            rst = st.executeQuery(Requett);
            //
            while (rst.next()) {
                bk.setNameBook(rst.getString("name_book"));
                bk.setAuthorBook(rst.getString("author_book"));
                bk.setIdBk(Integer.parseInt(rst.getString("id_book")));
                //
            }
        } catch (SQLException e) {
            System.out.println("Ereur::SelectEXE1 :: " + e.getMessage());
        }
        return bk;
    }
}
