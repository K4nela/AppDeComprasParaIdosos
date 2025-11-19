-- =====================
-- CRIAÇÃO DO BANCO
-- =====================
DROP DATABASE IF EXISTS easypeasy2;
CREATE DATABASE easypeasy2;
USE easypeasy2;

-- =====================
-- TABELA USUÁRIO
-- =====================
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nasc DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    tipo VARCHAR(20)
);

-- =====================
-- TRIGGERS DE USUÁRIO
-- =====================
-- Antes de inserir, cria ID do usuário
DROP TRIGGER IF EXISTS trg_before_insert_usuario;
DELIMITER $$
CREATE TRIGGER trg_before_insert_usuario
BEFORE INSERT ON usuario
FOR EACH ROW
BEGIN
    DECLARE novo_id INT;
    SELECT COUNT(*) + 1 INTO novo_id FROM usuario;
    SET NEW.id = CONCAT('USR', LPAD(novo_id, 4, '0'));
END$$
DELIMITER ;

-- Depois de inserir, cria o registro na tabela correspondente ao tipo
DROP TRIGGER IF EXISTS trg_after_insert_usuario;
DELIMITER $$
CREATE TRIGGER trg_after_insert_usuario
AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    IF NEW.tipo = 'familiar' THEN
        INSERT INTO familiar (id_usuario) VALUES (NEW.id);
    ELSEIF NEW.tipo = 'idoso' THEN
        INSERT INTO idoso (id_usuario) VALUES (NEW.id);
    ELSEIF NEW.tipo = 'administrador' THEN
        INSERT INTO administrador (id_usuario) VALUES (NEW.id);
    END IF;
END$$
DELIMITER ;

-- =====================
-- TABELA ADMINISTRADOR
-- =====================
DROP TABLE IF EXISTS administrador;
CREATE TABLE administrador (
    id_administrador VARCHAR(10) PRIMARY KEY,
    id_usuario VARCHAR(10) UNIQUE,
    CONSTRAINT fk_admin_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE
);

-- Trigger para gerar ID do administrador
DELIMITER $$
CREATE TRIGGER trg_before_insert_adm
BEFORE INSERT ON administrador
FOR EACH ROW
BEGIN
    DECLARE novo_id INT;
    SELECT COUNT(*) + 1 INTO novo_id FROM administrador;
    SET NEW.id_administrador = CONCAT('ADM', LPAD(novo_id, 4, '0'));
END$$
DELIMITER ;

-- =====================
-- TABELA FAMILIAR
-- =====================
DROP TABLE IF EXISTS familiar;
CREATE TABLE familiar (
    id_familiar VARCHAR(10) PRIMARY KEY,
    id_usuario VARCHAR(10) UNIQUE,
    CONSTRAINT fk_familiar_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE
);

-- Trigger para gerar ID do familiar
DELIMITER $$
CREATE TRIGGER trg_before_insert_fam
BEFORE INSERT ON familiar
FOR EACH ROW
BEGIN
    DECLARE novo_id INT;
    SELECT COUNT(*) + 1 INTO novo_id FROM familiar;
    SET NEW.id_familiar = CONCAT('FAM', LPAD(novo_id, 4, '0'));
END$$
DELIMITER ;

-- =====================
-- TABELA IDOSO
-- =====================
DROP TABLE IF EXISTS idoso;
CREATE TABLE idoso (
    id_idoso VARCHAR(10) PRIMARY KEY,
    id_usuario VARCHAR(10) UNIQUE,
    CONSTRAINT fk_idoso_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE
);

-- Trigger para gerar ID do idoso
DELIMITER $$
CREATE TRIGGER trg_before_insert_idoso
BEFORE INSERT ON idoso
FOR EACH ROW
BEGIN
    DECLARE novo_id INT;
    SELECT COUNT(*) + 1 INTO novo_id FROM idoso;
    SET NEW.id_idoso = CONCAT('IDS', LPAD(novo_id, 4, '0'));
END$$
DELIMITER ;

-- =====================
-- TABELA LISTA DE DESEJOS
-- =====================
DROP TABLE IF EXISTS listadedesejos;
CREATE TABLE listadedesejos (
    id_lista INT AUTO_INCREMENT PRIMARY KEY,
    nome_lista VARCHAR(100) NOT NULL DEFAULT 'LISTA',
    descricao VARCHAR(100) DEFAULT 'LISTA DE COMPRAS',
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_idoso VARCHAR(10),
    CONSTRAINT fk_lista_idoso FOREIGN KEY (id_idoso) REFERENCES idoso(id_idoso) ON DELETE CASCADE
);

-- =====================
-- TABELA ITEM
-- =====================
DROP TABLE IF EXISTS item;
CREATE TABLE item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_lista INT,
    nome_item VARCHAR(100) NOT NULL,
    descricao TEXT,
    quantidade INT,
    nome_loja VARCHAR(100),
    link VARCHAR(255),
    CONSTRAINT fk_item_lista FOREIGN KEY (id_lista) REFERENCES listadedesejos(id_lista) ON DELETE CASCADE
);

-- =====================
-- TABELA HISTÓRICO
-- =====================
DROP TABLE IF EXISTS historico;
CREATE TABLE historico (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    data_historico DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_item INT,
    status ENUM('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO'),
    CONSTRAINT fk_historico_item FOREIGN KEY (id_item) REFERENCES item(id_item) ON DELETE CASCADE
);


-- Trigger para inserir item no histórico automaticamente
DELIMITER $$
CREATE TRIGGER trg_item_insert
    AFTER INSERT ON item
    FOR EACH ROW
BEGIN
    INSERT INTO historico (data_historico, id_item, status)
    VALUES (CURDATE(), NEW.id_item, 'PENDENTE');
END$$
DELIMITER ;


-- =====================
-- TABELA MONITORA
-- =====================
DROP TABLE IF EXISTS monitora;
CREATE TABLE monitora (
    id_monitora INT AUTO_INCREMENT PRIMARY KEY,
    id_familiar VARCHAR(10) NOT NULL,
    id_idoso VARCHAR(10) NOT NULL,
    CONSTRAINT fk_monitora_familiar FOREIGN KEY (id_familiar) REFERENCES familiar(id_familiar),
    CONSTRAINT fk_monitora_idoso FOREIGN KEY (id_idoso) REFERENCES idoso(id_idoso) ON DELETE CASCADE
);

-- =====================
-- ÍNDICES
-- =====================
CREATE INDEX idx_historico_item ON historico (id_item);
CREATE INDEX idx_historico_status ON historico (status);
CREATE INDEX idx_item_lista ON item (id_lista);
CREATE INDEX idx_lista_idoso ON listadedesejos (id_idoso);

-- =====================
-- PROCEDURE
-- =====================
DELIMITER //
CREATE PROCEDURE marcar_item_concluido(IN p_id_item INT)
BEGIN
    UPDATE historico
    SET status = 'CONCLUIDO', data_historico = CURDATE()
    WHERE id_item = p_id_item;
END//
DELIMITER ;

-- =====================
-- FUNCTION
-- =====================
DELIMITER //
CREATE FUNCTION gerar_id_item()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE novo_id INT;
    SELECT IFNULL(MAX(id_item), 0) + 1 INTO novo_id FROM item;
    RETURN novo_id;
END//
DELIMITER ;

-- =====================
-- TRIGGER DE SEGURANÇA
-- =====================
DELIMITER $$
CREATE TRIGGER trg_prevent_delete_idoso
BEFORE DELETE ON idoso
FOR EACH ROW
BEGIN
    DECLARE lista_count INT;
    SELECT COUNT(*) INTO lista_count FROM listadedesejos WHERE id_idoso = OLD.id_idoso;
    IF lista_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possível excluir idoso com listas vinculadas';
    END IF;
END$$
DELIMITER ;

-- =====================
-- VIEWS
-- =====================
CREATE VIEW vw_idosos_listas AS
SELECT i.id_idoso, u.nome AS nome_idoso, l.nome_lista, l.descricao
FROM idoso i
JOIN usuario u ON u.id = i.id_usuario
LEFT JOIN listadedesejos l ON l.id_idoso = i.id_idoso;

CREATE VIEW vw_historico_completo AS
SELECT h.id_historico, i.nome_item, h.status, h.data_historico, l.nome_lista
FROM historico h
JOIN item i ON i.id_item = h.id_item
JOIN listadedesejos l ON l.id_lista = i.id_lista;

-- =====================
-- DADOS INICIAIS
-- =====================

-- Usuários
INSERT INTO usuario (nome, data_nasc, email, senha, endereco, telefone, tipo) VALUES
('Jaime', '2006-03-15', 'adm@gmail.com', '1234', 'Brazlândia', '61 9999-9999', 'administrador'),
('Adalberto Junior', '1970-06-12', 'adalberto@gmail.com', '1234', 'Xixique Brasília', '61 4002-8922', 'idoso'),
('Maria', '2006-03-15', 'maria@gmail.com', '1234', 'Brazlândia', '61 9999-9999', 'familiar'),
('Evaldro', '1970-03-15', 'idoso@gmail.com', '1234', 'Brazlândia', '61 9999-9999', 'idoso'),
('Idoso2', '1970-03-15', 'idoso2@gmail.com', '1234', 'Brazlândia', '61 9999-9999', 'idoso'),
('Idoso3', '1970-03-15', 'idoso3@gmail.com', '1234', 'Brazlândia', '61 9999-9999', 'idoso'),
('Jaiminhp', '2006-03-15', 'adm2@gmail.com', '1234', 'Brazlândia', '61 9999-8888', 'administrador');

-- Agora, as triggers cuidam de criar automaticamente:
-- administrador → tabela administrador
-- familiar → tabela familiar
-- idoso → tabela idoso

-- Listas de desejos
INSERT INTO listadedesejos (nome_lista, descricao, data_criacao, id_idoso) VALUES
('Lista do Seu Adalberto', 'Compras do mês', '2025-11-01', 'IDS0001'),
('Lista do Evaldro', 'Necessidades básicas', '2025-11-02', 'IDS0002'),
('Lista do Idoso 2', 'Itens pessoais', '2025-11-03', 'IDS0003'),
('Lista do Idoso 3', 'Coisas diversas', '2025-11-04', 'IDS0004');

-- Itens das listas
INSERT INTO item (id_lista, nome_item, descricao, quantidade, nome_loja, link) VALUES
(1, 'Fralda geriátrica', 'Tamanho G, pacote com 20', 2, 'Drogasil', 'https://lojaexemplo.com/fralda'),
(1, 'Sabonete neutro', 'Para pele sensível', 5, 'Extra', 'https://lojaexemplo.com/sabonete'),
(2, 'Arroz 5kg', 'Tipo 1', 3, 'Carrefour', 'https://lojaexemplo.com/arroz'),
(3, 'Cobertor', 'Para o frio', 1, 'Americanas', 'https://lojaexemplo.com/cobertor'),
(4, 'Remédio para pressão', 'Comprimidos 10mg', 1, 'Farmácia Popular', 'https://lojaexemplo.com/remedio');

-- Os triggers de item já criam automaticamente as entradas no histórico

-- Monitora (familiar vinculado a idosos)
INSERT INTO monitora (id_familiar, id_idoso) VALUES
('FAM0001', 'IDS0001'),
('FAM0001', 'IDS0002'),
('FAM0001', 'IDS0003'),
('FAM0001', 'IDS0004');

-- =====================
-- USUÁRIO LIMITADO PARA APLICAÇÃO
-- =====================
CREATE DATABASE IF NOT EXISTS meu_banco;
CREATE USER IF NOT EXISTS 'dev_user'@'%' IDENTIFIED BY 'senha123';
GRANT ALL PRIVILEGES ON meu_banco.* TO 'dev_user'@'%';
FLUSH PRIVILEGES;
