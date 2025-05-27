kubectl delete --all pods,deployments,services,ingress

kubectl apply -f secret.yml
# kubectl create configmap db-init-script --from-file=init.sql=db-init/init.sql
kubectl apply -f configmap.yml


kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.2/deploy/static/provider/cloud/deploy.yaml
kubectl apply -f ingress.yml

kubectl apply -f mariadb-dep.yml
kubectl apply -f redis-dep.yml
kubectl apply -f backend-dep.yml
kubectl apply -f frontend-dep.yml
