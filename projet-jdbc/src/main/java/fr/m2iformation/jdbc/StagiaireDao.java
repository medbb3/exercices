import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StagiaireDao extends MaConnextion {

	static public void insert(Stagiaire stg) {

		try (Connection cnx = getConnection()) {
			String sql = "INSERT INTO stagiaire(login, mdp, age)";
			sql += "VALUES('" + stg.getLogin() + "','" + stg.getMdp() + "','" + stg.getAge() + "')";
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rsCles = stmt.getGeneratedKeys();
			if (rsCles.next()) {
				int cle = rsCles.getInt(1);
				stg.setId(cle);
			}

		} catch (SQLException ex) {
			System.err.println("Problème SQL");
			ex.printStackTrace();
		}

	}

	static public void delete(String l) {
		try (Connection cnx = getConnection()) {
			PreparedStatement myStatement = cnx.prepareStatement("DELETE FROM stagiaire WHERE login = ?");
			myStatement.setString(1, l);
			myStatement.executeUpdate();
		} catch (SQLException ex) {
			System.err.println("Echec de connexion");
			ex.printStackTrace();
		}

	}

	static public String get(String l) {
		String str = null;
		try (Connection cnx = getConnection()) {
			String sql = "SELECT * from stagiaire WHERE login = ?";
			PreparedStatement stmt = cnx.prepareStatement(sql);
			stmt.setString(1, l);
			try (ResultSet myResultSet = stmt.executeQuery()) {
				if (myResultSet.next()) {
					int id = myResultSet.getInt("id");
					String login = myResultSet.getString("login");
					String mdp = myResultSet.getString("mdp");
					int age = myResultSet.getInt("age");
					str = "" + id + " " + login + " " + mdp + " " + age + " ";
				}
			} catch (SQLException ex) {
				System.err.println("Echec de la requete");
				ex.printStackTrace();
			}
		} catch (SQLException ex) {
			System.err.println("Echec de connexion");
			ex.printStackTrace();
		}
		return str;
	}

	static public String read() {
		StringBuilder str = new StringBuilder();
		try (Connection cnx = getConnection()) {
			String sql = "SELECT * from stagiaire";
			PreparedStatement stmt = cnx.prepareStatement(sql);
			try (ResultSet myResultSet = stmt.executeQuery()) {
				while (myResultSet.next()) {
					int id = myResultSet.getInt("id");
					String login = myResultSet.getString("login");
					String mdp = myResultSet.getString("mdp");
					int age = myResultSet.getInt("age");

					str.append(id).append(" " + login).append(" " + mdp).append(" " + age).append("\n");

				}
			} catch (SQLException ex) {
				System.err.println("Echec de la requête");
				ex.printStackTrace();
			}
		} catch (SQLException ex) {
			System.err.println("Echec de connexion");
			ex.printStackTrace();
		}

		return str.toString();

	}

	static public void update(String l, Stagiaire stg) {

		try (Connection cnx = getConnection()) {
			String sql = "UPDATE stagiaire SET login=?, mdp=?, age=? where login='" + l + "'";
			PreparedStatement stmt = cnx.prepareStatement(sql);
			stmt.setString(1, stg.getLogin());
			stmt.setString(2, stg.getMdp());
			stmt.setInt(3, stg.getAge());

			stmt.execute();
		}

		catch (SQLException ex) {
			System.err.println("Echec de connexion");
			ex.printStackTrace();
		}

	}

}
