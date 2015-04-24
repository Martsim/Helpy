import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {
	static ArrayList<Integer> requests;
	public static void add_req(int phonenr){
		requests.add(phonenr);
	}
	public static void main(String[] args) {
		
		while(true){
			//get request
			for (int pn: requests){
				find_helper(pn);
				
			}
			//use method
						
		}

	}
	
	private static void find_helper(int pn) {
		// TODO Auto-generated method stub
		
	}

}
class somecode{
	static void classmethod() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		//first thoughts
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", "");

		Statement st = con.createStatement();
		String sql = ("SELECT * FROM requests;");
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) { 
			int req_phone = rs.getInt("phonenumber");
			Statement st2 = con.createStatement();
			String sql2 = ("SELECT * FROM User WHERE phonenumber = "+Integer.toString(req_phone)+";");
			ResultSet rs2 = st2.executeQuery(sql);
			int id = rs.getInt("first_column_name");
			String reqName = rs.getString("Name");
			double loc_N = rs.getDouble("N");
			double loc_E = rs.getDouble("E");
		}
		con.close();
	}
}
//http://stackoverflow.com/questions/12041354/java-getting-data-from-mysql-database
