# marvel-api-dextra
Descrição do projeto

### Docker
`docker run -d --name couchbase -p 8091-8094:8091-8094 -p 11210:11210 couchbase`

Após executar o comando, será preciso configurar a instância local do DB. Acessar a url `http://localhost:8091` .
Selecione a opção "Setup New Cluster" e defina os seguintes valores :
Cluster name = marvel
Username: Administrator
Senha: 123456

Após informar os valores acima, prosseguir com a configuração deixando todas as opções padrão.

Execute o comando no terminal `curl -X POST -u Administrator:123456 \
            http://localhost:8091/pools/default/buckets/ \
            -d name=marvel \
            -d bucketType=couchbase \
            -d ramQuotaMB=512`

Acessar `http://localhost:8091/ui/index.html#!/query/workbench?scenarioZoom=minute` 
e executar a seguinte query: 

CREATE PRIMARY INDEX \`marvel\`  ON \`marvel\` USING GSI;

### API
Após Realizar a configuração manual do Couchbase, acesse o diretório da aplicação, por exemplo : `cd meu-diretorio/marvel-api-dextra`

Nesta etapa será preciso instalar a dependência couchbase_repository que se en contra em meu-diretorio/marvel-api-dextra/couchbase_repository.
execute o comando `mvn install -Dmaven.test.skip=true` neste diretório. Em seguida, `cd ..` e novamente `mvn install -Dmaven.test.skip=true`

Para finalizar, `mvn clean spring-boot:run`



