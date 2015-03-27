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

@Entity
@Table(name = "app_text_voice_op_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextVoiceOpLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8524621454957724528L;
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
	
}
