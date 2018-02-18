package techit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long id;		// Ticket's unique id.
	
	private String subject;			// Subject of the ticket.
	private String details; 		// Text concerning the project.
	private Date startDate; 		// Project's starting date.
	private Date endDate; 			// When the project was completed.
	
	@Column(name = "completion_details")
	private String completionDetails;		// Information pertaining vendors, cost, materials used.
	private Progress progress;		// Current progress of the ticket
	private Priority priority;		// Importance or level of urgency of the ticket
	private String location;		// Location where the project is.
	
	@ElementCollection
	private List<UpdateDetails> updates;	// List of all updates that was made to the ticket.
	
	@ManyToOne
	private Unit unit;
	
	@ManyToOne
	private User requesterDetails;
	
	public enum Progress { OPEN, IN_PROGRESS, ON_HOLD, COMPLETED, CLOSED }
	public enum Priority { NOT_ASSIGNED, LOW, MEDIUM, HIGH }
		
	public Ticket() {
	}

	public Ticket(Long id, String subject, String details, Date startDate,
			Progress progress, Priority priority, String location, Unit unit,
			User requesterDetails) {
		this.id = id;
		this.subject = subject;
		this.details = details;
		this.startDate = startDate;
		this.endDate = null;
		this.completionDetails = null;
		this.progress = progress;
		this.priority = priority;
		this.location = location;
		this.updates = null;
		this.unit = unit;
		this.requesterDetails = requesterDetails;
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

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", subject=" + subject + ", details=" + details + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", completionDetails=" + completionDetails + ", progress=" + progress
				+ ", priority=" + priority + ", location=" + location + ", updates=" + updates + ", unit=" + unit
				+ ", requesterDetails=" + requesterDetails + "]";
	}
	
	
}
