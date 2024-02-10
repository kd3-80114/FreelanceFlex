

	package com.app.dto;


	import javax.validation.constraints.NotBlank;



import com.app.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;


	import lombok.Getter;
	import lombok.Setter;
	import lombok.ToString;

	@Setter
	@Getter
	@ToString

	public class AddressDTO {
		
//		@JsonProperty(access = Access.READ_ONLY)
//		private Long id;
		@NotBlank(message = "country can not be blank")
		private String country;
		@NotBlank(message = "state can not be blank")
		private String state;
		@NotBlank(message = "city can not be blank")
		private String city;
		@NotBlank(message = "landmark can not be blank")
		private String landmark;
		@NotBlank(message = "pincode can not be blank")
		private String pincode;
		
		public Address  getPermanentAddress(Address address) {
			return address;
		}
		
	}

