def BRANCHCURRENT = 'master'

pipeline {

	agent 'slave-maven-01'

    
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
	           withCredentials([kubeconfigFile(credentialsId: 'kubernetes_config', variable: 'KUBECONFIG')]) {
	           sh """
	                kubectl get nodes
			   """
	           }
	        }
	    }
	}
}
