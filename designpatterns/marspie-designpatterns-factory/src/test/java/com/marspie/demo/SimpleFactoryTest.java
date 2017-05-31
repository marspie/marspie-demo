package com.marspie.demo;

import com.marspie.demo.simple.Product;
import com.marspie.demo.simple.SimpleFactory;

/**
 * 简单工厂测试类
 * @author alex.yao
 *
 */
public class SimpleFactoryTest {

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		SimpleFactory simpleFactory = new SimpleFactory();
		Product product = simpleFactory.factory("Washer");
		product.make();
	}
}
