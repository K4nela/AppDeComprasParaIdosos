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
    INSERT INTO usuario ( nome, data_nasc, email, senha, endereco, telefone) VALUES
    ( 'Maria Souza', '2000-01-22', 'maria.souza@gmail.com', '1234', 'Rua B, 456', '61988888888'),
    ( 'Ana Pereira',  '1995-07-08', 'ana.pereira@gmail.com', '1234', 'Rua D, 101', '61966666666'),
    ( 'Carlos Oliveira', '1948-03-12' , 'carlos.oliveira@gmail.com', '1234', 'Rua C, 789', '61977777777'),
    ( 'João Silva', '1955-11-27', 'joao.silva@gmail.com', '1234', 'Rua A, 123', '61999999999'),
    ( 'Maria Eduarda', '1988-09-15' , 'maria.eduarda@gmail.com', '1234', 'Rua E, 102', '61955555555');

CREATE TABLE administrador (
    id_administrador INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
    INSERT INTO administrador(id_usuario)VALUES
    (5);

CREATE TABLE familiar (
    id_familiar INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
    INSERT INTO familiar (id_usuario) VALUES
    (1),
    (2);

CREATE TABLE idoso (
    id_idoso INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
    INSERT INTO idoso (id_usuario) VALUES
    (3),
    (4);

CREATE TABLE monitora (
    id_familiar INT,
    id_idoso INT,
    PRIMARY KEY (id_familiar, id_idoso),
    FOREIGN KEY (id_familiar) REFERENCES familiar(id_familiar),
    FOREIGN KEY (id_idoso) REFERENCES idoso(id_idoso)
);
    INSERT INTO monitora (id_familiar, id_idoso) VALUES 
        (1, 1),
        (2, 2),
        (2, 1);

CREATE TABLE listaDeDesejos (
    id_lista INT AUTO_INCREMENT PRIMARY KEY,
    nome_lista VARCHAR(100) NOT NULL DEFAULT 'LISTA',
    descricao VARCHAR(100) DEFAULT 'LISTA DE COMPRAS',
    data_solicitacao DATE ,
    id_idoso INT,
    FOREIGN KEY (id_idoso) REFERENCES idoso(id_idoso)
);
    INSERT INTO listaDeDesejos (id_idoso, data_solicitacao) VALUES
    (1,CURDATE()),
    (1,CURDATE()),
    (2,CURDATE()),
    (2,CURDATE());

CREATE TABLE itens (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_lista INT,
    nome_item VARCHAR(100) NOT NULL,
    descricao TEXT,
    quantidade INT,
    nome_loja VARCHAR(100),
    link VARCHAR(255),
    FOREIGN KEY (id_lista) REFERENCES listaDeDesejos(id_lista) ON DELETE CASCADE
);
    INSERT INTO itens (id_lista, nome_item, descricao, quantidade, nome_loja, link) VALUES
    ( 1, 'Blusa do Brasil', 'Tamanho: G', 1, 'Loja TeamShirt','https://shopee.com'),
    ( 2, 'Óculos de Sol', 'Moledo preto', 1, 'Loja Óptica', 'https://firmo.com'),
    ( 3, 'Chinelo Ortopedico', 'Chinelo tamanho 40', 1, 'Loja Calçados', 'https://shein.com'),
    ( 4, 'Samgsung A15', 'Modelo 5G', 1, 'Loja Tech', 'https://mercadolivre.com');

CREATE TABLE status (
    id_status INT AUTO_INCREMENT PRIMARY KEY,
    status_item ENUM ('Pendente', 'Em andamento', 'Concluído', 'Cancelado') NOT NULL
);
    INSERT INTO status (status_item) VALUES
    ('Pendente'),
    ('Em andamento'),
    ('Concluído'),
    ('Cancelado');

CREATE TABLE historico (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    data_historico DATE NOT NULL,
    id_item INT NULL,
    id_status INT,
    FOREIGN KEY (id_item) REFERENCES itens (id_item) ON DELETE SET NULL,
    FOREIGN KEY (id_status) REFERENCES status(id_status)
);

    INSERT INTO historico(id_item, id_status, data_historico)VALUES
    (1,1,CURDATE()),
    (2,1,CURDATE()),
    (3,1,CURDATE()),
    (4,1,CURDATE());


--Modificando com SELECT e UPDATE--

--troca de email ou senha
SELECT * FROM usuario;

--alterando o email do Joao
UPDATE usuario
SET email = 'Joao.Silva123@gmail.com' 
WHERE id = 4;

--Alterando a senha do Joao (usuario 4)
UPDATE usuario 
SET senha = 'ABC123'
WHERE id = 4;

--Conferindo o as alterações feitas no ususario Joao
SELECT * FROM usuario
WHERE id = 4;

--conferindo as listas de desejo
SELECT * FROM listadedesejos;

--exibindo lista de desejos através do acesso dos familiares 
SELECT 
       u_f.nome AS familiar,
       u_i.nome AS idoso,
       ld.nome_lista AS lista,
       it.nome_item AS item,
       s.status_item AS status
FROM monitora m
JOIN familiar f ON f.id_familiar = m.id_familiar
JOIN usuario u_f ON u_f.id = f.id_usuario   -- nome do familiar
JOIN idoso i ON i.id_idoso = m.id_idoso
JOIN usuario u_i ON u_i.id = i.id_usuario   -- nome do idoso
LEFT JOIN listadedesejos ld ON ld.id_idoso = i.id_idoso
LEFT JOIN itens it ON it.id_lista = ld.id_lista
LEFT JOIN historico h ON it.id_item = h.id_item
LEFT JOIN status s ON h.id_status = s.id_status
WHERE it.nome_item = "Blusa do Brasil";

--atualizando o status do pedido 1 do historico atraves do id_historico
--atualizando para "Em andamento"
UPDATE historico
SET id_status = 2
WHERE id_historico = 1;
SELECT * FROM historico;

--atualizando para "Concluído"
UPDATE historico
SET id_status = 3
WHERE id_historico = 1;
SELECT * FROM historico;

--atualizando para "Cancelado"
UPDATE historico
SET id_status = 4
WHERE id_historico = 1;
SELECT * FROM historico; 

--deletando o item cancelado
DELETE FROM itens
WHERE id_item = 1;
