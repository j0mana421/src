package projet;
import java.sql.*;

public class Connect {
	Connection c;
	Statement st;
	Connect() throws ClassNotFoundException, SQLException{
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){
		    e.printStackTrace();
		} 

		//String url ="jdbc:mysql://mira.c2m.univ-st-etienne.fr:3306/ma00535m";
		String url="jdbc:mysql://localhost:3306/projet";
		try{
			c = DriverManager.getConnection(url, "root", "mdp");
			System.out.println("Connexion réussie.\n");
			st = c.createStatement();
		}catch (SQLException e){
			System.out.println("Connexion échouée.\n");
			e.printStackTrace();
		}
	}
	
	public ResultSet reqSQL(String query, char type) throws SQLException{
		if(type=='s'){
			try {
				ResultSet rs = st.executeQuery(query);
				return rs;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		else if (type=='m'){
			try{
				int ur = st.executeUpdate(query);
				System.out.println("Nombre de ligne(s) modifiée(s) " +ur);
				return null;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void close(){
			try {
				st.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String afficherRes(ResultSet rs) throws SQLException{
		ResultSetMetaData metadata = rs.getMetaData();
		String resultat="";
		while(rs.next()){
			for(int i=1; i<=metadata.getColumnCount();i++){
				System.out.print(rs.getString(i) +" ");
				resultat+=rs.getString(i) +" ";
			}
			System.out.println();
		}
		return resultat;
	}
}

