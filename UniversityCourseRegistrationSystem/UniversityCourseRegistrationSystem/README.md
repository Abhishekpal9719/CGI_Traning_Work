# University Course Registration System (Console, JDBC, Java 8 Streams)

## Tech
- Java 8
- JDBC (H2 database)
- Maven
- MVC-ish layering (DAO, Service, Controller)
- Streams/Lambdas for business logic in Service

## Run
```bash
# From project root
mvn -q -DskipTests package
mvn -q exec:java
```

DB files are created locally (`./university_db.*`).

## Menu
1. Add Student  
2. Add Course  
3. Register Student for Course  
4. View All Students with Registered Courses (JOIN in DAO + groupingBy in Service)  
5. Search Courses by Minimum Credit Requirement (stream filter)  
6. Get Students Registered in a Particular Course (filter + map)  
7. Sort Students by Year and then by Name (Comparator.comparing)  
8. Calculate Total Credits Per Student (groupingBy + summingInt)  
9. Seed Sample Data  
0. Exit
