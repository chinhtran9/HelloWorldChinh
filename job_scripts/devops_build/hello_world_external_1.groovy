import org.codehaus.groovy.runtime.StackTraceUtils
def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

def jobName = StackTraceUtils.deepSanitize(new Exception()).getStackTrace().last().getFileName()

println(StackTraceUtils.deepSanitize(new Exception()).getStackTrace().last())
println(StackTraceUtils.deepSanitize(new Exception()).getStackTrace())

job(jobName) {
  steps {
    shell('echo Hello 1, World External!')
  }
}
