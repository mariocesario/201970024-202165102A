
# Trabalho Jogo Duelo de Personagens

#### Alunos: <br/>
Mario Henrique de Oliveira Cesario - 202165102A <br/>
Jonas Henrique Nascimento - 201970024 <br/>

### Organização do projeto:
Classes: <br/>
Player: Classe responsável por representar o jogador humano ou bot e seus dados. <br/>
Character: Classe abstrata responsável por representar o jogador para a classe game. Aqui armazenamos todos os atributos e métodos pertinentes do personagem. <br/>
 ├─ Archer: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo arqueiro. <br/>
 ├─ Mage: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo mago. <br/>
 ├─ Warrior: Classe que extende a classe Character, armazena os atributos e métodos pertinentes ao Jogador do tipo guerreiro. <br/>
Game: Classe para gerenciar todos os estados e regras do jogo. <br/>
Board: Classe para controlar o tabuleiro. <br/>

### Lista de tarefas:
#### Game:
- [x] Ajustar movimentação, alterar de movimentação xadrez para formato: Cima(C), Baixo(B), Esquerda(E) ou Direita (D).
- [x] Iniciar jogadores em posições aleatórias.
- [x] Refatorar e separar em funções menores.
- [x] Adicionar escolhas aleatórias para caso jogador 2 seja computador.
- [x] Mostrar na tela para qual direção o Bot se moveu.
- [x] Ao terminar jogo, mostrar mensagem do ganhador e perguntar se deseja iniciar outro jogo.
#### Board:
- [x] Ajustar interface para mostrar ações a cada novo turno: "Mover - Cima(C), Baixo(B), Esquerda(E) ou Direita (D); Atacar - (A)".
- [x] Ajustar interface para mostrar vida de cada jogador.
- [x] Ajustar interface - Permitir digitar somente a primeira letra de cada opção?
- [x] Refatorar e simplificar
#### Character:
- [x] Implementar movimentação;
- [x] Implementar ataque;
- [x] Implementar especial;
- [x] Implementar defesa;
- [x] Implementar limite de 1 uso do special;
