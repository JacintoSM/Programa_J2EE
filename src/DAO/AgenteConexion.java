package DAO;
import java.sql.*;
public class AgenteConexion {
	
protected static AgenteConexion mInstancia=null;
protected Connection conn;

protected AgenteConexion() throws Exception {
	Class.forName("com.mysql.jdbc.Driver");
	conn=DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
}

public static AgenteConexion getAgente(){
	if (mInstancia==null) {
		try{
		mInstancia=new AgenteConexion();
	}catch(ClassNotFoundException cnfe){
		System.out.println("Driver JBDC no encontrado");
		cnfe.printStackTrace();
		}catch(SQLException sqle){
		System.out.println("Error al conectarse a la BD");
		sqle.printStackTrace();
		}catch(Exception e){
		System.out.println("Error general");
		e.printStackTrace();
		}
		
	}
	return mInstancia;
	}

public Connection getConexion(){
	return conn;
}

public Statement getStatement(){
	Statement st=null;
	try {
		 st= conn.createStatement();
	} catch (SQLException e) {
		System.out.println("Error al crear el statement");
		e.printStackTrace();
		
	}
	return st;
}
public void cerrarConexion(){
	try{
	conn.close();
	}catch(SQLException e){
		System.out.println("Error al cerrar la conexion con al base de datos");
		System.out.println(e.getStackTrace());
	}
}
}


