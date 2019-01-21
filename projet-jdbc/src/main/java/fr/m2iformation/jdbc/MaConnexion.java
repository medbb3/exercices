package fr.m2iformation.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe Formation respectant le pattern Singleton
 * 
 * @author Formation
 *
 */
public class MaConnexion {

	private static MaConnexion instance;
	private Connection connection;

	private static final String JDBC_DRIVER = "mysqlDriver";
	private static final String JDBC_URL = "mysqlUrl";
	private static final String JDBC_USER = "mysqlUser";
	private static final String JDBC_PASSWORD = "mysqlPassword";

	private MaConnexion() throws IOException, ClassNotFoundException, SQLException {

		// Acceder en lecteur au fichier de configuration
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/jdbcConfig.properties");
		props.load(fis);

		final String DRIVER = props.getProperty(JDBC_DRIVER);
		Class.forName(DRIVER);

		final String URL = props.getProperty(JDBC_URL);
		final String USER = props.getProperty(JDBC_USER);
		final String PASSWORD = props.getProperty(JDBC_PASSWORD);

		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public Connection getConnection() {
		return connection;
	}

	public static synchronized MaConnexion getInstance() throws ClassNotFoundException, IOException, SQLException {
		if (instance == null) {
			instance = new MaConnexion();
		}
		return instance;
	}
}
