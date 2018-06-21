package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.GestionUsuarios;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String fecha = request.getParameter("fechaNacimiento");
		String pass = request.getParameter("pass");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		try{
		Date fechaNacimiento= sdf.parse(fecha);
		GestionUsuarios gu = new GestionUsuarios();
		String texto;
		if(gu.registrarUsuario(nombre, apellidos, pass, fechaNacimiento))
	texto="<!DOCTYPE html>"
			+ "<html>"
				+ "<head>"
					+ "<title>Registro Usuario</title>"
					+ "<meta charset=\"UTF-8\"></meta>"
					+ "<link rel=\"stylesheet\" href=\"css/pruebaCSS.css\"></link>"
				+ "</head>"
				+ "<body>"
				+ "<main>"
					+ "<header><h1>Registro de Usuarios</h1></header>"
				+ "<section>"+
					"Se ha registrado correctamente <br/><br/>"
				+"<a href=\"index.html\"> Volver</a>"
				+ " </section>"
					+ "<footer>Copyright ©Victor</footer>"
				+ "</main>"
			+ "</body>"
		+ "</html>";
		else
			texto="<!DOCTYPE html><html><head><title>Registro Usuario</title><meta charset=\"UTF-8\"></meta><link rel=\"stylesheet\" href=\"css/pruebaCSS.css\"></link></head><body><main><header><h1>Registro de Usuarios</h1></header><section>"+
								"ha habido un error en el registro"
								+ "<br/><br/>"
								+"<a href=\"index.html\"> Volver</a></section><footer>Copyright ©Victor</footer></main></body></html>";
		response.getWriter().print(texto);
		}catch(ParseException e){
			System.out.println("Error en el formato de la fecha");
		}
		
		System.out.println(fecha);
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
