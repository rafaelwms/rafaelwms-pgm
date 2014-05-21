package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;

public class Tutorial implements Serializable{
	
	private String name;
	private String image;
	private String desc;
	
	
	
	
	public Tutorial(String name, String image, String desc) {
		this.setName(name);
		this.setImage(image);
		this.setDesc(desc);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
