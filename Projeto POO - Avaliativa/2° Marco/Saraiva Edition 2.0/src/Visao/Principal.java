package Visao;

import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Cliente;
import Dominio.Funcionario;
import Dominio.Livro;
import Persistencia.ClienteDAO;
import Persistencia.FuncionarioDAO;
import Persistencia.LivroDAO;

public class Principal {

	public static void main(String[] args) {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		LivroDAO livroDAO = new LivroDAO();
		ClienteDAO clienteDAO = new ClienteDAO();

		// 1234 eh a senha de acesso a funcao "Admin".
		ArrayList<Livro> estante = new ArrayList<>();
		ArrayList<Cliente> cadastrosClientes = new ArrayList<>();
		ArrayList<Funcionario> cadastrosFuncionarios = new ArrayList<>();
		ArrayList<Livro> compras = new ArrayList<>();

		// Funcionario instanciado e adicionado no cadastro caso queira utilizar as
		// funcoes de funcionario sem utilizar a operacao de cadastro de funcionario.
		Funcionario funcionarios;
		Livro livros;
		Cliente clientes;

		int i;
		boolean encontrou = false;

		int opcao = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			int opcaoFuncao;
			System.out.println("Selecione a sua funcao(1-funcionario, 2-cliente, 3-Admin): ");
			opcaoFuncao = scanner.nextInt();

			switch (opcaoFuncao) {
			case 1:
				cadastrosFuncionarios = funcionarioDAO.relatorio();
				int idAux;
				encontrou = false;

				System.out.println("LOGIN");
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
						estante = livroDAO.relatorio();

						System.out.println("---------------------------------------------------------");
						System.out.println("MENU FUNCIONARIO");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Adicionar livro na estante");
						System.out.println("2- Livros da estante");
						System.out.println("3- Voltar");
						System.out.println("Digite a opcao desejada: ");
						opcaoFuncionario = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoFuncionario) {
						case 1:
							livros = new Livro();
							System.out.println("Nome do livro: ");
							livros.setNome(scanner.nextLine());
							System.out.println("Autor: ");
							livros.setAutor(scanner.nextLine());
							System.out.println("Genero: ");
							livros.setGenero(scanner.nextLine());
							System.out.println("ISBN: ");
							livros.setIsbn(scanner.nextInt());
							System.out.println("Preco: ");
							livros.setPreco(scanner.nextFloat());
							livroDAO.inclusao(livros);
							System.out.println("Livro cadastrado com sucesso!");

							break;
						case 2:
							System.out.println("-----------Livros disponiveis------");
							for (i = 0; i < estante.size(); i++) {
								System.out.println(estante.get(i).informarDados());
							}
							break;
						}
					} while (opcaoFuncionario != 3);
				} else {
					System.out.println("Id nao corresponde a nenhum funcionario");
				}
				break;
			case 2:
				cadastrosClientes = clienteDAO.relatorio();

				scanner.nextLine();
				encontrou = false;
				int ClienteAux = 0;
				int opcaoCliente;
				String cpf;

				System.out.println("LOGIN");
				System.out.println("Digite o seu CPF para logar");
				cpf = scanner.nextLine();

				for (i = 0; i < cadastrosClientes.size(); i++) {
					if (cpf.equals(cadastrosClientes.get(i).getCpf())) {
						encontrou = true;
						ClienteAux = i;
					}
				}
				if (encontrou) {
					do {
						cadastrosClientes = clienteDAO.relatorio();
						compras = clienteDAO.minhasCompras(cadastrosClientes.get(ClienteAux).getCpf());

						System.out.println("---------------------------------------------------------");
						System.out.println("MENU CLIENTE");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Comprar");
						System.out.println("2- Exluir Conta");
						System.out.println("3- Ver minhas compras");
						System.out.println("4- Alterar meus dados");
						System.out.println("5- Mostrar meus dados");
						System.out.println("6- Voltar");
						System.out.println("Digite a opcao desejada: ");
						opcaoCliente = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoCliente) {
						case 1:
							estante = livroDAO.relatorio();

							encontrou = false;
							int encontrado = 0;
							int buscaLivro;
							System.out.println("Digite o numero ISBN do livro desejado: ");
							buscaLivro = scanner.nextInt();

							for (i = 0; i < estante.size(); i++) {
								if (buscaLivro == estante.get(i).getIsbn()) {
									encontrou = true;
									encontrado = i;
								}
							}
							if (encontrou) {
								int confirmacao;
								System.out.println("Dados de Livro");
								System.out.println(estante.get(encontrado).informarDados());

								System.out.println("Realizar a compra?(1-sim, 2-nÃ£o)");
								confirmacao = scanner.nextInt();

								if (confirmacao == 1) {

									clienteDAO.comprar(estante.get(encontrado),
											cadastrosClientes.get(ClienteAux).getCpf());
									livroDAO.exclusao(estante.get(encontrado).getIsbn());
									System.out.println("Compra realizada com sucesso!");
								}

							} else {
								System.out.println("livro nao encontrado");
							}

							break;
						case 2:
							int confirmarExclusao;
							System.out.println("Confirmar exclusao da conta?(1-sim, 2-nao)");
							confirmarExclusao = scanner.nextInt();
							if (confirmarExclusao == 1) {
								System.out.println("Conta excluida com sucesso!");
								clienteDAO.exclusao(cadastrosClientes.get(ClienteAux).getCpf());
								opcaoCliente = 6;

							} else {
								System.out.println("Exclusao cancelada!");
							}
							break;

						case 3:
							System.out.println("---------------------------------------------------------");
							System.out.println("LISTA DE COMPRAS");
							System.out.println("---------------------------------------------------------");
							for (i = 0; i < compras.size(); i++) {
								System.out.println(compras.get(i).informarDados());
							}

							break;
						case 4:
							int opcaoAlteracao;
							System.out.println("---------ALTERACAO DE DADOS--------");
							System.out.println("1- Alterar Nome");
							System.out.println("2- Alterar Telefone");
							System.out.println("Digite a opcao desejada: ");
							opcaoAlteracao = scanner.nextInt();
							scanner.nextLine();

							switch (opcaoAlteracao) {
							case 1:
								System.out.println("Digite o novo nome: ");
								clienteDAO.alterarNome(scanner.nextLine(), cadastrosClientes.get(ClienteAux).getCpf());
								System.out.println("Nome Alterado com sucesso!");

								break;
							case 2:
								System.out.println("Digite o novo telefone: ");
								clienteDAO.alterarTelefone(scanner.nextLine(),
										cadastrosClientes.get(ClienteAux).getCpf());
								System.out.println("Telefone Alterado com sucesso!");

								break;
							default:
								System.out.println("Nenhuma opcao selecionada!");
							}
							break;
						case 5:
							System.out.println("-----------MEUS DADOS-----------");
							System.out.println(cadastrosClientes.get(ClienteAux).informarDados());
							break;
						}
					} while (opcaoCliente != 6);
				} else {
					int opcaoCadastro;
					System.out.println("Cliente nao cadastrado!");
					// area para cadastro do cliente
					System.out.println("Deseja se cadastrar?(1-sim, 2-nao)");
					opcaoCadastro = scanner.nextInt();
					scanner.nextLine();

					if (opcaoCadastro == 1) {
						clientes = new Cliente();
						System.out.println("CADASTRO DE CLIENTE");
						System.out.println("CPF: ");
						clientes.setCpf(scanner.nextLine());
						System.out.println("Nome: ");
						clientes.setNome(scanner.nextLine());
						System.out.println("Numero do telefone: ");
						clientes.setNumeroTel(scanner.nextLine());
						clienteDAO.inclusao(clientes);
						System.out.println("Cadastro concluido com sucesso!");
					} else {
						System.out.println("Retornando ao menu...");
					}
				}

				break;
			case 3:
				int senha;
				System.out.println("LOGIN");
				System.out.println("Digite a sua senha para logar: ");
				senha = scanner.nextInt();
				if (senha == 1234) {
					int opcaoAdmin;
					do {
						cadastrosFuncionarios = funcionarioDAO.relatorio();

						System.out.println("---------------------------------------------------------");
						System.out.println("MENU DO ADMIN");
						System.out.println("---------------------------------------------------------");
						System.out.println("1- Cadastrar funcionario");
						System.out.println("2- Remover funcionario");
						System.out.println("3- Relatorio dos funcionarios");
						System.out.println("4- Voltar ao menu principal");
						System.out.println("Digite a opcao desejada: ");
						opcaoAdmin = scanner.nextInt();
						scanner.nextLine();

						switch (opcaoAdmin) {
						case 1:
							funcionarios = new Funcionario();
							System.out.println("CADASTRO DE FUNCIONARIO");
							System.out.println("CPF: ");
							funcionarios.setCpf(scanner.nextLine());
							System.out.println("Nome: ");
							funcionarios.setNome(scanner.nextLine());
							funcionarioDAO.inclusao(funcionarios);
							System.out.println("Funcionario cadastrado com sucesso!");

							break;
						case 2:
							int buscaFuncionario;
							System.out.println("Digite o ID do funcionario a ser removido: ");
							buscaFuncionario = scanner.nextInt();

							int idEncontrado = funcionarioDAO.buscar(buscaFuncionario);

							if (idEncontrado > 0) {
								System.out.println("Exclusao realizada!");
								funcionarioDAO.exclusao(idEncontrado);
							} else {
								System.out.println("Funcionario nao encontrado!");
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
			}
		} while (opcao != 7);
	}
}
