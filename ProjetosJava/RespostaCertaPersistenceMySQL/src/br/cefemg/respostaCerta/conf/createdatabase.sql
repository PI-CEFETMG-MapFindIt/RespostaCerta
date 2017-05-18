-- Create tables section -------------------------------------------------

-- Table Usuario

CREATE TABLE `Usuario`
(
  `idUsuario` Int NOT NULL,
  `nomeUsuario` Char(50) NOT NULL,
  `loginUsuario` Char(50) NOT NULL,
  `senhaUsuario` Char(20) NOT NULL,
  `idtUsuario` Char(1) NOT NULL
   COMMENT '"G" - Gestor, "P" - Professor, "A" - aluno (aprendiz)',
  `idCidade` Int NOT NULL,
  `idUF` Int NOT NULL,
  `userPhoto` Blob
)
;

ALTER TABLE `Usuario` ADD  PRIMARY KEY (`idUsuario`)
;

ALTER TABLE `Usuario` ADD UNIQUE `idUsuario` (`idUsuario`)
;

ALTER TABLE `Usuario` ADD UNIQUE `loginUsuario` (`loginUsuario`)
;

-- Table Questao

CREATE TABLE `Questao`
(
  `idQuestao` Int NOT NULL,
  `idModulo` Int NOT NULL,
  `idDominio` Int NOT NULL,
  `idUsuarioCriador` Int NOT NULL,
  `enunciadoQuestao` Text NOT NULL,
  `idtQuestao` Bool NOT NULL
 COMMENT '"0" - fechada, "1" - aberta',
  `dataCriacao` Datetime NOT NULL,
  `tituloQuestao` Tinytext,
  `questPhoto` Blob
)
;

ALTER TABLE `Questao` ADD  PRIMARY KEY (`idQuestao`)
;

ALTER TABLE `Questao` ADD UNIQUE `idQuestao` (`idQuestao`)
;

-- Table Dominio

CREATE TABLE `Dominio`
(
  `idDominio` Int NOT NULL,
  `nomeDominio` Char(50) NOT NULL,
  `descDominio` Tinytext
)
;

ALTER TABLE `Dominio` ADD  PRIMARY KEY (`idDominio`)
;

ALTER TABLE `Dominio` ADD UNIQUE `idDominio` (`idDominio`)
;

-- Table QuestaoFechada

CREATE TABLE `QuestaoFechada`
(
  `idQuestao` Int NOT NULL,
  `alt1` Tinytext NOT NULL,
  `alt2` Tinytext NOT NULL,
  `alt3` Tinytext NOT NULL,
  `alt4` Tinytext NOT NULL,
  `alt5` Tinytext NOT NULL,
  `altCorreta` Int NOT NULL
 COMMENT 'Indica qual alternativa é a correta'
)
;

ALTER TABLE `QuestaoFechada` ADD  PRIMARY KEY (`idQuestao`)
;

-- Table Modulo

CREATE TABLE `Modulo`
(
  `idDominio` Int NOT NULL,
  `idModulo` Int NOT NULL,
  `nomeModulo` Char(50) NOT NULL,
  `descModulo` Tinytext
)
;

ALTER TABLE `Modulo` ADD  PRIMARY KEY (`idDominio`,`idModulo`)
;

ALTER TABLE `Modulo` ADD UNIQUE `idModulo` (`idModulo`)
;

-- Table Forum

CREATE TABLE `Forum`
(
  `idQuestao` Int NOT NULL,
  `dataCriacao` Datetime NOT NULL,
  `status` Bool NOT NULL
 COMMENT '"0" - bloqueado, "1" - disponivel'
)
;

ALTER TABLE `Forum` ADD  PRIMARY KEY (`idQuestao`)
;

-- Table Mensagem

CREATE TABLE `Mensagem`
(
  `idQuestao` Int NOT NULL,
  `idMensagem` Int NOT NULL,
  `idUsuario` Int NOT NULL,
  `mensagem` Text NOT NULL,
  `dataPost` Datetime NOT NULL,
  `image` Blob
)
;

ALTER TABLE `Mensagem` ADD  PRIMARY KEY (`idQuestao`,`idMensagem`)
;

-- Table MensagemResposta

CREATE TABLE `MensagemResposta`
(
  `idMensagem` Int NOT NULL,
  `idQuestao` Int NOT NULL,
  `idMensagemResposta` Int NOT NULL,
  `idUsuario` Int NOT NULL,
  `mensagem` Text NOT NULL,
  `dataResposta` Datetime NOT NULL
)
;

ALTER TABLE `MensagemResposta` ADD  PRIMARY KEY (`idMensagem`,`idQuestao`,`idMensagemResposta`)
;

-- Table UF

CREATE TABLE `UF`
(
  `idUF` Int NOT NULL,
  `nomeUF` Char(30) NOT NULL
)
;

ALTER TABLE `UF` ADD  PRIMARY KEY (`idUF`)
;

ALTER TABLE `UF` ADD UNIQUE `idUF` (`idUF`)
;

-- Table Cidade

CREATE TABLE `Cidade`
(
  `idCidade` Int NOT NULL,
  `nomeCidade` Char(30) NOT NULL,
  `idUF` Int NOT NULL
)
;

ALTER TABLE `Cidade` ADD  PRIMARY KEY (`idCidade`,`idUF`)
;

ALTER TABLE `Cidade` ADD UNIQUE `idCidade` (`idCidade`)
;

-- Table Resposta

CREATE TABLE `Resposta`
(
  `idUsuario` Int NOT NULL,
  `idQuestao` Int NOT NULL,
  `dataResposta` Datetime NOT NULL,
  `idtResposta` Char(1) NOT NULL
)
;

ALTER TABLE `Resposta` ADD  PRIMARY KEY (`idUsuario`,`idQuestao`,`dataResposta`)
;

-- Table RespostaAberta

CREATE TABLE `RespostaAberta`
(
  `resposta` Text NOT NULL,
  `idUsuario` Int NOT NULL,
  `idQuestao` Int NOT NULL,
  `dataResposta` Datetime NOT NULL
)
;

ALTER TABLE `RespostaAberta` ADD  PRIMARY KEY (`idUsuario`,`idQuestao`,`dataResposta`)
;

-- Table RespostaFechada

CREATE TABLE `RespostaFechada`
(
  `idUsuario` Int NOT NULL,
  `idQuestao` Int NOT NULL,
  `dataResposta` Datetime NOT NULL,
  `resposta` Int NOT NULL,
  `boolCorreta` Bool NOT NULL
)
;

ALTER TABLE `RespostaFechada` ADD  PRIMARY KEY (`idUsuario`,`idQuestao`,`dataResposta`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `Questao` ADD CONSTRAINT `Relationship12` FOREIGN KEY (`idUsuarioCriador`) REFERENCES `Usuario` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship24` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MensagemResposta` ADD CONSTRAINT `Relationship25` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Resposta` ADD CONSTRAINT `Relationship37` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `QuestaoFechada` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`idQuestao`) REFERENCES `Questao` (`idQuestao`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Forum` ADD CONSTRAINT `Relationship16` FOREIGN KEY (`idQuestao`) REFERENCES `Questao` (`idQuestao`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Resposta` ADD CONSTRAINT `Relationship38` FOREIGN KEY (`idQuestao`) REFERENCES `Questao` (`idQuestao`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Modulo` ADD CONSTRAINT `Relationship5` FOREIGN KEY (`idDominio`) REFERENCES `Dominio` (`idDominio`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Questao` ADD CONSTRAINT `Relationship7` FOREIGN KEY (`idDominio`, `idModulo`) REFERENCES `Modulo` (`idDominio`, `idModulo`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship23` FOREIGN KEY (`idQuestao`) REFERENCES `Forum` (`idQuestao`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MensagemResposta` ADD CONSTRAINT `Relationship27` FOREIGN KEY (`idQuestao`, `idMensagem`) REFERENCES `Mensagem` (`idQuestao`, `idMensagem`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Cidade` ADD CONSTRAINT `Relationship35` FOREIGN KEY (`idUF`) REFERENCES `UF` (`idUF`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship36` FOREIGN KEY (`idCidade`, `idUF`) REFERENCES `Cidade` (`idCidade`, `idUF`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `RespostaAberta` ADD CONSTRAINT `Relationship40` FOREIGN KEY (`idUsuario`, `idQuestao`, `dataResposta`) REFERENCES `Resposta` (`idUsuario`, `idQuestao`, `dataResposta`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `RespostaFechada` ADD CONSTRAINT `Relationship41` FOREIGN KEY (`idUsuario`, `idQuestao`, `dataResposta`) REFERENCES `Resposta` (`idUsuario`, `idQuestao`, `dataResposta`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

