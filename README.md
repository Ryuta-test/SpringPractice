# CRUD�T���v���A�v���P�[�V����

***

### �T�v
Spring��p�����AWeb�A�v���P�[�V�����J���̃T���v�����쐬����B  
�N���C�A���g����̃��N�G�X�g�ɑ΂��鏈����Controller���s���A�K�v�ɉ�����Service���Ăяo���B  
Service�̓r�W�l�X���W�b�N��S���B  
ViewModel�̓N���C�A���g�ŕ\���������ێ�����B

|��ʖ�|�@�\|
|:---|---|
|���O�C�����  |Spring Security�ɂ��F�؁A�F��|
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