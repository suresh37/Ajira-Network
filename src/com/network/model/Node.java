package com.network.model;

import com.network.utils.AppConstants;

public class Node {
	private String name;
	private String type;
	private Integer strength;

	public Node(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		this.strength = AppConstants.DEFAULT_DEVICE_STRENGTH;
	}

	public Node(String name, String type, Integer strength) {
		super();
		this.name = name;
		this.type = type;
		this.strength = strength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", type=" + type + ", strength=" + strength + "]";
	}

}
