package com.marspie.demo.simple;

/**
 * 空调产品类
 * @author alex.yao
 *
 */
public class AirCondition implements Product {

	@Override
	public void make() {
		System.out.println("空调制造了");
	}

}
