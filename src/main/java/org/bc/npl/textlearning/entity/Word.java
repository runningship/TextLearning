package org.bc.npl.textlearning.entity;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "word")
public class Word {

	@Id
	@AutoIncrease
	private Long id;
	
	private String text;

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
}
