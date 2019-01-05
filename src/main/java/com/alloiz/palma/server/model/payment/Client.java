package com.alloiz.palma.server.model.payment;

import com.alloiz.palma.server.model.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


/**
 * For storing all information about clients
 */
@Entity
public class Client extends BaseEntity<Client>
{

	@NotNull(message = "This field can not be NULL")
	@NotEmpty(message = "This field can not be EMPTY")
	private String firstName;

	@NotNull(message = "This field can not be NULL")
	@NotEmpty(message = "This field can not be EMPTY")
	private String lastName;

	private String thirdName;

	@NotNull(message = "This field can not be NULL")
	@NotEmpty(message = "This field can not be EMPTY")
	private String phoneNumber;

	private String email;

	public Client()
	{
	}

	public String getFirstName()
	{
		return firstName;
	}

	public Client setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public String getLastName()
	{
		return lastName;
	}

	public Client setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	public String getThirdName()
	{
		return thirdName;
	}

	public Client setThirdName(String thirdName)
	{
		this.thirdName = thirdName;
		return this;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public Client setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public Client setEmail(String email)
	{
		this.email = email;
		return this;
	}

	@Override
	public String toString()
	{
		return "Client{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", thirdName='" + thirdName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", id=" + id +
				", available=" + available +
				'}';
	}
}
