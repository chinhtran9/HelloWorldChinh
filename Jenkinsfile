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
