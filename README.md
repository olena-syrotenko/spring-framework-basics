# Spring Boot Notes

1. [Spring Boot Core Concepts](#spring-boot-core-concepts)
2. [Life Cycle](#life-cycle)
3. [Scope](#scope)
4. [Setter Injection](#setter-injection)
   - [primitive types](#primitive-types)
   - [collections](#collections)
   - [reference types](#reference-types)

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

## Scope

***Bean scope*** defines the number of objects of a particular bean created in the conatiner.

1. _Singleton_ is a default scope according to which bean is created once per spring container and it is shared by all the clients requesting that bean.
2. _Prototype_ is a scope according to which bean is created each time it is requested from the spring container.
3. _Request_ is a scope that available only in Spring MVC web apps, beans with this scope are created once per HTTP request.
4. _Session_ is a scope that available only in Spring MVC web apps, beans with this scope are created once per HTTP session.

In Spring version < 5 _globalsession_ scope is defined according to which beans are created per global HTTP session.

In Spring 5 two additional scopes are defined:
- _Application_ is a scope that available only in Spring MVC web apps, beans with this scope are created once per web application and are shared across all sessions and requests. It is similar to singletn scope but with application scope beans are shared across all apps and contexts in the same server.
- _WebSocket_ is a scope that available only in Spring MVC web apps, beans with this scope are created once per Websocket connection and are available throughout the websocket session.

We can define scope for a bean using `scope` attribute of a `bean` element and set name of needed scope.

```xml
<bean name="university" class="assignment.University" p:name="Default University" scope="prototype"/>
```

## Setter Injection

### Primitive types

   To set primitive types, their wrappers (Integer, Double...) and String we can use `value` attribute in `property` element

```xml
<bean name="emp" class="example.Employee" >
	<property name="id" value="20"/>
	<property name="name" value="Tom"/>
</bean>
```

Or use `p:schema` and set value directly in `bean` element

```xml
<bean name="emp" class="example.Employee" p:id="20" p:name="Tom"/>
```

### Collections

   - ***List***
   
   To set non-empty list for a particular property we can use `value` elements inside `list` element. To set `null` value we can use `<null/>`. To create an empty list we need to leave `list` element empty.

```xml
<bean name="hospital" class="example.Hospital">
	<property name="departments">
		<list>
			<value>Front Office</value>
			<value>Surgical</value>
			<value>Nursing</value>
			<null/>
		</list>
	</property>
</bean>
```

If we want to set list with one element we can use only `value` attribute.

```xml
<bean name="hospital" class="example.Hospital">
	<property name="departments" value="Front Office"/>
</bean>
```

   - ***Set***

   To set non-empty set for a particular property we can use `value` elements inside `set` element. To create an empty set we need to leave `set` element empty.

```xml
<!--only two elements will be in the result set: BMW and Tesla-->
<bean name="carDealer" class="example.CarDealer">
	<property name="models">
		<set>
			<value>BMW</value>
			<value>Tesla</value>
			<value>Tesla</value>
		</set>
	</property>
</bean>
```

If we want to create set with one element we can use only `value` attribute.

```xml
<bean name="carDealer" class="example.CarDealer">
	<property name="models" value="Tesla"/>
</bean>
```

   - ***Map***

    To set non-empty map for a particular property we can use `entry` elements (with `key` and `value` attributes) inside `map` element. To create an empty map we need to leave `map` element empty.

```xml
<bean name="cart" class="example.Cart">
	<property name="products">
		<map>
			<entry key="1" value="IPhone"/>
			<entry key="2" value="MacBook"/>
		</map>
	</property>
</bean>
```

   - ***Properties***

    To set non-empty properties we can use `prop` elements (with `key` attribute) inside `props` element. To create an empty properties we need to leave `props` element empty.

```xml
<bean name="languages" class="example.Languages">
	<property name="countryAndLanguages">
		<props>
			<prop key="USA">American English</prop>
			<prop key="UK">British English</prop>
		</props>
	</property>
</bean>
```

### Reference types

   To set reference types we can use `ref` attribute of `property` element, in which we set name of necessary bean.
   
```xml
<bean name="scores" class="example.Scores" p:maths="100.0" p:physics="95.0" p:chemistry="80.0"/>
<bean name="student" class="example.Student">
	<property name="scores" ref="scores"/>
</bean>
```

Or we can use `p:schema` and set value directly in `bean` element using property name with "-ref" suffix

```xml
<bean name="scores" class="example.Scores" p:maths="100.0" p:physics="95.0" p:chemistry="80.0"/>
<bean name="student" class="example.Student" p:scores-ref="scores"/>
```

If we need to set collection of references we can use `ref` element with `bean` attribute.

```xml
<bean class="assignment.Item" p:id="1" p:name="Dress" p:price="100.50" p:quantity="3"/>
<bean class="assignment.Item" p:id="2" p:name="Handbag" p:price="20.59" p:quantity="1"/>
<bean name="shoppingCart" class="assignment.ShoppingCart">
	<property name="items">
		<list>
			<ref bean="dressItem"/>
			<ref bean="bagItem"/>
		</list>
	</property>
</bean>
```

If we need to set bean that will not be reused we can defune ***inner bean*** using `bean` element inside a particular property or collection.

```xml
<bean name="shoppingCart" class="assignment.ShoppingCart">
	<property name="items">
		<list>
			<bean class="Item" p:id="1" p:name="Dress" p:price="100.50" p:quantity="3"/>
			<bean class="Item" p:id="2" p:name="Handbag" p:price="20.59" p:quantity="1"/>
		</list>
	</property>
</bean>
```
