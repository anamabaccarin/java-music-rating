CREATE DATABASE musicas;

USE musicas;

-- Cria usuario padrão de sistema para acesso ao banco de dados
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'P@ssw0rd';

-- Configura permissões de acesso ao usuario padrão
GRANT ALL ON musicas.* TO 'admin'@'localhost';

-- Cria tabelas do sistema

CREATE TABLE tb_usuario(
    id_usuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(150) NOT NULL,
    email_usuario VARCHAR(100) NOT NULL UNIQUE,
    login_usuario VARCHAR(100) NOT NULL UNIQUE,
    senha_usuario VARCHAR(50) NOT NULL
);

CREATE TABLE tb_musica(
    id_musica INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome_musica VARCHAR(100) NOT NULL
);

CREATE TABLE tb_genero(
    id_genero INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome_genero VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tb_musica_genero (
    id_musica INT NOT NULL,
    id_genero INT NOT NULL,
    FOREIGN KEY (id_musica) REFERENCES tb_musica(id_musica),
    FOREIGN KEY (id_genero) REFERENCES tb_genero(id_genero),
    PRIMARY KEY (id_musica, id_genero)
);

CREATE TABLE tb_usuario_genero (
    id_usuario INT NOT NULL,
    id_genero INT NOT NULL,
    data_registro DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario),
    FOREIGN KEY (id_genero) REFERENCES tb_genero(id_genero),
    PRIMARY KEY (id_usuario, id_genero)
);

CREATE TABLE tb_usuario_musica (
    id_usuario INT NOT NULL,
    id_musica INT NOT NULL,
    data_registro DATETIME NOT NULL,
    avaliacao TINYINT NOT NULL CHECK (avaliacao IN (1,2,3,4,5)),
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario),
    FOREIGN KEY (id_musica) REFERENCES tb_musica(id_musica),
    PRIMARY KEY (id_usuario, id_musica)
);

-- Insere dados padrões para o sistema

INSERT INTO tb_usuario (id_usuario, nome_usuario, email_usuario, login_usuario, senha_usuario) VALUES
(1, 'Ana Maria', 'anamaria@email.com.br', 'anamaria', '1234'),
(2, 'Manuela', 'manuela@email.com.br', 'manuela', '1234');

INSERT INTO tb_musica (id_musica, nome_musica) VALUES
(1, 'Crossroad Blues'),
(2, 'Manish Boy'),
(3, 'Spoonful'),
(4, 'Baby Please Don''t Go'),
(5, 'Keep It To Yourself'),
(6, 'Smoke on the Water'),
(7, 'Layla'),
(8, 'November Rain'),
(9, 'Kashmir'),
(10, 'Born to be Wild'),
(11, 'Giant Steps'),
(12, 'Take 5'),
(13, 'April in Paris'),
(14, 'Autumn Leaves'),
(15, 'Meaning and Mistery'),
(16, 'Feeling Good'),
(17, 'Travessia'),
(18, 'Chão de Giz'),
(19, 'Hoje Eu Quero Sair Só'),
(20, 'Preciso me Encontrar'),
(21, 'Chega de Saudade'),
(22, 'La Belle de Jour'),
(23, 'Apesar de Você');

INSERT INTO tb_genero (id_genero, nome_genero) VALUES
(1, 'Blues'),
(2, 'Rock'),
(3, 'Jazz'),
(4, 'MPB');

INSERT INTO tb_musica_genero (id_musica, id_genero) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 1),
(16, 3),
(17, 4),
(18, 4),
(19, 4),
(20, 4),
(21, 4),
(22, 4),
(23, 4);

INSERT INTO tb_usuario_genero (id_usuario, id_genero, data_registro) VALUES
(1, 3, NOW()),
(1, 2, NOW()),
(2, 2, NOW());

INSERT INTO tb_usuario_musica (id_usuario, id_musica, data_registro, avaliacao) VALUES
(1, 11, NOW(), 5),
(1, 14, NOW(), 4),
(1, 8, NOW(), 4),
(2, 8, NOW(), 5);
