# Amazon Linux2上にWebアプリケーションをデプロイする

### 概要
[Spring Boot](https://spring.io/)を用いて、作成したWebアプリケーションをAWSの仮想サーバー上にデプロイして、  
インターネットからのアクセスを行う。

### インフラ設計
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

### 環境構築手順
1. デプロイするwarファイルの作成
    - コマンドプロンプトを起動
    - プロジェクトのフォルダまで移動
    ```
    cd <プロジェクトのあるフォルダ>
    ```
    - warファイルを作成するフォルダ(targetフォルダ)内を削除
    ```
    mvn clean
    ```
    - warファイルを作成
    ```
    mvn install
    ```
    - targetフォルダ内にwarファイルが作成される

1. AWSでEC2（仮想サーバー）を作成する
    - 以下を参考にして、Amazon Linux2の仮想サーバーを作成する
    - [参考：AWSの使い方（アカウントつくりから）](https://note.com/kuyo/n/n49b56b1448b1)
    - [参考：EC2設定](https://www.nedia.ne.jp/blog/tech/2015/04/28/5165)
    - [参考：AWS上のEC2（仮想サーバー）にTeratermでSSH接続する方法](https://dev.classmethod.jp/articles/aws-beginner-ec2-ssh/)

1. 各種ミドルウェアをインストールする
    - SSHで作成したAmazon Linux2の仮想サーバーにアクセス
    - ワークディレクトリを作成する
    ```
    mkdir ~/work
    ```
    - Javaをインストール
        - ワークディレクトリに移動
        ```
        cd ~/work
        ```
        - open-jdkのrpmをインターネットから入手
        ```
        wget https://d3pxv6yz143wms.cloudfront.net/11.0.3.7.1/java-11-amazon-corretto-devel-11.0.3.7-1.x86_64.rpm
        ```
        - Javaのインストールを実行
        ```
        sudo yum localinstall java-11-amazon-corretto-devel-11.0.3.7-1.x86_64.rpm
        ```
        - Javaのバージョンを確認
        ```
        java -version
        ```
        
    - Apacheをインストール
        - ワークディレクトリに移動
        ```
        cd ~/work
        ```
        - yumでインストールを実行
        ```
        sudo yum install -y httpd
        ```
        - Apacheのバージョンを確認
        ```
        sudo httpd -v
        ```
        - Apache起動
        ```
        sudo systemctl start httpd
        ```
        - Apache状態確認
        ```
        sudo systemctl status httpd
        ```
        - ブラウザから以下にアクセス可能
        ```
        http://<パブリックDNS>
        ※パブリックDNSの確認は、AWSのマネジメントコンソールからできます。
        ```
        - EC2インスタンス起動時に、自動でApacheサービスも起動
        ```
        sudo systemctl enable httpd.service
        ```

    - Tomcatをインストール
        - ワークディレクトリに移動
        ```
        cd ~/work
        ```
        - Tomcatユーザーの作成
        ```
        sudo useradd -s /sbin/nologin tomcat
        ```
        - Tomcatユーザーが作成されていることを確認
        ```
        sudo ls -la /home/
        ```
        - Tomcatのパッケージをインターネットからダウンロード
        ```
        wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.31/bin/apache-tomcat-9.0.31.tar.gz
        ```
        - tar.gzを解凍
        ```
        tar -xvf apache-tomcat-9.0.31.tar.gz
        ```
        - Tomcatを移動
        ```
        sudo mv apache-tomcat-9.0.31 /opt/
        ```
        - Tomcatのシンボリックリンクを作成
        ```
        ln -s /opt/apache-tomcat-9.0.31 /opt/apache-tomcat
        chown -h tomcat.tomcat /opt/apache-tomcat
        ```
        - 備考：Tomcatのバージョン変更
        ```
        ln -nfs [変更後のディレクトリのパス] /opt/apache-tomcat
        ```
        - Tomcatのログのシンボリックリンク作成
        ```
        ln -s /opt/apache-tomcat/logs/ /var/log/tomcat
        chown -h tomcat.tomcat /var/log/tomcat
        ```
        - TomcatをOSにサービスとして登録する為、ルート権限でUnitを作成
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
        - EC2インスタンス起動時に、自動でTomcatサービスも起動
        ```
        sudo systemctl enable tomcat.service
        ```

        - Tomcat状態確認
        ```
        sudo systemctl status tomcat.service
        ```

        - Tomcatのバージョンを確認
        ```
        /opt/apache-tomcat/bin/version.sh
        ```

        - apacheとtomcat起動（順番注意）
        ```
        sudo systemctl start httpd.service
        sudo systemctl start tomcat.service
        ```

        - ブラウザから以下にアクセス可能
        ```
        http://<パブリックDNS>/
        http://<パブリックDNS>:8080/
        ```

        - apacheとtomcatを停止（順番注意）
        ```
        sudo systemctl stop tomcat
        sudo systemctl stop httpd
        ```

    - warをローカルからコピー
        - Teratermの「ファイル」タブ
        - SSH SCP をクリック
        - 上の二つのテキストボックスに以下を入力
        ```
        from : ローカルに用意したwarファイル
        to : ~/work
        ```

        - warをデプロイ
        ```
        cd ~/work
        sudo cp Spring.war /opt/apache-tomcat/webapps
        ```

    - ネットワークの設定
        - プロキシの設定を確認
        ```
        cat /etc/httpd/conf.modules.d/00-proxy.conf
        ```
        ```
        以下の設定が含まれていることを確認する。
        LoadModule proxy_module modules/mod_proxy.so
        LoadModule proxy_ajp_module modules/mod_proxy_ajp.so
        ```
        
        - サーブレットへのリクエストがあったとき、ajpポートにフォワードする
        ```
        sudo vi /etc/httpd/conf.d/proxy-ajp.conf
        ```
        ```
        <Location /**warファイルの名前** >
        ProxyPass ajp://localhost:8009/**warファイルの名前**
        Order allow,deny
        Allow from all
        </Location>
        ```
        ```
        例：Spring.warを作成した場合
        <Location /Spring >
        ProxyPass ajp://localhost:8009/Spring
        Order allow,deny
        Allow from all
        </Location>
        ```
        
        - Tomcatがajpポートで受けれるように設定
        ```
        sudo vi /opt/apache-tomcat/conf/server.xml
        ```
        ```
         以下の設定のコメントを外して追記（117行目付近）
         <!-- Define an AJP 1.3 Connector on port 8009 -->
         <Connector protocol="AJP/1.3"
             address="localhost"
             port="8009"
             redirectPort="8443"
             secretRequired="false" />
        ```
        - Tomcatの8080ポートで受ける設定をコメントアウト
        ```
        sudo vi /opt/apache-tomcat/conf/server.xml
        ```
        ```
        Apache側からTomcatのページを表示させるため、8080ポートの設定を無効化する。（69行目付近）
        <!--
        <Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443" /> -->
        ```

        - httpdを再起動する
        ```
        sudo systemctl restart httpd
        ```

        - tomcatを再起動する
        ```
        sudo systemctl restart tomcat
        ```

        - ブラウザから以下にアクセスすると、作成したWebアプリケーションのログイン画面が表示される
        ```
        http://パブリックDNS/<warファイルの名前>
        ```

        - 参考：エラーが出たら、まずはtomcatのcatalina.outでhttpdからのリクエストを受けたか確認
        ```
        sudo less /var/log/tomcat/catalina.out | grep ajp
        ```

    - PostgreSQLをインストール
        - yumでインストールを実行
        ```
        sudo yum install postgresql-server postgresql-devel postgresql-contrib
        ```
        - DB初期設定
        ```
        sudo postgresql-setup initdb
        ```
        - DB起動
        ```
        sudo systemctl start postgresql
        ```

        - DB状態確認
        ```
        sudo systemctl status postgresql
        ```

        - dataフォルダが作成されたことを確認
        ```
        sudo ls -la /var/lib/pgsql/data
        ```

        - postgresユーザーが作成されていることを確認
        ```
        grep postgres /etc/passwd
        ```
        
        - postgresユーザーのパスワードを設定する
        ```
        sudo passwd postgres
        →PW：任意のパスワードを設定
        ```

        - postgresユーザーに切り替える
        ```
        su - postgres
        →PW:設定したパスワードを入力する
        ```

        - DBを確認
        ```
        psql -l
        ```

        - PostgreSqlのバージョン確認
        ```
        psql --version
        ```

        - psqlプログラムを起動
        ```
        psql
        ```

        - PostgreSQLが管理する「postgres」ユーザーのパスワードを設定
        ```
        postgres=# alter role postgres with password 'postgres';
        ```

        - psqlプログラム終了
        ```
        postgres=# \q
        ```

        - EC2-userに戻す
        ```
        exit
        ```

        - デフォルトのPeer認証を無効化する
        ```
        sudo vi /var/lib/pgsql/data/pg_hba.conf
        ```
        ```
        80行目付近コメントアウト
        #local   all       all        peer
        以下を追加
        local all all md5
        以下を変更
        # IPv4 local connections:
        host    all   all  127.0.0.1/32  ident
        ↓
        host    all   all  127.0.0.1/32  md5
        ```

        - postgresqlを停止
        ```
        sudo systemctl stop postgresql
        ```

        - postgresqlを起動
        ```
        sudo systemctl start postgresql
        ```

        - developユーザーを作成
        ```
        createuser -U postgres -P develop
        →developのパスワード：
        →postgresのパスワード：
        ```

        - practiceDBを作成
        ```
        createdb -E UTF8 -O develop -U postgres practiceDB
        →postgresのパスワード
        ```

        - practiceDBにアクセス
        ```
        psql -U develop -d practiceDB
        →developのパスワード
        ```

        - DBの確認
        ```
        practiceDB> \l
        ```

        - psqlプログラム終了
        ```
        practiceDB> \q
        ```

        - 参考：DBの削除
        ```
        dropdb -U postgres -i -e practiceDB
        ```

        - EC2-userに戻す
        ```
        exit
        ```

    - DDLを実行
        - ローカル環境で実行していたSQLを実行、レコードをインサート
        - ブラウザから以下にアクセスすると、作成したWebアプリケーションのログイン画面が表示される
        ```
        http://パブリックDNS/<warファイルの名前>
        ```
        - DBにインサートしたレコードで、ログインができる

1. ログの出力量を減らす
    - Tomcatのログ
    ```
    sudo vi /opt/apache-tomcat/conf/logging.properties
    ```
    ```
    25行目からすべてコメント
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

    - Tomcat(Server.xml)のログ
    ```
    sudo vi /opt/apache-tomcat/conf/server.xml
    ```
    ```
    164行目のvalueタグをコメントにする
     <!--<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
        prefix="localhost_access_log" suffix=".txt"
        pattern="%h %l %u %t &quot;%r&quot; %s %b" />-->
    ```


### ソフトウェア構成

[openjdk-11](https://openjdk.java.net/projects/jdk/11/)  
[postgreSQL-9.5.21](https://www.enterprisedb.com/ja/downloads/postgres-postgresql-downloads)  
[tomcat-9.0.31](http://tomcat.apache.org/)  
[apache-2.4](https://httpd.apache.org/)

### 備考

上記の設定で、インターネットからアクセスが可能になりますが、セキュリティ面では考慮していないので、  
AWSのマネジメントコンソールから、セキュリティグループの設定をしてください。  
なお、セキュリティグループで80ポート(http)を開ける必要があります。  
ちなみに、firewalldはAmazon Linux2にはインストールされていませんが、代わりにセキュリティグループを設定します。  
[AWSのセキュリティグループ](https://docs.aws.amazon.com/ja_jp/AWSEC2/latest/UserGuide/security-group-rules-reference.html)


### 参考

[ApacheとTomcatの連携](https://qiita.com/Dace_K/items/9d0419aefcb969335ca5)  
[Tomcatインストール](https://qiita.com/hiren/items/2a4f1b55c99ebfb3fd08)  
[PostgreSQLインストール](https://www.setouchino.cloud/blogs/95#postgresql-yum)  
[CentOS7にPostgreSQLインストール](https://qiita.com/jinnai73/items/af58bdac6fca869933c7)  
[Peer認証無効化](https://command-f.tech/postgresql/1)  
