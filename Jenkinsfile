
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/NataliaLaptsionak/laptsionak-tempus-project'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'

                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'

                sh 'mvn test'
            }
        }
        stage('Archive Artifacts') {
            steps {
                echo 'Archiving artifacts...'

                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
