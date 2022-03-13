folder('container-folder') {
    description('Folder containing all jobs for folder-a')
}

job('container-folder/seed-external-job') {
  scm {
      git('https://github.com/chinhtran9/HelloWorldChinh.git')
  }
  steps {
    dsl {
      external('jobs-script/external_job.groovy')
    }
  }
}
