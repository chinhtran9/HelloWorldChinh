/*GroovyShell shell = new GroovyShell()
def variables = shell.parse(new File('variables.groovy'))
echo(variables.devops_build_folder + "  -----")
*/
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

println ('build jobs and views')

job('seed_job') {
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
