import hudson.model.*;
import groovy.json.JsonOutput;

def echo_msg(msg){
    JsonOutput jsonOutput = new JsonOutput();
    jsonOutput.putAt("xiao","肖");
    jsonOutput.putAt("dong","东");
    println("输入json 信息 = "+jsonOutput.toString());
    println("在groovy脚本中输出信息="+msg);

}
return this;
//jenkinsmodules/files/modules/PipelineFirst.groovy
