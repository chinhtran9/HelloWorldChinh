def folderName = 'release_train'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

folder(folderName) {
    description('Release train jobs')
}

job(folderName + '/seed_' + folderName) {
  scm {
      git(tfs)
  }
  steps {
    dsl {

      GroovyShell shell = new GroovyShell()
      def script = shell.parse(new File('/jobs_scripts/variables.groovy'))
      echo(script.devops_build_Folder + ' dffd ')
      external('jobs_scripts/' + folderName + '/*.groovy')
    }
  }
}
