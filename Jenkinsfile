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


	stage("K8S") {
	    steps {
	           //withCredentials([file(credentialsId: 'KUBECONFIG', variable: 'config')]) {
	           //sh 'kubectl get nodes'
	           //}
		    withCredentials([kubeconfigFile(credentialsId: 'KUBECONFIG', variable: 'KUBECONFIG')]) {
		     sh 'use $KUBECONFIG' // environment variable; not pipeline variable
		     sh 'kubectl get nodes'
		   }
	        }
	    }
	}
}
