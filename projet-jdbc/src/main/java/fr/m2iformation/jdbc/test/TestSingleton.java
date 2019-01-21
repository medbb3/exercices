package fr.m2iformation.jdbc.test;

import java.sql.Connection;

import fr.m2iformation.jdbc.MaConnexion;

public class TestSingleton {

	public static void main(String[] args) {
		try {
			Connection connection = MaConnexion.getInstance().getConnection();
			System.out.println("Connexion OK");
		} catch (Exception e) {
			System.out.println("Connexion NOK\n");
			e.printStackTrace();
		}
	}
}
