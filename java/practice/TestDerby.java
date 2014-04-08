import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestDerby {
	public static void main(String[] args) {
		String frameworkd = "embedded";
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String protocol = "jdbc:derby:";
		
		try {
			Class.forName(driver).newInstance();
			System.out.println("Derby DB driver loaded");
			Connection conn = null;
			// Access Database file "TestDB.db". Create if none exists.
			conn = DriverManager.getConnection(protocol + "TestDB.d" + ";create=true");
			Statement stmt = conn.createStatement();
			//stmt.execute("create table settings (username varchar(50), id int, favorite_color varchar(20))");
			//stmt.execute("insert into settings values ('test', 5, 'blue')");
			ResultSet rs = stmt.executeQuery("select * from settings where username = 'test'");
			while (rs.next()) {
				for (int i = 1; i <= 3; i++)
					System.out.println(rs.getString(i));
			}
			System.out.println("Finished...");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
