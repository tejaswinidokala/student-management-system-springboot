package com.demo.entity;

import jakarta.persistence.*;

//This is the LOGIN account table. It is kept separate from Student on purpose,
//so the existing Student entity/table is not touched at all.
@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password; // stored as a SHA-256 hash, never plain text

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
