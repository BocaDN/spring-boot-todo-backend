pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            bat '''mvn --version
            git --version
            java -version'''
          }
        }

        stage('Check for POM') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('Run Static Code Analysis') {
      steps {
        build job: static-code-analysis}
      }
    }

  

    stage('Build Docker Image') {
      steps {
        build job: static-code-analysis
      }
    }

    stage('Create Executable JAR File') {
      steps {
        sh 'mvn package spring-boot:repackage'
      }
    }  

    stage('Build Docker IMage') {
      steps {
        sh 'sudo docker build -t bocadn/spring-boot-todo-backend .'
      }
    }   

    stage('Software Versions') {
            steps {
                       sh "docker push bocadn/spring-boot-todo-backend:first"
                    
                }
            }
       } 
}
