import hudson.model.*;
    timestamps{
        try{
        // 定义参数 如此定义的参数，需要在构建之前输入，才能进行构建
         //   properties([parameters([string(defaultValue: 'Hello', description: 'How should I greet the world?', name: 'Greeting')])])
            // 使用jenkinsScripted Pipeline 可以在里面的任何位置使用groovy语法

            def username = 'Jenkins'
            echo 'Hello Mr. ${username}'
            echo "I said, Hello Mr. ${username}";
            echo "WORKSPACE Mr. ${env.WORKSPACE}";
            node{

           // 进入node之后就有了workspace 具有了项目信息,但是没有源代码，所以在此处调用是报错的
             def workspace =  env.WORKSPACE;
             def baseGroovyFilePath = workspace+"/jenkinsmodules/files/modules/";
             println("baseGroovyFilePath = "+baseGroovyFilePath);


              stage("parall"){

               echo "并发处理开始";

              }
            stage("Utility Steps method"){

                def files = findFiles(glob: '**/*.log')
                println("输出files信息="+files);


            }

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
                  println("系统配置中的环境变量XIAODONGHONG_TEST的是: ${env.XIAODONGHONG_TEST}")
                   withEnv(['XIAODONGHONG_TEST=肖东红']) {
                        println("代码快中有变化?XIAODONGHONG_TEST自定义的环境变量是${env.XIAODONGHONG_TEST}")
                    }
                 println("XIAODONGHONG_TEST自定义的环境变量是${env.XIAODONGHONG_TEST}")
               // 准备阶段1.先判断上一个构建是否完成
                   def  content = "列举一些全局变量--";
                   content= content+ "env.BRANCH_NAME = "+ env.BRANCH_NAME +"\n  env.JOB_NAME ="+env.JOB_NAME +";  env.JOB_URL=${env.JOB_URL}";
                   emailext(body: '我是邮件emailext-阶段prepare stage'+content, subject: 'prepare stage', to: 'xiaodonghong@gsafety.com');
                    println("prepare stage");
                   def flag = fileExists('writeFile.txt');
                   if(flag){
                     println("fileExists('writeFile.txt') true");
                   }else{
                      println("fileExists('writeFile.txt') false");
                   }
                    println("2秒后，睡醒了，写入文件writeFile.txt");
                    // 将text信息写入到文件中，file路径是相对于此项目的workspace中的路径。
                    writeFile(encoding: 'UTF-8', file: 'writeFile.txt', text: '''将这些信息写入到writeFile.txt文件中！！
                    ''');
                     println("readFile 的读取相对路径信息=src/main/resources/application.yaml");
                     def text =  readFile(encoding: 'UTF-8', file: 'src/main/resources/application.yaml');
                    println("readFile 的读取相对路径信息"+text);


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
                   // 已经将源代码导出到workspace中，使用load加载文件是
                   def firstObj = load ('jenkinsmodules/files/modules/PipelineFirst.groovy');
                   firstObj.echo_msg(baseGroovyFilePath);


               }


                stage('Build') {
                   println("build stage-使用maven进行打包");
                   // 判断pom.xml文件是否存在

                    def pomeflag = fileExists('writeFile.txt');
                      if(pomeflag){
                        def pomfile = readMavenPom(file: 'pom.xml');
                        println("readMavenPom(file: 'pom.xml') true"+pomfile);
                      }else{
                         println("readMavenPom(file: 'pom.xml')('writeFile.txt') false");
                      }

                     def yamlfiles = findFiles(glob: '**/*.yaml')
                      println("输出files信息="+yamlfiles);


// 直接获取
                           def tempfile= yamlfiles[0];
                             println("tempfile="+tempfile);
                            def yamls = readYaml (file: tempfile);
                            println("yamls=" + yamls);



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
           println('错误信息'+e.getMessage());
        }finally {
                 if (currentBuild.result == 'UNSTABLE') {
                     echo 'I am unstable :/'
                 } else {
                     echo 'One way or another, I have finished'
                 }
             }

    }