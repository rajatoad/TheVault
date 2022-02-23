pipeline{

agent any
    stages{
    stage("build"){
      steps {
            echo 'building the application with Maven...'
            withMaven(maven: 'Maven') {
            sh ('mvn clean package')
}
          }
        }
      
                stage("deploy"){
                      steps {
                    echo 'No issues found with the project. Running JAR:'                                         
                          sh('fuser -k 9000/tcp || true')                       
                          sh('JENKINS_NODE_COOKIE=dontKillMe nohup java -jar target/demo-0.0.1-SNAPSHOT.jar &')

                          }
                        }
                        stage("aftermath"){
                          steps{
                            discordSend description: '', footer: '', image: '', link: '', result: currentBuild.currentResult, scmWebUrl: '', thumbnail: '', title: 'The Vault', webhookURL: 'https://discord.com/api/webhooks/941121485878210621/R_v7RaKOL2UhsNCj8_L5kOsnucxY19BQOjj63yXBuueDp6C2BGHhXSoCOkDAWKbHZ3s8'
                          }
                        }
      }

  }




