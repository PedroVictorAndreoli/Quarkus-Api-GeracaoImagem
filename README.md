# Api para geracao de imagem utilizando Quarkus

Antes de iniciar e necessario baixar o docker e a GraalVM JDk, alem de ter instalado a stable diffusion pelo tutorial https://www.dio.me/articles/inteligencia-artificial-generativa-com-modelos-de-difusao-stable-diffusion


Assim que baixado o necessario, e so deixar o docker rodando e executar a aplicacao na IDE de sua preferencia,


o servico subira na porta padrao 8080 para enviar uma imagem da api siga a requisicao curl -X POST http://localhost:8080/customers/1234 -F "photo=@caminho-da-imagem"

 por padrao, existe uma descricao que sera enviada para a api do stable difusion, caso queira trocar e so ir na classe StableDiffusion
![image](https://github.com/user-attachments/assets/198d76ee-2802-4d77-9472-fcdc4eb375a3)

tambem dentro da classe application.properties, o parametro quarkus.rest-client.stable-diffusion-api.url deve apontar para a url onde o StableDifusion esta upado


Quando uma imagem e enviada sua URL fica armazenado dentro do container da mariaDB

para conferir a url basta executar docker exec -it containerIDdoContainer mariadb -u quarkus -pquarkus -D quarkus
(para conferir o container id e so digitar Docker ps)


dentro do banco as informacoes sao armazenadas na tabela profile_photos
![image](https://github.com/user-attachments/assets/6149b3c9-4873-489a-b199-3b89e7354d4b)

para ver as imagens geradas e so clicar sobre o link armazenado

# API para Geração de Imagens com Quarkus

## Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos instalados:

1. **Docker**: Para executar o banco de dados e a aplicação.
2. **GraalVM JDK**: Necessário para a execução do Quarkus.
3. **Stable Diffusion**: Siga o tutorial disponível em [DIO](https://www.dio.me/articles/inteligencia-artificial-generativa-com-modelos-de-difusao-stable-diffusion) para instalar o Stable Diffusion.

## Configuração

1. **Inicie o Docker**: Certifique-se de que o Docker está em execução.

2. **Configure a aplicação**:
   - Abra a aplicação na sua IDE de preferência.
   - Verifique o arquivo `application.properties` e ajuste o parâmetro `quarkus.rest-client.stable-diffusion-api.url` para apontar para a URL onde o Stable Diffusion está hospedado.

3. **Descrição da Imagem**:
   - A descrição padrão enviada para a API do Stable Diffusion pode ser modificada na classe `StableDiffusion`.
     ![image](https://github.com/user-attachments/assets/198d76ee-2802-4d77-9472-fcdc4eb375a3)

## Executando a Aplicação

1. **Suba a aplicação**: A aplicação será executada na porta padrão `8080`.

2. **Enviar uma Imagem**:
   Use a seguinte requisição `curl` para enviar uma imagem para a API:
   ```bash
   curl -X POST http://localhost:8080/customers/1234 -F "photo=@caminho-da-imagem"

## Observações
 
dentro do banco as informacoes sao armazenadas na tabela profile_photos
![image](https://github.com/user-attachments/assets/6149b3c9-4873-489a-b199-3b89e7354d4b)

para ver as imagens geradas e so clicar sobre o link armazenado
