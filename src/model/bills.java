package model;

import java.sql.*;

public class bills {

	public String insertBills(String accID, String accName, String price, String unit, String date) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into bills values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,accID);
			preparedStmt.setString(3,accName);
			preparedStmt.setDouble(4,Double.parseDouble(price));
			preparedStmt.setDouble(5,Double.parseDouble(unit));
			preparedStmt.setString(6,date);
			
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
	
	public String readBills() {
		
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
					+ "			<th>Bill Id</th><th>Account Id</th><th>Account Name</th><th>Bill Price</th><th>Bill Unit</th><th>Bill Date</<th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from bills";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String billID = Integer.toString(rs.getInt("billID"));
				String accountID = rs.getString("accountID");
				String accountName = rs.getString("accountName");
				String billPrice = Double.toString(rs.getDouble("billPrice"));
				String billUnit = Double.toString(rs.getDouble("billUnit"));
				String billDate = rs.getString("billDate");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +billID+ "</td><td>" +accountID+ "</td><td>" +accountName+ "</td><td>" +billPrice+ "</td><td>" +billUnit+ "</td><td>"+billDate+"</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='billID' type='hidden' value='"+billID+"'><input name='accountID' type='hidden' value='"+accountID+"'><input name='accountName' type='hidden' value='"+accountName+"'><input name='billPrice' type='hidden' value='"+billPrice+"'><input name='billUnit' type='hidden' value='"+billUnit+"'><input name='billDate' type='hidden' value='"+billDate+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='billID' type='hidden' value='"+billID+"'><input name='accountID' type='hidden' value='"+accountID+"'></form></td>"
						+ "		</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the bills";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateBill(String id, String accId, String name, String price, String unit, String date) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update bills set accountID=?, accountName=?, billPrice=?, billUnit=?, billDate=? where billID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,accId);
			preparedStmt.setString(2,name);
			preparedStmt.setDouble(3,Double.parseDouble(price));
			preparedStmt.setDouble(4,Double.parseDouble(unit));
			preparedStmt.setString(5,date);
			preparedStmt.setInt(6, Integer.parseInt(id));
			
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
	
	public String deleteBill(String billID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from bills where billID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(billID));
			
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
