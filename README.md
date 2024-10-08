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
 
Quando uma imagem é enviada, a URL é armazenada no container da MariaDB. Para verificar a URL, execute:

      docker exec -it containerIDdoContainer mariadb -u quarkus -pquarkus -D quarkus
(Para encontrar o ID do container, execute docker ps.)

dentro do banco as informacoes sao armazenadas na tabela profile_photos
![image](https://github.com/user-attachments/assets/6149b3c9-4873-489a-b199-3b89e7354d4b)

para ver as imagens geradas e so clicar sobre o link armazenado

