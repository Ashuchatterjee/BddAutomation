package com.ashish.pojo;

import java.util.List;

import lombok.Data;

@Data
public class User {
	private List<Result> results;
	private Info info;
}
