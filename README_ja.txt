Ant、Maven および Gradle との統合に関する説明は、DTP_Engines_for_Java_User_Manual.pdf ファイルおよび plugins-manual.html にあります。
これらのマニュアルは、manuals/ja ディレクトリにあります。

以下の手順は、Demo プロジェクトに対して Static Analysis Engine (SAE) および
Code Coverage Engine (CCE) を有効にした Unit Tests Connector (UTC) を実行する
方法です。

Demo 用の設定が [INSTALL]/examples/demo ディレクトリの demo.properties ファイルとして提供されています。demo.properties ファイルは Ant、Maven、Gradle のビルド
スクリプトで使用できます。以下のコマンド ラインの例は、Demo プロジェクトのディレクトリ
から実行することを前提としています。


前提条件
-------------------------------------------------
1. インストール ディレクトリにある jtestcli.properties に Jtest DTP Engine のライセンスが設定されている必要があります。


Jtest DTP Engine のデータ ファイル
-------------------------------------------------
1. 静的解析 (SAE) を実行するには、次のコマンドを直接使用して 「Recommended Rules」コンフィギュレーションを実行します。
   Windows:
     ..\..\jtestcli.exe -config "builtin://Recommended Rules" -data demo.data.json -report report
   UNIX:
     ../../jtestcli -config "builtin://Recommended Rules" -data demo.data.json -report report

Ant
-------------------------------------------------
1. ant がパスに含まれていることを確認します。
2. 静的解析 (SAE) および単体テスト (UTC) を実行してカバレッジ (CCE) をレポートするには、「Demo Configuration」コンフィギュレーションを実行します。
     
     ant -file jtest.xml
     (Ant 1.6 の場合、コマンドライン オプション " -lib lib/junit-4.11.jar" を追加します。このオプションがないと、テストは実行できません)

   Demo プロジェクトがビルドされ、単体テストが実行されます。Ant プラグインはソース コードのコンパイル データを収集し、解析を実行してカバレッジ情報とともにテスト結果を収集し、レポートを生成します。

   注意:
   
   静的解析 (SAE) だけを実行するには、次のコマンドを使用します:
     
     ant -file jtest.xml jtest-sae
   
   単体テスト (UTC) だけを実行するには、次のコマンドを使用します。:
     
     ant -file jtest.xml jtest-utc
     (Ant 1.6 の場合、コマンドライン オプション " -lib lib/junit-4.11.jar" を追加します。このオプションがないと、テストは実行できません)
       
   コンフィギュレーションは jtest.xml で指定されています。jtest、jtest-sae、または jtest-utc
   ターゲットを参照してください。


Maven
-------------------------------------------------
1. mvn がパスに含まれていることを確認します。
2. 次のガイドに従って Maven を設定します。
   manuals/ja/plugins-manual.html: [Jtest Maven Plugin] > [使用方法] > [初期セットアップ]

3. 静的解析 (SAE) および単体テスト (UTC) を実行してカバレッジ (CCE) をレポートするには、「Demo Configuration」コンフィギュレーションを実行します。
    mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Demo Configuration"
    (または mvn clean test-compile jtest:instrument test jtest:jtest -Djtest.config="builtin://Demo Configuration")
  
   サンプル プロジェクトがビルドされ、単体テストが実行されます。Maven プラグインはソース コードのコンパイル データを収集し、解析を実行してカバレッジ情報とともにテスト結果を収集し、レポートを生成します。

   注意:
   
   静的解析 (SAE) だけを実行するには、次のコマンドを使用します:
   
     mvn jtest:jtest
   
   デフォルトで 「Recommended Rules」コンフィギュレーションが使用されます。

   単体テスト (UTC) だけを実行するには、次のコマンドを使用します。:
   
    mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Unit Tests"
    (または mvn clean test-compile jtest:instrument test jtest:jtest -Djtest.config="builtin://Unit Tests")


Gradle
-------------------------------------------------
1. gradle がパスに含まれていることを確認します。
2. インストール済みの Jtest DTP Engine パッケージを設定するか、ビルドスクリプトの jtest ブロックに任意の設定を追加します。

3. 静的解析 (SAE) および単体テスト (UTC) を実行して結果をレポートするには、「Demo Configuration」コンフィギュレーションを実行します。

     gradle clean jtest-agent test jtest -Djtest.config="builtin://Demo Configuration"
     (または gradle clean jtest-instrument test jtest -Djtest.config="builtin://Demo Configuration")

   サンプル プロジェクトがビルドされ、Junit テストが実行されます。Gradle プラグインはソース コードのコンパイル データを収集し、解析を実行してテスト結果を収集し、レポートを生成します。

   注意:
   
   静的解析 (SAE) だけを実行するには、次のコマンドを使用します:
     
     gradle clean build jtest
   
   デフォルトで 「Recommended Rules」コンフィギュレーションが使用されます。

   単体テスト (UTC) だけを実行するには、次のコマンドを使用します。:
   
     gradle clean jtest-agent test jtest -Djtest.config="builtin://Unit Tests"
     (または gradle clean jtest-instrument test jtest -Djtest.config="builtin://Unit Tests")


=================================================
アプリケーション カバレッジの収集

1. "mvn" および java がパスに設定されていることを確認します。

2. 次のガイドに従って Maven を設定します。
    manuals/ja/plugins-manual.html: [Jtest Maven Plugin] > [使用方法] > [初期セットアップ]

3. アプリケーションをビルドし、モニタリングに必要なデータを収集します。
     mvn clean package jtest:monitor
   
   注意: 結果として monitor.zip ファイルが得られるはずです。

4. アプリケーションを実行してカバレッジ データを収集します。
   
   Windows:
     
     a) monitor.zip アーカイブを demo ディレクトリに展開します (monitor というサブディレクトリが作成されます)。
        Archive path: target\jtest\monitor\monitor.zip
     
     b) agent.bat を実行します。
        cd monitor        
        agent.bat
        cd ..
      
     c) ステップ b) で生成された Java VM 引数を使ってアプリケーションを実行します。
        java -cp target\Demo.jar [ステップ b) で生成された Java VM 引数を貼り付ける] examples.stackmachine.RunnableStackMachine

     d) "Stack Machine Example" アプリケーションを使っていくつかアクションを実行します。
        - Insert 123 number into "Input" field
        - press "push" button 5 times
        - press "+", "-", "x" and "/" buttons
        - exit application

   UNIX:

     a) monitor.zip アーカイブを demo ディレクトリに展開します (monitor というサブディレクトリが作成されます)。
        unzip ./target/jtest/monitor/monitor.zip
     
     b) agent.sh を実行します。
        ./monitor/agent.sh
      
     c) ステップ b) で生成された Java VM 引数を使ってアプリケーションを実行します。
        java -cp ./target/Demo.jar [ステップ b) で生成された Java VM 引数を貼り付ける] examples.stackmachine.RunnableStackMachine

     d) "Stack Machine Example" アプリケーションを使っていくつかアクションを実行します。
        - Insert 123 number into "Input" field
        - press "push" button 5 times
        - press "+", "-", "x" and "/" buttons
        - exit application

5. カバレッジ レポートを生成します。

     Windows:  
       ..\..\jtestcli.exe -config "builtin://Calculate Application Coverage" -staticcoverage monitor\static_coverage.xml -runtimecoverage monitor\runtime_coverage
     UNIX
       ../../jtestcli -config "builtin://Calculate Application Coverage" -staticcoverage ./monitor/static_coverage.xml -runtimecoverage ./monitor/runtime_coverage

     カバレッジの詳細は report.html で確認できます。


