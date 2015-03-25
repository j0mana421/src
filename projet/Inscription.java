package projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String pseudo;
    public static String mail;
    public static String mdp;
    DbConnect dbc= new DbConnect();
    public Inscription() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out= res.getWriter();		
		pseudo=req.getParameter("pseudo");
		mail=req.getParameter("addr_mail");
		mdp=req.getParameter("mdp");
		try {
			dbc.creerUtilisateur(pseudo, mail, mdp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = req.getSession();
		session.setAttribute("nom",pseudo);
		try{
			res.sendRedirect("index.html");
		} finally {out.close();}
	}

}
