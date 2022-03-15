def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

job(devops_build + '/hello_world_external_1') {
  steps {
    shell('echo Hello 1, World External!')
  }
}
