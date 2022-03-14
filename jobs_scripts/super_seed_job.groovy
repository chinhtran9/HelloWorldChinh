def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'
def superSeedJobName = 'super_seed_job'

job(superSeedJobName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs_script/*.groovy')
    }
  }
}
