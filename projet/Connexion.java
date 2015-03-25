package projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;



public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String identifiant;
    public static String mdp;
    DbConnect dbc= new DbConnect();
    public Connexion() {
        super();
      
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String pseudo = (String) session.getAttribute("nom");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out= res.getWriter();
		out.println("<b>Bonjour "+pseudo+"</b>");
		out.println("<html><head>");
		out.println("<meta http-equiv='refresh' content='1' />");
		out.println("<title>Servlet formulaire</title>");
		out.println("</head><body>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");		
		identifiant=req.getParameter("identifiant");
		mdp=req.getParameter("motdepasse");
		try {
			if(dbc.verifUtilisateur(identifiant, mdp)){
				HttpSession session = req.getSession();
				session.setAttribute("nom",identifiant);
				PrintWriter out= res.getWriter();	
				try{
					res.sendRedirect("Connecte.html");
					JOptionPane.showMessageDialog(null,"Bonjour "+identifiant);
				} finally {out.close();}
			}
			else {
				PrintWriter out= res.getWriter();	
				try{
					res.sendRedirect("index.html");
					System.out.println("impossible de se connecter");
					JOptionPane.showMessageDialog(null,"Le login ou le mot de passe est incorrect !");
				} finally {out.close();}
			}
		} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

	}

}
