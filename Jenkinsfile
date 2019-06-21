import hudson.model.*;
timestamps{
    node{
        def username = 'Jenkins'
        echo 'Hello Mr. ${username}'
        echo "I said, Hello Mr. ${username}";
        echo "WORKSPACE Mr. ${env.WORKSPACE}";
        def basicSteps;
        def firstObj ;
        // 是全局变量，在stage里面的修改都有效果

        def abc = "我是肖东红";
        echo "abc1 = "+abc;
        try{

          stage('准备阶段') {
             if (currentBuild.previousBuild&&currentBuild.previousBuild.result == null) {
                  if(currentBuild.nextBuild){
                    println("skip this build"+currentBuild.id);
                    return ;
                  }else{
                    println("wait previousBuild ");
                    Thread.sleep(2000);
                  }

              }

          }

           stage('代码检出') {
              // 导出git的代码
               checkout(scm);
               println("readFile 的读取相对路径信息=src/main/resources/application.yaml");
                                def text =  readFile(encoding: 'UTF-8', file: 'src/main/resources/application.yaml');
              println("readFile 的读取相对路径信息"+text);
              basicSteps = load('jenkinsmodules/files/modules/PipelineBasicSteps.groovy');
              def flag = basicSteps.file_Exists('test.keystore');
              if(flag){
                println("fileExists('test.keystore') true");
              }else{
                 println("fileExists('test.keystore') false");
              }
               println("归档 archiveArtifacts 'pom.xml,Jenkinsfile' 两个文件");
               archiveArtifacts('pom.xml,Jenkinsfile');
               println("归档 archiveArtifacts 'pom.xml,Jenkinsfile' 两个文件完成！");
               // 已经将源代码导出到workspace中，使用load加载文件是
               firstObj = load ('jenkinsmodules/files/modules/PipelineUtilitySteps.groovy');
           }

            stage('代码检查') {

              echo "1. sonarqube代码质量检查 ";
              echo "2.maven项目可以使用findbug插件 ";

            }

            stage('构建') {
                 println("build stage-使用maven进行打包");

                // 判断pom.xml文件是否存在
                 def pomeflag = fileExists('pom.xml');

                  if(pomeflag){
                    def pomfile = readMavenPom(file: 'pom.xml');
                    println("readMavenPom(file: 'pom.xml') true"+pomfile);
                  }else{
                     println("readMavenPom(file: 'pom.xml')('writeFile.txt') false");
                  }

                   def yamlfiles = firstObj.find_files('**/*.yaml')
                    // 直接获取
                   def tempfile= yamlfiles[0];
                   println("读取yaml文件信息"+tempfile);

                 // 使用maven进行打包
                  def mavenResult =tool(name: 'maven', type: 'maven');
                  println("maven的工具信息="+mavenResult);
                 //执行sh的script脚本
                  def mvnpath;
                  if(isUnix()){
                    mvnpath = "${mavenResult}/bin/mvn package -l mvnlog.log"
                   }
                   echo "执行maven的package命令";
                   def status = sh(returnStatus: true,
                         script: mvnpath
                     );

                    if(status>0){
                      println("mvn package 执行失败！"+status);
                    }


            }

            stage('单元测试') {
                echo "做单元测试stage";
                def mavenResult =tool(name: 'maven', type: 'maven');
                println("maven的工具信息="+mavenResult);
               //执行sh的script脚本
                def mvnpath;
                if(isUnix()){
                   mvnpath = "${mavenResult}/bin/mvn test -l mvntest.log";
                   echo "执行maven的test命令";
                   def status = sh(returnStatus: true,
                         script: mvnpath
                     );

                     echo "status"+status;
                    echo "成功，归档mvn test 日志文件";
                    archiveArtifacts('mvntest.log');
                 }
            }

            stage('部署') {
                     echo "Deploy = abc4 = "+abc;
                     abc = "Deploy";
                     echo "Deploy-out = abc4 = "+abc;
                echo "必须有此条件，才可以部署";
                if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        println("Deploy stage");
                 }
            }

            stage("自动化测试"){
                echo "selenium自动化测试+chrome的dirver进行页面功能测试";
                echo "    1、下载具有selenium自动化测试+chrome的dirver的git代码";
                echo "    2、运行自动化代码里面的bat文件";

            //        checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                	//					    userRemoteConfigs: [[credentialsId: '6f4fa66c-eb02-46dc-a4b3-3a232be5ef6e',
                	//						url: 'https://github.com/QAAutomationLearn/JavaAutomationFramework.git']]]);


            }



            stage("常用指令模块"){

                echo "执行代码块执行超时的指令";
                def count =0;
                count =0;
                // 代码块最大执行时间，timeout单位是分，sleep单位是秒
                timeout(1){
                       count= count+1;
                            println "here we are test retry fuction"
                            sleep 1
                 }

                echo "执行错误后多次尝试的指令";
                 count =1;
                     //最大尝试执行次数是3
                   retry(2) {
                         count= count+1;
                         if(count==2){
                           echo "count==2不再尝试了";
                         }else{
                              println "here we are test retry fuction"
                              sleep 2
                              echo "2秒之后，再执行，除以0 异常，进入下一次尝试";
                              println 10/0
                         }
                   }

            }


        }catch(e){
           println('错误信息'+e.getMessage());
        }finally {
                echo "finally-out = abc4 = "+abc;
                // jdk的路径
                def jdkResult = tool(name: 'jdk1.8', type: 'jdk');
                println("jdkResult results ="+jdkResult);
                println("build stage");

                def  content = "列举一些全局变量--";
                content= content+ "env.BRANCH_NAME = "+ env.BRANCH_NAME +"\n  env.JOB_NAME ="+env.JOB_NAME +";  env.JOB_URL=${env.JOB_URL}";
                emailext(body: '我是邮件emailext-阶段prepare stage'+content, subject: 'prepare stage', to: 'xiaodonghong@gsafety.com');
                basicSteps.write_File( 'writeFile.txt','UTF-8',  '将这些信息写入到writeFile.txt文件中！');
               //做完了所有的step之后，将workspace删除
               //可用，这里调试先不删除  basicSteps.deletecdir();
                 if (currentBuild.result == 'UNSTABLE') {
                     echo 'I am unstable :/'
                 } else {
                     echo 'One way or another, I have finished'
                 }
             }
     }
  }