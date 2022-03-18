def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

folder('devops_build') {
    description('add devops_build description')
}

folder('release_train') {
    description('add release train description')
}

folder('devops_testing') {
    description('add devops_testing description')
}

folder('integration_release_train') {
    description('add integration_release_train description')
}

println('create seed job')

job('seed_jobs') {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      external('jobs_scripts/**/*.groovy')
      removeAction('DELETE')
    }
  }
}


println('create build_view')


job('build_view') {
  scm {
      git(tfs)
  }
  steps {
    dsl {
     
      external('jobs_scripts/build_view.groovy')
    }
  }
}

println('Completed')