package com.serpienteemplumada.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
@Table(name = "address", catalog = "serpiente")
public class Address  implements Serializable  {
	private static final long serialVersionUID = -9171994297188476284L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdAddress", unique = true, nullable = false)
	private Long idAddress;

	@Column(name = "IdCountries", nullable = false)
	private Long idCountries;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Countries.class)
	@JoinColumn(name = "IdCountries", nullable = false, insertable = false, updatable = false)
	private Countries countries;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;
	
	@Column(name = "Active", nullable = false)
	@ColumnDefault("1")
	private Boolean active;
	
	@Column(name = "Current", nullable = false)
	@ColumnDefault("0")
	private Boolean current;
	
	@Column(name = "State", length = 50)
	private String state;
	
	@Column(name = "PostalCode", length = 7)
	private String postalCode;
	
	@Column(name = "City", length = 50)
	private String city;
	
	@Column(name = "Street", length = 60)
	private String street;
	
	@Column(name = "ExtNo", length = 10)
	private String extNo;
	
	@Column(name = "IntNo", length = 7)
	private String intNo;
	
	@Column(name = "Firstname", length = 60)
	private String firstname;
	
	@Column(name = "Middlename", length = 60)
	private String middlename;
	
	@Column(name = "Lastname", length = 60)
	private String lastname;
	
	@Column(name = "Phone", length = 15)
	private String phone;
	
	@Column(name = "Observations", length = 40)
	private String observations;
	
}
