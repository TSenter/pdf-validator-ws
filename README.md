# Online PDF Validator

## Build Cycle

- `mvn package`
- `docker build -t pdf-validator .`

## Run Dev Server

- `docker run -p 9080:9080 --name online-validator pdf-validator`
