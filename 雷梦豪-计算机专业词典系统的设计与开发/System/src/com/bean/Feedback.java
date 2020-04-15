package com.bean;

public class Feedback {
	private int f_id;
	private int v_id;//词汇表中词汇的id
	private String f_abbreviation;
	private String f_fullWords;
	private String f_specificMeanning ;//用户提交上来的反馈的词义
	private String f_lemmaSummary;
	private String f_username;
	private String f_remark;//用户提交上来的反馈的详细信息
	
	public Feedback(int f_id, int v_id, String f_abbreviation, String f_fullWords, String f_specificMeanning,
			String f_lemmaSummary, String f_username, String f_remark) {
		super();
		this.f_id = f_id;
		this.v_id = v_id;
		this.f_abbreviation = f_abbreviation;
		this.f_fullWords = f_fullWords;
		this.f_specificMeanning = f_specificMeanning;
		this.f_lemmaSummary = f_lemmaSummary;
		this.f_username = f_username;
		this.f_remark = f_remark;
	}

	public Feedback() {
		super();
	}

	

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public int getV_id() {
		return v_id;
	}

	public void setV_id(int v_id) {
		this.v_id = v_id;
	}

	public String getF_abbreviation() {
		return f_abbreviation;
	}

	public void setF_abbreviation(String f_abbreviation) {
		this.f_abbreviation = f_abbreviation;
	}

	public String getF_fullWords() {
		return f_fullWords;
	}

	public void setF_fullWords(String f_fullWords) {
		this.f_fullWords = f_fullWords;
	}

	public String getF_specificMeanning() {
		return f_specificMeanning;
	}

	public void setF_specificMeanning(String f_specificMeanning) {
		this.f_specificMeanning = f_specificMeanning;
	}

	public String getF_lemmaSummary() {
		return f_lemmaSummary;
	}

	public void setF_lemmaSummary(String f_lemmaSummary) {
		this.f_lemmaSummary = f_lemmaSummary;
	}

	public String getF_username() {
		return f_username;
	}

	public void setF_username(String f_username) {
		this.f_username = f_username;
	}

	public String getF_remark() {
		return f_remark;
	}

	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
	}

	@Override
	public String toString() {
		return "Feedback [f_id=" + f_id + ", v_id=" + v_id + ", f_abbreviation=" + f_abbreviation + ", f_fullWords="
				+ f_fullWords + ", f_specificMeanning=" + f_specificMeanning + ", f_lemmaSummary=" + f_lemmaSummary
				+ ", f_username=" + f_username + ", f_remark=" + f_remark + "]";
	}
	
	
	
	
}
