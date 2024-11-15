package com.hibernate.HotelManagement;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class App 
{
	private static SessionFactory factory;

	public static void main( String[] args )
    {
		try {
       	 	Configuration con = new Configuration().configure().addAnnotatedClass(Reservations.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            factory = con.buildSessionFactory(reg); 
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
            	System.out.println();
            	System.out.println("----- Welcome to Hotel Reservation System -----");
            	System.out.println();
            	System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get a room number");
                System.out.println("4. Update a reservation");
                System.out.println("5. Delete a reservation");
                System.out.println("6. Exit");
                System.out.println("Choose an option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        HotelManagement.reserveRoom(factory, scanner);
                        break;
                    case 2:
                    	HotelManagement.viewReservations(factory);
                        break;
                    case 3:
                    	HotelManagement.getRoom(factory, scanner);
                        break;
                    case 4:
                    	HotelManagement.updateReservation(factory, scanner);
                        break;
                    case 5:
                    	HotelManagement.deleteReservation(factory, scanner);
                        break;
                    case 6:
                    	HotelManagement.exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}
