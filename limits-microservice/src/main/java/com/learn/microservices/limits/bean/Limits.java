package com.learn.microservices.limits.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Limits {
	
	int minimum;
	int maximum;

}
