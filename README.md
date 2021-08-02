# Observability using Jaeger, Istio & Kube

## How to
1. 	Clone the repo
2. 	Maven build
3. 	Dockerise the image
4. 	Run the container
5. 	Run Jaeger UI container
6. 	Access Jaeger UI -  http://localhost:16686/

## Run Jaeger UI
	docker run -d --name jaeger    -e COLLECTOR_ZIPKIN_HOST_PORT=:9411    -p 5775:5775/udp   -p 6831:6831/udp    -p 6832:6832/udp    -p 5778:5778    -p 16686:16686    -p 14268:14268    -p 14250:14250    -p 9411:9411    jaegertracing/all-in-one:1.24


