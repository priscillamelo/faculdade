import Pilha from "../src/Lista1/Questao2";

let pilha;
let pilhaInvertida;

beforeEach(() => {
  pilha = new Pilha(7);
  pilhaInvertida = new Pilha(7);
});

test("Adicionar elemento", () => {
  pilha.push("A");
  pilha.push("B");
  pilha.push("C");
  expect(pilha.isEmpty()).toBe(false);
});

test("Inverter pilha", () => {
  pilha.push("A");
  pilha.push("B");
  pilha.push("A");
  pilha.push("C");
  pilha.push("A");
  pilha.push("X");
  pilha.push("I");

  for (let i = 1; i <= pilha.size(); i++) {
    pilhaInvertida.push(pilha.peek());
    pilha.pop();
  }

  //console.log(pilhaInvertida);
  expect(pilhaInvertida.peek()).toBe("A");
});
