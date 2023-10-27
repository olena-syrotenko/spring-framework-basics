# Spring Boot Notes

1. [Spring Boot Core Concepts](#spring-boot-core-concepts)
2. [Bean Configuration](#bean-configuration)
3. [Life Cycle](#life-cycle)
4. [Scope](#scope)
5. [Setter Injection](#setter-injection)
   - [primitive types](#primitive-types)
   - [collections](#collections)
   - [reference types](#reference-types)
6. [Constructor Injection](#constructor-injection)
7. [Properties](#properties)
8. [Autowiring](#autowiring)
9. [Spring JDBC](#spring-jdbc)
10. [Spring ORM](#spring-orm)

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

## Bean Configuration

We can define bean using either xml configuration file or stereotype annotations.

With ***XML config*** we can identify a particular class as a bean using `bean` element with `name` and `class` attributes.

```xml
<bean name="customer" class="example.Customer"/>
```
 
With ***stereotype annotations*** we can set one of the available annotation at class level:

- `@Component` is the most general-purpose stereotype annotation and is used to mark any class as a component.
- `@Service` is used to mark a class as a service component, which typically performs business logic.
- `@Repository` is used to mark a class as a repository component, which is responsible to interact with the database and handle data operations.
- `@Controller` is used to mark a class as a controller component, which is responsible for handling incoming requests and returning appropriate responses.

To activate stereotype annotations we should add `component-scan` element into xml config file and set `base-package` attribute that is path to scan for beans.

```xml
<context:component-scan base-package="com.example.beans"/>
```

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

We can define scope for a bean with ***XML configuration*** using `scope` attribute of a `bean` element and set name of needed scope.

```xml
<bean name="university" class="assignment.University" p:name="Default University" scope="prototype"/>
```

Also, to define scope we can use ***`@Scope` annotation*** with needed scope name at a class level.

```java
@Component
@Scope("prototype")
public class Instructor {
    // attributes and methods
}
```

## Setter Injection

To use setter injection you should define `property` element inside `bean` element. Also, you can set values using `p:namespace` + field name as an attribute of `bean` element.

### Primitive types

   To set primitive types, their wrappers (Integer, Double...) and String with ***XML configuration*** we can use `value` attribute in `property` element

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

To set primitive types with ***annotations*** we can use `@Value` annotation with needed value at a field level.

```java
@Component
public class Instructor {
	@Value("1")
	private Integer id;
}
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

   - ***Standalone collecions***

     To create reusable collection we can use `util:schema`. To create bean of particular collection we need to create `util:{collection-name}` element and set `id` and `{collection}-type` attributes.

```xml
	<!--standalone collection that can be reused-->
	<util:set set-class="java.util.TreeSet" id="currencies">
		<value>USD</value>
		<value>EUR</value>
		<value>UAH</value>
	</util:set>
	<bean name="availableCurrencies" class=".setterinjection.example.Currencies" p:availableCurrencies-ref="currencies"/>
```

To set collections with ***annotations*** we can use `@Value` annotation with name of created standalone collection in xml file in format `#{collection-bean}`

```java
@Component
public class Currencies {
	@Value("#{currencies}")
	private Set<String> availableCurrencies;
}
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

If we need to set bean that will not be reused we can define ***inner bean*** using `bean` element inside a particular property or collection.

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

To set reference type with ***annotations*** we can use `@Autowired` annotation at needed level.

```java
@Component
public class Student {
	@Autowired
	private Scores scores;
}

```

## Constructor Injection

To use constructor injection you should define `constructor-arg` element inside `bean` element in the order of constructor parameters. Rules for primitive, collection and referenct types are the same as for setter injection.

```xml
<!--Constructor injection using constructor-arg element-->
<bean name="employee" class="example.Employee">
	<constructor-arg value="1"/>
	<constructor-arg ref="address"/>
	<constructor-arg>
		<list>
			<value>MacbookPro</value>
			<value>AirPods</value>
		</list>
	</constructor-arg>
</bean>

<!--Or with inner bean-->
<bean name="employee" class="example.Employee">
	<constructor-arg>
		<bean class="example.Address" c:houseNumber="10" c:street="Default Street" c:city="Kharkiv"/>
	</constructor-arg>
</bean>
```

Also, you can set values using `c:namespace` + constructor argument name as an attribute of `bean` element.

```xml
<!--Constructor injection using c:namespace-->
<bean name="address" class="example.Address" c:houseNumber="10" c:street="Default Street" c:city="Kharkiv"/>
```

***Ambiguity problem*** with constructor injection consists of using string and numbers in overload constructors. By default the constructor with _String_ parameter will be used.
To avoid this problem we can use `type` or `name` attributes of `constructor-arg` element.

```java
public class AmbiguityExample {
	private Integer intField;
	private String stringField;

	public AmbiguityExample(Integer intField) {
		this.intField = intField;
	}

	public AmbiguityExample(String stringField) {
		this.stringField = stringField;
	}
}
```

```xml
<!--constructor with string parameter will be used-->
<bean name="typeDefault" class="example.AmbiguityExample">
	<constructor-arg value="10"/>
</bean>

<!--constructor with int parameter will be used-->
<bean name="typeDefault" class="example.AmbiguityExample">
	<constructor-arg value="10" type="int"/>
</bean>

<!--constructor with int parameter will be used-->
<bean name="typeDefault" class="example.AmbiguityExample">
	<constructor-arg value="10" name="intField"/>
</bean>
```

Specifying an `index` attribute of `constructor-arg` element solves the problem of ambiguity where a constructor may have two arguments of the same type.

```java
public class NumDifference {
	private Integer firstValue;
	private Integer secondValue;

	public NumDifference(Integer firstValue, Integer secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public Integer getDifference() {
		return firstValue - secondValue;
	}
}
```
```xml
<!--In the first case difference will be equal 5-->
<bean name="numDifferenceDefaultOrder" class="example.NumDifference">
	<constructor-arg value="10"/>
	<constructor-arg value="5"/>
</bean>
<!--In the second case difference will be equal -5-->
<bean name="numDifferenceReverseOrder" class="example.NumDifference">
	<constructor-arg value="10" index="1"/>
	<constructor-arg value="5" index="0"/>
</bean>
<!--Also we can set value to parameter with a particular index using c:namespace-->
<bean name="numDifferenceReverseOrder" class="asd.syrotenko.constructorinjection.NumDifference" c:_0="5" c:_1="10"/>
```

## Properties

We can use values from `.properties` file to initialize beans. 
If we configure beans in ***xml file*** we can use `<context:property-placeholder/>` element and set path of property file in `location` attribute. We can get value from properties file in the next way: `${propName}`.

```properties
#config.properties
ws.url=test-xyz
ws.userName=test
ws.password=test
```

```xml
<!--beanConfig.xml-->
<context:property-placeholder location="properties/config.properties"/>
<bean name="clientConfig" class="assignment.ClientConfig">
	<constructor-arg name="url" value="${ws.url}"/>
	<constructor-arg name="userName" value="${ws.userName}"/>
	<constructor-arg name="password" value="${ws.password}"/>
</bean>
```

## Autowiring

***Autowiring*** is a form of dependency injection in Spring that allows IOC container to automatically wire beans together without the need for explicit configuration.
There are different ways through which we can autowire a spring bean:
1. ***XML configuration***
   To config autowiring for bean in a xml file we need to set needed autowiring startegy using `autowire` attribute of `bean` element. Autowiring can be configured with next strategies:
   
   - _no_ - default type of autowiring when bean references must be defined by `ref` elements.
   - _byName_ - Spring looks for a bean with the same name as the property that needs to be autowired. Setter injection is used.
   - _byType_ - Spring looks for a bean with the same class as the property that needs to be autowired. Property be successfully autowired if exactly one bean of the property type exists in the container. Setter injection is used.
   - _constructor_ - same as autowiring byType. Constructor injection is used.
  
```xml
<bean name="address" class="asd.syrotenko.autowiring.example.Address" p:hno="10" p:street="Default Street" p:city="Kharkiv"/>
<!--Setter method will be used-->
<bean name="employeeByType" class="autowiring.example.Employee" autowire="byType"/>
<!--Constructor will be used-->
<bean name="employeeByConstructor" class="autowiring.example.Employee" autowire="constructor"/>

<bean name="studentScores" class="autowiring.example.Scores" p:maths="100.0" p:physics="95.0" p:chemistry="90.0"/>
<bean name="scholarScores" class="autowiring.example.Scores" p:maths="70.0" p:physics="65.0" p:chemistry="60.0"/>
<!--private Scores studentScores;-->
<!--studentScores will be injected-->
<bean name="student" class="autowiring.example.Student" autowire="byName"/>
```

2. ***Annotations***
   
   - `@Autowired` annotation can be applied on variables and methods (equivalent to autowiring byType) or on constructors (equivalent to autowiring by constructor). Property be successfully autowired if exactly one bean of the property type exists in the container.
   - `@Qualifier` annotation is used to avoid conflicts in bean mapping and we need to provide the bean name that will be used for autowiring. This way we can avoid issues where multiple beans are defined for same type. This annotation usually works with the @Autowired annotation. For constructors with multiple arguments, we can use this annotation with the argument names in the method.

```java
// scholarScores bean will be injected
public class Scholar {
	@Autowired
	@Qualifier("scholarScores")
	private Scores scores;

	public Scores getScores() {
		return scores;
	}

	public void setScores(Scores scholarScores) {
		this.scores = scholarScores;
	}
}
```

## Spring JDBC

***Spring JDBC*** takes care of all the low-level details starting from opening the connection, preparing and executing the SQL statement, processing exceptions, handling transactions, and finally closing the connection.

First af all, we need to configure connection to the database in two steps: 
1. Prepare `DriverManagerDataSource` with database properties.
2. Inject data source to `JdbcTemplate` object that will be used to acces to db and execute queries.

To execute operations of ***insert, update and delete*** we need to use `update` method and pass query and parameters to it. It returns the count of updated records.

```java
public Integer create(Employee employee) {
	if (employee == null) {
		return 0;
	}
	return jdbcTemplate.update("INSERT INTO employee(first_name, last_name) VALUES(?,?)", employee.getFirstName(), employee.getLastName());
}
```

To ***select*** data from db we need to use:
- `queryForObject` method if we expect one record;
- `query` method if we expect multiple result.

Also, we need to implement ***RowMapper\<T\>*** with needed return type to map result set to object.

```java
public class EmployeeRowMapper implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setFirstName(rs.getString("first_name"));
		employee.setLastName(rs.getString("last_name"));
		return employee;
	}
}

public class EmployeeDao {
	@Override
	public Employee readById(Integer id) {
		if (id == null) {
			return null;
		}
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?", new EmployeeRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
```

## Spring ORM

***Spring ORM*** is a module of the Java Spring framework used to implement the ORM(Object Relational Mapping) Technique.

We need to prepare ***entity*** that would be mapped to a table in a database with next annotations:
- `@Entity` - mandatory, indicate JPA entitiy
- `@Id` - mandatory, define a primary key
- `@Table` - specify the table name if it's different from the class name
- `@Column` - specify the column name if it's different from the field name
- `@GeneratedValue` - specify the strategy for generating primary key values

```java
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String desc;
	@Column(name = "price")
	private Double price;
}
```

We need to ***configure connection*** to the database in two steps:
1. Prepare `DriverManagerDataSource` with database properties.
2. Initialize `LocalSessionFactoryBean` with dataSource and additional proprties:
   - `hibernateProperties` that includes dialect
   - `annotatedClasses` that is list of entity classes to register
3. Initialize `HibernateTemplate` with sessionFactory.

```xml
<!--DataSource bean-->
<bean class="org.springframework.jdbc.datasource.DriverManagerDataSource" name="dataSource" p:url="${db.url}" p:username="${db.username}" p:password="${db.password}"/>

<!--SessionFactory bean-->
<bean class="org.springframework.orm.hibernate5.LocalSessionFactoryBean" name="sessionFactory" p:dataSource-ref="dataSource">
	<property name="hibernateProperties">
		<props>
			<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
			<prop key="hibernate.show_sql">true</prop>
		</props>
	</property>
	<property name="annotatedClasses">
		<list>
			<value>asd.syrotenko.entity.Product</value>
		</list>
	</property>
</bean>

<!--HibernateTemplate bean-->
<bean class="org.springframework.orm.hibernate5.HibernateTemplate" name="hibernateTemplate" p:sessionFactory-ref="sessionFactory"/>
```

***HibernateTemplate*** is used to perform database operations. It provides various methods which facilitate the insertion, deletion, modification, and retrieval of data from the database:
- `save(Object entity)` - create object in the database;
- `update(Object entity)` - update object in the database;
- `delete(Object entity)` - delete object in the database;
- `get(Class<T> entityClass, Serializable id)` - get object by id;
- `List<T> loadAll(Class<T> entityClass)` - get all records.

Also we can configure ***TransactionManager*** by init `HibernateTransactionManager` class with sessionFactory and also allow transactions by `<tx:annotation-driven/>` element. 

```xml
<tx:annotation-driven/>

<!--TransactionManager bean-->
<bean class="org.springframework.orm.hibernate5.HibernateTransactionManager" name="transactionManager" p:sessionFactory-ref="sessionFactory"/>
```

After that we can use `@Transactional` annotation on method that should be executed with commit/rollback logic.

```java
@Transactional
public List<Product> findAll() {
	return hibernateTemplate.loadAll(Product.class);
}
```
