pipeline {
    agent any
    tools {
            maven 'maven-3.8.8' // Use the Maven version you defined in the Jenkins configuration
    }
    environment {
        DOCKER_IMAGE = "product-service"
        DOCKER_TAG = "latest"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                    sh 'docker push $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'docker run -d -p 8070:8070 --name product-service $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
    }
}
