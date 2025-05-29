package de.telran.dzMoisyeyenko210125mbe;

import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DzMoisyeyenko210125MBeApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DzMoisyeyenko210125MBeApplication.class, args);



//		//распечатать содержимое контейнера бинов:
//		String[] beans = applicationContext.getBeanDefinitionNames();
//		System.out.println(Arrays.toString(beans));

//		Product product = applicationContext.getBean(Product.class);
//		product.setProductId(1L);
//		product.setName("Морковь");
//		product.setPrice(1.1);
//		System.out.println(product);

	}

}
