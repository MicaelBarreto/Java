drop database if exists db_Drogalizando;

create database db_Drogalizando;

use db_Drogalizando;

create table cliente(
	cod integer not null,
    cnpj varchar(200) not null,
    nome_fantasia varchar(200) not null,
    endereco varchar(200) not null,
    telefone varchar(200) not null,
    data_inscricao date not null,
    email varchar(200) not null,
    razao_social varchar(200) not null,
    inscricao_estadual varchar(200) not null,
    primary key(cod)
);

create table produto(
	codProduto integer not null,
    nomeProduto varchar(200) not null,
    valorProduto double not null,
    qntProduto integer not null,
    registro_ms varchar(200) not null,
    primary key(codProduto)
);

create table venda(
	cod integer not null,
    codProduto integer not null,
    id_venda integer not null,
    qntVenda integer not null,
    valorVenda double not null,
    data_venda date not null,
    primary key(id_venda),
    foreign key(codProduto) references produto(codProduto),
    foreign key(cod) references cliente(cod)
);

select * from cliente;
select * from produto;