package org.bc.npl.textlearning.entity;

import java.util.Date;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "crawl_target")
public class CrawlTarget {

	@Id
	@AutoIncrease
	private Long id;

    private String link;

	private Date addtime;
	
	@Column(name = "fail_times")
	private Integer failTimes;
	
	@Column(name = "last_error")
	private String lastError;
	
	/**
     * 1已爬取，0未爬取
     */
	@Column(name = "craw_status")
	private Integer crawStatus;
	

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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(Integer failTimes) {
        this.failTimes = failTimes;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public Integer getCrawStatus() {
        return crawStatus;
    }

    public void setCrawStatus(Integer crawStatus) {
        this.crawStatus = crawStatus;
    }

}
