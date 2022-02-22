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
                          sh('export BUILD_ID=dontKillMe')        
                          sh('fuser -k 9000/tcp || true')
                          sh('nohup java -jar target/demo-0.0.1-SNAPSHOT.jar &')

                          }
                        }
      }

  }



