# Web Service without Web Project
(well, almost)

Here we have sample RestEasy-based web service project without any "web.xml" and/or "webapp" directory.

It builds as WAR package anyway, but still usable as regular java app module.

The project is packed as EAR, exposing some Web Service endpoints.


Build project: `mvn clean package`

then, deploy to **WildFly 9+** your resulting: `exposed-service-bundle\target\exposed-service-bundle.ear`

Deployed application (sample URL) should be available at: `http://localhost:8080/exposed-service/get`
