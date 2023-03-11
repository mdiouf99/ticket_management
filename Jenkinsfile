pipeline {
    agent any
    environment {
        DOCKER_USERNAME = credentials('DOCKER_USERNAME')
        DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
    }
    stages {
        stage('Preparation') {
            steps {
                bat 'echo "nothing needs to be installed"'
            }
        }
        stage('Build') {
            steps {
                bat 'docker --version'
                bat 'docker-compose -f docker-compose-build.yaml build --parallel'
                bat 'mvn --version'
            }
        }
        stage('Tag and Push') {
            steps {
                bat 'docker tag ticket-management ${DOCKER_USERNAME}/ticket-management-backend:%BUILD_ID%'
                withCredentials([usernamePassword(credentialsId: 'DOCKER_LOGIN', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    bat 'echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin'
                    bat 'docker push %DOCKER_USERNAME%/ticket-management-backend:%BUILD_ID%'
                }
            }
        }
    }
}
