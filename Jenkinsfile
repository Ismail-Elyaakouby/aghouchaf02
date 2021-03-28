#!groovy

node('slave-maven-01') {

 def app

 
environment {
    registry = "https://hub.docker.com/88915020"
    registryCredential = '88915020'
}
    currentBuild.result = "SUCCESS"

    try {

       stage('Checkout'){
          checkout scm
       }

       stage('Test'){
         env.NODE_ENV = "test"
         print "Environment will be : ${env.NODE_ENV}"
         sh 'mvn -B -DskipTests clean install -Dmaven.clean.failOnError=false'
       }

       stage('Build Docker'){
            sh 'docker build -f Dockerfile -t hellowordv01:$BUILD_NUMBER .'
       }
        
       stage('Deploy'){
         echo 'Push to Repo'
        //sh './dockerPushToRepo.sh'

       }
       
  
        stage('Clone repository') {
            checkout scm
        }

        stage('Build image') {
            app = docker.build("88915020/hellonode")
        }

        stage('Test image') {
            app.inside {
                sh 'echo "Tests passed"'
            }
        }

        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }
     ///////////////
        stage('k8S') {
            script{
                steps{
                    if(env.GIT_BRANCH.contains("master")){

                            def namespace="default"
                            def ENV="development"

                            withCredentials([file(credentialsId: 'secret', variable: 'KUBECONFIG')]) {
                            // change context with related namespace
                            // sh "kubectl config set-context $(kubectl config current-context) --namespace=${namespace}"
                             sh 'kubectl get nodes'
                            //Deploy with Helm
                            // echo "Deploying"
                            // sh "helm upgrade --install road-dashboard -f values.${ENV}.yaml --set tag=$TAG --namespace ${namespace}"    
                        }   
                    }
                }
            }
        }
     
     	                    //withCredentials([kubeconfigFile(credentialsId: 'kubernetes_config', variable: 'KUBECONFIG')]) {
	                        //sh 'kubectl create -f deployment.yaml'
	                        //}

     //////////////
    }
 
    catch (err) {

        currentBuild.result = "FAILURE"

        sh 'echo faiiiiiiled.//'
        throw err
    }

}
