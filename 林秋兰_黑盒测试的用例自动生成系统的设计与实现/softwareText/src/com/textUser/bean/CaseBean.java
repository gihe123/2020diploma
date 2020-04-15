package com.textUser.bean;

import javax.annotation.Resource;
import javax.persistence.*;


@Entity
@Table(name="t_usecase")
public class CaseBean {

	private Long id;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public CaseBean(Long id) {
		super();
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public CaseBean(Integer tab, String user, String flag) {
		super();
		this.tab = tab;
		this.user = user;
		this.flag = flag;
	}

	private Integer tab;
	@Column
	public Integer getTab() {
		return tab;
	}

	public void setTab(Integer tab) {
		this.tab = tab;
	}
	@Column
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public CaseBean(Long id, Integer tab, String user, String flag) {
		super();
		this.id = id;
		this.tab = tab;
		this.user = user;
		this.flag = flag;
	}

	private String user;

	private String flag;
	public CaseBean(String user, String flag) {
		super();
		this.user = user;
		this.flag = flag;
	}

	
	@Column
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CaseBean() {
		
	}

	public CaseBean(String user) {
		super();
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CaseBean other = (CaseBean) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	
	
	
	
}
