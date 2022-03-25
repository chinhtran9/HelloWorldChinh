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

    stage('Build docker image1') {
        steps {
            script{
                sh 'docker build -t dockerImage .'
            }
        }
    }

}

pipeline {
    agent any
    stages {
        stage('inti') {
            steps {
                echo 'init step'
            }
        }
        stage('Build docker image1') {
            steps {
                 echo 'Build docker image'
              /*  script{
                    sh 'docker build -t dockerImage .'
                }
                */
            }
    }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}
