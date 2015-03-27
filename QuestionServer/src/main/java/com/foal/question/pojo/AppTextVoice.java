package com.foal.question.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.foal.question.config.Constant;
import com.foal.question.util.GsonTools;
import com.foal.question.util.StringTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_text_voice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextVoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8490001678272587693L;
	private int id;
	private String ownerId;
	private String content;
	private String voiceUrl;
	private Date createTime;
	private int positiveCount;
	private int opLock;

	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id_")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Index(name = "owner_id_index")
	@Column(name = "owner_id_")
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Column(name = "content_")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "voice_url_")
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "positive_count_")
	public int getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}
	public void incPositiveCount() {
		this.positiveCount++;
	}
	@Version
	@Column(name = "op_lock_")
	public int getVersion() {
		return opLock;
	}
	public void setVersion(int opLock) {
		this.opLock = opLock;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((voiceUrl == null) ? 0 : voiceUrl.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + positiveCount;
		result = prime * result + opLock;
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
		AppTextVoice other = (AppTextVoice) obj;
		return id == other.id
				&& StringTools.equalsStr(ownerId, other.ownerId)
				&& StringTools.equalsStr(content, other.content)
				&& StringTools.equalsStr(voiceUrl, other.voiceUrl)
				&& createTime.getTime() == other.createTime.getTime()
				&& positiveCount == other.positiveCount
				&& opLock == other.opLock;
	}
	
	public JsonObject toJson() {
		JsonObject jo = GsonTools.parseJsonObject(this);
		jo.remove("opLock");
		jo.addProperty("imageUrl", Constant.CONTEXT_WEB_URL + this.voiceUrl);
		return jo;
	}
}
