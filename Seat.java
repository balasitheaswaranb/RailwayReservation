package com.Reservation.Railway;

public class Seat {
	static int availableLowerBerth = 1;
	static int availableUpperBerth = 1;
	static int availableMiddleBerth = 1;
	static int availableRAC = 1;
	static int availableWaitingList = 1;
	private static Berthpreference berthpreference;

	public static Berthpreference getBerthPreference() {
		return berthpreference;
	}

	public static void setBerthPreference(Berthpreference berthPreference) {
		berthpreference = berthPreference;
	}

	enum Berthpreference {
		LOWER, MIDDLE, UPPER;
	}

	public static void seatAllocation(Passenger pD) {
		if (pD.getAge() > 60 && Seat.availableLowerBerth > 0) {
			System.out.println("  You're a Senior citizen, so we allocate a Lower Berth.");
			Seat.availableLowerBerth--;
			pD.setAlloted(Passenger.Alloted.LOWER);
		} else if (pD.getChildName() != null && Seat.availableLowerBerth > 0) {
			System.out.println("  You have a child, so we allocate a Lower Berth...  ");
			Seat.availableLowerBerth--;
			pD.setAlloted(Passenger.Alloted.LOWER);
		} else if ((getBerthPreference() == Seat.Berthpreference.LOWER && Seat.availableLowerBerth > 0)
				|| (getBerthPreference() == Seat.Berthpreference.MIDDLE && Seat.availableMiddleBerth > 0)
				|| (getBerthPreference() == Seat.Berthpreference.UPPER && Seat.availableUpperBerth > 0)) {
			if (getBerthPreference() == Seat.Berthpreference.LOWER) {
				System.out.println("  Lower Berth is Alloted...   ");
				Seat.availableLowerBerth--;
				pD.setAlloted(Passenger.Alloted.LOWER);
			} else if (getBerthPreference() == Seat.Berthpreference.MIDDLE) {
				System.out.println("  Middle Berth is Alloted...  ");
				Seat.availableMiddleBerth--;
				pD.setAlloted(Passenger.Alloted.MIDDLE);
			} else if (getBerthPreference() == Seat.Berthpreference.UPPER) {
				System.out.println("  Upper Berth is Alloted...  ");
				Seat.availableUpperBerth--;
				pD.setAlloted(Passenger.Alloted.UPPER);
			}
		} else if (Seat.availableLowerBerth > 0) {
			System.out.println("  Prefered berth is not available...  ");
			System.out.println("  Lower Berth is Alloted...  ");
			Seat.availableLowerBerth--;
			pD.setAlloted(Passenger.Alloted.LOWER);
		} else if (Seat.availableMiddleBerth > 0) {
			System.out.println("  Prefered berth is not available...  ");
			System.out.println("  Middle Berth is Alloted...  ");
			Seat.availableMiddleBerth--;
			pD.setAlloted(Passenger.Alloted.MIDDLE);
		} else if (Seat.availableUpperBerth > 0) {
			System.out.println("  Prefered berth is not available...  ");
			System.out.println("  Upper Berth is Alloted...   ");
			Seat.availableUpperBerth--;
			pD.setAlloted(Passenger.Alloted.UPPER);
		}

		else if (Seat.availableRAC > 0) {
			System.out.println("  Prefered berth is not available...  ");
			System.out.println("  Your're in RAC List...   ");
			Seat.availableRAC--;
			pD.setAlloted(Passenger.Alloted.RAC);
		} else if (Seat.availableWaitingList > 0) {
			System.out.println("  Prefered berth is not available...  ");
			System.out.println("  You're in Waiting List...  ");
			Seat.availableWaitingList--;
			pD.setAlloted(Passenger.Alloted.WAITINGLIST);
		}
	}

}
