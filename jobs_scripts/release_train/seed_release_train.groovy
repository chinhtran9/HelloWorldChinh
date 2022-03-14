def folderName = 'release_train'
def tfs = 'https://github.com/figaw/freestyle-to-pipeline-jenkins.git'

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
