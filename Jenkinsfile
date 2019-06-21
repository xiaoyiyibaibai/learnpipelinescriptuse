import hudson.model.*;
    timestamps{
    node{
        def username = 'Jenkins'
        echo 'Hello Mr. ${username}'
        echo "I said, Hello Mr. ${username}";
        echo "WORKSPACE Mr. ${env.WORKSPACE}";
        def basicSteps;
        def firstObj ;
        try{

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
                 println("readFile 的读取相对路径信息=src/main/resources/application.yaml");
                 def text =  readFile(encoding: 'UTF-8', file: 'src/main/resources/application.yaml');
                println("readFile 的读取相对路径信息"+text);
           }

           stage('checkout') {
              // 导出git的代码
               checkout(scm);
               basicSteps = load('jenkinsmodules/files/modules/PipelineBasicSteps.groovy');
               basicSteps.echo_msg2();
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
               firstObj.echo_msg(baseGroovyFilePath);
           }


            stage('Build') {
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


            stage('Test') {

              echo "做单元测试";



            }


            stage('Deploy') {

                echo "必须有此条件，才可以部署";
                if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        println("Deploy stage");
                 }

            }

        }catch(e){
           println('错误信息'+e.getMessage());
        }finally {
                // jdk的路径
                def jdkResult = tool(name: 'jdk1.8', type: 'jdk');
                println("jdkResult results ="+jdkResult);
                println("build stage");

               def  content = "列举一些全局变量--";
               content= content+ "env.BRANCH_NAME = "+ env.BRANCH_NAME +"\n  env.JOB_NAME ="+env.JOB_NAME +";  env.JOB_URL=${env.JOB_URL}";
               //给人员发邮件
               emailext(body: '我是邮件emailext-阶段prepare stage'+content, subject: 'prepare stage', to: 'xiaodonghong@gsafety.com');
                // 将text信息写入到文件中，file路径是相对于此项目的workspace中的路径。
                basicSteps.write_File(encoding: 'UTF-8', file: 'writeFile.txt', text: '''将这些信息写入到writeFile.txt文件中！！
                ''');
               //做完了所有的step之后，将workspace删除
                  basicStep.deletecdir();
                 if (currentBuild.result == 'UNSTABLE') {
                     echo 'I am unstable :/'
                 } else {
                     echo 'One way or another, I have finished'
                 }
             }
     }
    }