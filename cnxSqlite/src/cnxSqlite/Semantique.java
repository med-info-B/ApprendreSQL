package cnxSqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author ACER
 *      Cette classe a comme responsabilité d'évaluer la requete de l'eleve 
 */
public class Semantique{
	
	private String requete;
	private List<String>reponse =new ArrayList<String>();
	private DataBase base;
	private String requete_prof = "select * from B";

	//constructeur 
	public Semantique (String path, String q) {
		 base =new  DataBase(path);	
		 requete = q;
	}
	
	 String setRequete(String q) { //requete de l'etudiant
		return requete=q;
	}
	 
	public String Getrequete() { //requete de l'etudiant
			return requete;
		}	
	public DataBase getBase() {
		return base;
	}

	
	public void afficheReponse() throws SQLException {
		for( String el:(base.getTuples(this.requete))) {
			System.out.println(el);
		}
	}
	
	public boolean evalue() throws Exception {
		if(requete.equalsIgnoreCase(requete_prof)){ return true;}
		else
		 { 
			if(base.connect() && base.ExistTable(base.tableRequete(requete))){
				
							 List<String> r1= new ArrayList();
							 List<String> r2= new ArrayList();
							 
								r1= base.getTuples(requete);
								r2= base.getTuples(requete_prof);
					
									if(base.NombreTuples(requete)==base.NombreTuples(requete_prof)) {
										return r2.containsAll(r1);
									}else return false;
									}
			else return false;
		 }
		
	}
	
	/*

	public boolean evalue() throws Exception {
	if(base.connect() && base.ExistTable(base.tableRequete(requete))){
        return base.getTuples(requete).containsAll(base.getTuples(requete_prof));  											
		}
			return false;
		}*/
	
}
	


