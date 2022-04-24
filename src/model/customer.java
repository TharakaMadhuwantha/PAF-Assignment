package model;

import java.sql.*;

public class customer {
	
	public String insertCustomer(String name, String pno, String address, String email) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into customer values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,pno);
			preparedStmt.setString(4,address);
			preparedStmt.setString(5,email);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readCustomer() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>Customer Name</th><th>Customer phone NO</th><th>Customer Address</th><th>Customer Email</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from customer";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String cusID = Integer.toString(rs.getInt("cusId"));
				String cusName = rs.getString("cusName");
				String cusPno = rs.getString("cusPno");
				String cusAddress = rs.getString("cusAddress");
				String cusEmail = rs.getString("cusEmail");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +cusName+ "</td><td>" +cusPno+ "</td><td>" +cusAddress+ "</td><td>" +cusEmail+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='cusID' type='hidden' value='"+cusID+"'><input name='cusName' type='hidden' value='"+cusName+"'><input name='cusPno' type='hidden' value='"+cusPno+"'><input name='cusAddress' type='hidden' value='"+cusAddress+"'><input name='cusEmail' type='hidden' value='"+cusEmail+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='cusID' type='hidden' value='"+cusID+"'><input name='cusName' type='hidden' value='"+cusName+"'></form></td>"
						+ "		</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the items";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateCustomer(String id, String name, String pno, String address, String email) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update customer set cusName=?, cusPno=?, cusAddress=?, cusEmail=? where cusID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,name);
			preparedStmt.setString(2,pno);
			preparedStmt.setString(3,address);
			preparedStmt.setString(4,email);
			preparedStmt.setInt(5, Integer.parseInt(id));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Updated Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while updating";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deleteCustomer(String cusID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from customer where cusID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(cusID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}

