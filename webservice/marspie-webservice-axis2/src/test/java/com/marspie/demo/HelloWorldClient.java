package com.marspie.demo;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * Webservices 客户端
 * 
 * @author alex.yao
 *
 */
public class HelloWorldClient {

	public static void main(String[] args) {
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference("http://localhost:8085/services/HelloWorld");
			options.setTo(targetEPR);

			Object[] objargs = new Object[] {};
			Class<?>[] getobj = new Class[] { String.class };
			Object[] response = serviceClient.invokeBlocking(new QName("http://demo.marspie.com", "sayHello"), objargs, getobj);
			String result = (String) response[0];
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
