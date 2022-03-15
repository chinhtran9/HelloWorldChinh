def folderName = 'release_train'

job(folderName + '/hello_release_train_1') {
  steps {
    shell('echo Hello hello_world_release_train_1')
  }
}
