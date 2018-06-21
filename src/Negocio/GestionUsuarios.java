package Negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Beans.Usuario;
import DAO.UsuarioDAO;

public class GestionUsuarios {

	private UsuarioDAO uDAO;
	
	public GestionUsuarios(){
		uDAO= new UsuarioDAO();
		
	}
	
	public boolean registrarUsuario(String nombre, String apellido, String pass, Date fecha) {
		Usuario user= new Usuario(nombre, apellido, pass, fecha);
		return uDAO.registrarUsuario(user);
	}

	public boolean login(String nombre, String pass){
		Usuario user =uDAO.recuperarUsuarioByNombre(nombre);
		if(user!= null && user.getPass().equals(pass))
			return true;
		return false;
	}
	
	public ArrayList<Usuario> recuperarTodos(){
		return uDAO.recuperarTodos();
		
	}

	public void cerrarConexion() {
		uDAO.cerrarConexion();
		
	}

	public boolean modificarUsuario(Usuario aModificar) {
		return uDAO.modificarUsuario(aModificar);
		
	}

	public boolean eliminarUsuario(Usuario aEliminar) {
		return uDAO.eliminarUsuario(aEliminar);
		
	}

	public void mostrarMetaDatos() {
		uDAO.mostrarMedata();
		
	}
	
	
}