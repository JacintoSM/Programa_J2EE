package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.GestionUsuarios;

/**
 * Servlet implementation class ServletLoguin
 */
@WebServlet("/ServletLoguin")
public class ServletLoguin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoguin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		GestionUsuarios gu = new GestionUsuarios();
		String texto;
		if(gu.login(nombre, pass)){
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
					"Se ha logueado correctamente <br/><br/>"
				+"<a href=\"index.html\"> Volver</a>"
				+ " </section>"
					+ "<footer>Copyright ©Victor</footer>"
				+ "</main>"
			+ "</body>"
		+ "</html>";
			response.getWriter().print(texto);
		}else{
			response.sendRedirect("loguin.html");
		}
			
	
	}

}
