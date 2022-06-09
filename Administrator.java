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

	public void assignSeats(int totalSeats) {

		Seat.availableLowerBerth = totalSeats * 25 / 100;
		Seat.availableMiddleBerth = totalSeats * 25 / 100;
		Seat.availableUpperBerth = totalSeats * 25 / 100;
		if (totalSeats >= 10) {
			Seat.availableRAC = totalSeats * 15 / 100;
			Seat.availableWaitingList = totalSeats * 10 / 100;
		} else {
			Seat.availableRAC = 1;
			Seat.availableWaitingList = 1;
		}
	}

}
