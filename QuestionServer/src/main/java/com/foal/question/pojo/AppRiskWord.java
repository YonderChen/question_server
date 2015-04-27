package com.foal.question.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "app_risk_word")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppRiskWord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4881652500744023580L;
	private String riskWord;
	
	@Id
	@Column(name = "risk_word_")
	public String getRiskWord() {
		return riskWord;
	}
	public void setRiskWord(String riskWord) {
		this.riskWord = riskWord;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((riskWord == null) ? 0 : riskWord.hashCode());
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
		AppRiskWord other = (AppRiskWord) obj;
		if (riskWord == null) {
			if (other.riskWord != null)
				return false;
		} else if (!riskWord.equals(other.riskWord))
			return false;
		return true;
	}
}
