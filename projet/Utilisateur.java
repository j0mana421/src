package projet;

public class Utilisateur{  
	  
    private int id;  
    private String pseudo;
    private String mail;
    private String mdp;
    private static int num=0;
    private final int NUM_PERSONNE;
  
  
    public Utilisateur (final String pseudo, final String mail, final String mdp) {  
        this.pseudo = pseudo;  
        this.mail = mail;  
        this.mdp = mdp;
    	num++;
    	NUM_PERSONNE=num; 
    }  
    
    public int getNumero(){
    	System.out.println(NUM_PERSONNE);
    	return NUM_PERSONNE;
    }
    
}