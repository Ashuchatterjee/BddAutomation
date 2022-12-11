package com.ashish.pojo;

import lombok.Data;

@Data
public class Location {
	private Street street;
	private String city; 
	private String state;
	private String country;
	private Object postcode;
	private Coordinates coordinates;
	private Timezone timezone;
}
