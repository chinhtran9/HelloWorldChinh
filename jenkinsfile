pipeline {

    agent {
        node {
            label 'main'
        }
    }

    options {
        buildDiscarder logRotator( 
                    daysToKeepStr: '16', 
                    numToKeepStr: '10'
            )
    }

    stages {
        
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }

        stage('Build docker image') {
            steps {
                script{
                    sh 'docker build -t dockerImage .'
                }
            }
        }

    }   
}
