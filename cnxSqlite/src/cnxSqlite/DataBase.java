package cnxSqlite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 //tout ce qui concerne la cnx et la bdd
public class DataBase {

	private String DBpath="chemain de la bd";
	private Connection cnx = null;
	private  Statement stat = null;
	private ResultSet resultat = null;
	
	
	//constructeur par rapport au chemain uniquement
	public DataBase(String path) {
		DBpath=path;
	}
	
	public void commande(String sql) throws SQLException {
		stat=cnx.createStatement();
		stat.executeUpdate(sql);
	}
	
	//connexion avec la sqlite et creation de la bdd si elle n'existe pas 
	public boolean connect() throws ClassNotFoundException {
		try { //vérfier l'existence de la bd
			Class.forName("org.sqlite.JDBC");
			cnx=DriverManager.getConnection("jdbc:sqlite:"+DBpath);
			System.out.println("SQLite DB connecé ! ");
			return true;
			
		}catch (SQLException sqlException) {
            sqlException.printStackTrace();
            //System.out.println("Erreur de connecxion");
            return false;
        }
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public boolean ExistTable(String table) throws Exception { //vérification de l'existence de la table 
		
		try {		
				resultat = stat.executeQuery("select * from "+table);
				return true;
				
		}catch(Exception e) {
				System.out.println("Table n'existe pas ");
				return false;
		}

	}
		 
	public List<String> getTuples(String requete) throws SQLException{
		
		List<String>data=new ArrayList<String>();	
		String donnee="";
		resultat = stat.executeQuery(requete);

		
		System.out.println("Le nom de la table est : "+resultat.getMetaData().getTableName(1));		
		int n=resultat.getMetaData().getColumnCount();//pour récuperer le nombre de colonnes 
		System.out.println("Le nombre de colonnes est : "+n );
	
		
		while(resultat.next()) {//on boucle par rapport aux tuples		
			for(int i=1;i<=n;i++) {//boucle par rapport par rapport aux attributs	
				
				donnee += resultat.getString(i)+" "; //concaténation 
			}					
		    data.add(donnee);
			donnee="";
		}
		return data;

	}
	public int NombreTuples(String requete) throws SQLException {
		int n=getTuples(requete).size();
		System.out.println("Nombre de tuple est : "+n);
		return n;
		}
	/**
	 * 
	 * @param requete
	 * @return
	 * @throws SQLException
	 */
	public String tableRequete(String requete) throws SQLException {
		try {
			resultat =stat.executeQuery(requete);
		return resultat.getMetaData().getTableName(1);
		} catch(Exception e) {
			return "Table n'existe pas";
			}
	}
	
	
	
	/**
	 * 
	 */
    public void close() { //Fermeture
        try {
            cnx.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
