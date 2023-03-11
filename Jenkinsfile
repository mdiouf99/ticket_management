node {
        // agent { docker { image 'node:14-alpine' } }
        stage('Clone') {
            checkout scm
        }
        stage('Build') {
          sh 'pwd'
          sh 'mvn install'
                
        }
}
