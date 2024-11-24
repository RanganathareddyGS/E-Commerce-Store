package com.s13sh.ecommerce.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Component
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 3, max = 15, message = "* Enter between 3~15 charecters")
	private String name;
	@NotNull(message = "* Enter Proper Value")
	@DecimalMax(value = "100000", message = "* Enter below 1 lakh rs")
	@DecimalMin(value = "48", message = "* Enter above 49rs")
	private double price;
	@NotNull(message = "* Enter Proper Value")
	@Min(value = 1, message = "* Should be atleast One")
	@Max(value = 30, message = "* Maximum 30 is allowed")
	private int stock;
	@Size(min = 15, max = 100, message = "* Enter between 15~100 charecters")
	private String description;
	private String imageLink;
	@NotEmpty(message = "* Enter Something")
	private String category;
	private boolean approved;
	@ManyToOne
	Seller seller;

	public int getQuantity(List<Item> items)
	{
		int quantity=0;
		if(items==null)
			return quantity;
		else {
		for(Item item:items)
		{
			if(this.name.equals(item.getName()))
				quantity=item.getQuantity();
		}
		return quantity;
		}
	}

}
