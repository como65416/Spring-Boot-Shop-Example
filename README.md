# Spring Boot Example

一個簡單的 spring boot API 範例

包含：
- JWT 驗證 (Service)
- 檢查是否登入 (Interceptor)
- JPA 與 Reposity
- Swagger UI (http://localhost:8080/swagger-ui.html)

資料庫：

```
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

