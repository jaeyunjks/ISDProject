package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String dob;
    private String gender;

    // Default constructor
    public User() {
    }

    // Constructor with all fields
    public User(int id, String name, String email, String password, String dob, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    // Constructor without ID (for new users before persistence)
    public User(String name, String email, String password, String dob, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) { // Basic validation
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid gender value.");
        }
    }

    // Override toString() for better object representation
    @Override
    public String toString() {
        return "User { " +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", DOB='" + dob + '\'' +
                ", Gender='" + gender + '\'' +
                " }";
    }
}

