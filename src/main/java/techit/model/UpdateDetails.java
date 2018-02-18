package techit.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UpdateDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String modifier; // modifier's username
	private String updateDetails;
	private String modifiedDate;
	
	public UpdateDetails() {}
	
	public UpdateDetails(String modifier, String updateDetails, String modifiedDate) {
		
		this.modifier = modifier;
		this.updateDetails = updateDetails;
		this.modifiedDate = modifiedDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
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

	@Override
	public String toString() {
		return "UpdateDetails [modifier=" + modifier + ", updateDetails=" + updateDetails + ", modifiedDate="
				+ modifiedDate + "]";
	}
	
	
}
