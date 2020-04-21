package org.launchcode.carDIY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class CarDiyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDiyApplication.class, args);

			System.out.println("Hello Denys");
	}









}
