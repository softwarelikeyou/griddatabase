package com.softwarelikeyou.model.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="ERCOTFile")
@XmlRootElement(name="ERCOTFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class File implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	public File() { }
	
	public File(final String name, final String url, final Date downloaded) {
		this();
		this.name = name;
		this.url = url;
		this.downloaded = downloaded;
	}
	
	@Id
	@Column(name="name", unique=true, nullable=false)
	@XmlElement(name="name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String value) {
		this.name = value;
	}
	
	@Column(name="url", nullable=false)
	@XmlElement(name="url")
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(final String value) {
		this.url = value;
	}
	
	@Column(name="downloaded", nullable=false)
	@XmlElement(name="downloaded")
	private Date downloaded = new Date();
	public Date getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(final Date value) {
		this.downloaded = value;
	}
	
	@XmlEnum
	public enum MimeType {
		@XmlEnumValue(value = "XML")
		XML, 
		@XmlEnumValue(value = "CSV")
		CSV;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="mimeType", nullable=false, length=10)
    @XmlElement(name="mimeType")
	private MimeType mimeType = MimeType.XML;
	public MimeType getMimeType() {
		return mimeType;
	}
	public void setMimeType(final MimeType mimeType) {
		this.mimeType = mimeType;
	}
	
	@XmlEnum
	public enum FileType {
		UNKNOWN, 
		ASCPC,
		RTDAM,
		RTSPP,
		H48DAMHP,
		H48DAMAS,
		RTLMP; 
	}
	@Enumerated(EnumType.STRING)
	@Column(name="fileType", nullable=false)
	@XmlElement(name="fileType")
	private FileType type = FileType.UNKNOWN;
	public FileType getType() {
		return type;
	}
	public void setType(final FileType type) {
		this.type = type;
	}
	
	public static Date stringToDate(final String source) throws ParseException {
		Date result = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");            
	    result = df.parse(source);
		return result;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(name)
		.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if( obj == null ) return false;
		if( this == obj ) return true;
		if( getClass() != obj.getClass() ) return false;

		File rhs = (File) obj;
        
		return new EqualsBuilder()
		.append(this.name, rhs.name)
		.isEquals();
	}
}