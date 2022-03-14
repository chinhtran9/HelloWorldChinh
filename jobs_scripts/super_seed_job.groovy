def tfs = 'https://github.com/figaw/freestyle-to-pipeline-jenkins.git'
def superSeedJobName = "super_seed_job"

job(superSeedJobName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs_script/*.groovy')
    }
  }
