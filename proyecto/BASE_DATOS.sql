drop database icaiproyecto;
create database icaiproyecto;
use icaiproyecto;

-- DROP TABLE IMAGENES_USUARIOS;
-- DROP TABLE COMENTARIOS;
-- DROP TABLE SESIONES;
-- DROP TABLE USUARIOS;

CREATE TABLE IF NOT EXISTS USUARIOS (
   EMAIL VARCHAR(100) PRIMARY KEY,
   NAME VARCHAR(20),
   TYPE INT DEFAULT 1,
   IMAGEN VARCHAR(100),
   PASSWORD BLOB
) ENGINE = INNODB ROW_FORMAT = DEFAULT;

CREATE TABLE SESIONES(
   ID INT PRIMARY KEY AUTO_INCREMENT,
   USUARIO_ID VARCHAR(60),
   FECHA DATETIME  DEFAULT CURRENT_TIMESTAMP,
   STATUS INT DEFAULT 1, -- O OFFLINE, 1 ACTIVO, 2 OCUPADO
  CONSTRAINT FK_USUARIO_S FOREIGN KEY (USUARIO_ID) REFERENCES
  USUARIOS (EMAIL) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB ROW_FORMAT = DEFAULT;

CREATE TABLE COMENTARIOS (
   COMENTARIO_ID INT PRIMARY KEY AUTO_INCREMENT,
   DESCRIPCION LONGTEXT ASCII,
   FECHA DATETIME  DEFAULT CURRENT_TIMESTAMP,
   USUARIO_ID VARCHAR(60),
   TIPO_MSJ INT DEFAULT 1, -- O privado, 1 publico
  CONSTRAINT FK_USER FOREIGN KEY (USUARIO_ID) REFERENCES
  USUARIOS (EMAIL) ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB ROW_FORMAT = DEFAULT;
  
CREATE TABLE COMENTARIOS_PERSONALES (
   COMENTARIO_ID INT PRIMARY KEY AUTO_INCREMENT,
   DESCRIPCION LONGTEXT ASCII,
   FECHA DATETIME  DEFAULT CURRENT_TIMESTAMP,
   USUARIO_DE VARCHAR(60),
   USUARIO_PARA VARCHAR(60),
   TIPO_MSJ INT DEFAULT 0, -- O privado, 1 publico
  CONSTRAINT FK_USER_DE FOREIGN KEY (USUARIO_DE) REFERENCES
  USUARIOS (EMAIL) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT FK_USER_PARA FOREIGN KEY (USUARIO_PARA) REFERENCES
  USUARIOS (EMAIL) ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB ROW_FORMAT = DEFAULT;
  
CREATE TABLE IMAGENES_USUARIOS(	  
	  NOMBRE_IMAGEN VARCHAR(60),
	  USUARIO VARCHAR(60),
	  URL VARCHAR(100),
	  PRIMARY KEY(NOMBRE_IMAGEN),
	  FOREIGN KEY(USUARIO) REFERENCES USUARIOS(EMAIL)
);
SELECT * FROM sesiones;
truncate sesiones;