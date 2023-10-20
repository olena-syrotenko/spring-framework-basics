package asd.syrotenko;

import asd.syrotenko.stereotypeannotations.Instructor;
import asd.syrotenko.stereotypeannotations.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StereotypeAnnotationsBeanTest {

	private ClassPathXmlApplicationContext ctx;

	@BeforeEach
	public void init() {
		ctx = new ClassPathXmlApplicationContext("stereotypeAnnotationsConfig.xml");
	}

	@Test
	public void testInstructorBean() {
		Instructor instructor = (Instructor) ctx.getBean("instructor");
		assertNotNull(instructor);
		assertEquals(1, instructor.getId());
		assertEquals("Default Instructor", instructor.getName());
		assertEquals(2, instructor.getTopics().size());

		Profile profile = instructor.getProfile();
		assertEquals("Languages", profile.getTitle());
		assertEquals("InternationalSchool", profile.getCompany());
	}

	@Test
	public void testInstructorBeanScope() {
		Instructor instructor = (Instructor) ctx.getBean("instructor");
		instructor.setId(10);

		Instructor anotherInstructor = (Instructor) ctx.getBean("instructor");
		assertEquals(1, anotherInstructor.getId());
	}
}
