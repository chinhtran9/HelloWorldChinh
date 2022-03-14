def folderName = 'devops-builds'
def tfs = 'https://github.com/figaw/freestyle-to-pipeline-jenkins.git'

folder(folderName) {
    description('Folder containing all jobs for folder-a')
}

job(folderName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs-script/' + folderName + '/hello_world_external.groovy')
    }
  }
}
