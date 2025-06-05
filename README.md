
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
- [ ] Ajustar movimentação, alterar de movimentação xadrez para formato: Cima(C), Baixo(B), Esquerda(E) ou Direita (D).
- [ ] Iniciar jogadores em posições aleatórias.
- [ ] Refatorar e separar em funções menores.
#### Board:
- [ ] Ajustar interface para mostrar ações a cada novo turno: "Mover - Cima(C), Baixo(B), Esquerda(E) ou Direita (D); Atacar - (A)".
- [ ] Ajustar interface para mostrar vida de cada jogador.
- [ ] Ajustar interface - Permitir digitar somenta a primeira letra de cada opção?
- [ ] Refatorar e simplificar
#### Character:
- [ ] Implementar ataque;
