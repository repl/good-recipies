## Build 
gradlew build
## Start App Service
gradlew java:web-service:bootRun
## Start Backend Service
gradlew java:backend-service:bootRun
## Test web service APIs
curl -X GET 'http://localhost:8080/api/v1/recipes' -H 'Content-Type: application/json'
## Start UI app
cd ui;  serve -s build
## Test the app
Open http://localhost:5000



