package com.marspie.demo.simple;

/**
 * 洗衣机产品
 * @author alex.yao
 *
 */
public class Washer implements Product {

	@Override
	public void make() {
		System.out.println("洗衣机制造了");
	}

}
