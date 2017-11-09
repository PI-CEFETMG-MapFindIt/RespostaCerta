/*
Created: 29/06/2017
Modified: 29/06/2017
Model: PostgreSQL 9.4
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- Table MensagemResposta

CREATE TABLE MensagemResposta(
 idMensagemResposta SERIAL,
 idMensagem Integer NOT NULL,
 idUsuario Integer NOT NULL,
 mensagem Text NOT NULL,
 dataResposta Timestamp NOT NULL
)
;

-- Add keys for table MensagemResposta

ALTER TABLE MensagemResposta ADD CONSTRAINT pk_MensagemResposta PRIMARY KEY (idMensagemResposta)
;

-- Table RespostaFechada

CREATE TABLE RespostaFechada(
 idResposta Integer NOT NULL,
 resposta Integer NOT NULL
)
;

-- Add keys for table RespostaFechada

ALTER TABLE RespostaFechada ADD CONSTRAINT pk_RespostaFechada PRIMARY KEY (idResposta)
;

-- Table RespostaAberta

CREATE TABLE RespostaAberta(
 idResposta Integer NOT NULL,
 resposta Text NOT NULL
)
;

-- Add keys for table RespostaAberta

ALTER TABLE RespostaAberta ADD CONSTRAINT pk_RespostaAberta PRIMARY KEY (idResposta)
;

-- Table Resposta

CREATE TABLE Resposta(
 idResposta SERIAL,
 idUsuario Integer NOT NULL,
 idQuestao Integer NOT NULL,
 idtResposta Character(1) NOT NULL,
 dataResposta Date NOT NULL
)
;

-- Add keys for table Resposta

ALTER TABLE Resposta ADD CONSTRAINT pk_Resposta PRIMARY KEY (idResposta)
;

-- Table Mensagem

CREATE TABLE Mensagem(
 idMensagem SERIAL,
 idQuestao Integer NOT NULL,
 idUsuario Integer NOT NULL,
 mensagem Text NOT NULL,
 dataPost Timestamp NOT NULL,
 image Bytea
)
;

-- Add keys for table Mensagem

ALTER TABLE Mensagem ADD CONSTRAINT pk_Mensagem PRIMARY KEY (idMensagem)
;

-- Table Forum

CREATE TABLE Forum(
 idQuestao Integer NOT NULL,
 dataCriacao Timestamp NOT NULL,
 status Boolean NOT NULL
)
;
COMMENT ON COLUMN Forum.status IS '0 - bloqueado, 1 - disponivel'
;

-- Add keys for table Forum

ALTER TABLE Forum ADD CONSTRAINT pk_Forum PRIMARY KEY (idQuestao)
;

-- Table Modulo

CREATE TABLE Modulo(
 idModulo SERIAL,
 idDominio Integer NOT NULL,
 nomeModulo Character(50) NOT NULL
)
;

-- Add keys for table Modulo

ALTER TABLE Modulo ADD CONSTRAINT pk_Modulo PRIMARY KEY (idModulo)
;

ALTER TABLE Modulo ADD CONSTRAINT idModulo UNIQUE (idModulo)
;

-- Table QuestaoFechada

CREATE TABLE QuestaoFechada(
 idQuestao Integer NOT NULL,
 alt1 Text NOT NULL,
 alt2 Text NOT NULL,
 alt3 Text NOT NULL,
 alt4 Text NOT NULL,
 alt5 Text NOT NULL,
 altCorreta Integer NOT NULL
)
;
COMMENT ON COLUMN QuestaoFechada.altCorreta IS 'Indica qual alternativa é a correta'
;

-- Add keys for table QuestaoFechada

ALTER TABLE QuestaoFechada ADD CONSTRAINT pk_QuestaoFechada PRIMARY KEY (idQuestao)
;

-- Table Dominio

CREATE TABLE Dominio(
 idDominio SERIAL,
 nomeDominio Character(50) NOT NULL
)
;

-- Add keys for table Dominio

ALTER TABLE Dominio ADD CONSTRAINT pk_Dominio PRIMARY KEY (idDominio)
;

ALTER TABLE Dominio ADD CONSTRAINT idDominio UNIQUE (idDominio)
;

-- Table Questao

CREATE TABLE Questao(
 idQuestao SERIAL,
 idModulo Integer NOT NULL,
 idUsuarioCriador Integer NOT NULL,
 enunciadoQuestao Text NOT NULL,
 idtQuestao Boolean NOT NULL,
 dataCriacao Timestamp NOT NULL,
 tituloQuestao Character(255),
 questPhoto Bytea,
 idtDificuldade Character(1) NOT NULL
)
;
COMMENT ON COLUMN Questao.idtQuestao IS '0 - fechada, 1 - aberta'
;

-- Add keys for table Questao

ALTER TABLE Questao ADD CONSTRAINT pk_Questao PRIMARY KEY (idQuestao)
;

ALTER TABLE Questao ADD CONSTRAINT idQuestao UNIQUE (idQuestao)
;

-- Table Usuario

CREATE TABLE Usuario(
 idUsuario SERIAL,
 nomeUsuario Character(50) NOT NULL,
 loginUsuario Character(50) NOT NULL UNIQUE,
 senhaUsuario Character(40) NOT NULL,
 idtUsuario Character(1) NOT NULL,
 userPhoto Bytea
)
;
COMMENT ON COLUMN Usuario.idtUsuario IS 'G - Gestor, P - Professor, A - aluno (aprendiz)'
;

-- Add keys for table Usuario

ALTER TABLE Usuario ADD CONSTRAINT pk_Usuario PRIMARY KEY (idUsuario)
;

ALTER TABLE Usuario ADD CONSTRAINT idUsuario UNIQUE (idUsuario)
;

ALTER TABLE Usuario ADD CONSTRAINT loginUsuario UNIQUE (loginUsuario)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE RespostaFechada ADD CONSTRAINT Relationship41 FOREIGN KEY (idResposta) REFERENCES Resposta (idResposta) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE RespostaAberta ADD CONSTRAINT Relationship40 FOREIGN KEY (idResposta) REFERENCES Resposta (idResposta) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE MensagemResposta ADD CONSTRAINT Relationship27 FOREIGN KEY (idMensagem) REFERENCES Mensagem (idMensagem) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Mensagem ADD CONSTRAINT Relationship23 FOREIGN KEY (idQuestao) REFERENCES Forum (idQuestao) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Questao ADD CONSTRAINT Relationship7 FOREIGN KEY (idModulo) REFERENCES Modulo (idModulo) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Modulo ADD CONSTRAINT Relationship5 FOREIGN KEY (idDominio) REFERENCES Dominio (idDominio) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Resposta ADD CONSTRAINT Relationship38 FOREIGN KEY (idQuestao) REFERENCES Questao (idQuestao) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Forum ADD CONSTRAINT Relationship16 FOREIGN KEY (idQuestao) REFERENCES Questao (idQuestao) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE QuestaoFechada ADD CONSTRAINT Relationship1 FOREIGN KEY (idQuestao) REFERENCES Questao (idQuestao) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Resposta ADD CONSTRAINT Relationship37 FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE MensagemResposta ADD CONSTRAINT Relationship25 FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Mensagem ADD CONSTRAINT Relationship24 FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE Questao ADD CONSTRAINT Relationship12 FOREIGN KEY (idUsuarioCriador) REFERENCES Usuario (idUsuario) ON DELETE CASCADE ON UPDATE CASCADE
;