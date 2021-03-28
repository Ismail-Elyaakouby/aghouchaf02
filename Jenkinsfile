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
		    withCredentials([kubeconfigFile(credentialsId: 'k8s_config_file', variable: 'k8s_config_file')]) {
		     sh 'kubectl get nodes'
		   }
	        }
	    }
	}
}
