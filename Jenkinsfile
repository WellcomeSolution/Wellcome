pipeline {
<<<<<<< HEAD
    agent anystages{
       stage("checkout") {
            steps{
=======
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh './gradlew clean test' //run a gradle task
>>>>>>> ffd8bb353309c4cbdd44268b02d6a3c76effe15c
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
