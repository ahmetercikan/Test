pipeline {
    agent {
        node {
            label "${env.AGENT}"
                }
    }
    tools {
        maven 'maven-3.6.3'
    }
    stages {
        stage("Test") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn -Dsurefire.suiteXmlFiles=src/main/resources/TestNG.xml test"
                }
            }
        }
        stage('ReportS') {
                steps {
                script {
                        allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/surefire-reports/']]
                        ])
                }
                }
            }
    }
    post {
    		always {
    			mail to: 'ahmetmesutercikan@hotmail.com',
    				subject: "Odev Test Status ${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
    				body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
      }
}