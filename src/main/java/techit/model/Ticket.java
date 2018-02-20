package techit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long id;					// Ticket's unique id.
	
	private String subject;			// Subject of the ticket.
	private String details;			// Text concerning the project.
	
	@Column(name = "start_dt")
	private Date startDate;			// Project's starting date.
	
	@Column(name = "end_dt")		
	private Date endDate; 			// When the project was completed.
	
	private String location;		// Location where the project is.
	
	@ElementCollection
	private List<UpdateDetails> updates;	// List of all updates that was made to the ticket.
	
	@Column(name = "completion_details")
	private String completionDetails;		// Information pertaining vendors, cost, materials used.
	
	@Enumerated(EnumType.STRING)
	private Progress progress;		// Current progress of the ticket
	
	@Enumerated(EnumType.STRING)
	private Priority priority;		// Importance or level of urgency of the ticket
	
	@ManyToOne
	private Unit unit;
	
	@ManyToOne
	private User requesterDetails;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "assignments", joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "technician_id", referencedColumnName = "id"))
	private List<User> technicians;
	
	public enum Progress { OPEN, IN_PROGRESS, ON_HOLD, COMPLETED, CLOSED }
	public enum Priority { NOT_ASSIGNED, LOW, MEDIUM, HIGH }
	
	// Default Constructor.
	public Ticket() {
	}

	// Constructor with all the fields.
	public Ticket(Long id, String subject, String details, Date startDate, Date endDate, String location,
			List<UpdateDetails> updates, String completionDetails, Progress progress, Priority priority, Unit unit,
			User requesterDetails, List<User> technicians) {
		super();
		this.id = id;
		this.subject = subject;
		this.details = details;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.updates = updates;
		this.completionDetails = completionDetails;
		this.progress = progress;
		this.priority = priority;
		this.unit = unit;
		this.requesterDetails = requesterDetails;
		this.technicians = technicians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<UpdateDetails> getUpdates() {
		return updates;
	}

	public void setUpdates(List<UpdateDetails> updates) {
		this.updates = updates;
	}

	public String getCompletionDetails() {
		return completionDetails;
	}

	public void setCompletionDetails(String completionDetails) {
		this.completionDetails = completionDetails;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public User getRequesterDetails() {
		return requesterDetails;
	}

	public void setRequesterDetails(User requesterDetails) {
		this.requesterDetails = requesterDetails;
	}

	public List<User> getTechnicians() {
		return technicians;
	}

	public void setTechnicians(List<User> technicians) {
		this.technicians = technicians;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", subject=" + subject + ", details=" + details + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", location=" + location + ", updates=" + updates + ", completionDetails="
				+ completionDetails + ", progress=" + progress + ", priority=" + priority + ", unit=" + unit
				+ ", requesterDetails=" + requesterDetails + ", technicians=" + technicians + "]";
	}

}
