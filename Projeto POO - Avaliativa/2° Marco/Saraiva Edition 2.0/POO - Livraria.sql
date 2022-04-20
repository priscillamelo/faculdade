CREATE DATABASE Livraria;

USE Livraria;
			
CREATE TABLE livro(
	isbn int primary key not null,
	nome_l varchar(100),
	autor varchar(100),
	preco decimal,
	genero varchar(100),
	volume int default 1
);

CREATE TABLE funcionario(
	id_f serial primary key,
	cpf varchar(12) unique,
	nome varchar(100)
);

CREATE TABLE cliente(
	cpf_c varchar(12) primary key not null,
	numerotel varchar(100),
	nome varchar(100)
);

CREATE TABLE compras(
	cpf_c varchar(12),
	foreign key (cpf_c) references cliente (cpf_c),
	isbn_c int,
	nome_c varchar(100),
	autor_c varchar(100),
	preco_c decimal,
	genero_c varchar(100),
	volume_c int default 1
);


