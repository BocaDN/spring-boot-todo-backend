pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials')
        IMAGE_NAME = 'bocadn/spring-boot-todo-backend'
    }

    stages {
        stage('Clone from GitHub') {
            steps {
                echo "Cloning repository from GitHub..."
                // Replace with your GitHub repo URL and credentials if private
                git url: 'https://github.com/BocaDN/spring-boot-todo-backend.git', branch: 'main'
            }
        }

        stage('Compile and Test') {
            steps {
                echo "Compiling and testing..."
                sh 'mvn clean test'
            }
        }

        stage('Build Spring Boot JAR File') {
            steps {
                echo "Building JAR..."
                sh 'mvn package -DskipTests'
            }
        }

        stage('Create Docker Image') {
            steps {
                echo "Building Docker image..."
                sh "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Deploy to DockerHub') {
            steps {
                echo "Logging in and pushing Docker image..."
                sh """
                    echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_CREDENTIALS_USR} --password-stdin
                    docker push ${IMAGE_NAME}:latest
                """
            }
        }
    }

    post {
        always {
            echo "Cleaning up Docker..."
            sh 'docker logout'
        }
        success {
            echo "Pipeline finished successfully!"
        }
        failure {
            echo "Pipeline failed."
        }
    }
}
