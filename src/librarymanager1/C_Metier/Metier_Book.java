/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager1.C_Metier;

import DAO.BookDAO;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import librarymanager1.Entities.Book;
import librarymanager1.pkg1.ConnectionSQLite;

/**
 *
 * @author Alex LUFUNGULA
 */
public class Metier_Book {

    //
    public void afficheBookDemarage(JFrame frameM, JTable tableB) {
        //
        try {
            BookDAO bkDAO = new BookDAO();
            String[][] tabRet = (String[][])bkDAO.trouverTout();
            //
            tableB.setModel(new javax.swing.table.DefaultTableModel(
                    tabRet,
                    new String[]{
                        "Title of the book", "Name of the author", "Number order"
                    }
            ));
            //
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameM, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //
    public void afficheBookLIKE(JFrame frameM, JTable tableB, JTextField tfdRech) {
        //
        try {
            //
            BookDAO bkDAO = new BookDAO();
            String[][] tabRet = (String[][])bkDAO.trouverToutParNom(tfdRech.getText());
            //
            tableB.setModel(new javax.swing.table.DefaultTableModel(
                    tabRet,
                    new String[]{
                        "Title of the book", "Name of the author", "Number order"
                    }
            ));
            //
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameM, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editBook(JTable tabBooks, JFrame frameM, JTextField jTfBook, JTextField jTfAuthor) {
        //
        Book bkNew = new Book();
        int rewI = tabBooks.getSelectedRow();
        int IdBks = Integer.parseInt(tabBooks.getValueAt(rewI, 2).toString());
        //
        bkNew.setIdBk(IdBks);
        bkNew.setNameBook(jTfBook.getText());
        bkNew.setAuthorBook(jTfAuthor.getText());
        //
        try {
            BookDAO bkDAO = new BookDAO();
            bkDAO.modifB(bkNew);
            JOptionPane.showMessageDialog(frameM, "Great, you have UPDATED one book", "Success", JOptionPane.INFORMATION_MESSAGE);
            //
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(frameM, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Transfert les données du tableau vers les TxtFld
    public void formModBook(JTable tabBooks, JTextField jTfBook, JTextField jTfAuthor, JFrame frameM) {
        //
        int rewI = tabBooks.getSelectedRow();
        //
        jTfBook.setText(tabBooks.getValueAt(rewI, 0).toString());
        jTfAuthor.setText(tabBooks.getValueAt(rewI, 1).toString());
        //
    }

    public void suppressionB(JTable tabBooks, JFrame frameM, JTextField jTfBook, JTextField jTfAuthor) {
        //On doit supprimer par le nom mais il est probable d'avoir plusieurs livre avec un seule nom, cela revien à supprimer selon l'ID
        Book bkNew = new Book();
        int rewI = tabBooks.getSelectedRow();
        int IdBks = Integer.parseInt(tabBooks.getValueAt(rewI, 2).toString());
        //
        bkNew.setIdBk(IdBks);
        bkNew.setNameBook(jTfBook.getText());
        bkNew.setAuthorBook(jTfAuthor.getText());
        //
        try {
            if (FindBook(bkNew.getIdBk())) {
                BookDAO bkDAO = new BookDAO();
                //
                bkDAO.supprimer(bkNew);
            }
            JOptionPane.showMessageDialog(frameM, "Great, you have removed one book", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameM, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Par conséquand cela revient egalement à trouver par l'ID
    private boolean FindBook(int IdBook) {
        //
        boolean testFBk = false;
        //
        Book bk = new Book();
        bk.setIdBk(IdBook);
        BookDAO bkDAO = new BookDAO();
        bkDAO.trouverID(bk);
        //
        if (bkDAO.trouverID(bk)!=null) {
            testFBk = true;
        }
        //
        return testFBk;
    }

    public void insertB(Book newBook, JFrame frameM) {
        //
        try {
            //
            BookDAO bookDAO = new BookDAO();
            bookDAO.ajoutB(newBook);
            //
            JOptionPane.showMessageDialog(frameM, "Great, you have saved a new book", "Success", JOptionPane.INFORMATION_MESSAGE);
            //
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameM, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //
    }

//    private String[][] tabOrdonnE(Book bk) {
//        String[][] tabRet;
//        int nElt;
//        int comparisonResult = 0;
//        List<Book> bList = new ArrayList<>();
//        //
//        BookDAO bkDAO = new BookDAO();
//        //
//        if (bk == null) {
//            bList = bkDAO.trouverTout();
//        } else {
//            bList = bkDAO.trouverToutParNom(bk.getNameBook());
//        }
//        nElt = bList.size();
//        tabRet = new String[nElt][3];
//        String stgTampB = "";
//        String stgTampA = "";
//        String idBkStrg = "";
//        //Recupere les elts de la BD et les met dans un tableau
//        for (int i = 0; i < bList.size(); i++) {
//            tabRet[i][0] = bList.get(i).getNameBook();
//            tabRet[i][1] = bList.get(i).getAuthorBook();
//            tabRet[i][2] = String.valueOf(bList.get(i).getIdBk());
//        }
//        //Ordonne les elts du tableau
//        for (int i = 1; i < tabRet.length; i++) {
//            //
//            for (int j = i; j < tabRet.length; j++) {
//                comparisonResult = tabRet[j - 1][0].compareTo(tabRet[j][0]);
//                //
//                if (comparisonResult > 0) {
//                    stgTampB = tabRet[j][0];
//                    stgTampA = tabRet[j][1];
//                    idBkStrg = tabRet[j][2];
//                    //
//                    tabRet[j][0] = tabRet[j - 1][0];
//                    tabRet[j][1] = tabRet[j - 1][1];
//                    tabRet[j][2] = tabRet[j - 1][2];
//                    //
//                    tabRet[j - 1][0] = stgTampB;
//                    tabRet[j - 1][1] = stgTampA;
//                    tabRet[j - 1][2] = idBkStrg;
//                }
//                //
//            }
//        }
//        //
//        return tabRet;
//    }
}
