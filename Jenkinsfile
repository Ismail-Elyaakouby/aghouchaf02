def BRANCHCURRENT = 'master'

pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
        jdk 'Java 17.0.4.1'
    }
   stages {
		stage ('Compile Stage') {
			when {
				expression {
					return BRANCHCURRENT == 'master';
				}
			}
			steps {
				script {
					try {
						sh 'echo "clean compile done.."'
						sh 'mvn -B -DskipTests clean install -Dmaven.clean.failOnError=false'
						
					} catch (error) {
						currentBuild.result = 'FAILURE'
					}
				}
			}
		}
	}
}
