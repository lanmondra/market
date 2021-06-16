pipeline {
  agent {
    docker {
      args '-v /home/optimalway/.m2:/root/.m2 -v /home/optimalway/.ssh:/root/.ssh'
      image 'maven:3.5.2'
    }
  }
  
  stages {
    stage('Build') {
      steps {
        echo "Executant build numero ${env.BUILD_ID} per ${env.JOB_NAME}"

        dir(path: 'fcbq-common') {
          git(url: 'git@bitbucket.org:owwebsgcjava/fcbq-common.git', credentialsId: 'bitbucket-ssh', branch: '${BRANCH_NAME}')
          sh 'cd ./fcbq-common && mvn -B -DskipTests clean install && cd ..'
        }

        dir(path: 'owc-spring-common') {
          git(url: 'git@bitbucket.org:owwebsgcjava/owc-spring-common.git', credentialsId: 'bitbucket-ssh', branch: '${BRANCH_NAME}')
          sh 'cd ./owc-spring-common && mvn -B -DskipTests clean install && cd ..'
        }

        dir(path: 'owc-authentication-utils') {
          git(url: 'git@bitbucket.org:owwebsgcjava/owc-authentication-utils.git', credentialsId: 'bitbucket-ssh', branch: '${BRANCH_NAME}')
          sh 'cd ./owc-authentication-utils && mvn -B -DskipTests clean install && cd ..'
        }

        sh 'mvn -B -DskipTests clean install'
  	  }
    }
    
    stage('S3Upload per develop') {
      when {
        branch 'develop'
      }
      
      steps {
        withAWS(credentials: 'awsS3Access', region: 'eu-west-1') {
          s3Upload(bucket: 'ow-deploy-repository', path: 'nextRelease/develop/owMS/', includePathPattern: 'target/*.jar')
        }
      }
    }
    
    stage('S3Upload per master') {
      when {
        branch 'master'
      }
      
      steps {
        withAWS(credentials: 'awsS3Access', region: 'eu-west-1') {
          s3Upload(bucket: 'ow-deploy-repository', path: 'nextRelease/master/owMS/', includePathPattern: 'target/*.jar')
        }
      }
    }
  }
}