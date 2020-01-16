package zadania;

import java.sql.*;

public class Zad13 {
	public static void main(String[] args)
	{
		new Zad13();
	}
	
	Statement stmt;
	
	public Zad13 () {
		Connection con = null;
		
		try {
			  String u = "jdbc:derby://localhost/ksidb;create=true";
			  con = DriverManager.getConnection(u);
			  stmt = con.createStatement();
			   
			  PreparedStatement ps = con.prepareStatement("set schema ?");
			  ps.setString(1,"USER");
			  ps.executeUpdate();
		   } catch (Exception exc)  {
		     System.out.println(exc);
		     System.exit(1);
		   }
		
		String sel1 = "SELECT w.WYDID,w.NAME FROM WYDAWCA w" +
	
				 " WHERE w.WYDID < 16";
		
		//A
		/*
		try  {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(sel1);
		      while (rs.next())  {
		         String nazwisko = rs.getString(1);
		         //...
		         System.out.println("Autor: " + nazwisko);
		         //...
		   
		      }
		      //stmt.close();
		      //con.close();
		   } catch (SQLException exc)  {
		     System.out.println(exc.getMessage());
		   }
		*/
		//B
		String sel2 = "SELECT * from WYDAWCA";// ... ?
				   try  {
				      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				                                           ResultSet.CONCUR_READ_ONLY);
				      ResultSet rs = stmt.executeQuery(sel2);
				      ResultSetMetaData rsmd = rs.getMetaData();
				      int cc = rsmd.getColumnCount();
				      for (int i = 1; i <= cc; i++)
				        System.out.print(rsmd.getColumnLabel(i) + "     ");

				      System.out.println("\n------------------------------ przewijanie do góry");

				      // ... ?
				      
				      rs.last();
				      //rs.beforeFirst();
				      //int licznik = 0;
				      
				      /*while (rs.next()) {
				    	licznik++;
				    	System.out.println(licznik);
				      }
				      */
					do 
					{
						for (int i = 1; i <= cc; i++)
							System.out.print(rs.getString(i) + "     ");
							System.out.println();
					}while(rs.previous());
				 
				      System.out.println("\n----------------------------- pozycjonowanie abs.");
				      int[] poz =  { 3, 7, 9 };
				      
				      for (int p = 0; p < poz.length; p++)  {
				         System.out.print("[ " + poz[p] + " ] ");
				         // ... ?
				         rs.absolute(poz[p]);
				         for (int i = 1; i <= cc; i++)
				        	 System.out.print(rs.getString(i) + ", ");
				         System.out.println("");
				      }
				      stmt.close();
				      con.close();
				   } catch (SQLException exc)  {
				     System.out.println(exc.getMessage());
				   }
	}
}
