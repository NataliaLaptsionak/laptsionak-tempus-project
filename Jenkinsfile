pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
        allure 'Allure Commandline instalations'
    }

     stages {
        stage('Checkout & Build') { // Изменил название стадии, чтобы было понятнее
            steps {

                checkout scm

                bat "mvn clean test"
            }
        }
    }

    post {
        always {
            echo 'Generating Allure report...'
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target\\allure-results']]
            ])
            archiveArtifacts artifacts: 'allure-report/**', fingerprint: true
        }

        success {
            echo 'Pipeline finished.'
        }

        failure {
            echo 'Pipeline suceeded'
        }

        unstable {
            echo 'Build is unstable'
        }
    }
}
