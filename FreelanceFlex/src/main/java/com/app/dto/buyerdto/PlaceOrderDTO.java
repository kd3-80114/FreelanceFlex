package com.app.dto.buyerdto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaceOrderDTO {

	@NotNull
	private BuyerOrderDTO buyer;
	@NotNull
	private GigsOrderDTO gigToOrder;
	
}
