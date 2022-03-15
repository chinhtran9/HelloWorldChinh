/*GroovyShell shell = new GroovyShell()
def variables = shell.parse(new File('variables.groovy'))
echo(variables.devops_build_folder + "  -----")
*/
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'
def superSeedJobName = 'super_seed_job'

job(superSeedJobName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {
      GroovyShell shell = new GroovyShell()
      def var = shell.parse(new File('variables.groovy'))
      echo(var.devops_build_folder + "  -----")
      external('jobs_scripts/*.groovy')
    }
  }
}
