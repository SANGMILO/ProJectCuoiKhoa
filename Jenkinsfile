pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'JDK17'; maven 'Maven3' }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test (headless, parallel)') {
      steps {
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
