
def deleteDir(){
    echo "执行PipelineBasicSteps的deleteDir方法";
    def  lsresult =sh("ls -al ${env.WORKSPACE}");
    println("ls -al ${env.WORKSPACE} result=" )
    println(lsresult)
    def  deleteResult =  deleteDir()  // clean up current work directory
    println("deleteDir() result=" +deleteResult)

    def  lsresult2 = sh("ls -al ${env.WORKSPACE}")
    println("ls -al ${env.WORKSPACE} result=" )
    println(lsresult2)
}

return this;