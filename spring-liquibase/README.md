## Getting started with multi tenancy

### Create initial database
`$ mvn liquibase:update -Dliquibase.propertyFile=./src/main/resources/liquibase/liquibase.cat.properties`

### Generate diff from JPA entities 
Make sure the project has been compiled before (target folder exists)
`$ mvn liquibase:diff -Dliquibase.propertyFile=./src/main/resources/liquibase/liquibase.cat.properties`

### Include diff in master changelog
Include the generated diff in the src/src/main/resources/liquibase/changelog/changelog-master.xml
Like so:

```
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
	  http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <include file="./src/main/resources/liquibase/changelog/diffs/2020-03-18T16:05:53Z_changelog.xml"/>

</databaseChangeLog>s
```

### Update database with new master changes
`$ mvn liquibase:update -Dliquibase.propertyFile=./src/main/resources/liquibase/liquibase.cat.properties`