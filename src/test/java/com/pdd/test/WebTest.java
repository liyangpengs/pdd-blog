package com.pdd.test;

import org.junit.Test;

import com.pdd.utils.getAddressUtil;

public class WebTest {

	@Test
	public void test() {
		System.out.println(getAddressUtil.getAddress("14.215.176.16"));
	}
}
