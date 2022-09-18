# Desafio DBC - Importador de Dados Bancários Sicredi
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge)

Uma aplicação java standalone que realiza a leitura de um arquivo CSV com informações bancárias, realiza o processamento das mesmas e, ao final, exporta um novo arquivo CSV com os dados do arquivo lido e o resultado do processamento.

## 🛠️ O que é necessário para rodar o projeto?

Para conseguir utilizar essa aplicação, você necessita dos seguintes itens:

* Java 8
* Arquivo .CSV seguindo o padrão definido pela Sicred. (Exemplo: [sicred.csv](https://github.com/NapoleaoHerculano/dbc-desafio/files/9540450/sicred.csv))

## :rocket: Como rodar o projeto?

Após baixar o projeto, você precisa fazer a compilação do arquivo **SincronizacaoReceita**. Para isso, você deve abrir o terminal na pasta em que você salvou o seu projeto e executar o comando de compilação, conforme exemplo abaixo (OS Windows - CMD):

* ` C:\Users\exemplo\Documents\dbc\src\main\java>javac SincronizacaoReceita.java `

Após a compilação, a pasta do projeto deve estar semelhante a imagem seguinte:

![poscompilacao](https://user-images.githubusercontent.com/33008128/190902405-c2e3fddb-7aa0-49ad-a1ee-6bb8e16ce91f.png)



Após a compilação você precisa precisa inserir o arquivo CSV na pasta que você compilou o projeto:

![importarquivo](https://user-images.githubusercontent.com/33008128/190902557-7b7797b6-ca78-4d4e-87ed-343179b267f6.png)


Feito isso, basta executar o comando necessário para executar o projeto, conforme exemplo abaixo (OS Windows - CMD):

* ` C:\Users\exemplo\Documents\dbc\src\main\java>java SincronizacaoReceita <sicred.csv `

Ao fim da execução, um novo arquivo chamado **saida-processamento** estará disponível na pasta. Nesse arquivo, estarão disponíveis todas as colunas e dados importados, mais uma nova coluna (**ret-rf**) com o resultado do processamento.

Exemplo da pasta após o processamento:

![posprocessamento](https://user-images.githubusercontent.com/33008128/190902823-db593a28-6ec0-473c-915e-9069ce61c368.png)


Exemplo do CSV gerado após processamento - [saida-processamento.csv](https://github.com/NapoleaoHerculano/dbc-desafio/files/9541017/saida-processamento.csv)

## :mailbox: Ficou com alguma dúvida?

* Entre em contato comigo pelo e-mail -> [![Gmail Badge](https://img.shields.io/badge/-francivaldo.napoleao@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:francivaldo.napoleao@gmail.com)](francivaldo.napoleao@gmail.com) 


