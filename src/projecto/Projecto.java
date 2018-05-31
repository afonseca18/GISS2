
package projecto;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Projecto {

    
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException, ClassNotFoundException {
        EcraInicial inicio = new EcraInicial();
        inicio.setVisible(true);
    }
    
}
