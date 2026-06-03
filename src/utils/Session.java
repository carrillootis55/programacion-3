package utils;

import models.Teacher;

public class Session {
	
	private static Teacher currentUser;
	
	public static void login(Teacher user) {
		currentUser = user;
	}
	
	public static Teacher getCurrentUser() {
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