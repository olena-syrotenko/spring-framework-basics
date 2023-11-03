package asd.syrotenko.annotationconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class SpringConfig {

	@Bean
	public ExampleService exampleService() {
		return new ExampleService();
	}
}
