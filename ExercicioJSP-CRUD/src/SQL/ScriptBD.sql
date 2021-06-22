CREATE DATABASE banco_crud;
USE banco_crud;
create table pessoa (
id int(3) NOT NULL AUTO_INCREMENT,
nome varchar(120) NOT NULL,
cpf varchar(14) NOT NULL,
endereco varchar(220) NOT NULL,
bairro varchar(120) NOT NULL,
cep varchar(9) NOT NULL,
cidade varchar(120) NOT NULL,
estado varchar(120) NOT NULL,
telefone varchar(13) NOT NULL,
PRIMARY KEY (id)
);

SELECT * FROM Pessoa;
INSERT INTO pessoa (nome, cpf, endereco, bairro, cep, cidade, estado, telefone) VALUES ('Gustavo Alves dos santos', '142.528.556-24', 'Rua Casablanca', 'Santa Terezinha', '31365-160', 'Belo Horizonte', 'Minas Gerais', '31997750675');