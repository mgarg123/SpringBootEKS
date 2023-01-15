 AWS EKS SETUP
 --------------

0. Create VPC, Subnet, Route Table and Internet Gateway

1. Push Image to ECR
   -> Create ECR Repo from AWS console: Name the repository as your application name. eg: first-app
   -> Login to AWS CLI using "aws configure"
   -> ECR Repo URL: 823767643261.dkr.ecr.ap-south-1.amazonaws.com/casestudy-1

Commands:
1. Tage Image: docker tag first-app 823767643261.dkr.ecr.ap-south-1.amazonaws.com/first-app:latest
2. Temp Login to ECR: aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 823767643261.dkr.ecr.ap-south-1.amazonaws.com/first-app:latest
3. Push Image to ECR Repo: docker push 823767643261.dkr.ecr.ap-south-1.amazonaws.com/first-app:latest

2. Create EKS Cluster
   -> Create cluster configuration yaml file with all cluster and nodes configuration.
   Command:
    1. eksctl create cluster -f cluster-config.yaml

3. Configure Kubectl
   Commands:
    1. Update Kube config: aws eks update-kubeconfig --region <region_code> --name <cluster_name>  -- ex: aws eks update-kubeconfig --region ap-south-1 --name mycluster
        2. kubectl get svc
        3. kubeclt get nodes

4. Create the deployment config and Service objects
   -> Create the deployment config and service object yaml file according to Kubernetes
   Commands:
    1. Create Deployment Config Object: kubectl apply -f deploy-config.yaml
    2. Create Service Object (to expose the app) : kubectl apply -f kube-service-config.yaml



EKS Addons:
-------------
1. Kube-Proxy: kube-proxy is a network proxy that runs on each node in your cluster, implementing part of the Kubernetes Service concept.
   kube-proxy maintains network rules on nodes. These network rules allow network communication to your Pods from network sessions
   inside or outside of your cluster.

2. CoreDNS: DNS server stores record in its database and answers domain name query using the database.
   If the DNS server doesn't have this data, it tries to find for a solution from other DNS servers.

3. VPC CNI: The Amazon VPC CNI plugin for Kubernetes is the networking plugin for pod networking in Amazon EKS clusters.
   The plugin is responsible for allocating VPC IP addresses to Kubernetes nodes and configuring the necessary networking for pods on each node.
