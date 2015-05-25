package com.foal.question.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.foal.question.config.Constant;
import com.foal.question.util.GsonTools;
import com.google.gson.JsonObject;

@Entity
@Table(name = "app_text_voice")
@Cache(region = "yonderHibernateCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTextVoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2915678199478151293L;
	private int id;
	private AppUser owner;
	private String content;
	private String voiceUrl;
	private String voiceInfo;
	private Date createTime;
	private int praiseCount;
	private int shareCount;

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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id_")
	public AppUser getOwner() {
		return owner;
	}
	public void setOwner(AppUser owner) {
		this.owner = owner;
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
	@Column(name = "voice_info_")
	public String getVoiceInfo() {
		return voiceInfo;
	}
	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
	}
	@Column(name = "create_time_")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "praise_count_")
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	public void incPraiseCount() {
		this.praiseCount++;
	}
	public void decPraiseCount() {
		this.praiseCount--;
	}
	@Column(name = "share_count_")
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public void incShareCount() {
		this.shareCount++;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + praiseCount;
		result = prime * result + shareCount;
		result = prime * result + ((voiceInfo == null) ? 0 : voiceInfo.hashCode());
		result = prime * result + ((voiceUrl == null) ? 0 : voiceUrl.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (praiseCount != other.praiseCount)
			return false;
		if (shareCount != other.shareCount)
			return false;
		if (voiceInfo == null) {
			if (other.voiceInfo != null)
				return false;
		} else if (!voiceInfo.equals(other.voiceInfo))
			return false;
		if (voiceUrl == null) {
			if (other.voiceUrl != null)
				return false;
		} else if (!voiceUrl.equals(other.voiceUrl))
			return false;
		return true;
	}
	public JsonObject toJson(boolean hasPraised, int commentCount) {
		JsonObject jo = GsonTools.parseJsonObject(this);
		jo.remove("owner");
		jo.addProperty("ownerId", owner.getUid());
		if (owner.getUserType() == AppUser.UserType.Local) {
			jo.addProperty("ownerFigureurl", Constant.CONTEXT_WEB_URL + owner.getFigureurl());
		} else {
			jo.addProperty("ownerFigureurl", owner.getFigureurl());
		}
		jo.addProperty("ownerName", owner.getName());
		jo.addProperty("voiceUrl", Constant.CONTEXT_WEB_URL + this.voiceUrl);
		jo.add("voiceInfo", GsonTools.parseJsonObject(voiceInfo));
		jo.addProperty("createTime", createTime.getTime());
		jo.addProperty("hasPraised", hasPraised ? 1 : 0);
		jo.addProperty("commentCount", commentCount);
		return jo;
	}
}
