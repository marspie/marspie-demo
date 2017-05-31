package com.marspie.demo.simple;

/**
 * 电视机产品
 * @author alex.yao
 *
 */
public class Television implements Product {

	@Override
	public void make() {
		System.out.println("电视机制造了");
	}

}
