package zadania;

import java.sql.*;

public class Ins1 {

  static public void main(String[] args) {
    new Ins1();
  }

Statement stmt;

Ins1()  {
   Connection con = null;
   try {
     //...
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
	// nazwy wydawców do wpisywania do tabeli
   String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

   	// pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
   int beginKey = 15;

   String[] ins =  new String[wyd.length];// ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...                  

   int insCount = 0;   // ile rekordów wpisano
   
   for (int i = 0; i < ins.length; i++)
	{
		ins[i] = "Insert into WYDAWCA (WYDID, NAME) " + "values( " + beginKey + ", '" + wyd[i] + "' )";
		beginKey++;
	}
   
   try  {
     for (int i=0; i < ins.length; i++) {// wpisywanie rekordów
     // ...
    	 stmt.executeUpdate(ins[i]);
		insCount++;
     }
   }
   catch (Exception ex) {
	   System.out.println(ex);
   }
   
   System.out.println("Insertion count: " + insCount); 
//...
   //usuwanie wstawionych rekordów
   beginKey = 15; //"zeruję" beginKey do początkowej wartości
   int delCount = 0;
   PreparedStatement stm;
   /*
   try {
	   stm = con.prepareStatement("DELETE FROM WYDAWCA WHERE (NAME = ?) OR (WYDID = ?)");
	   for (int i = 0; i < wyd.length; i++) {
		   stm.setString(1, wyd[i]);
		   stm.setInt(2, beginKey++);
		   delCount += stm.executeUpdate();
	   }
	   con.close();
   }
   catch (SQLException ex) {
	   System.out.println(ex);
   }
   
   System.out.println("Deleting count: " + delCount);
   */
}
} 
