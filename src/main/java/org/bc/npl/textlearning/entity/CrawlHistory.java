package org.bc.npl.textlearning.entity;

import java.util.Date;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "crawl_history")
public class CrawlHistory {

	@Id
	@AutoIncrease
	private Long id;

    private String link;

	private Date lasttime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

}
