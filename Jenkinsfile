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
					} catch (error) {
						currentBuild.result = 'FAILURE'
					}
				}
			}
		}


	stage("rollback deployment") {
	    steps {
	           withCredentials([file(credentialsId: 'KUBECONFIG', variable: 'KUBECONFIG')]) {
	           sh """
	                kubectl get nodes
			   """
	           }
	        }
	    }
	}
}
