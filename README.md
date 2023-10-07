# LSService-Java

The LSService-Java uses Spring boot and Spring web framework. It also uses lombok framework to reduce the boiler plate code such as getters and setters, constructors etc. For the tests mockito is used to provide mocks for the service. 
If you do not have maven installed, you can download the jar file directly and run with a jvm.

To run the server use command:
```
java -jar Kambi-0.0.1.jar
```

To send request use command:
```
curl -X POST -H "Content-Type: application/json" -d '{"path": "/Users/admin/", "delay": 5}' http://127.0.0.1:8080/api/v1/dir/list
```

I get response like:
```
["Applications","Desktop","Documents","Downloads","IdeaProjects","JDev","Library","Movies","Music","Pictures","Postman","Postman Agent","Public"]
```


Request body:
Request body has two parameters, path: It is required for the service to work. delay: Number of seconds before
the service should provide response. It is optional and if left out behaves as if response_delay is 0 seconds.
```json
{
    "path": "/Users/admin/",
    "delay": 5
}
```
Finally to stop the serer:
Press CTRL+C to send a stop signal. The server will close all worker threads except for the ones which are currently
serving the clients. These will eventually close once they are no longer busy.

