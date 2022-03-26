pipeline {

    agent any

    stages {
        stage('init') {
            steps {
                echo 'init step'
            }
        }
        stage('build') {
            steps {
                echo 'Build docker image'
                script{
                    sh 'docker build -t dockerImage .'
                }
               
            }
        }
    } 
}
