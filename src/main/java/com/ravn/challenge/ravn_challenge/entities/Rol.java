package com.ravn.challenge.ravn_challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Table(name = "rol")
@Entity
public class Rol {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,name="id_rol")
	private Integer Id;
	
	@Column(nullable = false,name = "rol_name")
	private String rolName;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	@Override
	public String toString() {
		return "Rol [Id=" + Id + ", rolName=" + rolName + "]";
	}	

}
