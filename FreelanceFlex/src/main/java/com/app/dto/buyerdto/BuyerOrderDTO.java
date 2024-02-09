package com.app.dto.buyerdto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyerOrderDTO {
	@NotNull
	private Long id;
}
