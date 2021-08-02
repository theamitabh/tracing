# Observability using Jaeger, Istio & Kube

## Steps
1. 	Clone the repo
2. 	Maven build
3. 	Dockerise the image
4. 	Run the container
5. 	Run Jaeger UI container
6. 	Access Jaeger UI -  http://localhost:16686/


	mvn package
	docker build -t tracingdemo:6 .
	docker run -d --name trace6 -p 8080:8080 tracingdemo:6
	docker run -d --name jaeger -p 14268:14268 -p 16686:16686  jaegertracing/all-in-one:1.24

	#trigger the checkout and other functions
	curl http://127.0.0.1:8080/checkout

Start eshop container 
Access Jaeger UI at http://localhost:16686/

## Jaeger UI Screen

![TracesView](jaegerui.jpg)
