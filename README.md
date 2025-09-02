# Trabalho Jogo Duelo de Personagens

#### Alunos: <br/>
Mario Henrique de Oliveira Cesario - 202165102A <br/>
Jonas Henrique Nascimento - 201970024 <br/>


### Vídeo de apresentação no Youtube: [Trabalho duelo de personagens RPG](https://youtu.be/TI6iJ_1Yza0)


### Visão Geral do Projeto

Este é um jogo de tabuleiro por turnos, desenvolvido em Java, onde dois personagens se enfrentam em um duelo. O projeto foi criado como um trabalho para a disciplina de **Orientação a Objetos**, com o objetivo principal de aplicar conceitos fundamentais da POO na prática.

---

### Organização do Projeto:
Classes: <br/>
Player: Classe responsável por representar o jogador humano ou bot e seus dados. <br/>
Character: Classe abstrata responsável por representar o jogador para a classe game. Aqui armazenamos todos os atributos e métodos pertinentes do personagem. <br/>
 ├─ Archer: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo arqueiro. <br/>
 ├─ Mage: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo mago. <br/>
 ├─ Warrior: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo guerreiro. <br/>
Game: Classe para gerenciar todos os estados e regras do jogo. <br/>
Board: Classe para controlar o tabuleiro. <br/>

---

### Conceitos de Orientação a Objetos Aplicados

A arquitetura do código foi pensada para demonstrar os pilares da Orientação a Objetos de forma clara e funcional.

-   **Classes e Objetos**: O jogo é construído a partir de várias classes, como `Game`, `Board`, `Character` e `Player`, que representam as entidades e o fluxo do jogo.
-   **Herança**: A classe abstrata `Character` define os atributos e comportamentos básicos (como vida, ataque e defesa) que são comuns a todos os personagens. As classes `Warrior`, `Mage` e `Archer` herdam de `Character`, reutilizando o código da classe-pai e implementando suas próprias características e habilidades especiais.
-   **Polimorfismo**: O método `useSpecialPower` é um exemplo de polimorfismo. Ele é declarado na classe-mãe `Character`, mas cada subclasse (`Warrior`, `Mage`, `Archer`) o implementa de maneira única, adaptando a habilidade especial para cada tipo de personagem.
-   **Encapsulamento**: Os atributos de cada personagem, como `hp`, `atk` e `def`, são protegidos (`protected`), garantindo que a lógica do jogo só possa acessá-los através de métodos públicos (`getters` e `setters`) definidos na classe. Isso mantém os dados seguros e consistentes.
-   **Abstração**: A classe `Character` é `abstract` e não pode ser instanciada diretamente. Ela serve como um modelo para as classes concretas de personagens. O método `useSpecialPower` é abstrato, forçando as classes filhas a fornecerem sua própria implementação.

---

### Como Jogar

O jogo "Duelo de Personagens" é um combate por turnos em um tabuleiro 10x10. Cada jogador escolhe um personagem com habilidades únicas e se move pelo tabuleiro para atacar o oponente.

#### Regras:

1.  **Objetivo**: Derrotar o personagem do seu oponente. O primeiro a ter o HP (Pontos de Vida) zerado perde.
2.  **Personagens**: Escolha entre três classes, cada uma com atributos e poderes especiais diferentes:
    * **Guerreiro**: Focado em ataque e defesa. Seu poder especial aumenta drasticamente o dano do próximo ataque.
    * **Mago**: Focado em ataque e alcance médio. Seu poder especial troca de vida (HP) com o oponente.
    * **Arqueiro**: Focado em alcance e defesa. Seu poder especial aumenta o alcance de ataque por um turno.
3.  **Turnos**: Cada jogador tem uma ação por turno. As opções são:
    * **A (Attack)**: Ataca o oponente se ele estiver dentro do seu alcance.
    * **M (Move)**: Move seu personagem uma casa em uma das quatro direções (cima, baixo, esquerda, direita).
    * **D (Defender)**: Aumenta a sua defesa.
    * **S (Special)**: Ativa o poder especial do seu personagem.

---

### Como Rodar o Projeto

Você pode rodar o jogo de duas maneiras:

#### Opção 1: Via Terminal (Compilação e Execução Manual)

Você precisa ter o **Java JDK** instalado na sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/mariocesario/201970024-202165102A.git](https://github.com/mariocesario/201970024-202165102A.git)
    cd 201970024-202165102A
    ```
2.  **Compile os arquivos `.java`:**
    ```bash
    javac -d target\classes src\main\java\app\*.java src\main\java\character\*.java src\main\java\game\*.java src\main\java\player\*.java
    ```
3.  **Execute o jogo:**
    ```bash
    java -cp target\classes app.Main
    ```

#### Opção 2: Via Maven (Recomendado para ambientes de desenvolvimento)

Você precisa ter o **Java JDK** e o **Apache Maven** instalados na sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/mariocesario/201970024-202165102A.git](https://github.com/mariocesario/201970024-202165102A.git)
    cd 201970024-202165102A
    ```
2.  **Execute o jogo:**
    ```bash
    mvn exec:java
    ```
    *O Maven irá automaticamente compilar e executar o projeto para você.*
