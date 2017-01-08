import java.sql.*;
public class ShowUsers{

	public static void main(String []args){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/solo?user=root&password=123abc");
			Statement sm = ct.createStatement();
			ResultSet rs = sm.executeQuery("select * from b3_solo_user");
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
