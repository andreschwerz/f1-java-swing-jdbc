/**
 * Author:  André Luis Schwerz
 */

DROP TABLE IF EXISTS PILOTO;
DROP TABLE IF EXISTS EQUIPE;
DROP TABLE IF EXISTS PAIS;

CREATE TABLE PAIS(
   sigla CHAR(3),
   nome VARCHAR(100),
   CONSTRAINT pk_pais PRIMARY KEY(sigla)
);

CREATE TABLE EQUIPE(
	id INTEGER AUTO_INCREMENT,
    nome VARCHAR(100),
    diretor VARCHAR(100),
    pais_sigla CHAR(3),
    status BOOLEAN,
    CONSTRAINT pk_equipe PRIMARY KEY(id),
    CONSTRAINT fk_pais_equipe FOREIGN KEY(pais_sigla) REFERENCES PAIS(sigla)
);

CREATE TABLE PILOTO(
	id INTEGER AUTO_INCREMENT,
    nome VARCHAR(100),
    pais_sigla CHAR(3),
    equipe_id INTEGER,
    status BOOLEAN,
    CONSTRAINT pk_piloto PRIMARY KEY(id),
    CONSTRAINT fk_pais_piloto FOREIGN KEY(pais_sigla) REFERENCES PAIS(sigla),
    CONSTRAINT fk_equipe_piloto FOREIGN KEY(equipe_id) REFERENCES EQUIPE(id)
);

 
INSERT INTO PAIS(sigla, nome) VALUES ('AUS','Austrália'), ('AUT','Áustria'), ('BEL','Bélgica'), ('BRA','Brasil'), ('CAN','Canadá'), ('CHI','Chile'), ('DEN','Dinamarca'), ('ESP','Espanha'), ('FIN','Finlândia'), ('FRA','França'), ('GBR','Grã-Bretanha'), ('GER','Alemanha'), ('IND', 'Índia'), ('ITA', 'Itália'), ('MEX', 'México'), ('MON', 'Mónaco'), ('NED', 'Países Baixos'), ('NZL', 'Nova Zelândia'), ('RUS', 'Rússia'), ('SUI', 'Suíça'), ('SWE', 'Suécia'), ('USA','Estados Unidos');
INSERT INTO EQUIPE(nome, diretor, pais_sigla, status) VALUES 
('Scuderia Ferrari',                 '','ITA',1), 
('Sahara Force India F1 Team',       '','IND',1), 
('Haas F1 Team',                     '','USA',1),
('McLaren F1 Team',                  '','GBR',1), 
('Mercedes AMG Petronas Motorsport', '','GER',1), 
('Aston Martin Red Bull Racing',     '','AUT',1), 
('Renault Sport Formula One Team',   '','FRA',1),
('Alfa Romeo Sauber F1 Team',        '','SUI',1), 
('Red Bull Toro Rosso Honda',        '','ITA',1), 
('Williams Martini Racing',          '','GBR',1);
INSERT INTO PILOTO(nome, pais_sigla, equipe_id, status) VALUES
('Sebastian Vettel' ,'GER',1,1), 
('Kimi Räikkönen'   ,'FIN',1,1), 
('Sergio Pérez'     ,'MEX',2,1), 
('Esteban Ocon'     ,'FRA',2,1), 
('Romain Grosjean'  ,'FRA',3,1), 
('Kevin Magnussen'  ,'DEN',3,1), 
('Stoffel Vandoorne','BEL',4,1), 
('Fernando Alonso'  ,'ESP',4,1), 
('Lewis Hamilton'   ,'GBR',5,1), 
('Valtteri Bottas'  ,'FIN',5,1), 
('Daniel Ricciardo' ,'AUS',6,1), 
('Max Verstappen'   ,'NED',6,1), 
('Nico Hülkenberg'  ,'GER',7,1), 
('Carlos Sainz Jr.' ,'ESP',7,1), 
('Marcus Ericsson'  ,'SWE',8,1), 
('Marcus Ericsson'  ,'MON',8,1), 
('Pierre Gasly'     ,'FRA',9,1), 
('Brendon Hartley'  ,'NZL',9,1), 
('Lance Stroll'     ,'CAN',10,1), 
('Sergey Sirotkin'  ,'RUS',10,1);