CREATE TABLE produto(
    sku                 VARCHAR(12)     NOT NULL PRIMARY KEY ,
    codigo_de_barras    VARCHAR(13)     NOT NULL,
    nome                VARCHAR(50)     NOT NULL,
    preco               NUMERIC(8,2)    NOT NULL,
    categoria           VARCHAR(20)     NOT NULL,
    descricao           VARCHAR(300)    NULL,
    fabricante          VARCHAR(100)    NULL
);