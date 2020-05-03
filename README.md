# CRUDサンプルアプリケーション

### 概要
[Spring](https://spring.io/)を用いた、Webアプリケーション開発のサンプルを作成する。  
クライアントからのリクエストに対する処理はControllerが行い、必要に応じてServiceを呼び出す。  
Serviceはビジネスロジックを担う。  
ViewModelはクライアントで表示する情報を保持する。

### 処理フロー
```
+----------------+  HTTP Request    +----------------+       +----------------+       +----------------+
|    browser     | +--------------> |   Controller   | +---> |    Service     | +---> |   PostgreSQL   |
|                |                  |                |       |                |       |                |
|                |                  |                | <---+ |                | <---+ |                |
|                |                  |                |       +----------------+       +----------------+
|                |                  |                |
|                |                  |                |       +----------------+
|                |                  |                | +---> |    ViewModel   |
|                |  HTTP Response   |                |       |                |
|                | <--------------+ |                | <---+ |                |
+----------------+                  +----------------+       +----------------+
```

|画面名|機能|
|:---|---|
|ログイン画面  |[Spring Security](https://spring.io/projects/spring-security)による認証、認可|
|トップ画面  |ログイン成功時の遷移画面|
|README画面  |備忘録を記載している画面|
|Ajaxサンプル画面  |AjaxのGet、Postリクエストのサンプル画面|
|CRUDサンプル画面  |メモを追加、参照、更新、削除ができる画面|


### プロジェクト構成
```
main
├─java
│  └─jp
│      └─practice
│          └─spring
│              │  PracticeApplication.java
│              │  ServletInitializer.java
│              │
│              ├─config
│              │      PrimaryConfig.java
│              │      WebMvcConfig.java
│              │      WebSecurityConfig.java
│              │
│              ├─controller
│              │      AjaxTemplateController.java
│              │      IndexController.java
│              │      LoginController.java
│              │      MemoController.java
│              │      ReadmeController.java
│              │
│              ├─mybatis
│              │  ├─dto
│              │  │      Login.java
│              │  │      LoginExample.java
│              │  │      Memo.java
│              │  │      MemoExample.java
│              │  │      MemoKey.java
│              │  │
│              │  └─mapper
│              │          LoginMapper.java
│              │          MemoMapper.java
│              │
│              ├─service
│              │      MemoService.java
│              │      UserDetailsServiceImpl.java
│              │
│              └─viewmodel
│                      LoginViewModel.java
│                      MemoViewModel.java
│                      ReqAjaxViewModel.java
│                      RetAjaxViewModel.java
│
├─resources
│  │  application.properties
│  │  generatorConfig.xml
│  │  messages.properties
│  │  messages_ja.properties
│  │
│  ├─mybatis
│  │  └─mapper
│  │          LoginMapper.xml
│  │          MemoMapper.xml
│  │
│  ├─static
│  │  ├─css
│  │  │      bootstrap.min.css
│  │  │
│  │  ├─img
│  │  └─js
│  │      ├─lib
│  │      │      bootstrap.min.js
│  │      │      jquery-3.4.1.min.js
│  │      │      jquery.validate.min.js
│  │      │
│  │      └─origin
│  │              common.js
│  │              location.js
│  │              memo.js
│  │
│  └─templates
│      │  ajaxTemplate.html
│      │  index.html
│      │  login.html
│      │  memo.html
│      │  Readme.html
│      │
│      └─common
│              footer.html
│              header.html
│              layout.html
│              layout_login.html
│
└─webapp
```

### デモ画面

![1_ログイン画面](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/1_%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3.PNG)  
![3_トップページ画面1](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/3_%E3%83%88%E3%83%83%E3%83%97%E3%83%9A%E3%83%BC%E3%82%B8.PNG)  
![4_README画面](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/4_README.PNG)  
![5_Ajaxサンプル画面](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/5_Ajax%E3%82%B5%E3%83%B3%E3%83%97%E3%83%AB.PNG)  
![10_Ajaxサンプル画面](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/10_%E3%83%A1%E3%83%A2%E3%82%92%E8%BF%BD%E5%8A%A0.PNG)  

### ソフトウェア構成

[openjdk-11](https://openjdk.java.net/projects/jdk/11/)  
[postgreSQL-9.5.21](https://www.enterprisedb.com/ja/downloads/postgres-postgresql-downloads)  
[jquery-3.4.1](https://jquery.com/)  
[bootstrap-4.4.1](https://getbootstrap.com/)  
[maven-3.6.3](https://maven.apache.org/install.html)  
[tomcat-9.0.31](http://tomcat.apache.org/)  

