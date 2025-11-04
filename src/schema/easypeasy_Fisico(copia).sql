CREATE DATABASE easypeasy;
USE easypeasy;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nasc DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20)
);

CREATE TABLE administrador (
    id_administrador INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE familiar (
    id_familiar INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE idoso (
    id_idoso INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE monitora (
    id_monitora INT NOT NULL AUTO_INCREMENT,
    id_familiar INT NOT NULL,
    id_idoso    INT NOT NULL,
    PRIMARY KEY (id_monitora),
    FOREIGN KEY (id_familiar) REFERENCES familiar (id_familiar),
    FOREIGN KEY (id_idoso) REFERENCES idoso (id_idoso)
);

CREATE TABLE listaDeDesejos (
    id_lista INT AUTO_INCREMENT PRIMARY KEY,
    nome_lista VARCHAR(100) NOT NULL DEFAULT 'LISTA',
    descricao VARCHAR(100) DEFAULT 'LISTA DE COMPRAS',
    data_criacao DATE ,
    id_idoso INT,
    FOREIGN KEY (id_idoso) REFERENCES idoso(id_idoso)
);

CREATE TABLE item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_lista INT,
    nome_item VARCHAR(100) NOT NULL,
    descricao TEXT,
    quantidade INT,
    nome_loja VARCHAR(100),
    link VARCHAR(255),
    FOREIGN KEY (id_lista) REFERENCES listaDeDesejos(id_lista) ON DELETE CASCADE
);

CREATE TABLE historico (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    data_historico DATE NOT NULL,
    status ENUM ('Pendente', 'Em andamento', 'Concluído', 'Cancelado') NOT NULL,
    id_item INT NULL,
    id_status INT,
    FOREIGN KEY (id_item) REFERENCES item (id_item) ON DELETE SET NULL
);

-- POPULANDO

-- tabela usuario
INSERT INTO usuario (id, nome, data_nasc, email, senha, endereco, telefone, tipo) VALUES
    (1, 'Jaime', '2006-03-15', 'adm@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'administrador'),
    (2, 'Adalberto Junior', '1970-06-12', 'adalberto@gmail.com', '1234', 'xixique brasilia', '61 40028922', 'idoso'),
    (3, 'Maria', '2006-03-15', 'maria@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'familiar'),
    (4, 'Evaldro', '1970-03-15', 'idoso@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso'),
    (5, 'Idoso2', '1970-03-15', 'idoso2@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso'),
    (6, 'Idoso3', '1970-03-15', 'idoso3@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso');

-- tabela administrador
INSERT INTO administrador (id_administrador, id_usuario) VALUES
    (1, 1);

-- tabela monitora
INSERT INTO monitora (id_familiar, id_idoso, id_monitora) VALUES
    (1, 2, 1),
    (1, 3, 2),
    (1, 4, 3),
    (1, 5, 4);

-- tabela lista_de_desejos
INSERT INTO listaDeDesejos (id_lista, nome_lista, descricao, data_criacao, id_idoso) VALUES
    (1, 'ListaDeDesejos', 'Lista para salvar os produtos que eu quero comprar na internete', '2025-11-02', 2);

-- tabela item
INSERT INTO item (id_item, id_lista, nome_item, descricao, quantidade, nome_loja, link) VALUES
    (1, 1, 'item', 'descricao', 3, 'loja', 'loja.com');

-- tabela historico
INSERT INTO historico (id_historico, data_historico, id_item, status) VALUES
    (1, '2025-11-02', 1, 'PENDENTE');

-- tabela familiar
INSERT INTO familiar (id_familiar, id_usuario) VALUES
    (1, 3);
