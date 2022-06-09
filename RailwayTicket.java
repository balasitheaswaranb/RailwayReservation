package com.Reservation.Railway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class RailwayTicket {

	static ArrayList<Integer> confirmedTicketList;
	static Queue<Integer> racQueue;
	static Queue<Integer> waitingListQueue;
	static HashMap<Integer, Passenger> passengersDetails;

	public void ticketBooking(Passenger pD) {
		if (Seat.availableWaitingList == 0) {
			System.out.println("  No tickets are available...  ");
		} else {
			Seat.seatAllocation(pD);
			ticketAllocation(pD);
		}

	}

	public void ticketAllocation(Passenger pD) {
		
		if (pD.getAlloted() == Passenger.Alloted.LOWER || pD.getAlloted() == Passenger.Alloted.MIDDLE
				|| pD.getAlloted() == Passenger.Alloted.UPPER) {
			confirmedTicketList.add(pD.getPassengerID());

		} else if (pD.getAlloted() == Passenger.Alloted.RAC) {
			racQueue.add(pD.getPassengerID());
		} else if (pD.getAlloted() == Passenger.Alloted.WAITINGLIST) {
			waitingListQueue.add(pD.getPassengerID());
		}
		
		passengersDetails.put(pD.getPassengerID(), pD);
		System.out.println("  Passenger ID is     : " + pD.getPassengerID());
		System.out.println("  Passenger Name is   : " + pD.getName());
		System.out.println("  Passenger Age is    : " + pD.getAge());
		System.out.println("  Passenger Gender is : " + pD.getGender());
		
		if ((pD.getChildName() != null)) {
			System.out.println("  Passenger Child Name is   : " + pD.getChildName());
			System.out.println("  Passenger Child Age is    : " + pD.getChildAge());
			System.out.println("  Passenger Child Gender is : " + pD.getChildGender());
		}
		
		System.out.println("  Alloted Berth is    : " + pD.getAlloted());
		System.out.println("  ---------------Ticket Booked Successfully..!---------------\n");
	}

	public void ticketCancelling(int passengerID) {
		
		if(RailwayTicket.passengersDetails.containsKey(passengerID)) {
			Passenger pD = RailwayTicket.passengersDetails.get(passengerID);

			if (pD.getAlloted() == Passenger.Alloted.WAITINGLIST) {
				RailwayTicket.passengersDetails.remove(passengerID);
				RailwayTicket.waitingListQueue.remove(passengerID);
				Seat.availableWaitingList++;
				System.out.println("  ---------------Your Ticket is Cancelled..!---------------\n");
			} else if (pD.getAlloted() == Passenger.Alloted.RAC) {
				System.out.println("  You are not able to cancel RAC ticket");
			} else {
				RailwayTicket.passengersDetails.remove(passengerID);
				RailwayTicket.confirmedTicketList.remove(Integer.valueOf(passengerID));

				if (pD.getAlloted() == Passenger.Alloted.LOWER) {
					Seat.availableLowerBerth++;
				} else if (pD.getAlloted() == Passenger.Alloted.MIDDLE) {
					Seat.availableMiddleBerth++;
				} else if (pD.getAlloted() == Passenger.Alloted.UPPER) {
					Seat.availableUpperBerth++;
				}

				System.out.println("  ---------------Your Ticket is Cancelled..!---------------\n");

				if (RailwayTicket.racQueue.size() > 0) {
					passengerID = RailwayTicket.racQueue.poll();
					pD = RailwayTicket.passengersDetails.get(passengerID);
					Seat.seatAllocation(pD);
					RailwayTicket.confirmedTicketList.add(passengerID);
					RailwayTicket.passengersDetails.put(passengerID, pD);
					Seat.availableRAC++;

					if (RailwayTicket.waitingListQueue.size() > 0) {
						passengerID = RailwayTicket.waitingListQueue.poll();
						pD = RailwayTicket.passengersDetails.get(passengerID);
						Seat.seatAllocation(pD);
						RailwayTicket.racQueue.add(passengerID);
						RailwayTicket.passengersDetails.put(passengerID, pD);
						Seat.availableWaitingList++;
					}
				}
			}
		} else {
			System.out.println("  Please enter the valid Passenger's ID.");
		}
	}
}
