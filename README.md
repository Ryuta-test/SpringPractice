# CRUD�T���v���A�v���P�[�V����

### �T�v
[Spring](https://spring.io/)��p�����AWeb�A�v���P�[�V�����J���̃T���v�����쐬����B  
�N���C�A���g����̃��N�G�X�g�ɑ΂��鏈����Controller���s���A�K�v�ɉ�����Service���Ăяo���B  
Service�̓r�W�l�X���W�b�N��S���B  
ViewModel�̓N���C�A���g�ŕ\���������ێ�����B

### �����t���[
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

|��ʖ�|�@�\|
|:---|---|
|���O�C�����  |[Spring Security](https://spring.io/projects/spring-security)�ɂ��F�؁A�F��|
|�g�b�v���  |���O�C���������̑J�ډ��|
|README���  |���Y�^���L�ڂ��Ă�����|
|Ajax�T���v�����  |Ajax��Get�APost���N�G�X�g�̃T���v�����|
|CRUD�T���v�����  |������ǉ��A�Q�ƁA�X�V�A�폜���ł�����|


### �v���W�F�N�g�\��
```
main
����java
��  ����jp
��      ����practice
��          ����spring
��              ��  PracticeApplication.java
��              ��  ServletInitializer.java
��              ��
��              ����config
��              ��      PrimaryConfig.java
��              ��      WebMvcConfig.java
��              ��      WebSecurityConfig.java
��              ��
��              ����controller
��              ��      AjaxTemplateController.java
��              ��      IndexController.java
��              ��      LoginController.java
��              ��      MemoController.java
��              ��      ReadmeController.java
��              ��
��              ����mybatis
��              ��  ����dto
��              ��  ��      Login.java
��              ��  ��      LoginExample.java
��              ��  ��      Memo.java
��              ��  ��      MemoExample.java
��              ��  ��      MemoKey.java
��              ��  ��
��              ��  ����mapper
��              ��          LoginMapper.java
��              ��          MemoMapper.java
��              ��
��              ����service
��              ��      MemoService.java
��              ��      UserDetailsServiceImpl.java
��              ��
��              ����viewmodel
��                      LoginViewModel.java
��                      MemoViewModel.java
��                      ReqAjaxViewModel.java
��                      RetAjaxViewModel.java
��
����resources
��  ��  application.properties
��  ��  generatorConfig.xml
��  ��  messages.properties
��  ��  messages_ja.properties
��  ��
��  ����mybatis
��  ��  ����mapper
��  ��          LoginMapper.xml
��  ��          MemoMapper.xml
��  ��
��  ����static
��  ��  ����css
��  ��  ��      bootstrap.min.css
��  ��  ��
��  ��  ����img
��  ��  ����js
��  ��      ����lib
��  ��      ��      bootstrap.min.js
��  ��      ��      jquery-3.4.1.min.js
��  ��      ��      jquery.validate.min.js
��  ��      ��
��  ��      ����origin
��  ��              common.js
��  ��              location.js
��  ��              memo.js
��  ��
��  ����templates
��      ��  ajaxTemplate.html
��      ��  index.html
��      ��  login.html
��      ��  memo.html
��      ��  Readme.html
��      ��
��      ����common
��              footer.html
��              header.html
��              layout.html
��              layout_login.html
��
����webapp
```

### �f�����

![1_���O�C�����](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/1_%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3.PNG)  
![3_�g�b�v�y�[�W���1](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/3_%E3%83%88%E3%83%83%E3%83%97%E3%83%9A%E3%83%BC%E3%82%B8.PNG)  
![4_README���](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/4_README.PNG)  
![5_Ajax�T���v�����](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/5_Ajax%E3%82%B5%E3%83%B3%E3%83%97%E3%83%AB.PNG)  
![10_Ajax�T���v�����](https://github.com/Ryuta-test/SpringPractice/blob/images/capture/10_%E3%83%A1%E3%83%A2%E3%82%92%E8%BF%BD%E5%8A%A0.PNG)  

### �\�t�g�E�F�A�\��

[openjdk-11](https://openjdk.java.net/projects/jdk/11/)  
[postgreSQL-9.5.21](https://www.enterprisedb.com/ja/downloads/postgres-postgresql-downloads)  
[jquery-3.4.1](https://jquery.com/)  
[bootstrap-4.4.1](https://getbootstrap.com/)  
[maven-3.6.3](https://maven.apache.org/install.html)  
[tomcat-9.0.31](http://tomcat.apache.org/)  

