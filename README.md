# Aplicação de pontos de interesse (POI) #

Aplicativo para cadastro de pontos de interesse (POI). Esta aplicação foi desenvolvida em Java e utiliza MongoDB para armazenamento das informações. 

Está apta a ser executada em qualquer servlet container, porém, apenas o Apache Tomcat 7 foi testado.

Seguem abaixo algumas considerações para execução da aplicação:

### Requerimentos ###

* Java 6 ou 7
* Maven: http://maven.apache.org/download.cgi
* MongoDB 2.6.1: http://www.mongodb.org/downloads
* Apache Tomcat 7: http://tomcat.apache.org/download-70.cgi

## Passos para executar ##

1. Realize a instalação do MongoDB: http://docs.mongodb.org/manual/installation/

2. Realize a instalação do Apache Tomcat 7: http://tomcat.apache.org/tomcat-7.0-doc/setup.html

3. Realize a instalação do Maven: http://maven.apache.org/download.cgi

4. Faça o download dos fontes desta aplicação

5. Para configurar a conexão entre a aplicação e o MongoDB basta editar o arquivo em: src/main/resources/application.properties e alterar os valores (abaixo estão os valores default):

    ```
    mongo.hostname=localhost
    mongo.port=27017
    ```

6. Dentro da pasta do projeto, execute:

    ```
    mvn clean install
    ```
    
7. Realize a cópia do arquivo xy-inc.war que está contido dentro da pasta target para dentro da pasta "webapps" do Apache Tomcat

8. Após o deploy, a aplicação estará apta a responder requisições, porém, não existirão informações cadastradas. Caso queira, realize a carga de informações acessando: http://localhost:8080/xy-inc/rest/pois/initialize

9. URLs para teste:

  * Para criação de POI:                
    
    ```
    REST: http://localhost:8080/xy-inc/rest/pois/create
    Página: http://localhost:8080/xy-inc
    ```

  * Para consultar todos os POIs:
    
    ```
    http://localhost:8080/xy-inc/rest/pois/list
    ```    

  * Para consultar os POIs, mais próximos, basta acessar:
    
    ```
    http://localhost:8080/xy-inc/rest/pois/nearest/{X}/{Y}/{maxDistance} onde:

    X             = Coordenada X
    Y             = Coordenada Y
    maxDistance   = Distância máxima entre as coordenadas informadas e os pontos de interesse (POIs)
    ```
    
