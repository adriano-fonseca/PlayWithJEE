# JAVA EE 6 Technologies and Unit and Integrating Tests

This project aim is to be a proof of concept of use of some frameworks, usually used to develop enterprise systems.
The idea is to test JPA, EJB, Hibernate in order to produce WebServices Rest and SOAP. For this I intend use CDI and other concepts and maybe construct a simple API using Swaeger.

# Testing

I intend configure unit and integration tests through use of JUnit and Arquillian.

Example of datasource config to jboss 7.1.1 Final using Postgres 9.5, don't forget to put de jar inside of modules folder, respecting
the structur org/postgresql/main/postgresql-xxx.jar

```xml	
			<datasources>
                <datasource jndi-name="java:jboss/datasources/AppDS" pool-name="AppDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://localhost:5432/app</connection-url>
                    <driver>org.postgresql</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>app</user-name>
                        <password>pass</password>
                    </security>
                    <statement>
                        <prepared-statement-cache-size>32</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
                <drivers>
                    <driver name="pgsql" module="org.org.postgresql"/>
                </drivers>
 ```