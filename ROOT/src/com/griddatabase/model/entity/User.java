package com.griddatabase.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="user")
@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@XmlAttribute(name="id")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	
	@Column(name="userName", nullable=false, unique=true)
	private String userName;
	@Override
	public String getUsername() {
		return userName;
	}
	public void setUsername(final String username) {
		this.userName = username;
	}
	
	@Column(name="password", nullable=false)
	private String password = "password";
	@Override
	public String getPassword() {
	   return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	
	@Column(name="display", nullable=false)
	private String display;
	public String getDisplay() {
		return display;
	}
	public void setDisplay(final String display) {
		this.display = display;
	}
	
	@Override
	public GrantedAuthority[] getAuthorities() {
		return null;
	}

	@Column(name="enabled", nullable=false)
	private boolean enabled = true;
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}
	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	public enum UserType {
		ADMINISTRATOR, 
		PREMIUM,
		FREEMIUM;
		
		public static UserType get(final String type) {
			for (UserType userType : UserType.values()) {
			   if (userType.toString().equals(type)) 
				   return userType;
			}
			return null;
		}
	}
	@Enumerated(EnumType.STRING)
	@Column(name="userType", nullable=false)
	@XmlElement(name="userType")
	private UserType type = UserType.FREEMIUM;
	public UserType getType() {
		return type;
	}
	public void setType(final UserType type) {
		this.type = type;
	}
	
	public boolean isAdministrator() { 
		return UserType.ADMINISTRATOR.equals(type);
	}

	public boolean isPremium() { 
		return UserType.PREMIUM.equals(type);
	}
	
	public boolean isFreemium() { 
		return UserType.FREEMIUM.equals(type);
	}
	
	@Transient
	protected Boolean selected = false;
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(id)
		.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if( obj == null ) return false;
		if( this == obj ) return true;
		if( getClass() != obj.getClass() ) return false;

		User rhs = (User) obj;
        
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.isEquals();
	}
}