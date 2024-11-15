package com.hibernate.HotelManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="guest_id")
	private long id;
	private String name;
	private String contact_number;
	private int room_number;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	
	@Override
	public String toString() {
		return "Reservations [id=" + id + ", name=" + name + ", contact_number=" + contact_number + ", room_number="
				+ room_number + "]";
	}
	
}
