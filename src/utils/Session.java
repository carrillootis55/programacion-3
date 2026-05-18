package utils;

import models.Maestro;

public class Session {
	
	private static Maestro currentUser;
	
	public static void login(Maestro user) {
		currentUser = user;
	}
	
	public static Maestro getCurrentUser() {
		return currentUser;
	}
	
	public static void logout() {
		currentUser = null;
	}
	
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public static String getRole( ) {
		return currentUser.getRole();
	}
	

}