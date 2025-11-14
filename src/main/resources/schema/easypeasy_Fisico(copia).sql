create database easypeasy2;
use easypeasy2;

create table usuario
(
    id        int auto_increment primary key,
    nome      varchar(100) not null,
    data_nasc date         not null,
    email     varchar(100) not null unique,
    senha     varchar(100) not null,
    endereco  varchar(200),
    telefone  varchar(20),
    tipo      varchar(20)
);

create table administrador
(
    id_administrador int auto_increment primary key,
    id_usuario       int unique,
    constraint fk_admin_usuario
        foreign key (id_usuario) references usuario (id)
            on delete cascade
);

create table familiar
(
    id_familiar int auto_increment primary key,
    id_usuario  int unique,
    constraint fk_familiar_usuario
        foreign key (id_usuario) references usuario (id)
            on delete cascade
);

create table idoso
(
    id_idoso   int auto_increment primary key,
    id_usuario int unique,
    constraint fk_idoso_usuario
        foreign key (id_usuario) references usuario (id)
            on delete cascade
);

create table listadedesejos
(
    id_lista     int auto_increment primary key,
    nome_lista   varchar(100) not null default 'LISTA',
    descricao    varchar(100)          default 'LISTA DE COMPRAS',
    data_criacao date,
    id_idoso     int,
    constraint fk_lista_idoso
        foreign key (id_idoso) references idoso (id_idoso)
);

create table item
(
    id_item    int auto_increment primary key,
    id_lista   int,
    nome_item  varchar(100) not null,
    descricao  text,
    quantidade int,
    nome_loja  varchar(100),
    link       varchar(255),
    constraint fk_item_lista
        foreign key (id_lista) references listadedesejos (id_lista)
            on delete cascade
);

create table historico
(
    id_historico   int auto_increment primary key,
    data_historico date not null,
    id_item        int,
    status         enum ('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO'),
    constraint fk_historico_item
        foreign key (id_item) references item (id_item)
            on delete set null
);

create index idx_historico_item on historico (id_item);
create index idx_historico_status on historico (status);
create index idx_item_lista on item (id_lista);
create index idx_lista_idoso on listadedesejos (id_idoso);

create table monitora
(
    id_monitora int auto_increment primary key,
    id_familiar int not null,
    id_idoso    int not null,
    constraint fk_monitora_familiar
        foreign key (id_familiar) references familiar (id_familiar),
    constraint fk_monitora_idoso
        foreign key (id_idoso) references idoso (id_idoso)
);

INSERT INTO usuario (id, nome, data_nasc, email, senha, endereco, telefone, tipo)
VALUES (1, 'Jaime', '2006-03-15', 'adm@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'administrador'),
       (2, 'Adalberto Junior', '1970-06-12', 'adalberto@gmail.com', '1234', 'xixique brasilia ', '61 40028922',
        'idoso'),
       (3, 'Maria', '2006-03-15', 'maria@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'familiar'),
       (4, 'evaldro', '1970-03-15', 'idoso@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso'),
       (5, 'idoso2', '1970-03-15', 'idoso2@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso'),
       (6, 'idoso3', '1970-03-15', 'idoso3@gmail.com', '1234', 'Brazlandia', '61 9999-9999', 'idoso'),
       (7, 'Jaiminhp', '2006-03-15', 'adm2@gmail.com', '1234', 'Brazlandia', '61 9999-8888', 'administrador');

-- administrador (ligando com usuario tipo administrador)
INSERT INTO administrador (id_administrador, id_usuario)
VALUES (1, 1),
       (2, 7);

-- familiar (ligando com usuario tipo familiar)
INSERT INTO familiar (id_familiar, id_usuario)
VALUES (1, 3);

-- idoso (ligando com usuario tipo idoso)
INSERT INTO idoso (id_idoso, id_usuario)
VALUES (1, 2),
       (2, 4),
       (3, 5),
       (4, 6);

-- listadedesejos (uma pra cada idoso)
INSERT INTO listadedesejos (id_lista, nome_lista, descricao, data_criacao, id_idoso)
VALUES (1, 'Lista do Seu Adalberto', 'Compras do mês', '2025-11-01', 1),
       (2, 'Lista do Evaldro', 'Necessidades básicas', '2025-11-02', 2),
       (3, 'Lista do Idoso 2', 'Itens pessoais', '2025-11-03', 3),
       (4, 'Lista do Idoso 3', 'Coisas diversas', '2025-11-04', 4);

-- item (itens de cada lista)
INSERT INTO item (id_item, id_lista, nome_item, descricao, quantidade, nome_loja, link)
VALUES (1, 1, 'Fralda geriátrica', 'Tamanho G, pacote com 20', 2, 'Drogasil', 'https://lojaexemplo.com/fralda'),
       (2, 1, 'Sabonete neutro', 'Para pele sensível', 5, 'Extra', 'https://lojaexemplo.com/sabonete'),
       (3, 2, 'Arroz 5kg', 'Tipo 1', 3, 'Carrefour', 'https://lojaexemplo.com/arroz'),
       (4, 3, 'Cobertor', 'Para o frio', 1, 'Americanas', 'https://lojaexemplo.com/cobertor'),
       (5, 4, 'Remédio para pressão', 'Comprimidos 10mg', 1, 'Farmácia Popular', 'https://lojaexemplo.com/remedio');

-- historico (mudanças de status de itens)
INSERT INTO historico (id_historico, data_historico, id_item, status)
VALUES (1, '2025-11-01', 1, 'PENDENTE'),
       (2, '2025-11-02', 2, 'EM_ANDAMENTO'),
       (3, '2025-11-03', 3, 'CONCLUIDO'),
       (4, '2025-11-04', 4, 'PENDENTE'),
       (5, '2025-11-05', 5, 'CANCELADO');

-- monitora (ligação familiar ↔ idoso)
INSERT INTO monitora (id_monitora, id_familiar, id_idoso)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4);
