# UserDemo (Spring Boot project)
本 project 為簡易的會員登入系統與會員後台管理系統設置，旨在使用 Spring Boot 框架中提供的功能架設，可當作日後框架中各項功能使用之參考

## 系統環境
- Java 17
- Spring Boot v3.2.3 (framework)
- Jpa/Hibernate (ORM)
- DataBase : MySQL v8.0.22
- Bootstrap v5.3.3

## 使用到框架功能
- Spring AOP : check user login
- Spring Data Jpa : CRUD for database
    - Specification : Dynamic search in database
- Validation : check the infomation from user input

## 建置作業
1. 首先先將程式碼下載到本機指定的資料夾中，執行以下指令或是直接按右上方的下載按鈕

```
git clone https://github.com/BreezeWhite/THSR-Ticket.git
```

2. 以 Java IDE 將其以 Maven 專案匯入，並更新此專案，使其下載相關的套件 (dependency)
3. 確認電腦中有 MySQL database，沒有的話須另行下載並進行設定
4. 設定 src/main/resources/application.properties 檔裡適當的連線資訊 (下方文字xxx處)

```
## datasource
spring.datasource.url=jdbc:mysql://localhost:3306/xxx?serverTimezone=Asia/Taipei&characterEncoding=utf-8
spring.datasource.username=xxx
spring.datasource.password=xxx
```

5. 在 IDE 中進行編譯 (build project)，並啟動專案
6. 網址列中輸入 http://localhost:8099/user-demo/index 即可進入首頁
7. 進入使用者後台管理頁面需輸入網址 : http://localhost:8099/user-demo/admin/login
    - 系統預設帳號 : ADMIN
    - 系統預設密碼 : 123456

## TODO
* i18n 功能
* 初次登入強制更改管理者密碼
* 新增其他管理者功能






