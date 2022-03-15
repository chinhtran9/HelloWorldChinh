def folderName = 'devops_build'

job(folderName + '/hello_world_external_2') {
  steps {
    shell('echo Hello 2, World External!')
  }
}
