package com.alloiz.palma.server.model.payment;

import com.alloiz.palma.server.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class Description extends BaseEntity<Description>
{
	@OneToOne
	private Language language;
	@Column(columnDefinition = "text")
	private String text;

	public Language getLanguage()
	{
		return language;
	}

	public Description setLanguage(Language language)
	{
		this.language = language;
		return this;
	}

	public String getText()
	{
		return text;
	}

	public Description setText(String text)
	{
		this.text = text;
		return this;
	}
}
