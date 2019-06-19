timestamps{
    try{
        // 使用jenkinsScripted Pipeline 可以在里面的任何位置使用groovy语法
        node {
          stage('prepare') {
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