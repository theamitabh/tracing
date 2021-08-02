# tracing
Tracing with Jaeger and spans

## Steps
	mvn package
	docker build -t tracingdemo:6 .
	docker run -d --name trace6 -p 8080:8080 tracingdemo:6
	docker run -d --name jaeger -p 14268:14268 -p 16686:16686  jaegertracing/all-in-one:1.24

	#trigger the checkout and other functions
	curl http://127.0.0.1:8080/checkout

	# Run Jaeger UI
	


Start eshop container 
Access Jaeger UI at http://localhost:16686/

## Jaeger UI Screen

![TracesView](jaegerui.jpg)