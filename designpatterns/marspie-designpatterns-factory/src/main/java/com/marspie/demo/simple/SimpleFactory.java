package com.marspie.demo.simple;

public class SimpleFactory {

	public static Product factory(String productName) throws Exception{ 
        if(productName.equals("Washer")){ 
            return new Washer(); 
        }else if(productName.equals("Television")){ 
            return new Television(); 
        }else if(productName.equals("AirCondition")){ 
            return new AirCondition(); 
        }else{ 
            throw new Exception("没有该产品"); 
        } 
    }  
	
}
