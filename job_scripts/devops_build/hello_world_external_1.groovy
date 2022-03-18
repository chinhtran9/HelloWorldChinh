def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

File scriptFile = new File(getClass().protectionDomain.codeSource.location.path)

// This holds the file name like "myscript.groovy"
def scriptName = scriptFile.getName()

// strip the extension to get "myscript"
def withoutExt = scriptName.take(scriptName.lastIndexOf('.'))

println(withoutExt + 'sdfdfd')



job(folderName + '/hello_world_external_1') {
  steps {
    shell('echo Hello 1, World External!')
  }
}
