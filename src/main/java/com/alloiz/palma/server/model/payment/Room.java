package com.alloiz.palma.server.model.payment;

import com.alloiz.palma.server.model.BaseEntity;
import com.alloiz.palma.server.model.enums.RoomType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * Describes one room
 */
@Entity(name = "payment_room")
public class Room extends BaseEntity<Room>
{
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	private String name;
	private Integer roomNumber;
	private Integer additionalPlaces;
	private Integer price;


	public RoomType getRoomType()
	{
		return roomType;
	}

	public Room setRoomType(RoomType roomType)
	{
		this.roomType = roomType;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Room setName(String name)
	{
		this.name = name;
		return this;
	}

	public Integer getRoomNumber()
	{
		return roomNumber;
	}

	public Room setRoomNumber(Integer roomNumber)
	{
		this.roomNumber = roomNumber;
		return this;
	}

	public Integer getAdditionalPlaces()
	{
		return additionalPlaces;
	}

	public Room setAdditionalPlaces(Integer additionalPlaces)
	{
		this.additionalPlaces = additionalPlaces;
		return this;
	}

	public Integer getPrice()
	{
		return price;
	}

	public Room setPrice(Integer price)
	{
		this.price = price;
		return this;
	}

	@Override
	public String toString() {
		return super.toString() +"\nRoomType = "+ roomType + "\nName = " + name +"\nRoomNumber = "+roomNumber
				+"\nAdditionalPlaces = "+additionalPlaces + "\nPrice = "+price+"\n";
	}
}
