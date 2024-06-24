pipeline {
    agent any

    stages {
        stage('Run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        stage('Install postgres') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l gcloud-db-vm  ~/workspace/ansible/playbooks/postgres.yaml --vault-password-file ~/workspace/.vaultpass
                '''
            }
        }

        stage('Deploy spring boot app') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l gcloud-backend-server ~/workspace/ansible/playbooks/spring.yaml --vault-password-file ~/workspace/.vaultpass
                '''
            }
        }
    }
}