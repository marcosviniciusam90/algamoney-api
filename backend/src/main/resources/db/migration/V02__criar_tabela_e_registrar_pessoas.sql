CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(14),
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(50),
    estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Adenilson Alves', '321.665.720-57', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 'Uberlândia', 'MG', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Vaniele Fernanda', '543.780.030-40', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 'Ribeirão Preto', 'SP', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Guilherme Souza', '524.058.470-27', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 'Goiânia', 'GO', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Erenilsa Bassi dos Reis', '750.981.790-09', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Natália Fortes', '625.196.560-69', 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 'Natal', 'RN', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Marcos Vinícius de Almeida Mendonça', '999.569.850-15', 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 'Porto Alegre', 'RS', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Marcela de Almeida Mendonça', '340.791.340-02', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 'Rio de Janeiro', 'RJ', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Antonina Rocha de Almeida Mendonça', '351.127.790-63', 'Rua da Manga', '433', null, 'Centro', '31.400-121', 'Belo Horizonte', 'MG', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Marcos Antonio Mendonça', null, 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO pessoa (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Silvia Almeida', '716.994.390-52', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', 'Manaus', 'AM', true);