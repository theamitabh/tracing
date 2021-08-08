# Observability across microservices using Jaeger & Kube 

## Steps
### Package and Push to Container registry
```
git clone https://github.com/theamitabh/tracing.git

mvn package
docker build -t tracingdemo:9 .
docker login
docker tag tracingdemo:9 amitprem/tracingdemo:9
docker push amitprem/tracingdemo:9
```

### Get azure Kube cluster creds , kube nodes & deploy
```
az aks get-credentials --resource-group rgkube --name amitcluster
kubectl get nodes
kubectl apply -f k8s/deploy.yaml
```
### Check pod & deployment status
```
kubectl get pods

kubectl get svc

```

### Port forward API & JAEGERUI ports to localhost
```
kubectl port-forward service/eshop 8080:8080
Forwarding from 127.0.0.1:8080 -> 8080
Forwarding from [::1]:8080 -> 8080

kubectl port-forward service/jaeger 16686:16686
Forwarding from 127.0.0.1:16686 -> 16686
Forwarding from [::1]:16686 -> 16686

```

### Test the checkout with user baggage parameter
```	**curl 127.0.0.1:8080/checkout --header "user: amitabh"
amitabh's order has been created!<BR>amitabh's order has been paid!<BR>Your order is on the way!<BR>amitabh's order is delivered! 
```

### View Jaeger UI
cess Jaeger UI at http://localhost:16686/

## Jaeger UI Screen
![TracesView](jaegerui.jpg)
