package projet;

import java.sql.ResultSet;
import java.sql.SQLException;  

public class DbConnect {  
	Connect c;

	public String creerUtilisateur(final String pseudo, final String mail, final String mdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		String chaine;
		ResultSet rs =c.reqSQL("SELECT MAX(id_u) FROM utilisateurs",'s');
		rs.next();
		int num =Integer.parseInt(rs.getString(1))+1;
		chaine= "INSERT INTO utilisateurs VALUES ('"+num+"', '"+pseudo+"','"+mail+"','"+mdp+"')";
		c.reqSQL(chaine, 'm');
		c.close();
		return chaine;
	}
	
	public boolean verifUtilisateur(String psd, String motdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		ResultSet rs = c.reqSQL("SELECT id_u FROM utilisateurs WHERE pseudo='"+psd+"' AND mdp='"+motdp+"'", 's');
		if(rs.first()){
			c.close();
			return true;
		}
		c.close();
		return false;
	}
}
