### Java and Postgres JDBC interaction 
* Make sure that JDBC driver is connected.

table example:

| id | first_name | last_name   |
|----|------------|-------------|
| 1  | Marsel     | Sidikov     |
| 2  | Ildar      | Gainatullin |
| 3  | Vitaliy    | Dubrovets   |
| 4  | Azat       | Nabiev      |

program execution example: 

```java
Testing connection to PostgreSQL JDBC
PostgreSQL JDBC Driver successfully connected
You successfully connected to database now
3 Vitaliy Dubrovets

1 Marsel Sidikov

1 Marsel Marsel
2 Ildar Ildar
3 Vitaliy Vitaliy
4 Azat Azat

Process finished with exit code 0
```



 
 