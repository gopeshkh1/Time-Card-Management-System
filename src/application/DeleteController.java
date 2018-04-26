package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteController {
	@FXML
	private TextField id;
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   static final String DB_URL = "jdbc:mysql://localhost/timecard";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "abc123";
	
	public void delete(ActionEvent event) throws IOException
    {
		String ID = id.getText();
		
		Connection conn = null;
	 	   Statement stmt = null;
	 	   try{
	 	      //STEP 2: Register JDBC driver
	 	      Class.forName("com.mysql.jdbc.Driver");

	 	      //STEP 3: Open a connection
	 	      System.out.println("Connecting to database for emp...");
	 	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	 	      //STEP 4: Execute a query
	 	      System.out.println("Creating statement for name...");
	 	      stmt = conn.createStatement();
	 	      String sql1,sql2,sql3,sql4;
	 	      sql1 = "delete from dependents where uno="+ id.getText();
	 	      sql2 = "delete from works where eid="+id.getText();
	 	      sql3 = "delete from leav where eno="+id.getText();
	 	      sql4 = "delete from emp where uid=" + id.getText();
	 	      stmt.executeUpdate(sql1);
	 	     stmt.executeUpdate(sql2);
	 	    stmt.executeUpdate(sql3);
	 	   stmt.executeUpdate(sql4);
	 	   
	 	      //STEP 6: Clean-up environment
	 	      
	 	      stmt.close();
	 	      conn.close();
	 	   }catch(SQLException se){
	 	      //Handle errors for JDBC
	 	      se.printStackTrace();
	 	   }catch(Exception e){
	 	      //Handle errors for Class.forName
	 	      e.printStackTrace();
	 	   }finally{
	 	      //finally block used to close resources
	 	      try{
	 	         if(stmt!=null)
	 	            stmt.close();
	 	      }catch(SQLException se2){
	 	      }// nothing we can do
	 	      try{
	 	         if(conn!=null)
	 	            conn.close();
	 	      }catch(SQLException se){
	 	         se.printStackTrace();
	 	      }//end finally try
	 	   }//end try
	 	   
	 	  Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
	    	Scene tableViewscene = new Scene(tableView);
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(tableViewscene);
	    	window.show();
    }
	public void back(ActionEvent event) throws IOException
    {
    	Parent tableView = FXMLLoader.load(getClass().getClassLoader().getResource("Admin.fxml"));
    	Scene tableViewscene = new Scene(tableView);
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(tableViewscene);
    	window.show();
    }
	

}
