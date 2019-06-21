import hudson.model.*;
import hudson.util.*;
import hudson.scm.*;
import java.util.Collections;
import java.util.Collection;
def echo_msg2(msg){
    println("在groovy脚本中输出信息="+msg);
}

// 用于递归删除workspace中的文件
def deletecdir(){
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

def file_Exists(path){
    return  fileExists(path);
}
// 将内容写入文件
def write_File(encoding="UTF_8",file,text=""){
    writeFile(encoding: encoding, file: file, text: text);
}

return this;