pipeline {

    agent any

    stages {
        stage ('Clone') {
            steps {
                git branch: 'master', url: "https://github.com/chinhtran9/HelloWorldChinh.git"
            }
        }
        stage('build') {
            steps {
                echo 'Build docker image'
                script{
                   // sh 'docker build -t dockerImage .'

                    def customImage = docker.build("jenkinsImageTest:${env.BUILD_ID}")

                }
               
            }
        }
    } 
}
