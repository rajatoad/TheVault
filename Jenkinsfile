pipeline{
agent any
    stages{
    stage("build"){
      steps {
            echo 'building the application with Maven...'
            withMaven(maven: 'Maven') {
            sh ('mvn clean install')
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
      }

  }



