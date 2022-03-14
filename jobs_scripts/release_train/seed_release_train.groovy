def folderName = 'release_train'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

folder(folderName) {
    description('Release train jobs')
}

job(folderName + '/seed_' + folderName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs_script/' + folderName + '/*.groovy')
    }
  }
}
