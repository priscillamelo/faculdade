package Visão;

import java.util.ArrayList;

import java.util.Scanner;

import Domínio.*;

public class Principal {
	public static void main(String[] args) {

		ArrayList<Livro> estante = new ArrayList<>();
		ArrayList<Cliente> cadastrosClientes = new ArrayList<>();
		ArrayList<Funcionario> cadastrosFuncionarios = new ArrayList<>();

		Funcionario funcionarios;
		Livro livros;
		Cliente clientes;

		int i;
		boolean encontrou = false;

		int opcaoFuncao;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("Selecione a sua função (1- Funcionário, 2- Cliente, 3- Admin): ");
			opcaoFuncao = scanner.nextInt();

			switch (opcaoFuncao) {
			case 1:
				int idAux;
				encontrou = false;

				System.out.println("===== LOGIN =====");
				System.out.println("Digite o seu ID para logar");
				idAux = scanner.nextInt();
				for (i = 0; i < cadastrosFuncionarios.size(); i++) {
					if (idAux == cadastrosFuncionarios.get(i).getId()) {
						encontrou = true;
					}
				}
				if (encontrou) {
					int opcaoFuncionario;
					do {
						System.out.println("---------------------------------------------------------");
						System.out.println("MENU FUNCIONÁRIO");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Adicionar livro");
						System.out.println("2- Ver livros da estante");
						System.out.println("3- Voltar");
						System.out.println("Digite a opção desejada: ");
						opcaoFuncionario = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoFuncionario) {
						case 1:
							livros = new Livro();

							System.out.println("Nome do livro: ");
							livros.setNome(scanner.nextLine());
							System.out.println("Autor: ");
							livros.setAutor(scanner.nextLine());
							System.out.println("Gênero: ");
							livros.setGenero(scanner.nextLine());
							System.out.println("ISBN: ");
							livros.setIsbn(scanner.nextInt());
							System.out.println("Preço: ");
							livros.setPreco(scanner.nextFloat());
							estante.add(livros);
							break;
						case 2:
							System.out.println("===== Livros disponíveis =====");
							for (i = 0; i < estante.size(); i++) {
								System.out.print("Nome do livro: ");
								System.out.println(estante.get(i).getNome());
								System.out.print("Autor: ");
								System.out.println(estante.get(i).getAutor());
								System.out.print("Gênero: ");
								System.out.println(estante.get(i).getGenero());
								System.out.print("ISBN: ");
								System.out.println(estante.get(i).getIsbn());
								System.out.print("Preço: ");
								System.out.println(estante.get(i).getPreco());
							}
							break;
						}
					} while (opcaoFuncionario != 3);
				} else {
					System.out.println("Id não corresponde a nenhum funcionário!");
				}
				break;
			case 2:
				encontrou = false;
				int ClienteAux = 0;
				int opcaoCliente;
				String cpf;

				System.out.println("===== LOGIN =====");
				System.out.println("Digite o seu CPF para logar");
				scanner.nextLine();
				cpf = scanner.nextLine();

				for (i = 0; i < cadastrosClientes.size(); i++) {
					if (cpf.equals(cadastrosClientes.get(i).getCpf())) {
						encontrou = true;
						ClienteAux = i;
					}
				}
				if (encontrou) {
					do {
						System.out.println("---------------------------------------------------------");
						System.out.println("MENU CLIENTE");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Comprar");
						System.out.println("2- Exluir Conta");
						System.out.println("3- Ver minhas compras");
						System.out.println("4- Alterar meus dados");
						System.out.println("5- Mostrar meus dados");
						System.out.println("6- Sair");
						System.out.println("Digite a opção desejada: ");
						opcaoCliente = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoCliente) {
						case 1:
							encontrou = false;
							int encontrado = 0;
							int buscaLivro;
							System.out.println("Digite o número ISBN do livro desejado: ");
							buscaLivro = scanner.nextInt();

							for (i = 0; i < estante.size(); i++) {
								if (buscaLivro == estante.get(i).getIsbn()) {
									encontrou = true;
									encontrado = i;
								}
							}
							if (encontrou) {
								int confirmacao;

								System.out.println("===== Dados de Livro =====");
								System.out.print("Nome do livro: ");
								System.out.println(estante.get(encontrado).getNome());
								System.out.print("Autor: ");
								System.out.println(estante.get(encontrado).getAutor());
								System.out.print("Gênero: ");
								System.out.println(estante.get(encontrado).getGenero());
								System.out.print("ISBN: ");
								System.out.println(estante.get(encontrado).getIsbn());
								System.out.print("Preço: ");
								System.out.println(estante.get(encontrado).getPreco());

								System.out.println("Realizar a compra?(1-sim, 2-não)");
								confirmacao = scanner.nextInt();

								if (confirmacao == 1) {
									cadastrosClientes.get(ClienteAux).comprar(estante.get(encontrado));
									estante.remove(encontrado);
									System.out.println("Compra realizada com sucesso!");
								}
							} else {
								System.out.println("Livro não encontrado!");
							}
							break;
						case 2:
							int confirmarExclusao;

							System.out.println("Confirmar exclusão da conta? (1-sim, 2-não): ");
							confirmarExclusao = scanner.nextInt();
							if (confirmarExclusao == 1) {
								System.out.println("Conta excluída com sucesso!");
								cadastrosClientes.remove(ClienteAux);
								opcaoCliente = 6;
							} else {
								System.out.println("Exclusão cancelada!");
							}
							break;
						case 3:
							ArrayList<Livro> compras = cadastrosClientes.get(ClienteAux).getMinhasCompras();

							System.out.println("===== Lista de compras =====");
							for (i = 0; i < compras.size(); i++) {
								System.out.println("Nome do Livro: " + compras.get(i).getNome());
							}
							break;
						case 4:
							int opcaoAlteracao;

							System.out.println("===== Alteração de dados =====");
							System.out.println("1- Alterar Nome");
							System.out.println("2- Alterar Telefone");
							System.out.println("Digite a opção desejada: ");
							opcaoAlteracao = scanner.nextInt();

							switch (opcaoAlteracao) {
							case 1:
								scanner.nextLine();
								System.out.println("Digite o novo nome: ");
								cadastrosClientes.get(ClienteAux).setNome(scanner.nextLine());
								System.out.println("Nome Alterado com sucesso!");
								break;
							case 2:
								System.out.println("Digite o novo telefone: ");
								scanner.nextLine();
								cadastrosClientes.get(ClienteAux).setNumeroTel(scanner.nextLine());
								System.out.println("Telefone Alterado com sucesso!");
								break;
							default:
								System.out.println("Opção inválida!");
							}
							break;
						case 5:
							System.out.println("===== Meus dados =====");
							System.out.println(cadastrosClientes.get(ClienteAux).informarDados());
							break;
						}
					} while (opcaoCliente != 6);
				} else {
					int opcaoCadastro;
					System.out.println("Cliente não cadastrado!");
					// área para cadastro do cliente
					System.out.println("Deseja se cadastrar? (1-sim, 2-não): ");
					opcaoCadastro = scanner.nextInt();
					scanner.nextLine();

					if (opcaoCadastro == 1) {
						clientes = new Cliente();
						System.out.println("===== Cadastro do cliente =====");
						System.out.println("CPF: ");
						clientes.setCpf(scanner.nextLine());
						System.out.println("Nome: ");
						clientes.setNome(scanner.nextLine());
						System.out.println("Número do telefone: ");
						clientes.setNumeroTel(scanner.nextLine());
						cadastrosClientes.add(clientes);
						System.out.println("Cadastro concluído com sucesso!");
					} else {
						System.out.println("Retornando ao menu...");
					}
				}
				break;
			case 3:
				int senha;

				System.out.println("===== LOGIN =====");
				System.out.println("Digite a sua senha para logar: ");
				senha = scanner.nextInt();
				if (senha == 1234) {
					int opcaoAdmin;
					do {
						System.out.println("---------------------------------------------------------");
						System.out.println("MENU DO ADMIN");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Cadastrar funcionário");
						System.out.println("2- Remover funcionário");
						System.out.println("3- Dados dos funcionários");
						System.out.println("4- Voltar ao menu principal");
						System.out.println("Digite a opção desejada: ");
						opcaoAdmin = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoAdmin) {
						case 1:
							funcionarios = new Funcionario();

							System.out.println("===== Cadastro de funcionário =====");
							System.out.println("ID: ");
							funcionarios.setId(scanner.nextInt());
							scanner.nextLine();
							System.out.println("CPF: ");
							funcionarios.setCpf(scanner.nextLine());
							System.out.println("Nome: ");
							funcionarios.setNome(scanner.nextLine());
							cadastrosFuncionarios.add(funcionarios);
							System.out.println("Funcionário cadastrado com sucesso!");
							break;
						case 2:
							int buscaFuncionario;

							System.out.println("Digite o ID do funcionário a ser removido: ");
							buscaFuncionario = scanner.nextInt();

							for (i = 0; i < cadastrosFuncionarios.size(); i++) {
								if (buscaFuncionario == cadastrosFuncionarios.get(i).getId()) {
									cadastrosFuncionarios.remove(i);
									System.out.println("Funcionário removido do sistema!");
									i--;
								} else {
									System.out.println("ID não corresponde a nenhum funcionário!");
								}
							}
							break;
						case 3:
							for (i = 0; i < cadastrosFuncionarios.size(); i++) {
								System.out.println(cadastrosFuncionarios.get(i).informarDados());
							}
							break;
						}
					} while (opcaoAdmin != 4);
				} else {
					System.out.println("Senha incorreta!");
				}
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcaoFuncao != 4);

		System.out.println("Fim de programa!");
	}
}
