package net.codejava.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "GUI_FIELDS")
public class GuiForm {
	
	@Id 
	@GeneratedValue
	@Column(name = "gui_id ")
	private long guiId ;
	
	@Column(name = "gui_type")
	private String guiType;
	
	@Column(name = "field_name")
	private String fieldName;
	
	
	@Column(name = "field_type")
	private String fieldType;
	
	
	@Column(name = "access_by")
	private String accessBy;


	public long getGuiId() {
		return guiId;
	}


	public void setGuiId(long guiId) {
		this.guiId = guiId;
	}


	public String getGuiType() {
		return guiType;
	}


	public void setGuiType(String guiType) {
		this.guiType = guiType;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public String getFieldType() {
		return fieldType;
	}


	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}


	public String getAccessBy() {
		return accessBy;
	}


	public void setAccessBy(String accessBy) {
		this.accessBy = accessBy;
	}

	/**********************************************************/
	


}
