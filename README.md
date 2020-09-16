# marvel-api-dextra
It's an API project to consume Marvel's API.

### Orientações de instalação

### Maven
`mvn install -Dmaven.test.skip=true`
### Docker:

#### Criar imagem couchbase

Execute o seguinte comando ao acessar o diretório do projeto: 
`docker build -t dextra-api .`
`cd src/main/resources/config/couchbase/`
`docker build -t couchbase-custom .`

`docker-compose run -d --service-ports --name couchbase couchbase`
`docker-compose run -d --service-ports --name spring-boot spring-boot`

