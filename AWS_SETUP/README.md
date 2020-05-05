# Amazon Linux2���Web�A�v���P�[�V�������f�v���C����

### �T�v
[Spring Boot](https://spring.io/)��p���āA�쐬����Web�A�v���P�[�V������AWS�̉��z�T�[�o�[��Ƀf�v���C���āA  
�C���^�[�l�b�g����̃A�N�Z�X���s���B

### �C���t���݌v
```
                                 +-------+
                                 |console|
                                 +-------+
                                     +
                                     |SSH 
                                     v
+---------------+                +------------------------------------------------------------+
|Client         |                |Amazon Linux2                                               |
| +---------+   |                |   +--------+        +----------+           +------------+  |
| | browser |   |                |   | httpd  |        | tomcat   |           | PostgreSql |  |
| |         |   |  http, https   |   |        |   ajp  |  +----+  | DB access |  +----+    |  |
| |         | +--------------------> |        | +-------> |8009| +-------------> |5432|    |  |
| |         |   |                |   |        |        |  |port|  |           |  |port|    |  |
| |         |   |                |   |        |        |  |    |  |           |  |    |    |  |
| |         |   |                |   |        |        |  |    |  |           |  |    |    |  |
| |         |   |                |   |        |        |  |    |  |           |  |    |    |  |
| |         | <--------------------+ |        | <-------+ |    | <-------------+ |    |    |  |
| |         |   |                |   |        |        |  +----+  |           |  +----+    |  |
| |         |   |                |   |        |        |          |           |            |  |
| +---------+   |                |   +--------+        +----------+           +------------+  |
|               |                |                                                            |
+---------------+                +------------------------------------------------------------+
```

### ���\�z�菇
1. �f�v���C����war�t�@�C���̍쐬
    - �R�}���h�v�����v�g���N��
    - �v���W�F�N�g�̃t�H���_�܂ňړ�
    ```
    cd <�v���W�F�N�g�̂���t�H���_>
    ```
    - war�t�@�C�����쐬����t�H���_(target�t�H���_)�����폜
    ```
    mvn clean
    ```
    - war�t�@�C�����쐬
    ```
    mvn install
    ```
    - target�t�H���_����war�t�@�C�����쐬�����

1. AWS��EC2�i���z�T�[�o�[�j���쐬����
    - �ȉ����Q�l�ɂ��āAAmazon Linux2�̉��z�T�[�o�[���쐬����
    - [�Q�l�FAWS�̎g�����i�A�J�E���g���肩��j](https://note.com/kuyo/n/n49b56b1448b1)
    - [�Q�l�FEC2�ݒ�](https://www.nedia.ne.jp/blog/tech/2015/04/28/5165)
    - [�Q�l�FAWS���EC2�i���z�T�[�o�[�j��Teraterm��SSH�ڑ�������@](https://dev.classmethod.jp/articles/aws-beginner-ec2-ssh/)

1. �e��~�h���E�F�A���C���X�g�[������
    - SSH�ō쐬����Amazon Linux2�̉��z�T�[�o�[�ɃA�N�Z�X
    - ���[�N�f�B���N�g�����쐬����
    ```
    mkdir ~/work
    ```
    - Java���C���X�g�[��
        - ���[�N�f�B���N�g���Ɉړ�
        ```
        cd ~/work
        ```
        - open-jdk��rpm���C���^�[�l�b�g�������
        ```
        wget https://d3pxv6yz143wms.cloudfront.net/11.0.3.7.1/java-11-amazon-corretto-devel-11.0.3.7-1.x86_64.rpm
        ```
        - Java�̃C���X�g�[�������s
        ```
        sudo yum localinstall java-11-amazon-corretto-devel-11.0.3.7-1.x86_64.rpm
        ```
        - Java�̃o�[�W�������m�F
        ```
        java -version
        ```
        
    - Apache���C���X�g�[��
        - ���[�N�f�B���N�g���Ɉړ�
        ```
        cd ~/work
        ```
        - yum�ŃC���X�g�[�������s
        ```
        sudo yum install -y httpd
        ```
        - Apache�̃o�[�W�������m�F
        ```
        sudo httpd -v
        ```
        - Apache�N��
        ```
        sudo systemctl start httpd
        ```
        - Apache��Ԋm�F
        ```
        sudo systemctl status httpd
        ```
        - �u���E�U����ȉ��ɃA�N�Z�X�\
        ```
        http://<�p�u���b�NDNS>
        ���p�u���b�NDNS�̊m�F�́AAWS�̃}�l�W�����g�R���\�[������ł��܂��B
        ```
        - EC2�C���X�^���X�N�����ɁA������Apache�T�[�r�X���N��
        ```
        sudo systemctl enable httpd.service
        ```

    - Tomcat���C���X�g�[��
        - ���[�N�f�B���N�g���Ɉړ�
        ```
        cd ~/work
        ```
        - Tomcat���[�U�[�̍쐬
        ```
        sudo useradd -s /sbin/nologin tomcat
        ```
        - Tomcat���[�U�[���쐬����Ă��邱�Ƃ��m�F
        ```
        sudo ls -la /home/
        ```
        - Tomcat�̃p�b�P�[�W���C���^�[�l�b�g����_�E�����[�h
        ```
        wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.31/bin/apache-tomcat-9.0.31.tar.gz
        ```
        - tar.gz����
        ```
        tar -xvf apache-tomcat-9.0.31.tar.gz
        ```
        - Tomcat���ړ�
        ```
        sudo mv apache-tomcat-9.0.31 /opt/
        ```
        - Tomcat�̃V���{���b�N�����N���쐬
        ```
        ln -s /opt/apache-tomcat-9.0.31 /opt/apache-tomcat
        chown -h tomcat.tomcat /opt/apache-tomcat
        ```
        - ���l�FTomcat�̃o�[�W�����ύX
        ```
        ln -nfs [�ύX��̃f�B���N�g���̃p�X] /opt/apache-tomcat
        ```
        - Tomcat�̃��O�̃V���{���b�N�����N�쐬
        ```
        ln -s /opt/apache-tomcat/logs/ /var/log/tomcat
        chown -h tomcat.tomcat /var/log/tomcat
        ```
        - Tomcat��OS�ɃT�[�r�X�Ƃ��ēo�^����ׁA���[�g������Unit���쐬
        ```
        sudo vi /usr/lib/systemd/system/tomcat.service
        ```
        ```
        # Systemd unit file for default tomcat
        #
        # To create clones of this service:
        # DO NOTHING, use tomcat@.service instead.
        [Unit]
        Description=Apache Tomcat Web Application Container
        After=syslog.target network.target
        [Service]
        Type=oneshot
        PIDFile=/opt/apache-tomcat/tomcat.pid
        RemainAfterExit=yes
        #EnvironmentFile=/etc/tomcat/tomcat.conf
        #Environment="NAME="
        #EnvironmentFile=-/etc/sysconfig/tomcat
        ExecStart=/opt/apache-tomcat/bin/startup.sh
        ExecStop=/opt/apache-tomcat/bin/shutdown.sh
        ExecReStart=/opt/apache-tomcat/bin/shutdown.sh;/opt/apache-tomcat/bin/startup.sh
        SuccessExitStatus=143
        User=tomcat
        Group=tomcat
        [Install]
        WantedBy=multi-user.target
        ```
        - EC2�C���X�^���X�N�����ɁA������Tomcat�T�[�r�X���N��
        ```
        sudo systemctl enable tomcat.service
        ```

        - Tomcat��Ԋm�F
        ```
        sudo systemctl status tomcat.service
        ```

        - Tomcat�̃o�[�W�������m�F
        ```
        /opt/apache-tomcat/bin/version.sh
        ```

        - apache��tomcat�N���i���Ԓ��Ӂj
        ```
        sudo systemctl start httpd.service
        sudo systemctl start tomcat.service
        ```

        - �u���E�U����ȉ��ɃA�N�Z�X�\
        ```
        http://<�p�u���b�NDNS>/
        http://<�p�u���b�NDNS>:8080/
        ```

        - apache��tomcat���~�i���Ԓ��Ӂj
        ```
        sudo systemctl stop tomcat
        sudo systemctl stop httpd
        ```

    - war�����[�J������R�s�[
        - Teraterm�́u�t�@�C���v�^�u
        - SSH SCP ���N���b�N
        - ��̓�̃e�L�X�g�{�b�N�X�Ɉȉ������
        ```
        from : ���[�J���ɗp�ӂ���war�t�@�C��
        to : ~/work
        ```

        - war���f�v���C
        ```
        cd ~/work
        sudo cp Spring.war /opt/apache-tomcat/webapps
        ```

    - �l�b�g���[�N�̐ݒ�
        - �v���L�V�̐ݒ���m�F
        ```
        cat /etc/httpd/conf.modules.d/00-proxy.conf
        ```
        ```
        �ȉ��̐ݒ肪�܂܂�Ă��邱�Ƃ��m�F����B
        LoadModule proxy_module modules/mod_proxy.so
        LoadModule proxy_ajp_module modules/mod_proxy_ajp.so
        ```
        
        - �T�[�u���b�g�ւ̃��N�G�X�g���������Ƃ��Aajp�|�[�g�Ƀt�H���[�h����
        ```
        sudo vi /etc/httpd/conf.d/proxy-ajp.conf
        ```
        ```
        <Location /**war�t�@�C���̖��O** >
        ProxyPass ajp://localhost:8009/**war�t�@�C���̖��O**
        Order allow,deny
        Allow from all
        </Location>
        ```
        ```
        ��FSpring.war���쐬�����ꍇ
        <Location /Spring >
        ProxyPass ajp://localhost:8009/Spring
        Order allow,deny
        Allow from all
        </Location>
        ```
        
        - Tomcat��ajp�|�[�g�Ŏ󂯂��悤�ɐݒ�
        ```
        sudo vi /opt/apache-tomcat/conf/server.xml
        ```
        ```
         �ȉ��̐ݒ�̃R�����g���O���ĒǋL�i117�s�ڕt�߁j
         <!-- Define an AJP 1.3 Connector on port 8009 -->
         <Connector protocol="AJP/1.3"
             address="localhost"
             port="8009"
             redirectPort="8443"
             secretRequired="false" />
        ```
        - Tomcat��8080�|�[�g�Ŏ󂯂�ݒ���R�����g�A�E�g
        ```
        sudo vi /opt/apache-tomcat/conf/server.xml
        ```
        ```
        Apache������Tomcat�̃y�[�W��\�������邽�߁A8080�|�[�g�̐ݒ�𖳌�������B�i69�s�ڕt�߁j
        <!--
        <Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443" /> -->
        ```

        - httpd���ċN������
        ```
        sudo systemctl restart httpd
        ```

        - tomcat���ċN������
        ```
        sudo systemctl restart tomcat
        ```

        - �u���E�U����ȉ��ɃA�N�Z�X����ƁA�쐬����Web�A�v���P�[�V�����̃��O�C����ʂ��\�������
        ```
        http://�p�u���b�NDNS/<war�t�@�C���̖��O>
        ```

        - �Q�l�F�G���[���o����A�܂���tomcat��catalina.out��httpd����̃��N�G�X�g���󂯂����m�F
        ```
        sudo less /var/log/tomcat/catalina.out | grep ajp
        ```

    - PostgreSQL���C���X�g�[��
        - yum�ŃC���X�g�[�������s
        ```
        sudo yum install postgresql-server postgresql-devel postgresql-contrib
        ```
        - DB�����ݒ�
        ```
        sudo postgresql-setup initdb
        ```
        - DB�N��
        ```
        sudo systemctl start postgresql
        ```

        - DB��Ԋm�F
        ```
        sudo systemctl status postgresql
        ```

        - data�t�H���_���쐬���ꂽ���Ƃ��m�F
        ```
        sudo ls -la /var/lib/pgsql/data
        ```

        - postgres���[�U�[���쐬����Ă��邱�Ƃ��m�F
        ```
        grep postgres /etc/passwd
        ```
        
        - postgres���[�U�[�̃p�X���[�h��ݒ肷��
        ```
        sudo passwd postgres
        ��PW�F�C�ӂ̃p�X���[�h��ݒ�
        ```

        - postgres���[�U�[�ɐ؂�ւ���
        ```
        su - postgres
        ��PW:�ݒ肵���p�X���[�h����͂���
        ```

        - DB���m�F
        ```
        psql -l
        ```

        - PostgreSql�̃o�[�W�����m�F
        ```
        psql --version
        ```

        - psql�v���O�������N��
        ```
        psql
        ```

        - PostgreSQL���Ǘ�����upostgres�v���[�U�[�̃p�X���[�h��ݒ�
        ```
        postgres=# alter role postgres with password 'postgres';
        ```

        - psql�v���O�����I��
        ```
        postgres=# \q
        ```

        - EC2-user�ɖ߂�
        ```
        exit
        ```

        - �f�t�H���g��Peer�F�؂𖳌�������
        ```
        sudo vi /var/lib/pgsql/data/pg_hba.conf
        ```
        ```
        80�s�ڕt�߃R�����g�A�E�g
        #local   all       all        peer
        �ȉ���ǉ�
        local all all md5
        �ȉ���ύX
        # IPv4 local connections:
        host    all   all  127.0.0.1/32  ident
        ��
        host    all   all  127.0.0.1/32  md5
        ```

        - postgresql���~
        ```
        sudo systemctl stop postgresql
        ```

        - postgresql���N��
        ```
        sudo systemctl start postgresql
        ```

        - develop���[�U�[���쐬
        ```
        createuser -U postgres -P develop
        ��develop�̃p�X���[�h�F
        ��postgres�̃p�X���[�h�F
        ```

        - practiceDB���쐬
        ```
        createdb -E UTF8 -O develop -U postgres practiceDB
        ��postgres�̃p�X���[�h
        ```

        - practiceDB�ɃA�N�Z�X
        ```
        psql -U develop -d practiceDB
        ��develop�̃p�X���[�h
        ```

        - DB�̊m�F
        ```
        practiceDB> \l
        ```

        - psql�v���O�����I��
        ```
        practiceDB> \q
        ```

        - �Q�l�FDB�̍폜
        ```
        dropdb -U postgres -i -e practiceDB
        ```

        - EC2-user�ɖ߂�
        ```
        exit
        ```

    - DDL�����s
        - ���[�J�����Ŏ��s���Ă���SQL�����s�A���R�[�h���C���T�[�g
        - �u���E�U����ȉ��ɃA�N�Z�X����ƁA�쐬����Web�A�v���P�[�V�����̃��O�C����ʂ��\�������
        ```
        http://�p�u���b�NDNS/<war�t�@�C���̖��O>
        ```
        - DB�ɃC���T�[�g�������R�[�h�ŁA���O�C�����ł���

1. ���O�̏o�͗ʂ����炷
    - Tomcat�̃��O
    ```
    sudo vi /opt/apache-tomcat/conf/logging.properties
    ```
    ```
    25�s�ڂ��炷�ׂăR�����g
    ############################################################
    # Handler specific properties.
    # Describes specific configuration info for Handlers.
    ############################################################
    #1catalina.org.apache.juli.AsyncFileHandler.level = FINE
    #1catalina.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
    #1catalina.org.apache.juli.AsyncFileHandler.prefix = catalina.
    #1catalina.org.apache.juli.AsyncFileHandler.maxDays = 90
    #1catalina.org.apache.juli.AsyncFileHandler.encoding = UTF-8
    #2localhost.org.apache.juli.AsyncFileHandler.level = FINE
    #2localhost.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
    #2localhost.org.apache.juli.AsyncFileHandler.prefix = localhost.
    #2localhost.org.apache.juli.AsyncFileHandler.maxDays = 90
    #2localhost.org.apache.juli.AsyncFileHandler.encoding = UTF-8
    #3manager.org.apache.juli.AsyncFileHandler.level = FINE
    #3manager.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
    #3manager.org.apache.juli.AsyncFileHandler.prefix = manager.
    #3manager.org.apache.juli.AsyncFileHandler.maxDays = 90
    #3manager.org.apache.juli.AsyncFileHandler.encoding = UTF-8
    #4host-manager.org.apache.juli.AsyncFileHandler.level = FINE
    #4host-manager.org.apache.juli.AsyncFileHandler.directory = ${catalina.base}/logs
    #4host-manager.org.apache.juli.AsyncFileHandler.prefix = host-manager.
    #4host-manager.org.apache.juli.AsyncFileHandler.maxDays = 90
    #4host-manager.org.apache.juli.AsyncFileHandler.encoding = UTF-8
    #java.util.logging.ConsoleHandler.level = FINE
    #java.util.logging.ConsoleHandler.formatter = org.apache.juli.OneLineFormatter
    #java.util.logging.ConsoleHandler.encoding = UTF-8
    ############################################################
    # Facility specific properties.
    # Provides extra control for each logger.
    ############################################################
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].level = INFO
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].handlers = 2localhost.org.apache.juli.AsyncFileHandler
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/manager].level = INFO
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/manager].handlers = 3manager.org.apache.juli.AsyncFileHandler
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/host-manager].level = INFO
    #org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/host-manager].handlers = 4host-manager.org.apache.juli.AsyncFileHandler
    ```

    - Tomcat(Server.xml)�̃��O
    ```
    sudo vi /opt/apache-tomcat/conf/server.xml
    ```
    ```
    164�s�ڂ�value�^�O���R�����g�ɂ���
     <!--<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
        prefix="localhost_access_log" suffix=".txt"
        pattern="%h %l %u %t &quot;%r&quot; %s %b" />-->
    ```


### �\�t�g�E�F�A�\��

[openjdk-11](https://openjdk.java.net/projects/jdk/11/)  
[postgreSQL-9.5.21](https://www.enterprisedb.com/ja/downloads/postgres-postgresql-downloads)  
[tomcat-9.0.31](http://tomcat.apache.org/)  
[apache-2.4](https://httpd.apache.org/)

### ���l

��L�̐ݒ�ŁA�C���^�[�l�b�g����A�N�Z�X���\�ɂȂ�܂����A�Z�L�����e�B�ʂł͍l�����Ă��Ȃ��̂ŁA  
AWS�̃}�l�W�����g�R���\�[������A�Z�L�����e�B�O���[�v�̐ݒ�����Ă��������B  
�Ȃ��A�Z�L�����e�B�O���[�v��80�|�[�g(http)���J����K�v������܂��B  
���Ȃ݂ɁAfirewalld��Amazon Linux2�ɂ̓C���X�g�[������Ă��܂��񂪁A����ɃZ�L�����e�B�O���[�v��ݒ肵�܂��B  
[AWS�̃Z�L�����e�B�O���[�v](https://docs.aws.amazon.com/ja_jp/AWSEC2/latest/UserGuide/security-group-rules-reference.html)


### �Q�l

[Apache��Tomcat�̘A�g](https://qiita.com/Dace_K/items/9d0419aefcb969335ca5)  
[Tomcat�C���X�g�[��](https://qiita.com/hiren/items/2a4f1b55c99ebfb3fd08)  
[PostgreSQL�C���X�g�[��](https://www.setouchino.cloud/blogs/95#postgresql-yum)  
[CentOS7��PostgreSQL�C���X�g�[��](https://qiita.com/jinnai73/items/af58bdac6fca869933c7)  
[Peer�F�ؖ�����](https://command-f.tech/postgresql/1)  
