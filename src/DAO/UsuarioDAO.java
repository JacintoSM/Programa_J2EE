package DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Beans.Usuario;

public class UsuarioDAO {

	AgenteConexion agente;
	
	public UsuarioDAO(){
	agente=AgenteConexion.getAgente();
	crearTablaUsuarios();
	}

	public void crearTablaUsuarios(){
		Statement st= agente.getStatement();
		String query ="CREATE TABLE IF NOT EXISTS usuarios(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(25), apellidos VARCHAR(50),pass VARCHAR(16), fecha_nacimiento DATE)";
		try{
			st.execute(query);
		}catch(SQLException e){
			System.out.println("Error al crear la tabla de usuarios");
			System.err.println(e.getMessage());
		}
	}
	
	public boolean registrarUsuario(Usuario user) {
		Statement st= agente.getStatement();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha =sdf.format(user.getFecha_nacimiento().getTime());
		String query= "insert into usuarios values(null,'"+user.getNombre()+"','"+user.getApellidos()+"','"+user.getPass()+"','"+fecha+"')";
		try{
		if(st.executeUpdate(query)>0)
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	public Usuario recuperarUsuarioByNombre(String nombre){
		Statement st= agente.getStatement();
		String query= "Select * from usuarios  where nombre= '"+nombre+"'";
		ResultSet rs;
		try {
			rs = st.executeQuery(query);
			Usuario recuperado = new Usuario();
		
		if(rs.next()){
			recuperado.setNombre(rs.getString("nombre"));
			recuperado.setApellidos(rs.getString("apellidos"));
			recuperado.setId(rs.getInt("id"));
			recuperado.setPass(rs.getString("pass"));
			recuperado.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
			return recuperado;
		}
		} catch (SQLException e) {
			System.out.println("Error al recuperar Usuario por nombre");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Usuario> recuperarTodos(){
		Statement st= agente.getStatement();
		String query= "Select * from usuarios";
		ArrayList<Usuario> recuperados= new ArrayList<Usuario>();
		ResultSet rs;
		try {
			rs = st.executeQuery(query);
	
		while(rs.next()){
			Usuario recuperado = new Usuario();
			recuperado.setNombre(rs.getString("nombre"));
			recuperado.setApellidos(rs.getString("apellidos"));
			recuperado.setId(rs.getInt("id"));
			recuperado.setPass(rs.getString("pass"));
			recuperado.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
			recuperados.add(recuperado);
		}
		} catch (SQLException e) {
			System.out.println("Error al recuperar Usuario por nombre");
			e.printStackTrace();
		}
		return recuperados;
	}

	public void cerrarConexion() {
		agente.cerrarConexion();
		
	}

	public boolean modificarUsuario(Usuario aModificar) {
		Statement st= agente.getStatement();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha =sdf.format(aModificar.getFecha_nacimiento().getTime());
		String query= "update usuarios set nombre='"+aModificar.getNombre()+"', apellidos='"+aModificar.getApellidos()+"', pass='"+aModificar.getPass()+"',fecha_nacimiento='"+fecha+"' "
				+ "where id="+aModificar.getId();
		try{
		if(st.executeUpdate(query)>0)
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}

	public boolean eliminarUsuario(Usuario aEliminar) {
		Statement st= agente.getStatement();
		String query= "delete from usuarios where id="+aEliminar.getId();
		try{
		if(st.executeUpdate(query)>0)
			return true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
		return false;
		
	}
	
	public void mostrarMedata(){
		try{
		DatabaseMetaData dbmd=agente.getConexion().getMetaData();
		System.out.println("tipo de base de datos: " + dbmd.getDatabaseProductName());
		System.out.println("versión: " + dbmd.getDatabaseProductVersion());
		System.out.println("nombre del driver: " + dbmd.getDriverName());
		System.out.println("versión del driver: " + dbmd.getDriverVersion());
		System.out.println("nombre del usuario: " + dbmd.getUserName());
		System.out.println("url de conexión: " + dbmd.getURL());
		ResultSet rs=dbmd.getTables(null,null,"%",null);
		System.out.println("estructura de la base de datos");
		System.out.println("base de datos\tesquema\tnombre de tabla\ttipo de tabla");
		while(rs.next())
		{
		for (int i = 1; i <=4 ; i++)
		{
		System.out.print(rs.getString(i)+"\t");
		}
		System.out.println();
		}
		}catch(Exception e){
			System.out.println("ERRRRROOOOORRR");
		}
	}
}