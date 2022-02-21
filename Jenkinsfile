pipeline{
agent any
    stages{
    stage("build"){
      steps {
            echo 'building the application with Maven...'
            withMaven(globalMavenSettingsConfig: 'null', jdk: 'null', maven: 'Maven', mavenSettingsConfig: 'null') {
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



