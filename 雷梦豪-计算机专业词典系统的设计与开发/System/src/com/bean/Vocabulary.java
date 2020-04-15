package com.bean;

import java.math.BigInteger;

public class Vocabulary {
	private int id;
	private String abbreviation;//缩写
	private String fullWords;//完整词汇
	private String specificMeanning;//具体翻译
	private String lemmaSummary;//词条概述
	private String address;
	private long priority;//优先级
	private String remark;
	
	
	public Vocabulary(int id, String abbreviation, String fullWords, String specificMeanning, String lemmaSummary,
			String address, long priority,String remark) {
		super();
		this.id = id;
		this.abbreviation = abbreviation;
		this.fullWords = fullWords;
		this.specificMeanning = specificMeanning;
		this.lemmaSummary = lemmaSummary;
		this.address = address;
		this.priority = priority;
		this.remark=remark;
	}

	public Vocabulary() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getFullWords() {
		return fullWords;
	}
	public void setFullWords(String fullWords) {
		this.fullWords = fullWords;
	}
	public String getSpecificMeanning() {
		return specificMeanning;
	}
	public void setSpecificMeanning(String specificMeanning) {
		this.specificMeanning = specificMeanning;
	}
	public String getLemmaSummary() {
		return lemmaSummary;
	}
	public void setLemmaSummary(String lemmaSummary) {
		this.lemmaSummary = lemmaSummary;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPriority() {
		return priority;
	}
	public void setPriority(long priority) {
		this.priority = priority;
	}
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Vocabulary [id=" + id + ", abbreviation=" + abbreviation + ", fullWords=" + fullWords
				+ ", specificMeanning=" + specificMeanning + ", lemmaSummary=" + lemmaSummary + ", address=" + address
				+ ", priority=" + priority + ", remark=" + remark + "]";
	}
	

	
}
