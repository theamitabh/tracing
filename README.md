# Observability across microservices using Jaeger & Kube 

## Steps
### Install istio

```
# install istio ctl on local machine
choco install istioctl

#install istion using config file in the Kube cluster
istioctl install -f installistio.yaml
This will install the Istio 1.10.2 demo profile with ["Istio core" "Istiod" "Ingress gateways" "Egress gateways"] components into the cluster. Proceed? (y/N) y
✔ Istio core installed
✔ Istiod installed
✔ Egress gateways installed
✔ Ingress gateways installed
✔ Installation complete                                                                                                                Thank you for installing Istio 1.10. 
```
### Verify Istio Install

```
kubectl get svc -A
NAMESPACE      NAME                   TYPE           CLUSTER-IP     EXTERNAL-IP     PORT(S)                                                                      AGE
default        kubernetes             ClusterIP      10.0.0.1       <none>          443/TCP                                                                      5d1h
istio-system   istio-egressgateway    ClusterIP      10.0.188.62    <none>          80/TCP,443/TCP                                                               3m39s
istio-system   istio-ingressgateway   LoadBalancer   10.0.226.10    20.xx.xx.xxx   15021:30766/TCP,80:31827/TCP,443:30341/TCP,31400:31896/TCP,15443:32552/TCP   3m39s
istio-system   istiod                 ClusterIP      10.0.210.193   <none>          15010/TCP,15012/TCP,443/TCP,15014/TCP                                        3m51s
kube-system    kube-dns               ClusterIP      10.0.0.10      <none>          53/UDP,53/TCP                                                                5d1h
kube-system    metrics-server         ClusterIP      10.0.182.5     <none>          443/TCP                                                                      5d1h



C:\work\tracing (prj4.1)
λ kubectl get po -A
NAMESPACE      NAME                                    READY   STATUS    RESTARTS   AGE
istio-system   istio-egressgateway-7d4f75956-jhcq8     1/1     Running   0          2m37s
istio-system   istio-ingressgateway-5d57955454-rmbsv   1/1     Running   0          2m37s
istio-system   istiod-6f6c6bbbbd-96htm                 1/1     Running   0          2m49s
kube-system    azure-ip-masq-agent-p46qv               1/1     Running   0          5d1h
kube-system    coredns-8869cbf9b-kkm8j                 1/1     Running   0          5d1h
kube-system    coredns-8869cbf9b-w2wp5                 1/1     Running   0          5d1h
kube-system    coredns-autoscaler-549956c48c-tgk65     1/1     Running   0          5d1h
kube-system    csi-azuredisk-node-tdhhv                3/3     Running   0          5d1h
kube-system    csi-azurefile-node-2nb2d                3/3     Running   0          5d1h
kube-system    kube-proxy-nvbc7                        1/1     Running   0          5d1h
kube-system    metrics-server-7f469c787-9mtt4          1/1     Running   1          5d1h
kube-system    tunnelfront-75f8c5fdd9-m5fhm            1/1     Running   0          5d1h
```

### Install Jaeger on Cluster
```
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.10/samples/addons/jaeger.yaml
deployment.apps/jaeger created
service/tracing created
service/zipkin created
service/jaeger-collector created


kubectl label namespace default istio-injection=enabled --overwrite=true

kubectl get namespaces --show-labels
NAME              STATUS   AGE    LABELS
default           Active   5d1h   istio-injection=enabled,kubernetes.io/metadata.name=default
istio-system      Active   23m    kubernetes.io/metadata.name=istio-system
kube-node-lease   Active   5d1h   kubernetes.io/metadata.name=kube-node-lease
kube-public       Active   5d1h   kubernetes.io/metadata.name=kube-public
```

### Package and Push to Container registry

```
git clone https://github.com/theamitabh/tracing.git

mvn package
docker build -t tracingdemo:10 .
docker login
docker tag tracingdemo:10 amitprem/tracingdemo:10
docker push amitprem/tracingdemo:10
```

###  Deploy eShop application microservices
```
kubectl apply -f k8s/
```
### Check pod & deployment status
```
kubectl get pods
kubectl get svc
```

### Create an Istio Gateway as the ingress for the eShop application
```
_kubectl apply -f istio/_
```

### Port forward API & JAEGERUI ports to localhost
```
kubectl port-forward service/eshop 8080:8080
Forwarding from 127.0.0.1:8080 -> 8080
Forwarding from [::1]:8080 -> 8080

#optional - Use the public IP of the service/istio-ingressgateway instead
kubectl port-forward service/istio-ingressgateway 8080:80 -n istio-system
```

### Test the checkout with user baggage parameter
```	**curl 127.0.0.1:8080/checkout 

### View Jaeger UI
cess Jaeger UI at http://public-ip-of-ingress-gateway

## Jaeger UI Screen
![TracesView](jaegerui.jpg)