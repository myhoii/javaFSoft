package qnu.edu.vn.MXH.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private Connection connection;
	private static DBUtils instance;

	private DBUtils() {
		Properties pr = new Properties();
		try {
			var input = DBUtils.class.getResourceAsStream("/DBConfig.properties");

			if (input == null) {
				throw new RuntimeException("Không tìm thấy DBConfig.properties");
			}

			pr.load(input);

			String driver = pr.getProperty("driver");
			String url = pr.getProperty("url");
			String userName = pr.getProperty("userName");
			String pass = pr.getProperty("password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pass);

			System.out.println("Kết nối DB thành công");

		} catch (Exception e) {
			System.out.println("Lỗi kết nối DB");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		System.out.println("Đã tạo connection: " + connection);
		return connection;
	}

	public static DBUtils getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed()) {
			instance = new DBUtils();
		}
		return instance;
	}

}
