package asd.syrotenko.annotationconfig;

import org.springframework.stereotype.Repository;

@Repository
public class ExampleDao {
	public String create(String obj) {
		return "Create: " + obj;
	}
}
