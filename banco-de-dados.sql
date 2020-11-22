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
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario),
    FOREIGN KEY (id_genero) REFERENCES tb_genero(id_genero),
    PRIMARY KEY (id_usuario, id_genero)
);

CREATE TABLE tb_avaliacao (
    id_avaliacao INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_musica INT NOT NULL,
    data_avaliacao DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario),
    FOREIGN KEY (id_musica) REFERENCES tb_musica(id_musica),
    UNIQUE KEY indice_unico_avaliacao_usuario_musica (id_usuario, id_musica)
);

-- Insere dados padrões para o sistema

INSERT INTO tb_usuario(id_usuario, nome_usuario, email_usuario, login_usuario, senha_usuario) VALUES
(1, 'Ana Maria', 'anamaria@email.com.br', 'anamaria', '1234'),
(2, 'Manuela', 'manuela@email.com.br', 'manuela', '1234');

INSERT INTO tb_musica(id_musica, nome_musica) VALUES
(1, 'Crossroad Blues'),
(2, 'Manish Boy'),
(3, 'Spoonful'),
(4, 'Baby Please Don''t Go'),
(5, 'Keep It To Yourself'),
(6, 'Smoke on the Water'),
(7, 'Layla'),
(8, 'Black Dog'),
(9, 'Kashmir'),
(10, 'Sweet Home Alabama'),
(11, 'Giant Steps'),
(12, 'Take 5'),
(13, 'April in Paris'),
(14, 'Autumn Leaves'),
(15, 'Meaning and Mistery'),
(16, 'Feeling Good');

INSERT INTO tb_genero(id_genero, nome_genero) VALUES
(1, 'Blues'),
(2, 'Rock'),
(3, 'Jazz');

INSERT INTO tb_musica_genero(id_musica, id_genero) VALUES
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
(16, 3);
