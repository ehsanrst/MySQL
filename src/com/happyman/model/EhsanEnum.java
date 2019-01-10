package com.happyman.model;

public enum EhsanEnum {

	ITEM1(1, "Ehsan"), ITEM2(2, "Mohammad");

	private EhsanEnum(Integer v, String n) {
		this.val = v;
		this.name = n;
	}

	private Integer val;
	private String name;

	public Integer getVal() {
		return val;
	}

	public String getName() {
		return name;
	}

}
