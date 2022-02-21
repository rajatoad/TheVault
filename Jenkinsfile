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
                    echo 'deploying the application'
                          }
                        }
      }

  }



