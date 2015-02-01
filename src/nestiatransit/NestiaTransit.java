/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestiatransit;

/**
 *
 * @author Allen
 */
public class NestiaTransit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBManager DB = new DBManager();
        DB.executeSQL();
    }
    
}
