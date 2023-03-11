node {
        // agent { docker { image 'node:14-alpine' } }
        stage('Clone') {
            checkout scm
        }
        stage('Build') {
          bat 'dir'
          bat 'mvn install'
                
        }
}
