package com.ardi.belajar.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Catering {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String cateringName;
	private String alamat;
	@ManyToMany
	private List<Beverages> beverages;
	
	public Catering(){
		super();
	}
	
	public Catering(String cateringName, String alamat, List<Beverages> beverages){
		this.cateringName = cateringName;
		this.alamat = alamat;
		this.beverages = beverages;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getCateringName() {
		return cateringName;
	}
	
	public void setCateringName(String cateringName) {
		this.cateringName = cateringName;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public List<Beverages> getBeverages(){
		return beverages;
	}
	
	public void setBeverages(List<Beverages> beverages){
		this.beverages = beverages;
	}
	
	public boolean hasBeverages(Beverages beverages){
		for(Beverages containedBeverages: getBeverages()){
			if(containedBeverages.getId() == beverages.getId()){
				return true;
			}
		}
		return false;
	}
}
