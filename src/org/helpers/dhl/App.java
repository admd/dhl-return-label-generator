package org.helpers.dhl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml");
		DHLServiceClient obj1 = (DHLServiceClient) context.getBean("dhlServiceClient");
		obj1.generateReturnLabel("");
	}
}