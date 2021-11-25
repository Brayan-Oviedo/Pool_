pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  options {
      	buildDiscarder(logRotator(numToKeepStr: '3'))
   	disableConcurrentBuilds()
    }


	tools {
    jdk 'JDK11_Centos' //Preinstalada en la Configuración del Master
    gradle 'Gradle5.6_Centos' //Preinstalada en la Configuración del   Master
  }

	//Aquí comienzan los “items” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
		checkout([
			$class: 'GitSCM',
			branches: [[name: '*/master']],
			doGenerateSubmoduleConfigurations: false,
			extensions: [],
			gitTool: 'Default',
			submoduleCfg: [],
			userRemoteConfigs: [[
			credentialsId: 'GitHub_Brayan-Oviedo',
			url:'https://github.com/Brayan-Oviedo/Pool_'
			]]
		])
      }
    }

    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Compile & Unit Tests<------------"
        sh 'chmod +x pool/**/microservicio/gradlew'
		sh 'pool/**/microservicio/gradlew --b pool/**/microservicio/build.gradle clean'
		sh 'pool/**/microservicio/gradlew --b pool/**/microservicio/build.gradle test'
      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
		sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
        }
      }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
        // Construir sin tarea test que se ejecutó previamente
        sh 'pool/**/microservicio/gradlew --b pool/**/microservicio/build.gradle build -x test'
      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'

    }

    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}