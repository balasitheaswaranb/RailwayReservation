package com.Reservation.Railway;

public class Administrator {
	String admin = "admin";
	int password = 12345;

	public boolean isAdmin(String username, int password) {
		boolean verify = false;
		if (this.admin.equals(username)) {
			if (this.password == password) {
				verify = true;
			}
		}
		return verify;

	}

}
