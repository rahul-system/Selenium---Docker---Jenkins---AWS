pipeline {
    agent any

    stages {
        stage('Build Jar') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image') {
            steps {
                bat "docker build -t thakurkumarrahul/selenium:latest ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-creds') // Replace 'dockerhub-creds' with your actual credentials ID
            }

            steps {
                script {
                    // Login to Docker Hub
                    bat """
                    docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}
                    """

                    // Push Docker image
                    bat "docker push thakurkumarrahul/selenium:latest"
                    bat "docker tag thakurkumarrahul/selenium:latest thakurkumarrahul/selenium:${env.BUILD_NUMBER}"
                    bat "docker push thakurkumarrahul/selenium:${env.BUILD_NUMBER}"
                }
            }
        }
    }

    post {
        always {
            // Logout from Docker Hub
            bat "docker logout"
        }
    }
}
