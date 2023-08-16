# Challenge requirements


# Challenge: Team Management System

You are one of the programmers involved in developing a system that manages sports teams. The client company provided a list of necessary requirements to carry out this type of management.

An entity-relationship diagram was created to help design the system, as shown below.

![](img/der.png)


A empresa cliente informou que os seguintes dados precisam ser armazenados no sistema:

+	**Time**
    * Identificador 
    * Nome
    * Lista de Jogadores
    * Lista de Torcedores

+ **Jogador**
    * Identificador 
    * Nome
    * Posição
    * Documento
    * Time no qual trabalha

+ **Documento**
    * Identificador 
    * CPF
    * Número da Carteira de Trabalho
	* Número CBF
	
**Atenção:** *não inclua* um mapeamento reverso entre Documento e Jogador (ou seja, o Jogador tem um Documento, mas o Documento não tem um Jogador).

+ **Torcedor**
    * Identificador 
    * Nome

Por fim, foi definido que o identificador de cada entidade deve ser gerado automaticamente pelo sistema.

**Sua tarefa nesta demanda é construir um sistema, utilizando o framework hibernate, em que seja possível realizar operações de CRUD (CREATE, READ, UPDATE, DELETE) para todas as entidades listadas, conforme a especificação levantada.**

Para isso, você terá que:

1. Criar todas as classes Data Access Object (DAO)
2. Criar todas as classes Entity

### Dicas
1.  Analise o diagrama de entidade relacionamento e veja quais classes será necessário criar.
2. Analise o relacionamento entre as entidades, com base nela você irá escolher as anotações do hibernate que serão necessárias:

```java
@OnetoOne
@OneToMany
@ManyToOne
@ManyToMany
```

3. Como boa prática, faça camadas diferentes para responsabilidade. Por exemplo, utilize um pacote chamado dao para as classes que representem o Data Access Object.
4. Utilize o conceito de Herança para implementar as classes de Data Access Object.


Depois que você implementar esse desafio, terá feito um processo similar à construção de sistemas do mundo real, modelando os dados e construindo as operações para acesso a eles. Legal, né? #VQV

---