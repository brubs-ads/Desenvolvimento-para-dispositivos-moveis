 # Confinance

###  Sobre Nós: 💼
O Grupo five Tech composto por:
 Bruna Camilly, João Victtor Silva, Lucas Fernando, Selton Alves e Thamyres Cordeiro.
 Teve a iniciativa de fazer uma aplicação com a principal ideia de ajudar o usuário em suas questões financeiras, onde o usuário terá em sua visiblidade suas dispensas e receitas do mês em questão.

### Conceito Da Aplicação: 📃
    
O aplicativo tem como objetivo ajudar o usuário a gerenciar suas finanças pessoais de maneira eficiente. Com o uso do app, o usuário pode inserir informações sobre seus gastos mensais, como alimentação, educação e outras categorias, além de registrar suas receitas, seja por meio de investimentos ou salário. O layout do aplicativo é simples, mas foi projetado com foco na experiência do usuário e na facilidade de uso no dia a dia. 

Além de permitir que o usuário insira informações sobre seus gastos e receitas, o aplicativo também realiza cálculos matemáticos para fornecer ao usuário uma visão clara de sua situação financeira no final do mês. Com base nos dados inseridos, o aplicativo realiza operações matemáticas para calcular o total de gastos do usuário em diferentes categorias, como alimentação, educação, transporte, entre outras. Além disso, o aplicativo também realiza cálculos para determinar o total de receitas do usuário em um determinado período.

Ao combinar essas informações, o aplicativo é capaz de fornecer uma visão completa de quanto o usuário ganhou e gastou durante o mês, bem como seu saldo final. Isso permite que o usuário veja claramente onde seu dinheiro está sendo gasto e faça ajustes em seus hábitos de consumo, se necessário, para atingir seus objetivos financeiros. Com esses recursos, o aplicativo ajuda o usuário a ter mais controle sobre suas finanças e tomar decisões mais informadas sobre como gerenciar seu dinheiro de forma mais eficiente.

### Tecnologias Utilizadas: 🛠
   - MySQL 
   - Spring Boot
   - Kotlin

### Formato de Desenvolvimento: 🧑‍💻
A aplicação utilizará o MySQL como SGBD para armazenarinformações do usuário, como nome, e-mail, senha, entre outras.O Aplicativo será desenvolvido com Kotlin para Android, sendo responsável pela criação dos layouts e pela lógica de negócios usando a arquitetura MVVM.

Para a comunicação com o banco de dados, a aplicação utilizará o Spring Boot, que permitirá a criação de uma API RESTful. Serão utilizadas as dependências Spring Web, Spring Data JPA, MySQL Driver e Validation. O Spring Boot seguirá a arquitetura MVC.

Este formato de desenvolvimento permitirá uma estrutura modular da aplicação, possibilitando a fácil manutenção do código e a escalabilidade da aplicação no futuro. O uso do Spring Boot como framework de desenvolvimento de API RESTful permitirá uma comunicação eficiente e segura com o banco de dados MySQL, garantindo a segurança dos dados do usuário. A utilização do Kotlin como linguagem de programação permitirá um desenvolvimento mais ágil e eficiente da aplicação para a plataforma Android. A arquitetura MVVM permitirá uma separação clara entre a camada de apresentação e a lógica de negócios, facilitando a manutenção e o teste da aplicação.

MVVM (Model-View-ViewModel) e MVC (Model-View-Controller) são duas arquiteturas de software usadas para separar a lógica de apresentação da lógica de negócios em uma aplicação.

A arquitetura MVVM é composta por três camadas: a camada do modelo, a camada da visão e a camada do ViewModel. A camada do modelo representa os dados e a lógica de negócios da aplicação. A camada da visão é responsável pela interface do usuário e pela apresentação dos dados. A camada do ViewModel atua como um intermediário entre a camada da visão e a camada do modelo, permitindo que a visão seja atualizada automaticamente quando os dados do modelo são alterados.

Já a arquitetura MVC é composta pelas camadas do modelo, da visão e do controlador. A camada do modelo representa os dados e a lógica de negócios da aplicação. A camada da visão é responsável pela interface do usuário e pela apresentação dos dados. A camada do controlador atua como um intermediário entre a camada da visão e a camada do modelo, respondendo às ações do usuário na visão e atualizando o modelo de acordo.

### Diagrama UML: 📊

![UML](https://user-images.githubusercontent.com/101484450/232238897-3bd997f2-d8de-4922-aafe-5cf4a718df17.png)

### Protótipos da aplicação 📱

### Cadastro do Usuário:

#### 1.1 abertura / 1.2 criação de conta  
![1 1 Tela de Abertura](https://user-images.githubusercontent.com/101484450/232239045-e0acd88d-1c87-46d7-8096-4b64952dbd8a.jpg)

![1 2 tela criar conta](https://user-images.githubusercontent.com/101484450/232239059-ac698a8e-f062-4ad4-814b-a2af61f9af9c.jpg)

#### 1.1 abertura: 
Ao entrar no aplicativo, é exibida  duas opções: "Criar uma conta" e "Fazer Login". O usuário escolhe uma das opções clicando em um dos botões. Quando o usuário clica no botão "Criar uma Conta". O aplicativo redireciona o usuário para a criação de sua conta. Assim como quando o usuário clica no botão " Fazer login". O aplicativo redireciona o usuário para fazer login. 


#### 1.2 Criação de conta:
 O usuário preenche os campos solicitados, como nome, endereço de e-mail e senha.Se algum dos campos estiver em branco, aparecerá a mensagem "Preencha o campo". O aplicativo verifica se os dados fornecidos são válidos e únicos. Se os dados estiverem corretos, o aplicativo cria uma nova conta para o usuário e redireciona-o para a tela principal do aplicativo. Se os dados estiverem incorretos, ou já estiverem sendo usados por outra conta, o aplicativo notifica o usuário com a mensagem “Já existe conta criada ou dados incorretos". Se o usuário já tiver uma conta, poderá clicar no botão "Já tenho uma conta" e será direcionado para a parte login. Para voltar à parte de abertura, basta clicar na seta localizada na parte superior esquerda.


#### 1.3 Login 
![tela de login](https://user-images.githubusercontent.com/101484450/232239092-faf635df-82d2-485a-9436-68fd7c511113.jpg)

#### 1.3 Login: 
 O usuário insere seu endereço de e-mail e senha. Se algum dos campos estiver em branco, aparecerá a mensagem "Preencha o campo". O aplicativo verifica se as informações de login são válidas e correspondem a uma conta existente. Se as informações de login estiverem corretas, o aplicativo redireciona o usuário para a parte Inicial do aplicativo. Se o usuário digitar um e-mail diferente do que foi registrado, aparecerá a mensagem "E-mail incorreto". Da mesma forma, se digitar uma senha diferente, aparecerá a mensagem "Senha incorreta". Se quiser criar uma nova conta, poderá voltar para a parte  criação de conta clicando no botão "Criar conta". Se o usuário preencher o e-mail e a senha correta, poderá clicar no botão "Entrar", o que levará para a parte inicial. Para voltar à parte de abertura, basta clicar na seta localizada na parte superior esquerda


### Página Inicial

#### 1.1 Inicial / Exemplo

![1 1 tela Inicial](https://user-images.githubusercontent.com/101484450/232239171-d0c72394-3eb7-4a18-b16a-7ad2396f0be8.jpg)

![Exemplo -Tela Inicial](https://user-images.githubusercontent.com/101484450/232239183-eea6a71c-a560-4b0f-8570-7ca98df0cc56.jpg)

#### 1.1 Inicial:
Nesta parte inicial, o usuário poderá visualizar um botão com três traços um abaixo do outro, como no exemplo ao lado “ ≡ “, que representará a parte de menu. Ao clicar, será direcionado para a mesma. Assim como o mês que terá duas setas, uma para a esquerda “←” e a outra para a direita “→”, com o intuito de, quando o usuário clicar na seta esquerda, voltar para o mês anterior e, quando clicar na seta direita, ir para o mês seguinte. Também haverá a possibilidade de visualizar o saldo da conta, o total das receitas e despesas e suas categorias. A parte de categoria de receitas e despesas só terá funcionalidade após adicionar as respectivas receitas e despesas. Se o usuário não criar as categorias, elas ficarão vazias com a mensagem: “Nenhum registro encontrado”. Após adicionadas, aparecerão o ícone, o nome e o valor descrito na parte de receita ou parte de despesa. Ao clicar no ícone da categoria, será direcionado para a respectiva categoria. O usuário não poderá editar o saldo de sua conta, pois ele será atualizado automaticamente ao adicionar receitas e despesas. Ao clicar no nome “receitas”, será direcionado para a parte de receita, assim como ao clicar no nome “despesa”, será direcionado para parte de despesas.

#### 1.2 Menu / 1.3 Usuário
![1 2 tela de menu](https://user-images.githubusercontent.com/101484450/232239251-5af5552a-e0f4-484d-aa4c-2a98c6cfe864.jpg)
![1 3 usuario](https://user-images.githubusercontent.com/101484450/232239262-4a8f9de1-d16c-4fc7-b7c6-a4b1bb809c91.jpg)


####  1.2  Menu: 
  Nesta parte de menu, ao clicar no ícone do perfil redondo na parte superior da tela, o usuário será direcionado para a parte do usuário. Ao clicar em 'Início', será direcionado para a página inicial. Da mesma forma, ao clicar em 'Objetivos', será direcionado para a parte de objetivos, em 'Receitas' para a parte receitas, em 'Despesas' para a parte de despesas, em 'Sobre Nós' para a parte Sobre Nós e em 'Termos de Uso' para a parte de termos de uso.

#### 1.3 Usuário: 
 Nesta parte do usuário, o usuário poderá visualizar seu nome e email cadastrados na parte de criar conta, e também poderá editá-los. Além disso, poderá clicar no botão 'Sair' para ser direcionado à parte de abertura. Para voltar ao menu, basta clicar na seta na parte superior esquerda da tela, e se for editar, basta clicar na caixa do nome ou senha e editá-los, assim que formatado poderá confirmar a alteração clicando no ícone “✔” e sendo direcionado à parte de menu.Se algum dos campos estiver em branco, aparecerá a mensagem "Preencha o campo". 

#### 1.4 Sobre nós / 1.5 Termo de uso
![1 4 Tela Sobre Nós](https://user-images.githubusercontent.com/101484450/232239281-7ef74c77-c019-4215-ac28-ef5c26a8ef43.jpg)
![1 5 Tela Termos de Uso](https://user-images.githubusercontent.com/101484450/232239287-4695b296-63fd-4cca-a215-c60db54ca1a2.jpg)


#### 1.4 Sobre Nós: 
 Nesta parte, apresentaremos um pouco sobre o propósito de nosso aplicativo. Para retornar à parte de menu, basta clicar na seta localizada na parte superior esquerda.

#### 1.5 Termo de Uso:
  Nesta parte, apresentaremos o termo de uso do nosso aplicativo. Para retornar à parte de menu, basta clicar na seta localizada na parte superior esquerda.


### Cadastro do Objetivo:


#### 1.1 Objetivo / Exemplo 
![1 1 Tela do Objetivo](https://user-images.githubusercontent.com/101484450/232239386-c9719a1b-48e9-4027-b900-db7281f6b184.jpg)
![1 2 Exemplo - tela de Objetivo](https://user-images.githubusercontent.com/101484450/232239399-fa539dda-1dab-4e4a-beda-c84f058279e3.jpg)

 
#### 1.1 Objetivo:
Nesta parte, o usuário pode clicar no botão "+" que o direciona para a parte  de criação do objetivo. Assim que o usuário criar seu objetivo, poderá visualizá-lo, podendo também editá-lo clicando no ícone de lápis que ficará ao lado da descrição do objetivo, indo então para a parte editar objetivo. O usuário também poderá clicar no ícone de lixeira que ficará acima do valor do objetivo para excluí-lo da tela. Ao clicar na seta na parte superior esquerda, o usuário irá voltar para a parte de menu.

#### 1.2 Criação do objetivo /  1.3 Edição do objetivo
![1 3 Tela da Criação do Objetivo](https://user-images.githubusercontent.com/101484450/232240319-438037a2-8498-4880-87af-b083bd7a13ca.jpg)
![Exemplo - Tela da edição do Objetivo](https://user-images.githubusercontent.com/101484450/232240350-ff7cc80c-32a1-46ce-82b3-60e538ae5189.jpg)


#### 1.2 Criação do objetivo:
 Nesta parte, o usuário pode definir uma meta que deseja alcançar. Ele pode inserir o nome do objetivo, o valor a ser alcançado, o valor já poupado e, se desejar, uma data para alcançá-lo. Além disso, ele pode escolher um ícone para representar o objetivo. Ao clicar no botão "Criar", o usuário será direcionado para a parte do objetivo, se desejar cancelar a criação do objetivo, ele pode clicar no "X" na parte superior esquerda, o que o levará de volta à parte do objetivo.

#### 1.3 Edição do objetivo:
 Nesta parte, o usuário poderá editar o que foi criado na parte de criação de objetivo , alterando o nome do objetivo, o valor do objetivo, o valor já poupado, assim como a data desejada para alcançá-lo, além dos ícones. Depois de alterado, o usuário poderá clicar no botão "salvar" na parte inferior da tela para salvar suas alterações. No entanto, se desejar cancelar a edição, poderá apertar o botão no formato de um "x" no canto superior esquerdo para cancelar as alterações.

### Cadastro de receita: 

#### 1.1 Criação de Receita / 1.2 Edição da receita

![1 1 tela receita](https://user-images.githubusercontent.com/101484450/232240540-8c79fced-5b61-4048-8d46-39155a6d286a.jpg)
![1 2 Tela editar receita](https://user-images.githubusercontent.com/101484450/232240616-89c9d917-cdd0-4218-a1ed-7567e2eb695b.jpg)

#### 1.1 Criação da Receita: 
 Nesta parte, o usuário poderá inserir o valor da receita, a descrição do que se trata essa receita, a data em que foi recebida e, por fim, selecionar uma das quatro categorias: salário, investimento, serviços ou outros. Essa funcionalidade permite ao usuário identificar em quais áreas as receitas estão dando mais retorno. Ao clicar no botão 'Criar', a receita criada irá aparecer na parte inicial na categoria de receitas. Já ao clicar no 'X', localizado na parte superior esquerda da tela, o usuário poderá fechar a tela de criação de receitas e voltar para a parte inicial .

#### 1.2 Edição da receita:
 Nesta parte, o usuário poderá editar o que foi criado na parte de receita, alterando o valor da receita, bem como a descrição, data e categoria da receita. Depois de alterar, o usuário poderá clicar no botão "salvar" na parte inferior da tela para salvar suas modificações. Se desejar cancelar a alteração, basta clicar no botão em forma de "x" no canto superior esquerdo para cancelar as alterações.

### Cadastro da despesa: 

#### 1.1 Despesas / 1.2 Edição da Despesas

![1 1 Tela Despesa](https://user-images.githubusercontent.com/101484450/232240867-c7ad6a87-bd17-449e-9f45-1795089dfb55.jpg)
![1 2 Tela  Editar Despesa](https://user-images.githubusercontent.com/101484450/232240899-3189b73f-7077-4497-a6a7-b4e8de9d3a2e.jpg)


#### 1.1 Criação da Despesa:
 Nesta parte, o usuário poderá inserir o valor da despesa, a descrição do que é essa despesa, a data em que a despesa foi feita e, por fim, as categorias que serão separadas em cinco ícones: alimentação, casa, saúde, educação e outros. Com essa funcionalidade, o usuário poderá saber onde suas despesas estão sendo gastas. Ao clicar no botão "criar", a despesa criada aparecerá na parte inicial na categoria de despesas. Ao clicar no botão em forma de  "X" na parte superior esquerda da tela, o usuário fechará a criação da despesa e voltará para a parte inicial.

#### 1.2 Edição da Despesas:
Nesta tela, o usuário poderá editar o que foi criado na parte Despesa, alterando o valor da despesa, bem como a descrição, data e categoria da despesa. Depois de alterado, o usuário poderá clicar no botão "Salvar" na parte inferior da tela para salvar suas modificações. Caso queira cancelar a alteração, poderá clicar no botão em formato de "x" no canto superior esquerdo , assim, a alteração será cancelada.

### Categorias de receitas

#### 1.1 Exemplo - Categoria Salário / 1.2 Exemplo - Categoria Investimento: 
![1 1 Exemplo - tela salário](https://user-images.githubusercontent.com/101484450/232241462-159b73da-3089-4733-826c-10c79e7b2145.jpg)
![1 2 Exemplo - tela Investimento](https://user-images.githubusercontent.com/101484450/232241484-5d47d835-3203-4132-8b7b-49bc33401f21.jpg)


#### 1.1 Exemplo - Categoria Salário:
Assim que o usuário criar sua receita com o ícone da categoria salário, ele poderá visualizar as receitas criadas com o mesmo ícone, podendo ver a data do recebimento, a descrição e também podendo editar clicando no lápis ao lado do nome da descrição, indo para a parte de editar receita. Depois de editado, terá o botão "salvar" na parte inferior da tela, que, ao ser clicado, salvará automaticamente as informações editadas. O usuário também poderá clicar no ícone da lixeira que ficará acima do valor da receita e esta será excluída automaticamente da categoria. Ao clicar na seta na parte superior esquerda, o usuário voltará para a parte inicial.

#### 1.2 Exemplo - Categoria Investimento: 
 Assim que o usuário criar sua receita com ícone da categoria investimento, ele poderá visualizar seus investimentos criados com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar clicando no lápis que ficará ao lado do nome da descrição, indo então para a categoria editar receita. Depois de editado, terá o botão para 'salvar' na parte inferior da tela, que ao clicar, salvará automaticamente as informações editadas. E podendo clicar no ícone da lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda, irá voltar para a parte inicial.



#### 1.3 Exemplo - Categoria Serviço / 1.4 Exemplo - Categoria outros 
![1 3 Exemplo - tela serviços](https://user-images.githubusercontent.com/101484450/232241607-21bb813d-3b10-42df-82f2-d12785a84048.jpg)
![1 4 Exemplo - tela outros 1](https://user-images.githubusercontent.com/101484450/232241644-d1f1c0c6-9a33-4a6b-91a7-b44f0a07165a.jpg)


#### 1.3 Exemplo - Categoria Serviço: 
 Assim que usuário criar sua receita  com ícone da Categoria serviço ele poderá visualizar suas receitas criadas com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar,  clicando no lápis que ficará ao lado no nome da descrição, indo então para parte de editar receita, depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para a parte inicial.


#### 1.4 Exemplo - Categoria outros: 
 Assim que usuário criar sua receita com ícone outros ele poderá visualizar suas receitas criadas com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para a parte de editar receita, depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para a parte inicial.

###  Categorias de despesas 

#### 1.1 Exemplo - Categoria Alimentação / 1.2 Exemplo - Categoria Casa 

![1 1 Exemplo - tela Alimentação](https://user-images.githubusercontent.com/101484450/232241755-003cb1d1-b42a-443b-b71c-a07c5d43cf1f.jpg)
![1 2 Exemplo - tela casa](https://user-images.githubusercontent.com/101484450/232241792-d4c6361b-bce9-4ea2-a606-50279b391d11.jpg)

#### 1.1 Exemplo - Categoria Alimentação:
 Assim que o usuário criar sua despesa com o ícone da Categoria Alimentação, podendo  visualizar suas despesas criadas com o mesmo ícone, podendo ver a data do gasto, descrição e também poderá editá-las clicando no ícone de lápis que ficará ao lado do nome da descrição, indo então para a tela de editar despesa. Depois de editada, haverá o botão "Salvar" na parte inferior,  que ao ser clicado salvará automaticamente as informações editadas. Além disso, poderá clicar no ícone da lixeira que fica acima do valor da despesa para excluí-la automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda, irá voltar para a parte tela inicial.

#### 1.2 Exemplo - Categoria Casa: 
 Assim que usuário criar sua despesa com ícone da Categoria Casa ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para a parte de editar despesa, depois de editado terá o botão para “salvar” na parte inferior, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para a parte inicial .

#### 1.3 Exemplo - Tela Saúde 1.4 Exemplo - Tela Educação
![1 3 Exemplo - tela saúde](https://user-images.githubusercontent.com/101484450/232241836-7b628b40-2e1b-4c68-a42e-7c1cab99e52e.jpg)
![1 4 Exemplo - tela educação](https://user-images.githubusercontent.com/101484450/232241843-2f56b3cd-5a8d-41f1-ac06-fe9553955511.jpg)


#### 1.3 Exemplo - Categoria Saúde
 Assim que usuário criar sua despesa com ícone da Categoria saúde ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para a parte de editar despesa, depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para a parte inicial.

#### 1.4 Exemplo - Categoria Educação: 
 Assim que usuário criar sua despesa com ícone da Categoria educação ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para a parte de editar despesa, depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído 


#### 1.5 Exemplo - Categoria Outros

![1.5 Exemplo - Tela Outros](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.5%20Exemplo%20-%20tela%20outros.jpg)


#### 1.5 Exemplo - Categoria Outros: 
 Assim que usuário criar sua despesa com ícone outros ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para a parte de editar despesa, depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para a parte de inicial.

### Contrato com o JSON

Delete/User: Esse endpoint é responsável por deletar um usuário na aplicação. Receberá como paramêtro Id do tipo long para idntificar o usuário que será removido.

Resposta: COD 200.

    {
      "Info": "Deletado com sucesso."
    }

Resposta: COD 404.

    {
      "Info": "Usuário não encontrado."
    }

Login/User: Esse endpoint é responsável por fazer o login do usuário na aplicação. Receberá como paramêtro E-mail e Senha.

Resposta: COD 200.

    {
      "Id":1,
      "name":"Amanda chaves",
      "Email": "Amanda@Gmail.com",
      "Password": "745614!",
      "Salary": 879514
    }

Resposta: COD 404.
 
    {
      "info": "Usuário não encontrado"
    }

Create/User: Esse endpoint é responsável por fazer o cadastro do usuário na aplicação. Receberá como paramêtro Nome, Id, Salário, E-mail, Senha.

Resposta: COD 201.

    {
      "Id":1,
      "name":"João Alves",
      "Email": "Joao@Gmail.com",
      "Password": "17894@",
      "Salary": 147451
    }

Resposta: COD 403.

    {
      "info": "Email já está em uso"
    }

Resposta: COD 404.

    {
      "info": "Erro No Email ou Senha do Usuário."
    }

UPDATE/USER: Esse endpoint é responsável por fazer a atualização dos usuários na aplicação. Receberá como paramêtro Id.

Resposta: COD 200.

    {
        "Info": "Atualização com sucesso."
    }

Resposta: COD 404.

    {
        "Info": "Usuário não encontrado."
    }
    
Create/Category:
Esse endpoint é responsável por criar uma categoria de despesas do usuário na aplicação. Receberá como paramêtro Id do usuário e da categoria.

Resposta: COD 201.

   {
        "category_id": 12,
        "user_id": 4,
        "type_moviment": "despesas",
        "value": 200,
   "date": "2024-04-23T18:25:43.511Z"
    }
    
Resposta: COD 404.
   
   {
        "Informações": "Parâmetros passados errados."
    }
    
Delete/Category:
Esse endpoint é responsáel por deletar algo que está atrelado a um tipo de categoria. Receberá como paramêtro Id da categoria.

Resposta: COD 200.
   {
        "Info": "Categoria deletada com sucesso."
    }
    
Resposta: COD 404.

   {
        "Info": "Categoria não encontrada."
    }
    
Update/Category:
Esse endpoint é responsáel por atualizar uma categoria. Receberá como paramêtro Id da categoria.

Resposta: COD 200.

  {
        "Info": "Atualizaçãoão realizada com sucesso."
    }
    
Resposta: COD 404.

   {
        "Info": "ERRO ao atualizar , por favor verifique os parâmetros."
    }
