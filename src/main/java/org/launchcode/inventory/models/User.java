package org.launchcode.inventory.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	private String username;
	private String pwHash;
	private String firstName;
	private String lastName;
	private String role;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public User() {}
	
	public User(String username, String password, String firstName, String lastName, String role) {
		
		super();
		
		if (!isValidUsername(username)) {
			throw new IllegalArgumentException("Invalid username");
		}
		
		this.username = username;
		this.pwHash = hashPassword(password);
		this.firstName=firstName;
		this.lastName=lastName;
		this.role=role;
		
	}
	
	@NotNull
    @Column(name = "pwhash")
	public String getPwHash() {
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}
	
	@NotNull
    @Column(name = "username", unique = true)
	public String getUsername() {
		return username;
	}
	
	@NotNull
	@Column(name = "firstName")
	public String getFirstName(){
		return firstName;
	}
	
	@SuppressWarnings("unused")
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull
	@Column(name = "lastName")
	public String getLastName(){
		return lastName;
	}
	
	@SuppressWarnings("unused")
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotNull
	@Column(name = "user_role")
	public String getRole(){
		return role;
	}
	
	@SuppressWarnings("unused")
	private void setRole(String role) {
		this.role = role;
	}

	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, pwHash);
	}
	
	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean isValidUsername(String username) {
		Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
		Matcher matcher = validUsernamePattern.matcher(username);
		return matcher.matches();
	}

}
