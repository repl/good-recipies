## Create DB and Schema
CREATE DATABASE recipes
Execute DML scripts in resources/db.migration 

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

##Signup users
curl -X POST \
  http://localhost:8080/api/v1/auth/signup \
  -H 'Content-Type: application/json' \
  -d '{"name":"John Doe","email":"john.doe@mail.com","username":"johndoe","password":"Qwerty123"}'
Response:  
{"data":"User registered successfully","success":true,"messages":[],"errors":[]}

## Authenticate user
curl -X POST \
  http://localhost:8080/api/v1/auth/signin \
  -H 'Content-Type: application/json' \
  -d '{"usernameOrEmail":"johndoe","password":"Qwerty123"}'
Response:
{"accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc4MzUwNjg4LCJleHAiOjE1Nzg5NTU0ODh9.ctC_wa95fGJRlWKmrmVALB34L1VNKpGQw4hsp5gxmafPyPDodUY8o8msl0pv130ZZViaoMjm5leP3KbyIrd3CQ","tokenType":"Bearer"}

## Access protected endpoints
curl -X GET \
  http://localhost:8080/api/v1/user/me \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc4MzUwNjg4LCJleHAiOjE1Nzg5NTU0ODh9.ctC_wa95fGJRlWKmrmVALB34L1VNKpGQw4hsp5gxmafPyPDodUY8o8msl0pv130ZZViaoMjm5leP3KbyIrd3CQ' \
  -H 'Content-Type: application/json'

curl -X GET \
  http://localhost:8080/api/v1/recipes \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc4MzUwNjg4LCJleHAiOjE1Nzg5NTU0ODh9.ctC_wa95fGJRlWKmrmVALB34L1VNKpGQw4hsp5gxmafPyPDodUY8o8msl0pv130ZZViaoMjm5leP3KbyIrd3CQ' \
  -H 'Content-Type: application/json'
  
curl -X POST \
  http://localhost:8080/api/v1/recipes \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc4MzUwNjg4LCJleHAiOjE1Nzg5NTU0ODh9.ctC_wa95fGJRlWKmrmVALB34L1VNKpGQw4hsp5gxmafPyPDodUY8o8msl0pv130ZZViaoMjm5leP3KbyIrd3CQ' \
  -H 'Content-Type: application/json' \
  -d '{"name":"Chicken Salad"}'



