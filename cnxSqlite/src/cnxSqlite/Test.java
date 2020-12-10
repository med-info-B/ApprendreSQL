package cnxSqlite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {


	public static void main(String[] args) throws Exception {
			
		Semantique s = new Semantique("test.db","select * from A");
		s.getBase().connect();		
		System.out.println(s.getBase().tableRequete("select * from A"));	
		s.getBase().commande("create table B(A1 int, A2 int);");
		s.getBase().commande("insert into B values(1,2);");
		System.out.println("La tables existe il ?" +s.getBase().ExistTable("B"));
		s.getBase().NombreTuples("select * from B");
	    System.out.println("Equivalence des requete : "+s.evalue());
		s.getBase().commande("drop table B;");		
		
	}

}
