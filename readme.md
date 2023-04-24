
# JVM



```sql
CREATE DATABASE demo;
USE demo;

create table posts (
 id  int primary key NOT NULL AUTO_INCREMENT,
 header varchar(120) NOT NULL,
 content varchar(220) NOT NULL,
 author varchar(120),
 Date datetime not null default now()
);
```

