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
emailext(body: '我是邮件emailext', subject: '我是邮件emailext', to: 'xiaodonghong@gsafety.com');



            println("prepare stage");
           }
           stage('checkout') {
               println("checkout scm");
              // 导出git的代码
               checkout(scm);
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
