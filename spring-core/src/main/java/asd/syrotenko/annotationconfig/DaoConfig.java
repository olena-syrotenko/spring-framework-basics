package asd.syrotenko.annotationconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
	@Bean
	public ExampleDao exampleDao() {
		return new ExampleDao();
	}

}
