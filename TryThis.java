static String get_data() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", ""); //i think this should have the DB address

		Statement st = con.createStatement();
		String sql = ("SELECT * FROM user;");
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) { 
			//int req_phone = rs.getInt("phonenumber");
			int id = rs.getInt("id");
			String name = rs.getString("name");
			//double latitude = rs.getDouble("Latitude");
			//double longitude = rs.getDouble("Longitude");
			con.close();
			return Integer.toString(id)+""+name+";
		}
		con.close();//will it still close?
		//return new User(0, "", 0, 0, 0);//the error case
		return "Failure";
	}
