package com.user;

public class User {

	 private int id;
	    private String fullname;
	    private String email;
	    private String password;

	    // Constructors
	    public User() {
	    }

	    public User(int id, String fullname, String email, String password) {
	        this.id = id;
	        this.fullname = fullname;
	        this.email = email;
	        this.password = password;
	    }

	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getFullname() {
	        return fullname;
	    }

	    public void setFullname(String fullname) {
	        this.fullname = fullname;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    // toString() method for easy debugging
	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", fullname='" + fullname + '\'' +
	                ", email='" + email + '\'' +
	                ", password='" + password + '\'' +
	                '}';
	    }
}
