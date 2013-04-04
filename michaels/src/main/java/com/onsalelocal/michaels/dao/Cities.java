package com.onsalelocal.michaels.dao;

import java.math.BigDecimal;

public class Cities {
   
	
	private String id;
	private String City;
	private String State;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String Population;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public String getPopulation() {
		return Population;
	}
	public void setPopulation(String population) {
		Population = population;
	}
	public String getCityname() {
		return City;
	}
	public void setCityname(String cityname) {
		City = cityname;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
}
