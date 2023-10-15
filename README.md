# Spring Boot Notes

## Spring Boot Core Concepts

Spring Framework combines Inversion of Control (IoC) and Dependecy Injection (DI) principles.

***Inversion of Control*** is a principle which transfers the control of objects or portions of a program to a container. 

_Spring Container_ is a component that is responsible for creating objects, holding them in memory and injecting them into other objects. It maintains complete lifecycle of object.
It contains _beans_ (.java classes which objects we need to create) and uses _configuration metadata_, that is represented in XML, Java annotations, or Java code, and describes what the objects depends on.

Container is represented by `ApplicationContext` interface that is used to access objects in container. It has next implementations:
  - `ClasssPathXMLApplicationContext` - loads the definitions of the beans from an XML file in classpath;
  - `FileSystemXMLApplicationContext` - loads the definitions of the beans from an XML file in file system;
  - `WebXmlApplicationContext` - loads the definitions of the beans from an XML file from a web application;
  - `AnnotationConfigApplicationContext` - loads the definitions of the beans from annotations.

***Dependency Injection*** is a process injecting that an objet requires at runtime dynamically. There are 2 types of injection:
  - setter injection - container invokes setter methods to initialize bean after invoking no-arguments constructor to create it.
  - constructor injection - container invokes parametrized constructor to initialize bean.

## Life Cycle

Bean life cycle is managed by the spring container. The process flow of the bean life cycle is next: 
  1. Bean initialized;
  2. Dependencies injected;
  3. Custom init() method (when the spring container starts up and the bean is instantiated);
  4. Custom utility method;
  5. Custom destroy() method (on closing the container).

Spring provides three ways to implement the life cycle of a bean:
  1. **By XML**

     In this approach we need to define in bean methods that will be executed according to init and destroy processes. Then these methods are need to be registered in XML config file with `init-method` and `destroy-method` attributes.

 ```xml
<bean id="exampleXml" class="com.ExampleXml" init-method="createBean" destroy-method="destroyBean"/>
```

  2. **By Spring Interfaces**

     In this approach we need to implement `InitializingBean` interface and override `afterPropertiesSet()` method that represents custom init method.
     And implement `DisposableBean` interface and override `destroy()` method that represents custom destroy method.

```java
public class ExampleInterface implements InitializingBean, DisposableBean {
	@Override
	public void afterPropertiesSet() throws Exception {
		// custom init logic
	}

	@Override
	public void destroy() throws Exception {
		// custom destroy logic
	}
}
```

  3. **By Annotations**

     In this approach we need to use `@PostConstruct` to annotate custom init method and `@PreDestroy` to annotate custom destroy method. Also, need to configure XML file to activate these annotations.

```java
public class ExampleWithAnnotation {
	@PostConstruct
	public void createBean() {
		// custom init logic
	}

	@PreDestroy
	public void destroyBean() {
		// custom destroy logic
	}
}
```
```xml
<bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"/>
```
