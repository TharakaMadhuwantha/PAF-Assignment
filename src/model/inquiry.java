package model;

import java.sql.*;

public class inquiry {
	
	public String insertInquiries(String accNo, String name, String contact, String email, String det) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into inquiries values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,accNo);
			preparedStmt.setString(3,name);
			preparedStmt.setInt(4,Integer.parseInt(contact));
			preparedStmt.setString(5,email);
			preparedStmt.setString(6,det);
			
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
	
	public String readInquiries() {
		
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
					+ "			<th>Account Number</th><th>Name</th><th>Contact Number</th><th>Email</th><th>Inquiry Details</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from inquiries";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String inquiryID = Integer.toString(rs.getInt("inquiryID"));
				String accountNum = rs.getString("accountNum");
				String Name = rs.getString("Name");
				String contactNum = Integer.toString(rs.getInt("contactNum"));
				String email = rs.getString("email");
				String inquiryDet = rs.getString("inquiryDet");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +accountNum+ "</td><td>" +Name+ "</td><td>" +contactNum+ "</td><td>" +email+ "</td><td>" +inquiryDet+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='inquiryID' type='hidden' value='"+inquiryID+"'><input name='accountNum' type='hidden' value='"+accountNum+"'><input name='Name' type='hidden' value='"+Name+"'><input name='contactNum' type='hidden' value='"+contactNum+"'><input name='email' type='hidden' value='"+email+"'><input name='inquiryDet' type='hidden' value='"+inquiryDet+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='inquiryID' type='hidden' value='"+inquiryID+"'><input name='accountNum' type='hidden' value='"+accountNum+"'></form></td>"
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
	
	public String updateInquiry(String id, String accNo, String name, String contact, String email, String det) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update inquiries set accountNum=?, Name=?, contactNum=?, email=?, inquiryDet=? where inquiryID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,accNo);
			preparedStmt.setString(2,name);
			preparedStmt.setInt(3,Integer.parseInt(contact));
			preparedStmt.setString(4,email);
			preparedStmt.setString(5,det);
			preparedStmt.setInt(6,Integer.parseInt(id));
			
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
	
	public String deleteInquiry(String inquiryID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from inquiries where inquiryID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(inquiryID));
			
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
