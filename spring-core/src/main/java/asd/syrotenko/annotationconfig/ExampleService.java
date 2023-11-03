package asd.syrotenko.annotationconfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ExampleService {

	@Autowired
	private ExampleDao exampleDao;

	public String saveMessage(String message) {
		if (StringUtils.isBlank(message)) {
			return "No message was provided";
		}
		return exampleDao.create(message);
	}
}
