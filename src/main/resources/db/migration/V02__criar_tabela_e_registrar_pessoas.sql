CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(50),
    estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo) values ('Denilson Alves', true);
INSERT INTO pessoa (nome, ativo) values ('Guilherme Alves', true);
INSERT INTO pessoa (nome, ativo) values ('João Francisco', true);
INSERT INTO pessoa (nome, ativo) values ('Laudelina Rocha de Almeida', true);
INSERT INTO pessoa (nome, ativo) values ('José Luiz', true);
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Marcos Mendonça', true, 'Rua Antonio Ribeiro de Souza', '332', 'Condominio Cidade Verde', 'Colorado', 'Paraná');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Vaniele Fernanda', true, 'Rua Antonio Ribeiro de Souza', '332', 'Condominio Cidade Verde', 'Colorado', 'Paraná');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Marcela Mendonça', true, 'Rua Antonio Ribeiro de Souza', '332', 'Condominio Cidade Verde', 'Colorado', 'Paraná');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Antonina Mendonça', true, 'Rua Antonio Ribeiro de Souza', '332', 'Condominio Cidade Verde', 'Colorado', 'Paraná');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cidade, estado) values ('Erenilda Bassi', true, 'Rua dos Escoteiros', '70', 'Jardim Cairi', 'Alto Alegre', 'Paraná');