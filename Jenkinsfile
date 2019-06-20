timestamps{
    try{
    // 定义参数 如此定义的参数，需要在构建之前输入，才能进行构建
     //   properties([parameters([string(defaultValue: 'Hello', description: 'How should I greet the world?', name: 'Greeting')])])
        // 使用jenkinsScripted Pipeline 可以在里面的任何位置使用groovy语法

        def username = 'Jenkins'
        echo 'Hello Mr. ${username}'
        echo "I said, Hello Mr. ${username}";
        node {
          stage('prepare') {
             if (currentBuild.previousBuild&&currentBuild.previousBuild.result == null) {
                  if(currentBuild.nextBuild){
                    println("skip this build"+currentBuild.id);
                    return ;
                  }else{
                    println("wait previousBuild ");
                    Thread.sleep(2000);
                  }

                }
                  withEnv(['MYName=xiaodonghong']) {
                                    println("代码块中env.MYName自定义的环境变量是${env.MYName}")
                                     println("代码块中MYName自定义的环境变量是${MYName}")
                  }

              println("代码块中后env.MYName自定义的环境变量是${env.MYName}")
              println("代码块中后MYName自定义的环境变量是${MYName}")
              println("系统配置中的环境变量XIAODONGHONG_TEST的是${env.XIAODONGHONG_TEST}")
               withEnv(['XIAODONGHONG_TEST=肖东红']) {
                    println("代码快中有变化?XIAODONGHONG_TEST自定义的环境变量是${env.XIAODONGHONG_TEST}")
                }
             println("XIAODONGHONG_TEST自定义的环境变量是${env.XIAODONGHONG_TEST}")
           // 输出参数信息 println("输出参数--${params.Greeting}");
            // 发送邮件报250 ok queue id 不知如何解决
          //  mail(
        //         from: 'a250604@sina.com',
         //        subject: '我要发邮件了',
          //       cc: 'xiaodonghong@gsafety.com',
         //        to: 'xiaodonghong@gsafety',
          //       body: '我是content'
          //     );
           // 准备阶段1.先判断上一个构建是否完成
               def  content = "列举一些全局变量--";
               content= content+ "env.BRANCH_NAME = "+ env.BRANCH_NAME +"\n  env.JOB_NAME ="+env.JOB_NAME +";  env.JOB_URL=${env.JOB_URL}";
               emailext(body: '我是邮件emailext-阶段prepare stage'+content, subject: 'prepare stage', to: 'xiaodonghong@gsafety.com');
                println("prepare stage");
               println("error 标记-start");
                // 遇到次标记，抛出异常，不再进行下面的操作。error('错误信息');
               println("error 标记-end");
               def flag = fileExists('writeFile.txt');
               if(flag){
                 println("fileExists('writeFile.txt') true");
               }else{
                  println("fileExists('writeFile.txt') false");
               }
                println("将要沉睡2秒");
               Thread.sleep(2000);
                println("2秒后，睡醒了，写入文件writeFile.txt");
                // 将text信息写入到文件中，file路径是相对于此项目的workspace中的路径。
                writeFile(encoding: 'UTF-8', file: 'writeFile.txt', text: '''将这些信息写入到writeFile.txt文件中！！
                ''');

                 println("readFile 的读取相对路径信息=src/main/resources/application.yaml");
                 def text =  readFile(encoding: 'UTF-8', file: 'src/main/resources/application.yaml');
                println("readFile 的读取相对路径信息"+text);

               println("readFile 的读取绝对路径地址=/opt/yunweibyxdh/data/jenkinsdata/workspace/springboot2test_develop/src/main/resources/application.yaml");
               def text2 =  readFile(encoding: 'UTF-8', file: '/opt/yunweibyxdh/data/jenkinsdata/workspace/springboot2test_develop/src/main/resources/application.yaml');
               println("readFile 的读取绝对路径地址"+text2);
           }
           stage('checkout') {
               println("checkout scm");
              // 导出git的代码
               checkout(scm);
                def flag = fileExists('test.keystore');
              if(flag){
                println("fileExists('test.keystore') true");
              }else{
                 println("fileExists('test.keystore') false");
              }
               println("归档 archiveArtifacts 'pom.xml,Jenkinsfile' 两个文件");
               archiveArtifacts('pom.xml,Jenkinsfile');
               println("归档 archiveArtifacts 'pom.xml,Jenkinsfile' 两个文件完成！");
           }


            stage('Build') {
               println("build stage-使用maven进行打包");
                 // 使用maven进行打包
                def mavenResult =tool(name: 'maven', type: 'maven');
                 println("mavenResult results ="+mavenResult);
                //执行sh的script脚本
                def mvnpath;
               if(isUnix()){
                 mvnpath = "${mavenResult}/bin/mvn package -l mvnlog.log"
               }
               def status = sh(returnStatus: true,
                     script: mvnpath
                     );

                 println("status="+status);
                if(status>0){
                  println("mvn package 执行失败！"+status);
                }


                // jdk的路径
                def jdkResult = tool(name: 'jdk1.8', type: 'jdk');
                println("jdkResult results ="+jdkResult);
                println("build stage");
            }


            stage('Test') {
                def inputStr = input(
                            message: '请输入信息',
                            ok: '确认',
                            parameters:
                            [string(defaultValue: '肖东红', description: '输入你的名字', name: 'username', trim: false),
                            text(defaultValue: '我爱你小人', description: '请输入描述信息', name: '描述')]);
                println("输入信息："+inputStr);
                println("单元测试 stage");
            }


            stage('Deploy') {
          // 必须有此条件，才可以部署
               if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        println("Deploy stage");
                  }

            }
        }
    }
    catch(e){
     println("异常信息="+e.getMessage());
    }finally {
             if (currentBuild.result == 'UNSTABLE') {
                 echo 'I am unstable :/'
             } else {
                 echo 'One way or another, I have finished'
             }
         }

}
