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

![posCompilacao](https://user-images.githubusercontent.com/33008128/189494632-9f39aa38-7fa4-4fa2-9a11-d933b5d8e37c.png)


Após a compilação você precisa precisa inserir o arquivo CSV na pasta que você compilou o projeto:

![importArquivo](https://user-images.githubusercontent.com/33008128/189493543-146e8cd3-866d-4dd7-826b-01429764a95d.png)

Feito isso, basta executar o comando necessário para executar o projeto, conforme exemplo abaixo (OS Windows - CMD):

* ` C:\Users\exemplo\Documents\dbc\src\main\java>java SincronizacaoReceita <sicred.csv `

Ao fim da execução, um novo arquivo chamado **saida-processamento** estará disponível na pasta. Nesse arquivo, estarão disponíveis todas as colunas e dados importados, mais uma nova coluna (**ret-rf**) com o resultado do processamento.

Exemplo da pasta após o processamento:

![image](https://user-images.githubusercontent.com/33008128/189494357-acc4a59c-f1ab-42b7-ae1e-fa57527c4a6a.png)

Exemplo do CSV gerado após processamento - [saida-processamento.csv](https://github.com/NapoleaoHerculano/dbc-desafio/files/9541017/saida-processamento.csv)

## :mailbox: Ficou com alguma dúvida?

* Entre em contato comigo pelo e-mail -> [![Gmail Badge](https://img.shields.io/badge/-francivaldo.napoleao@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:francivaldo.napoleao@gmail.com)](francivaldo.napoleao@gmail.com) 


