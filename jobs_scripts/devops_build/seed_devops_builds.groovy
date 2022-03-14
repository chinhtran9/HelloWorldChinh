def folderName = 'devops_builds'
def tfs = 'https://github.com/figaw/freestyle-to-pipeline-jenkins.git'

folder(folderName) {
    description('Folder containing all jobs for folder-a')
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
