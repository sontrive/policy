package com.hcl.policy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -8671803081992677318L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private  String name;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
	private List<UserPolicyDetails> userPolicyDetailsList = new ArrayList<>();
	
}
