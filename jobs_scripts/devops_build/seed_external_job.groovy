def folderName = 'devops_builds'
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
      external('jobs_script/' + folderName + '/hello_world_external_1.groovy')
      external('jobs_script/' + folderName + '/hello_world_external_2.groovy')
    }
  }
