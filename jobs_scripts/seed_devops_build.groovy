def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

folder(folderName) {
    description('Folder containing all jobs for folder-a')
}

job(folderName + '/seed_' + folderName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs_scripts/' + folderName + '/*.groovy')
    }
  }
}
