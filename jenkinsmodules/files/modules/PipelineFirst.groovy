import hudson.model.*;

def echo_msg(msg){
    println("在groovy脚本中输出信息="+msg);

}
def find_files(filetype) {
    echo "find_files";
    def files = findFiles(glob:filetype)
    for (file in files) {
        println file.name
    }
    return files;
}

def read_json_file(file_path) {
    echo "read_json_file";
    def propMap = readJSON file : file_path
    propMap.each {
        println ( it.key + " = " + it.value )
    }
}

def read_json_file2(json_string) {
    def propMap = readJSON text : json_string
    propMap.each {
        println ( it.key + " = " + it.value )
    }
}

def read_yaml_file(file_path) {
    echo "read_yaml_file";
    def propMap = readYaml file : file_path
    propMap.each {
        println ( it.key + " = " + it.value )
    }
}

def read_yaml_str(yaml_str) {
    def propMap = readYaml  text : yaml_str
    propMap.each {
        println ( it.key + " = " + it.value )
    }
}


return this;
//jenkinsmodules/files/modules/PipelineFirst.groovy
