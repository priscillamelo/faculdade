/**
 * Uma estrutura de dados do tipo pilha pode ser usada em um algoritmo que permite imprimir uma palavra de forma invertida. Por exemplo, ABACAXI deve ser impresso IXACABA. Utilizando uma pilha e suas operações base, desenvolva a função inverte que recebe uma String e, utilizando as funções push e pop da pilha, retorne a palavra de forma invertida.
 */

class Pilha {
  constructor(size = 10) {
    this.topo = 0;
    this.dados = [];
    this.maxSize = size;
  }

  push(dado) {
    if (!this.isFull()) {
      this.dados[this.topo] = dado;
      this.topo++;
    } else {
      throw new Error("stack overflow");
    }
  }

  pop() {
    if (!this.isEmpty()) {
      this.topo--;
    } else {
      throw new Error("stack underflow");
    }
  }

  isEmpty() {
    return this.peek === 0;
  }

  isFull() {
    return this.dados[this.topo] === this.size();
  }

  peek() {
    return this.dados[this.topo - 1];
  }

  size() {
    return this.maxSize;
  }

  toString() {}
}

export default Pilha;
