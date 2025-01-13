pipeline {
    agent any
    tools {
        maven 'maven-3.9.9' // Use the Maven version you defined in the Jenkins configuration
    }
    environment {
        DOCKER_IMAGE = "product-service"
        DOCKER_TAG = "latest"
        DOCKER_HOST = 'unix:///var/run/docker.sock'
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
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Check if the container exists and stop/remove it if it does
                    sh '''
                    if [ "$(docker ps -q -f name=$DOCKER_IMAGE)" ]; then
                        echo "Stopping existing container: $DOCKER_IMAGE"
                        docker stop $DOCKER_IMAGE
                        echo "Removing existing container: $DOCKER_IMAGE"
                        docker rm $DOCKER_IMAGE
                    else
                        echo "No existing container found with the name: $DOCKER_IMAGE"
                    fi
                    '''
                    // Run the new container
                    echo "Starting new container: $DOCKER_IMAGE"
                    sh 'docker run -d -p 8070:8070 --name $DOCKER_IMAGE $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
    }
}