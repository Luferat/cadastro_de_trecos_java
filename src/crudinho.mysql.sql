DROP DATABASE IF EXISTS crudinho;
CREATE DATABASE crudinho CHARACTER SET utf8 COLLATE utf8_general_ci;
USE crudinho;

CREATE TABLE trecos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nome VARCHAR(63) NOT NULL,
    descricao VARCHAR(127) NOT NULL,
    localizacao VARCHAR(127) NOT NULL,
    status ENUM('0', '1', '2') DEFAULT '2'
);

INSERT INTO trecos (nome, descricao, localizacao, status) VALUES
('Pelo de iguana', 'Pelinhos bem pequenos e amarelados.', 'Pote de vidro no armário', '2'),
('Perna de cobra', 'Grande e forte, permitindo correr muito.', 'Canudo de papel-toalha na cozinha', '1'),
('Orelha de pernilongo', 'Não escuta bem. Só tem zumbido.', 'Caixa de sapatos sobre o guarda-roupas', '2'),
('Bigode de sapo', 'Chique, fashion e másculo.', 'Dentro da máquina de lavar', '0'),
('Olhos de cobra cega', 'Ainda não vimos um melhor.', 'No estojo onde guardam-se os óculos', '2');