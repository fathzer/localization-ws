# Translation bundle web service 

# TODO
* Write something in the log if bundle location is wrong
* Explain what it does ... and why in a decent Swagger documentation

# How to run it
To run the example, cd to the directory that contains this file then type the following in the command line:

```bash
mvn spring-boot:run
```
# How to build the Docker image
```bash
mvn package dockerfile:build
```

In your preferred browser enter the url [http://127.0.0.1:8080/swagger-ui.html#/](http://127.0.0.1:8080/swagger-ui.html#/) to display the services documentation

# Setting package bundles location
Bundles are retrieved using their url. You can set the **BUNDLE_ROOT_URL** environment variable to specify the root url of your bundles.

Suppose you want to expose two bundles (bundle1 and bundle2) accessible at the URL https://bundles.example.com/.

If you set BUNDLE\_ROOT_URL to *https://bundles.example.com/*, your bundles can be identified in the call to the web service *messages* by *?bundle=bundle1* or *?bundle=bundle2*. If you let BUNDLE\_ROOT_URL undefined, bundles must be identified by their full URLs (example: *?bundle=https://bundles.example.com/bundle1*).

Please note that setting BUNDLE\_ROOT_URL environment variable prevents the web service users to access to bundles outside of the specified location.    

# Setting CORS
By default, CORS is activated for all origins, you can use the **CORS_ORIGINS** environment variable to change this behavior.
1. Set it to an empty string to disable CORS
2. Set it to a comma separated list of domains to restrict access to these origins (example:"http://domain1.com,http://domain2.com") 

# Dealing with proxies
If this application is running behind a proxy, you can use the following environment variables to set it:
1. **PROXY_HOST** (example: myproxy.mycompany.com)
2. **PROXY_PORT** (example: 3128)

If that proxy requires authentication, you'll have to add these environment variables:
1. **PROXY_USER**
2. **PROXY_PWD**