# Observability across microservices using Jaeger & Kube 

## Steps
### Package and Push to Container registry
```
git clone https://github.com/theamitabh/tracing.git

mvn package
docker build -t tracingdemo:8 .
docker login
docker tag tracingdemo:8 amitprem/tracingdemo:8
docker push amitprem/tracingdemo:8
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
NAME                         READY   STATUS    RESTARTS   AGE
billing-7f8b7fdf7-blcjm      1/1     Running   0          22s
delivery-55d446d6b7-lvg9c    1/1     Running   0          22s
eshop-7b7ccb6d6f-5grmx       1/1     Running   0          23s
inventory-6f67bcc995-9lm9t   1/1     Running   0          22s
jaeger-787766c5b9-9n7pj      1/1     Running   0          21s
logistics-5b9bd77645-dwt6g   1/1     Running   0          21s

λ kubectl get svc
NAME         TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)               AGE
billing      ClusterIP   10.0.69.220    <none>        8080/TCP              52s
delivery     ClusterIP   10.0.144.17    <none>        8080/TCP              51s
eshop        ClusterIP   10.0.101.136   <none>        8080/TCP              52s
inventory    ClusterIP   10.0.37.204    <none>        8080/TCP              52s
jaeger       ClusterIP   10.0.223.188   <none>        14268/TCP,16686/TCP   50s
kubernetes   ClusterIP   10.0.0.1       <none>        443/TCP               4d23h
logistics    ClusterIP   10.0.84.100    <none>        8080/TCP              51s
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

### Test the checkout 
```	curl http://127.0.0.1:8080/checkout ```

### View Jaeger UI
cess Jaeger UI at http://localhost:16686/

## Jaeger UI Screen
![TracesView](jaegerui.jpg)