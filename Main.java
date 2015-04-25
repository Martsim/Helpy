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
			for (int pn: requests){//has to be changed to int i ..x...++. way to remove helped ones
				User requester = get_helpreqdata(pn);
				find_helper(requester);
				//remove this request
			}		
		}
	}
	
	private static User get_helpreqdata(int pn) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		int req_phone = 0;
		int id = 0;
		String name = "";
		float latitude = 0;
		float longitude = 0;
		
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", ""); //i think this should have the DB address

		Statement st = con.createStatement();
		String sql = ("SELECT * FROM user WHERE phonenr = " + Integer.toString(pn) + ";");
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) { 
			req_phone = rs.getInt("phonenumber");
			id = rs.getInt("id");
			name = rs.getString("name");
			latitude = rs.getFloat("Latitude");
			longitude = rs.getFloat("Longitude");
			
			con.close();
		}
		
		return new User(id, name, req_phone, longitude, latitude);//the error case	
	}
	
	private static void find_helper(User requester) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		//gets chosen people
		ArrayList<User> users = getchosenOnes(requester);
		
		for(User user: users){
			//TODO: send message to this user part
		}

	}
	
	private static ArrayList<User> getchosenOnes(User requester) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		ArrayList<User> chosenOnes = new ArrayList<User>();

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", ""); //i think this should have the DB address

		Statement st = con.createStatement();
		String sql = ("SELECT * FROM user;");
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) { 
			float Latitude = rs.getFloat("Latitude");
			float Longitude = rs.getFloat("Longitude");
			if(distFrom(requester.getLatitude(), requester.getLongitude(), Latitude, Longitude)<1000){
				int phone = rs.getInt("phonenumber");
				int id = rs.getInt("id");
				String name = rs.getString("name");
				chosenOnes.add(new User(id, name, phone, Longitude, Longitude));
			}
		}
		con.close();
		return chosenOnes;
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

class User{
	private int id;
	private String name;
	private float longitude;
	private float latitude;
	private int phonenr;

	User(int id, String name, int phonenr ,float longitude, float latitude){
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phonenr = phonenr;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public String toString(){
		return "Name: "+ name + ", N: "+ Float.toString(latitude)+" , E: " + Float.toString(longitude);
	}

}

//You can ignore this last part, it's and example of a request
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
			int id = rs.getInt("id");
			double Latitude = rs.getDouble("Latitude");
			double Longitude = rs.getDouble("Longitude");
		}
		con.close();
	}
}
