create database controle_estoque;
use controle_estoque;

 create table Cadastro_de_Produtos(
 ID int primary key auto_increment,
 Nome varchar (20),
 Marca varchar (15),
 Categoria varchar (15),
 Quantidade int
 );
 
 select * from Cadastro_de_Produtos;