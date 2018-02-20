package techit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UpdateDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(
	        name = "modifier", 
	        referencedColumnName = "username"
	    )
	private User modifier; // modifier's username
	
	private String updateDetails;
	private String modifiedDate;
	
	@Column(name = "lastUpdate_dt")
	private Date lastUpdatedDate;	// Last date where changes were made to the ticket.
	
	//Default Constructor.
	public UpdateDetails() {
		
	}

	public UpdateDetails(User modifier, String updateDetails, String modifiedDate, Date lastUpdatedDate) {
		super();
		this.modifier = modifier;
		this.updateDetails = updateDetails;
		this.modifiedDate = modifiedDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public String getUpdateDetails() {
		return updateDetails;
	}

	public void setUpdateDetails(String updateDetails) {
		this.updateDetails = updateDetails;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UpdateDetails [modifier=" + modifier + ", updateDetails=" + updateDetails + ", modifiedDate="
				+ modifiedDate + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}
	
}
