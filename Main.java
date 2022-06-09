package com.Reservation.Railway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int id = 1;

		RailwayTicket.confirmedTicketList = new ArrayList<Integer>();
		RailwayTicket.racQueue = new LinkedList<Integer>();
		RailwayTicket.waitingListQueue = new LinkedList<Integer>();
		RailwayTicket.passengersDetails = new HashMap<Integer, Passenger>();

		RailwayTicket ticket = new RailwayTicket();
		TicketList list = new TicketList();
		Administrator admin = new Administrator();

		System.out.println("\nWelcome to the Railway Reservation System...");
		boolean flag = true;

		while (flag == true) {
			try {
				System.out.println("\n  1. Admin\n  2. Passenger\n");
				System.out.print("  Enter your choice : ");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.print("  Username : ");
					String username = sc.next();
					System.out.print("  Password : ");
					int password = sc.nextInt();
					if (admin.isAdmin(username, password)) {
						System.out.print("  Enter number of total tickets : ");
						admin.assignSeats(sc.nextInt());
					} else {
						System.out.println("  Invalid Username and Password");
					}
					break;
				case 2:
					flag = false;
				}
			} catch (Exception e) {
				System.out.println(e);
			    sc.next();
			}
		}
		flag = true;
		while (flag) {
			try {
				System.out.println(
						"  *************************\n  1. Book Ticket.\n  2. Cancel Ticket\n  3. Booked Tickets List.\n "
								+ " 4. Available Tickets List.\n  5. Exit.\n  *************************\n ");
				System.out.print("  Enter your choice : ");
				int choice = sc.nextInt();
				if (choice <= 5) {
					switch (choice) {
					case 1:
						Passenger passengerInput = new Passenger();
						System.out.print("  Enter passenger's Name                                     : ");
						passengerInput.setName(sc.next());
						System.out.print("  Enter passenger's Age                                      : ");
						int age = sc.nextInt();
						if (age <= 100 && age > 5) {
							passengerInput.setAge(age);
						} else {
							System.out.println("  Pleas enter valid age ");
							break;
						}
						System.out.print("  Enter passenger's Gender as [MALE press 1 ,FEMALE press 2] : ");
						int gender = sc.nextInt();
						if (gender == 1) {
							passengerInput.setGender(Passenger.Gender.MALE);
						} else if (gender == 2) {
							passengerInput.setGender(Passenger.Gender.FEMALE);
						} else {
							System.out.println("  Please enter the correct option !!");
							break;
						}
						if (passengerInput.getGender() == Passenger.Gender.MALE
								|| passengerInput.getGender() == Passenger.Gender.FEMALE) {
							System.out.println("\tYou travel with your child under 5 or equal old ?");
							System.out.println("\t1. Yes.");
							System.out.println("\t2. No means click any number.");
							System.out.print("\tEnter your choice : ");
							int gChoice = sc.nextInt();
							if (gChoice == 1) {
								System.out.print("\tEnter your child's Name               : ");
								passengerInput.setChildName(sc.next());
								System.out.print("\tEnter your child's Age                : ");
								int childAge = sc.nextInt();
								if (childAge <= 5) {
									passengerInput.setChildAge(childAge);
								} else {
									System.out.println("  please enter valid age !! ");
									break;
								}
								System.out.print("\tEnter your child's Gender as [FEMALE or MALE] : ");
								passengerInput.setChildGender(sc.next());
							} else {
								passengerInput.setChildName(null);
								passengerInput.setChildAge(0);
								passengerInput.setChildGender(null);
							}
						}
						System.out.print(
								"  Enter passenger's Berth Preference as [UPPER press 1 or MIDDLE press 2 or LOWER press 3 ] : ");
						int berth = sc.nextInt();
						if (berth == 1) {
							Seat.setBerthPreference(Seat.Berthpreference.UPPER);
						} else if (berth == 2) {
							Seat.setBerthPreference(Seat.Berthpreference.MIDDLE);
						} else if (berth == 3) {
							Seat.setBerthPreference(Seat.Berthpreference.LOWER);
						} else {
							System.out.println("  Enter the correct option!!");
						}
						passengerInput.setPassengerID(id++);
						ticket.ticketBooking(passengerInput);
						break;
					case 2:
						System.out.print("  Enter your PassengerID : ");
						int passengerID = sc.nextInt();
						ticket.ticketCancelling(passengerID);
						break;
					case 3:
						list.bookedTicketList();
						break;
					case 4:
						list.ticketAvailabilityList();
						break;
					case 5:
						System.out.println("..........Thankyou please visit again..........");
						flag = false;
						break;
					}
				} else {
					System.out.println("  Please enter correct option ? ");
					flag = true;
				}
			} catch (Exception e) {
				System.out.println(e);
				sc.next();
			}
		}
		sc.close();
	}
}