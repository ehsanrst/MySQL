package com.happyman.model;

import java.util.Date;

public class Actor {

	public Actor() {

	}

	public static final int EHSAN = 20;

	private Integer id;
	private String firstName;
	private String lastName;
	private Date lastUpdate;

	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", lastUpdate=" + lastUpdate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
