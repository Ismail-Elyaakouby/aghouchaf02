def BRANCHCURRENT = 'master'

pipeline {
    agent any
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
