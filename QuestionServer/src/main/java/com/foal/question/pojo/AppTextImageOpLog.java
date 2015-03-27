package com.foal.question.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.foal.question.util.StringTools;

@Entity
@Table(name = "app_text_image_op_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextImageOpLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4940235761015290824L;
	private String opId;

	@GenericGenerator(name = "generator", strategy = "assigned")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "op_id_")
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opId == null) ? 0 : opId.hashCode());
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
		AppTextImageOpLog other = (AppTextImageOpLog) obj;
		return StringTools.equalsStr(opId, other.opId);
	}
}
