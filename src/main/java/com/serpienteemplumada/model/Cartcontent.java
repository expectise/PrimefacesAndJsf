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
@Table(name = "cartcontent", catalog = "serpiente")
public class Cartcontent implements Serializable {
	private static final long serialVersionUID = -947078734936123005L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCartcontent", unique = true, nullable = false)
	private Long idCartcontent;
	
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
	
	@Column(name = "IdProducts", nullable = false)
	private Long idProducts;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Products.class)
	@JoinColumn(name = "IdProducts", nullable = false, insertable = false, updatable = false)
	private Products products;
	
	@Column(name = "IdCart", nullable = false)
	private Long idCart;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Cart.class)
	@JoinColumn(name = "IdCart", nullable = false, insertable = false, updatable = false)
	private Cart cart;

}
