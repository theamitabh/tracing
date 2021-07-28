# tracing
Tracing with Jaeger and spans

# Steps
	mvn package
	docker build -t tracingdemo:5 .
	docker run -d -p 8080:8080 tracingdemo:5
	docker run -d --name jaeger -p 14268:14268 -p 16686:16686  jaegertracing/all-in-one:1.24

	#trigger the checkout and other functions
	curl http://127.0.0.1:8080/checkout

	# Run Jaeger UI
	


Start eshop container 
Access Jaeger UI at http://localhost:16686/