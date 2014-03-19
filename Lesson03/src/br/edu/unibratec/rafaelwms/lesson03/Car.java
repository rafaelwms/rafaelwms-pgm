package br.edu.unibratec.rafaelwms.lesson03;

import java.io.Serializable;

public class Car implements Serializable{
	
	private Integer id;
	private String industry;
	private String model;
	private String fuel;
	private String year;
	
	
	public Car(){}
	
	public Car(String industry, String model, String fuel, String year){
		this.setIndustry(industry);
		this.setModel(model);
		this.setYear(year);
		this.setFuel(fuel);		
	}
	
	public Car(Integer id, String industry, String model, String fuel, String year){
		this.setId(id);
		this.setIndustry(industry);
		this.setModel(model);
		this.setYear(year);
		this.setFuel(fuel);		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
		result = prime * result
				+ ((industry == null) ? 0 : industry.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (fuel == null) {
			if (other.fuel != null)
				return false;
		} else if (!fuel.equals(other.fuel))
			return false;
		if (industry == null) {
			if (other.industry != null)
				return false;
		} else if (!industry.equals(other.industry))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}



}
