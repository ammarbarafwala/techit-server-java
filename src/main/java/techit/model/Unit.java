package techit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "unit")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue
	private int id; 
	 
	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private String phone;
	
	@Column
	private String location;
	
	@Column
	private String email;
	
	@Column
	private String description;
	
	@OneToMany
	private List<User> supervisor; 
	
	public Unit(){
		
	}

	public Unit(int id, String name, String phone, String location, String email, String description) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.location = location;
		this.email = email;
		this.description = description;

	}

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
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setContact(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
