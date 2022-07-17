
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Persistencia {
 
    private Connection cn;
    private ResultSet rs; //recibe los datos de la BD
    private PreparedStatement ps; //recibe la orden del select a la BD y trae la devolución
   // private ResultSetMetaData rsm;//setea los metadatos de la conexion
 
    public String servidor, basededatos, usuario, clave, ejecutar;
    
    //hacemos un metodo para generar una conexion
    
    public Connection conectarse() throws SQLException{ //al metodo conectarse() lo vamos a usar a traves de una clase para consulta SQL **
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Indicamos el driver de la BD. pide clausula try&catch, se agrega despues de aclarar servidor, bd, usuario, clave
            
               servidor = "localhost:3306/";
               basededatos = "cacproyecto2022";
               usuario = "root";
               clave = "";
         //Como nosotros usamos un Localhost, vamos a validar que la BD se conecte a pesar de los errores y sea una conexion seguro. Para eso hay que hacer una modificacion en el cn +"?autoReconnect=true&useSSL=false"
         
               cn=DriverManager.getConnection("jdbc:mysql://" + servidor + basededatos+"?", usuario, clave); //darle forma a la conexion e indicar que servicio se le va a asignar
            } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cn;
    }
    
    //** //al metodo conectarse() lo vamos a usar a traves de una clase para consulta SQL
    
    public ResultSet consultaSQL(String busqueda){ //devuelve el Select de busqueda
        try {
            ps = conectarse().prepareStatement(busqueda);//pide agregar clausulas try&catch ya que los metodos y conexiones lo necesitas (por qué?)
       
            rs = ps.executeQuery();
            //rsm = rs.getMetaData();
        } 
        catch (SQLException ex) {
                Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
    
    
