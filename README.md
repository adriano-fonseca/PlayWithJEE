# JAVA EE 6 Technologies and Unit and Integrating Tests

This project aim is to be a proof of concept of use of some frameworks, usually used to develop enterprise systems.
The idea is to test JPA, EJB, Hibernate in order to produce WebServices Rest and SOAP. For this I intend use CDI and other concepts and maybe construct a simple API using Swaeger.
It is inside o scope of this project build of RESTFul API that respect HATEOAS (Hypermedia as the Engine of Application State)

## WebServices REST

To access the services you shall use http://localhost:8080/PlayWithJavaEE/rest/{serviceName}/  

# Testing

I have configured unit and integration tests through use of JUnit and Arquillian.

For configure the data source from jboss 7.1.1 embedded, you will follow the steps bellow:

1. Create the path $JBOSS_HOME/modules/org/postgresql/main. The modules/org part should already exist, make directories for the rest.

2. In $JBOSS_HOME/modules/org/postgresql/main/module.xml with the following content, changing the resource-root entry for the PgJDBC driver to refer to the driver you wish to use.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
     <resources>
         <resource-root path="postgresql-9.1-902.jdbc4.jar"/>
     </resources>
     <dependencies>
         <module name="javax.api"/>
         <module name="javax.transaction.api"/>
         <module name="javax.servlet.api" optional="true"/>
     </dependencies>
 </module>
 ```

3. Into the same directory as module.xml place postgresql-9.1-902.jdbc4.jar
4. Start JBoss AS
5. Open jboss-cli by running $JBOSS_HOME/bin/jboss-cli --connect
6. Run the command:

```
/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql, driver-class-name=org.postgresql.Driver, driver-module-name=org.postgresql)
```

Thas is what you need to put in your standalone xml:

```xml	
			<datasource jndi-name="java:jboss/datasources/AppDS" pool-name="AppDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://localhost:5432/app</connection-url>
                    <driver>postgresql-driver</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>app</user-name>
                        <password>password</password>
                    </security>
                    <statement>
                        <prepared-statement-cache-size>32</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
                 <drivers>
                    <driver name="postgresql-driver" module="org.postgresql"/>
                </drivers>
 ```