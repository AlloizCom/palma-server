package com.alloiz.palma.server.model.payment;


import com.alloiz.palma.server.model.BaseEntity;
import com.alloiz.palma.server.model.payment.enums.Status;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 * Information about client`s order
 */
@Entity
public class Book extends BaseEntity<Book>
{

	@ManyToOne
	private Client client;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToMany
	private List<Room> rooms;

	private Timestamp dateFrom;
	private Timestamp dateTo;
	private Timestamp bookingDate;
	private Boolean boughtOnLine;
	private Double totalPrice;
	private Double payedPrice;
	private Boolean isCash;

	public Book()
	{
	}

	public Client getClient()
	{
		return client;
	}

	public Book setClient(Client client)
	{
		this.client = client;
		return this;
	}

	public Status getStatus()
	{
		return status;
	}

	public Book setStatus(Status status)
	{
		this.status = status;
		return this;
	}

	public Timestamp getDateFrom()
	{
		return dateFrom;
	}

	public Book setDateFrom(Timestamp dateFrom)
	{
		this.dateFrom = dateFrom;
		return this;
	}

	public Timestamp getDateTo()
	{
		return dateTo;
	}

	public Book setDateTo(Timestamp dateTo)
	{
		this.dateTo = dateTo;
		return this;
	}

	public Timestamp getBookingDate()
	{
		return bookingDate;
	}

	public Book setBookingDate(Timestamp bookingDate)
	{
		this.bookingDate = bookingDate;
		return this;
	}

	public Boolean getBoughtOnLine()
	{
		return boughtOnLine;
	}

	public Book setBoughtOnLine(Boolean boughtOnLine)
	{
		this.boughtOnLine = boughtOnLine;
		return this;
	}

	public Double getTotalPrice()
	{
		return totalPrice;
	}

	public Book setTotalPrice(Double totalPrice)
	{
		this.totalPrice = totalPrice;
		return this;
	}

	public Double getPayedPrice()
	{
		return payedPrice;
	}

	public Book setPayedPrice(Double payedPrice)
	{
		this.payedPrice = payedPrice;
		return this;
	}

	public Boolean getCash()
	{
		return isCash;
	}

	public Book setCash(Boolean cash)
	{
		isCash = cash;
		return this;
	}

	@Override
	public String toString()
	{
		return "Book{" +
				"client=" + (client == null ? "null" : client) +
				", status=" + status +
				", dateFrom=" + dateFrom +
				", dateTo=" + dateTo +
				", bookingDate=" + bookingDate +
				", boughtOnLine=" + boughtOnLine +
				", totalPrice=" + totalPrice +
				", payedPrice=" + payedPrice +
				", isCash=" + isCash +
				", id=" + id +
				", available=" + available +
				'}';
	}

	public List<Room> getRooms()
	{
		return rooms;
	}

	public Book setRooms(List<Room> rooms)
	{
		this.rooms = rooms;
		return this;
	}
}
