pipeline {

    agent any

    stages {
        stage('build') {
            steps {
                echo 'init step'
            }
        }
        stage('test') {
            steps {
                echo 'Build docker image'
            /*  script{
                    sh 'docker build -t dockerImage .'
                }
                */
            }
        }
    } 
}
