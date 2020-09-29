### Java and Postgres JDBC interaction 
* Make sure that JDBC driver is connected.

table example:

| id | first_name | last_name   | age         |
|----|------------|-------------|-------------|
| 1  | Marsel     | Sidikov     | 27          |
| 2  | Ildar      | Gainatullin | 18          |
| 3  | Vitaliy    | Dubrovets   | 19          |
| 4  | Azat       | Nabiev      | 19          |
| 5  | Felix      | Nabiev      | 19          |
| 5  | Kamil      | Nabiev      | 19          |


###Program execution example: 

```java
Testing connection to PostgreSQL JDBC
PostgreSQL JDBC Driver successfully connected
You successfully connected to database now

//ID = 3
3 Vitaliy Dubrovets 18

//UserName = "Marsel"
1 Marsel Sidikov 27

//All by age = 19
2 Ildar Gainatullin 19
4 Azat Nabiev 19
5 Felix Rosenberg 19
6 Kamil Axanov 19

//All 
2 Ildar Gainatullin 19
1 Marsel Sidikov 27
4 Azat Nabiev 19
5 Felix Rosenberg 19
6 Kamil Axanov 19
3 Vitaliy Dubrovets 18

Process finished with exit code 0
```



 
 