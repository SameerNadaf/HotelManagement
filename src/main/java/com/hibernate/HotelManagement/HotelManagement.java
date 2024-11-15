package com.hibernate.HotelManagement;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HotelManagement {

	public static void reserveRoom(SessionFactory factory, Scanner scanner) {
		
		System.out.println();
	    System.out.println("Enter guest name : ");
	    String name = scanner.next();
	    System.out.println("Enter Contact Number : ");
	    String number = scanner.next();
	    System.out.println("Enter Room Number : ");
	    int room = scanner.nextInt();

	    Transaction transaction = null;

	    try (Session session = factory.openSession()) {
	    	transaction = session.beginTransaction();
	    	Reservations res = new Reservations();
	    	res.setName(name);
	    	res.setContact_number(number);
	    	res.setRoom_number(room);
	    	
	    	session.persist(res);
	    	transaction.commit();
	    	
	    	System.out.println();
	    	System.out.println("Reservation has been made successfully.");
	    	
	    } catch (Exception e) {
	    	if (transaction != null) transaction.rollback();
	    	System.err.println("Error while saving reservation: " + e.getMessage());
	    }
	}

	public static void viewReservations(SessionFactory factory) {

	    Transaction transaction = null;

		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			Query<Reservations> query = session.createQuery("FROM Reservations", Reservations.class);
			List<Reservations> reservations = query.list();
			
			System.out.println();
			System.out.println("Reservations list");
			
			for (Reservations res : reservations) {
				System.out.println("--------------------------------------------");
	            System.out.println("Guest ID     : " + res.getId());
	            System.out.println("Guest Name   : " + res.getName());
	            System.out.println("Phone Number : " + res.getContact_number());
	            System.out.println("Room Number  : " + res.getRoom_number());
	            System.out.println("--------------------------------------------");
	            System.out.println();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			System.err.println("Error while fetching reservation: " + e.getMessage());
		}
	}

	public static void getRoom(SessionFactory factory, Scanner scanner) {
		
		System.out.println();
	    System.out.println("Enter Guest ID : ");
	    long id = scanner.nextLong();

	    Transaction transaction = null;

	    try (Session session = factory.openSession()) {
	    	transaction = session.beginTransaction();

	    	CriteriaBuilder criteria = session.getCriteriaBuilder();
	    	CriteriaQuery<Reservations> cq = criteria.createQuery(Reservations.class);
	    	Root<Reservations> root = cq.from(Reservations.class);
	    	cq.select(root).where(criteria.equal(root.get("id"),id));
	    	Reservations reservation = session.createQuery(cq).getSingleResult();
	    	
	    	if (reservation != null) {
	    		System.out.println();
	    		System.out.println("Guest ID : " + reservation.getId() + ", Guest Name : " + reservation.getName() 
	    		+ ", Phone Number : " + reservation.getContact_number() + ", Room Number : " + reservation.getRoom_number());
	    	}
	    	else {
	    		System.out.println();
	    		System.out.println("Room not found with the Guest ID " + id);
	    	}
	    	transaction.commit();
	    } catch (Exception e) {
	    	if (transaction != null) transaction.rollback();
	    	System.err.println("Error while fetching room details: " + e.getMessage());
	    }
	}

	public static void updateReservation(SessionFactory factory, Scanner scanner) {
		
		System.out.println();
	    System.out.println("Enter Guest ID : ");
	    long id = scanner.nextLong();

	    Transaction transaction = null;

	    try (Session session = factory.openSession()) {
	    	transaction = session.beginTransaction();
	    	
	    	Reservations reservation = (Reservations) session.get(Reservations.class, id);
	    	
	    	if (reservation != null) {
	    		System.out.println();
	    	    System.out.println("Enter guest name : ");
	    	    String name = scanner.next();
	    	    System.out.println("Enter Contact Number : ");
	    	    String number = scanner.next();
	    	    System.out.println("Enter Room Number : ");
	    	    int room = scanner.nextInt();
	    	    
	    	    reservation.setName(name);
	    	    reservation.setContact_number(number);
	    	    reservation.setRoom_number(room);
	    	    
	    	    session.merge(reservation);
	    	    transaction.commit();
	    	    
	    	    System.out.println();
	    	    System.out.println("Reservation has been updated successfully.");
	    	}
	    	else {
	    		System.out.println();
	    		System.out.println("Guest not found with the Guest ID " + id);
	    	}
	    } catch (Exception e) {
	    	if (transaction != null) transaction.rollback();
	    	System.err.println("Error while updating reservation: " + e.getMessage());
	    }
	}

	public static void deleteReservation(SessionFactory factory, Scanner scanner) {
		
		System.out.println();
	    System.out.println("Enter Guest ID : ");
	    long id = scanner.nextLong();

	    Transaction transaction = null;

	    try (Session session = factory.openSession()) {
	    	transaction = session.beginTransaction();
	    	
	    	Reservations reservation = (Reservations) session.get(Reservations.class, id);
	    	
	    	if (reservation != null) {
	    		session.remove(reservation);
	    		transaction.commit();
	    		
	    		System.out.println();
	    		System.out.println("Reservation has been deleted successfully.");
	    	}
	    	else {
	    		System.out.println();
	    		System.out.println("Guest not found with the Guest ID " + id);
	    	}
	    } catch (Exception e) {
	    	if (transaction != null) transaction.rollback();
	    	System.err.println("Error while deleting reservation: " + e.getMessage());
	    }
	}
	
	public static void exit() throws InterruptedException {
        System.out.print("Exiting Hotel Reservation");
        int i = 5;
        while (i!=0) {
            System.out.print(".");
            Thread.sleep(300);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using Hotel Reservation System!!!");
    }
	
}
