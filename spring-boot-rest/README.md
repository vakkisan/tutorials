## Responsibilities

## Dependencies

### Resource dependencies
- Postgres
- Kafka (and Zookeeper)


## How To

#### Profiles
We declare several convenient profiles for everyday tasks:
* `deploy` - deploys api + client modules to nexus
* `docker` - builds docker image using jib maven plugin

### Build and run only UTs
Compiles, runs unit tests and generates Jacoco report
```bash
./mvnw -pl api,service clean verify
```

### Build and run
Local docker image should be build to pass integration tests
```bash
./mvnw -P test
```

### Build local Docker image
```bash
./mvnw -P docker
```

### Deploy Docker image to remote
```bash

```
```
### Use Docker composer files
Check `./docker-compose` folder:
- docker-compose.yml (all services and their dependencies)
 

### Start account service, kafka and consul in docker-compose
- go to `./docker-compose`
- run `docker-compose up`

### Manual testing with postman
- import `./postman/*.json` into postman
- try out list of requests
- hostname is `localhost:`

### Run locally
Use VM options to run locally

```
-Dspring.profiles.active=local
-Dflowable.database-schema-update=true
-Dserver.port=8080

```
```

### Set/Override configuration
| Environment Variable Name | Description | Example/Default |
| --------- | ----------- | --------------- |

