package techit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName; 			// User's first name

	@Column(name = "last_name", nullable = false)
	private String lastName; 			// User's last name

	private String email;
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Position post;
	
	@OneToMany(mappedBy="requesterDetails")
	private List<Ticket> ticketsRequested;
	
	@ManyToMany(mappedBy="technicians")
	private List<Ticket> ticketsAssigned;
	
	@ManyToOne
	private Unit unit;				// Unit that this user belongs to.
	
	// Types of users on the system.
	public enum Position {
		SYS_ADMIN, SUPERVISING_TECHNICIAN, TECHNICIAN, USER
	}

	// Default Contructor.
	public User() {
	} 
	
	// Simple constructor for regular users ( students )
	public User(Long id, String firstname, String lastname, String username, String password) {

		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.username = username;
		this.password = password;
		this.post = Position.USER;
	}
	
	

	public User(Long id, String username, String password, String firstName, String lastName, String email,
			String phone, Position post, Unit unit) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.post = post;
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Position getPost() {
		return post;
	}

	public void setPost(Position post) {
		this.post = post;
	}

	public String getPostString() {
		switch (getPost()) {
		case SYS_ADMIN:
			return "SYSTEM ADMINISTRATOR";
		case SUPERVISING_TECHNICIAN:
			return "SUPERVISING TECHNICIAN";
		case TECHNICIAN:
			return "TECHNICIAN";
		default:
			return "USER";
		}
	}

	public List<Ticket> getTicketsRequested() {
		return ticketsRequested;
	}

	public void setTicketsRequested(List<Ticket> ticketsRequested) {
		this.ticketsRequested = ticketsRequested;
	}

	public List<Ticket> getTicketsAssigned() {
		return ticketsAssigned;
	}

	public void setTicketsAssigned(List<Ticket> ticketsAssigned) {
		this.ticketsAssigned = ticketsAssigned;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "First name: " + getFirstName() + "\n" + "Last name:" + getLastName() + "\n" + "User name:"
				+ getUsername() + "\n" + "Phone number:" + getPhone() + "\n" + "Email:" + getEmail() + "\n" + "Post:"
				+ getPostString() + "\n";
	}

}
