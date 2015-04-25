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
	
	
//taken from http://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
 public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
    double earthRadius = 6371000; //meters
    double dLat = Math.toRadians(lat2-lat1);
    double dLng = Math.toRadians(lng2-lng1);
    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
               Math.sin(dLng/2) * Math.sin(dLng/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    float dist = (float) (earthRadius * c);

    return dist;
    }


}



class somecode{
	static void classmethod() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		//first thoughts
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", ""); //i think this should have the DB address

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
