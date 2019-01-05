package com.alloiz.palma.server.model.payment;

import com.alloiz.palma.server.model.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


/**
 * Use it when you need to add names in few languages
 */
@Entity
public class MultiLanguageName extends BaseEntity<MultiLanguageName>
{

	@ManyToOne
	private Language language;

	@NotNull(message = "This field can not be NULL")
	@NotEmpty(message = "This field can not be EMPTY")
	private String name;

	public MultiLanguageName()
	{
	}

	public Language getLanguage()
	{
		return language;
	}

	public MultiLanguageName setLanguage(Language language)
	{
		this.language = language;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public MultiLanguageName setName(String name)
	{
		this.name = name;
		return this;
	}

	@Override
	public String toString()
	{
		return "MultiLanguageName{" +
				"language=" + (language == null ? "null" : language) +
				", name='" + name + '\'' +
				", id=" + id +
				", available=" + available +
				'}';
	}
}
