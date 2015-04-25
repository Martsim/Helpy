import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {
	static ArrayList<String> requests = new ArrayList<String>;
	public static void add_req(String phonenr){
		requests.add(phonenr);
	}
	public static void main(String[] args) {
		add_req("55");//put the phonenr
		while(true){
			//get requests into the requests table
			//Deal with the requests
			for (String pn: requests){//has to be changed to int i ..x...++. way to remove helped ones
				User requester = get_helpreqdata(pn);
				find_helper(requester);
				//remove this request
			}		
		}
	}
	
	private static User get_helpreqdata(String pn) {
		
		String req_phone = "0";
		int id = 0;
		String name = "";
		float latitude = 0;
		float longitude = 0;
		
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/helpy_db", "root", ""); 

			Statement st = con.createStatement();
			String sql = ("SELECT * FROM user WHERE phonenr = " + pn + ";");
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()) { 
				try{
					latitude = rs.getFloat("latitude");
					longitude = rs.getFloat("longitude");
				}
				catch(SQLException e){
					latitude = 0;
					longitude = 0;
				}
				try{
					req_phone = rs.getString("phonenr");
					id = rs.getInt("id");
					name = rs.getString("name");
				} catch (SQLException e){
					req_phone = "0";
					id = 0;
					name = "Didnt get";
				}		
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Problem in the help requester part - " + e);
		}

		return new User(id, name, req_phone, longitude, latitude);//the error case		
	}
	
	private static void find_helper(User requester) {
		
		//gets chosen people
		ArrayList<User> users = getchosenOnes(requester);
		
		for(User user: users){
			//TODO: send message to this user part
			System.out.println(user.getName() + " will be requested!");
		}

	}
	
	private static ArrayList<User> getchosenOnes(User requester) {

		ArrayList<User> chosenOnes = new ArrayList<User>();
try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/helpy_db", "root", "");

			Statement st = con.createStatement();
			String sql = ("SELECT * FROM user;");
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) { 
				float Latitude;
				float Longitude;
				try{
					Latitude = rs.getFloat("latitude");
					Longitude = rs.getFloat("longitude");
				}
				catch(SQLException e){
					Latitude = 0;
					Longitude = 0;
				}
				if(distFrom(requester.getLatitude(), requester.getLongitude(), Latitude, Longitude)<1000){
					int id;
					String name;
					String phone;
					try{
						id = rs.getInt("id");
						name = rs.getString("name");
						phone = rs.getString("phonenr");
					} catch (SQLException e){
						id = 99;
						name = "Didnt get";
						phone = "0";
					}
					chosenOnes.add(new User(id, name, phone, Longitude, Longitude));
				}
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Problem in the help requester part - " + e);
		}
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
	private String phonenr;

	User(int id, String name, String phonenr ,float longitude, float latitude){
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phonenr = phonenr;
	}
	public String getName() {
		return name;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public String getPhonenr() {
		return phonenr;
	}
	public String toString(){
		return "Name: "+ name + ", N: "+ Float.toString(latitude)+" , E: " + Float.toString(longitude);
	}
}
