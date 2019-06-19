timestamps{
    try{
        // 使用jenkinsScripted Pipeline 可以在里面的任何位置使用groovy语法
        node {

          stage('prepare') {
            // 发送邮件
           mail(
               from: 'a250604@sina.com',
                subject:"我要发邮件了",
                cc:"xiaodonghong@gsafety.com",
                to:"xiaodonghong@gsafety",
                content:"我是content"
              );
           // 准备阶段1.先判断上一个构建是否完成

            println("prepare stage");
           }
           stage('checkout') {
               println("checkout scm");
              // 导出git的代码
               checkout(scm);

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
