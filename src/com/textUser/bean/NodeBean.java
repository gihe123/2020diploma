package com.textUser.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="t_Menu")
public class NodeBean implements Serializable {
	@Id
	private String id;
	@Basic
	private String text;
	@Basic
	private Boolean leaf;
	@Basic
	private String fatherid;
	@Basic
	private String description;
	public NodeBean() {
		// TODO 自动生成的构造函数存根
	}
	public NodeBean(String id, String text, Boolean leaf,
			 String fatherid, String description) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.fatherid = fatherid;
		this.description = description;
	}
	public NodeBean(String id, String text, Boolean leaf, String fatherid) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.fatherid = fatherid;
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String gettext() {
		return text;
	}
	public void settext(String text) {
		this.text = text;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	
}
