package com.marspie.demo;

import java.util.Observable;
import java.util.Observer;

/** 
 * 观察者类 
 */        
public class SimpleObserver implements Observer    
{    
   public SimpleObserver(SimpleObservable simpleObservable){    
      simpleObservable.addObserver(this);    
   }    
      
   public void update(Observable observable ,Object data){  // data为任意对象，用于传递参数  
      System.out.println("Data has changed to" + ((SimpleObservable)observable).getData());
//      System.err.println(data);
   }    
}  