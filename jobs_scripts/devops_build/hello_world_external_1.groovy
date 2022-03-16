evaluate(new File("./BaseTest.groovy"))
// Safer to use 'def' here as Groovy seems fussy about whether the filename (and therefore implicit class name) has a capital first letter
def tu = new Testutils()
tu.myUtilityMethod("hello world")


def folderName = 'devops_build'
def tfs = 'https://github.com/chinhtran9/HelloWorldChinh.git'

job(folderName + '/hello_world_external_1') {
  steps {
    shell('echo Hello 1, World External!')
  }
}
