# Spring Boot Example

一個簡單的 spring boot API 範例

包含：
- Validation
- Transaction
- JPA 與 Repository
- JWT 驗證 (Service)
- 驗證 JWT 是否正確 (Filter)
- Swagger UI (http://localhost:8080/swagger-ui.html)

資料庫： [連結](./docs/database.sql)

## 指令

啟動

```sh
./gradlew bootRun
```

build 成 WAR 檔案

```sh
./gradlew build
```

---

不完善的地方 :
- [ ] Swagger 的 Example 目前版本無法正常顯示 (等待 3.0 穩定版再看看)
