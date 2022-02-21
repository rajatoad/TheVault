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
                          sh('cd /var/jenkins_home/workspace/P3-TheVault/target/')
                          sh('export BUILD_ID=dontKillMe')        
                      
                          sh('nohup java -jar target/demo-0.0.1-SNAPSHOT.jar')

                          }
                        }
      }

  }



