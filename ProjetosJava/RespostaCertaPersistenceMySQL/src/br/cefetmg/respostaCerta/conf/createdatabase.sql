-- Create tables section -------------------------------------------------

-- Table Usuario

CREATE TABLE `Usuario`
(
  `idUsuario` Int NOT NULL AUTO_INCREMENT,
  `nomeUsuario` Char(50) NOT NULL,
  `loginUsuario` Char(50) NOT NULL,
  `senhaUsuario` Char(20) NOT NULL,
  `idtUsuario` Char(1) NOT NULL
 COMMENT '"G" - Gestor, "P" - Professor, "A" - aluno (aprendiz)',
  `userPhoto` Blob,
 UNIQUE `idUsuario` (`idUsuario`)
)
;

ALTER TABLE `Usuario` ADD UNIQUE `loginUsuario` (`loginUsuario`)
;

-- Table Questao

CREATE TABLE `Questao`
(
  `idQuestao` Int NOT NULL AUTO_INCREMENT,
  `idModulo` Int NOT NULL,
  `idUsuarioCriador` Int NOT NULL,
  `enunciadoQuestao` Text NOT NULL,
  `idtQuestao` Bool NOT NULL
 COMMENT '"0" - fechada, "1" - aberta',
  `dataCriacao` Datetime NOT NULL,
  `tituloQuestao` Tinytext,
  `questPhoto` Blob,
  PRIMARY KEY (`idQuestao`),
 UNIQUE `idQuestao` (`idQuestao`)
)
;

-- Table Dominio

CREATE TABLE `Dominio`
(
  `idDominio` Int NOT NULL AUTO_INCREMENT,
  `nomeDominio` Char(50) NOT NULL,
  `descDominio` Tinytext,
  PRIMARY KEY (`idDominio`),
 UNIQUE `idDominio` (`idDominio`)
)
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
  `idModulo` Int NOT NULL AUTO_INCREMENT,
  `idDominio` Int NOT NULL,
  `nomeModulo` Char(50) NOT NULL,
  `descModulo` Tinytext,
  PRIMARY KEY (`idModulo`),
 UNIQUE `idModulo` (`idModulo`)
)
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
  `idMensagem` Int NOT NULL AUTO_INCREMENT,
  `idQuestao` Int NOT NULL,
  `idUsuario` Int NOT NULL,
  `mensagem` Text NOT NULL,
  `dataPost` Datetime NOT NULL,
  `image` Blob,
  PRIMARY KEY (`idMensagem`)
)
;

-- Table MensagemResposta

CREATE TABLE `MensagemResposta`
(
  `idMensagemResposta` Int NOT NULL AUTO_INCREMENT,
  `idMensagem` Int NOT NULL,
  `idUsuario` Int NOT NULL,
  `mensagem` Text NOT NULL,
  `dataResposta` Datetime NOT NULL,
  PRIMARY KEY (`idMensagemResposta`)
)
;

-- Table Resposta

CREATE TABLE `Resposta`
(
  `idResposta` Int NOT NULL AUTO_INCREMENT,
  `idUsuario` Int NOT NULL,
  `idQuestao` Int NOT NULL,
  `idtResposta` Char(1) NOT NULL,
  `dataResposta` Date NOT NULL,
  PRIMARY KEY (`idResposta`)
)
;

-- Table RespostaAberta

CREATE TABLE `RespostaAberta`
(
  `idResposta` Int NOT NULL,
  `resposta` Text NOT NULL
)
;

ALTER TABLE `RespostaAberta` ADD  PRIMARY KEY (`idResposta`)
;

-- Table RespostaFechada

CREATE TABLE `RespostaFechada`
(
  `idResposta` Int NOT NULL,
  `resposta` Int NOT NULL
)
;

ALTER TABLE `RespostaFechada` ADD  PRIMARY KEY (`idResposta`)
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

ALTER TABLE `Questao` ADD CONSTRAINT `Relationship7` FOREIGN KEY (`idModulo`) REFERENCES `Modulo` (`idModulo`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Mensagem` ADD CONSTRAINT `Relationship23` FOREIGN KEY (`idQuestao`) REFERENCES `Forum` (`idQuestao`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MensagemResposta` ADD CONSTRAINT `Relationship27` FOREIGN KEY (`idMensagem`) REFERENCES `Mensagem` (`idMensagem`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `RespostaAberta` ADD CONSTRAINT `Relationship40` FOREIGN KEY (`idResposta`) REFERENCES `Resposta` (`idResposta`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `RespostaFechada` ADD CONSTRAINT `Relationship41` FOREIGN KEY (`idResposta`) REFERENCES `Resposta` (`idResposta`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

