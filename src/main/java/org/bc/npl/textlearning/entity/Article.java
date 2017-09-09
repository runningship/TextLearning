package org.bc.npl.textlearning.entity;

import java.util.Date;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "article")
public class Article {

	@Id
	@AutoIncrease
	private Long id;
	
	@Column(name="craw_target_id")
	private Long crawTargetId;

	private String text;
	
    private String link;

	@Column(name = "addtime")
	private Date addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getCrawTargetId() {
        return crawTargetId;
    }

    public void setCrawTargetId(Long crawTargetId) {
        this.crawTargetId = crawTargetId;
    }

}
