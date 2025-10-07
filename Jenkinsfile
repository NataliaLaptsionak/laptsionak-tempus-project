ppipeline {
     agent any
     stages {
         stage('Checkout') {
             steps {
                 git branch: 'main', credentialsId: 'github-pat-laptsionak', url: 'https://github.com/NataliaLaptsionak/laptsionak-tempus-project.git'
             }
         }
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
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
