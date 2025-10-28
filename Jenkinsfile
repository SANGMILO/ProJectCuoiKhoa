pipeline {
  agent any
  options { timestamps() }
  environment {
    JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
    M2_HOME   = 'D:\\MVN\\apache-maven-3.9.11'
    PATH      = "${M2_HOME}\\bin;${JAVA_HOME}\\bin;${env.PATH}"
  }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build & Test') {
      steps {
        bat 'java -version'
        bat 'mvn -v'
        bat 'mvn -B clean test -DsuiteFile=execute.xml -Dthreads=5 -Dheadless=true'
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
          archiveArtifacts artifacts: 'target/**, test-output/**, allure-results/**', fingerprint: true
        }
      }
    }
  }
}
