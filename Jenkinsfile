pipeline {

    agent {
        node {
            label 'main'
        }
    }

    stage('Cleanup Workspace') {
        steps {
            cleanWs()
            sh """
            echo "Cleaned Up Workspace For Project"
            """
        }
    }

    stage('Build docker image12') {
        steps {
            script{
                sh 'docker build -t dockerImage .'
            }
        }
    }

}
