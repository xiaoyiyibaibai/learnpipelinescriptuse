timestamps{
    try{
        // 使用jenkinsScripted Pipeline 可以在里面的任何位置使用groovy语法
        node {

          stage('prepare') {
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
               def flag = fileExists('test.keystore');
               if(flag){
                 println("fileExists('test.keystore') true");
               }else{
                  println("fileExists('test.keystore') false");
               }

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

                println("build stage");
            }
            stage('Test') {
                println("Test stage");
            }
            stage('Deploy') {
                 println("Deploy stage");
            }
        }
    }
    catch(e){
     println("异常信息="+e.getMessage());
    }

}
