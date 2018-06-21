package Beans;

import java.util.Calendar;
import java.util.Date;

public class Usuario {

	private int id;
	private String nombre,apellidos,pass;
	private Date fecha_nacimiento;
	
	public Usuario(String nombre, String apellidos, String pass, Date fecha_nacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pass = pass;
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public Usuario(){}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		
		int edad= calcularEdad();
		return nombre+" "+apellidos+" - "+edad+" años"; 
	}

	public int calcularEdad() {
		Calendar c= Calendar.getInstance();
		return (int) ((c.getTimeInMillis() - fecha_nacimiento.getTime())/1000/60/60/24/365.24);
	}
}
