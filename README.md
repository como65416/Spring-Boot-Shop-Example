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

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`id`, `username`, `password`, `role`) VALUES (1,'admin','$2a$10$K.5nkR8rQRkX5ISBFvVzJu6ETNVxAnZPN9kO7fPhsyeWXT78LAp3a','manager');
INSERT INTO `user` (`id`, `username`, `password`, `role`) VALUES (2,'bob','$2a$10$rPxC6zaGIyodjXDXoWNtA.Hvwi4GySDnAxDT8SKrvJLsmRGk7K4Om','member');
```

不完善的地方 :
- [ ] Swagger 的 Example 目前版本無法正常顯示 (等待 3.0 穩定版再看看)
