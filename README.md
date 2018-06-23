# Translation bundle web service 

# TODO
Explain what it does ... and why in a decent Swagger documentation

Fix cors headers

# How to run it
To run the example, cd to the directory that contains this file then type the following in the command line:

```bash
mvn spring-boot:run
```

In your preferred browser enter the url [http://127.0.0.1:8080/swagger-ui.html#/](http://127.0.0.1:8080/swagger-ui.html#/) to display the services documentation

# Dealing with proxies
If this application is running behind a proxy, you can use the following environment variables to set it:
1. PROXY_HOST (example: myproxy.mycompany.com)
2. PROXY_PORT (example: 3128)
If that proxy requires authentication, you'll have to add these environment variables:
1. PROXY_USER
2. PROXY_PWD