job('seed-external-job') {
  scm {
      git('https://github.com/chinhtran9/HelloWorldChinh.git')
  }
  steps {
    dsl {
      external('jobs-script/external_job.groovy')
    }
  }
}
