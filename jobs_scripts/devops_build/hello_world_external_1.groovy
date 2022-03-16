import groovy.transform.BaseScript
@BaseScript MainScript mainScript

def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'


println "$meaningOfLife" 

job(folderName + '/hello_world_external_1') {
  steps {
    shell('echo Hello 1, World External!')
  }
}
