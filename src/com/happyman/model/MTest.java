package com.happyman.model;

public class MTest {

	public static void main(String[] args) {

		EhsanEnum e = EhsanEnum.ITEM1;

		EhsanEnum ee = EhsanEnum.valueOf("ITEM2");

		System.out.println(ee.getName());

		System.out.println(e.getVal());

		System.out.println(e.getName());

	}

}
