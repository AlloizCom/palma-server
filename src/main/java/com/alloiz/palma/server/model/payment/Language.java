package com.alloiz.palma.server.model.payment;

import com.alloiz.palma.server.model.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


/**
 * Contains name of language
 */
@Entity
public class Language extends BaseEntity<Language>
{

	@NotNull(message = "This field can not be NULL")
	@NotEmpty(message = "This field can not be EMPTY")
	private String languagesName;

	public Language()
	{
	}

	public String getLanguagesName()
	{
		return languagesName;
	}

	public Language setLanguagesName(String languagesName)
	{
		this.languagesName = languagesName;
		return this;
	}

	@Override
	public String toString()
	{
		return "Language{" +
				"languagesName='" + languagesName + '\'' +
				", id=" + id +
				", available=" + available +
				'}';
	}
}
