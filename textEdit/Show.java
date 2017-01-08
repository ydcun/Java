import java.sql.*;
public class Show{
	public static void main(String[] args){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/solo?user=root&password=123abc");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from b3_solo_user");
			while(rs.next()){
				System.out.print(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
