# Spring Boot Example

一個簡單的 spring boot API 範例

包含：
- JWT 驗證 (Service)
- 檢查是否登入 (Interceptor)
- JPA 與 Reposity
- Swagger UI (http://localhost:8080/swagger-ui.html)

資料庫：

```sql
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

不完善的地方 :
- [ ] Interceptor 到 Controller 的資料傳遞
- [ ] Swagger 的 Example 目前版本無法正常顯示 (等待 3.0 穩定版再看看)
