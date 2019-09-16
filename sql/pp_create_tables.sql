
-- java -jar schemaSpy_5.0.0.jar -t pgsql -s box -db box -u postgres -host localhost -o D:\dev\ambientes\box\workspace\Base\sql\schemaSpy\salida -dp postgresql-9.4.1212.jar -gv D:\programas\ATT\Graphviz

-- CREATE EXTENSION "uuid-ossp";
-- SELECT uuid_generate_v4();

-- DROP SCHEMA IF EXISTS box CASCADE;

-- CREATE SCHEMA box AUTHORIZATION boxroot;	

-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- =======================																				=====================
-- =======================				FUNCIONES UTILES												=====================	
-- =======================																				=====================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================

DROP FUNCTION IF EXISTS box.random_between (low INT ,high INT) CASCADE;

CREATE OR REPLACE FUNCTION box.random_between (low INT ,high INT) 
   RETURNS INT AS
$$
BEGIN
   RETURN floor(random()* (high-low + 1) + low);
END;
$$ language 'plpgsql' STRICT;


-- SELECT box.random_between(1, 100);

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.white_is_null (att_val VARCHAR) CASCADE;

CREATE OR REPLACE FUNCTION box.white_is_null(att_val VARCHAR) RETURNS VARCHAR AS $$
BEGIN
	IF CHAR_LENGTH(TRIM(att_val)) = 0 THEN
	
		RETURN null::VARCHAR;
	END IF;

	RETURN TRIM(att_val)::VARCHAR;
		
END;
$$  LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.zero_is_null (att_val INTEGER) CASCADE;

CREATE OR REPLACE FUNCTION box.zero_is_null(att_val INTEGER) RETURNS INTEGER AS $$
BEGIN
	IF att_val = 0 THEN
	
		RETURN null::INTEGER;
	END IF;

	RETURN att_val::INTEGER;
		
END;
$$  LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.zero_is_null (att_val BIGINT) CASCADE;

CREATE OR REPLACE FUNCTION box.zero_is_null(att_val BIGINT) RETURNS BIGINT AS $$
BEGIN
	IF att_val = 0 THEN
	
		RETURN null::BIGINT;
	END IF;

	RETURN att_val::BIGINT;
		
END;
$$  LANGUAGE plpgsql;


-- ---------------------------------------------------------------------------------------------------------------------------

-- SELECT TRANSLATE('12345', '134', 'ax')

DROP FUNCTION IF EXISTS box.translate_from () CASCADE;

CREATE OR REPLACE FUNCTION box.translate_from() RETURNS VARCHAR AS $$
BEGIN
	
	RETURN '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'::VARCHAR;
		
END;
$$  LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

-- SELECT box.traslate_from();

DROP FUNCTION IF EXISTS box.translate_to () CASCADE;

CREATE OR REPLACE FUNCTION box.translate_to() RETURNS VARCHAR AS $$
BEGIN
	
	RETURN '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN'::VARCHAR;
		
END;
$$  LANGUAGE plpgsql;

-- SELECT box.traslate_to();

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.translate (att_val VARCHAR) CASCADE;

CREATE OR REPLACE FUNCTION box.translate(att_val VARCHAR) RETURNS VARCHAR AS $$
BEGIN
	IF CHAR_LENGTH(TRIM(att_val)) = 0 THEN
	
		RETURN null::VARCHAR;
	END IF;

	RETURN TRANSLATE(att_val, box.translate_from (), box.translate_to())::VARCHAR;
		
END;
$$  LANGUAGE plpgsql;

-- SELECT box.translate('1234567890' || box.translate_from());


-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- =======================																				=====================
-- =======================							TABLAS												=====================	
-- =======================																				=====================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================


-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- //                                                                                                                        //
-- //          TABLA: MimeType                                                                                               //
-- //                                                                                                                        //
-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


-- Table: box.MimeType

-- ---------------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS box.MimeType CASCADE;

CREATE TABLE box.MimeType
(
	id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
	
	-- Code
	code VARCHAR(3) NOT NULL, 
	
	-- Name
	name VARCHAR(50) NOT NULL, 
	
	-- File extension
	fileExtension VARCHAR(5) NOT NULL
);

-- ---------------------------------------------------------------------------------------------------------------------------


CREATE UNIQUE INDEX u_MimeType_code ON box.MimeType (TRANSLATE(LOWER(TRIM(code))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

CREATE UNIQUE INDEX u_MimeType_name ON box.MimeType (TRANSLATE(LOWER(TRIM(name))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

CREATE UNIQUE INDEX u_MimeType_fileExtension ON box.MimeType (TRANSLATE(LOWER(TRIM(fileExtension))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.ftgFormatMimeType() CASCADE;

CREATE OR REPLACE FUNCTION box.ftgFormatMimeType() RETURNS TRIGGER AS $formatMimeType$
DECLARE
BEGIN
	 NEW.id := box.white_is_null(NEW.id);
	 NEW.code := box.white_is_null(NEW.code);
	 NEW.name := box.white_is_null(NEW.name);
	 NEW.fileExtension := box.white_is_null(NEW.fileExtension);

	RETURN NEW;
END;
$formatMimeType$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatMimeType ON box.MimeType CASCADE;

CREATE TRIGGER tgFormatMimeType BEFORE INSERT OR UPDATE
	ON box.MimeType FOR EACH ROW
	EXECUTE PROCEDURE box.ftgFormatMimeType();

-- ---------------------------------------------------------------------------------------------------------------------------



-- SELECT COUNT(*) FROM box.MimeType;

-- SELECT * FROM box.MimeType LIMIT 100 OFFSET 0;

-- SELECT * FROM box.MimeType;

-- SELECT * FROM box.MimeType WHERE id = 'xxx';


-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- //                                                                                                                        //
-- //          TABLA: Store                                                                                                  //
-- //                                                                                                                        //
-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


-- Table: box.Store

-- ---------------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS box.Store CASCADE;

CREATE TABLE box.Store
(
	id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
	
	-- Code
	code VARCHAR(3) NOT NULL, 
	
	-- Name
	name VARCHAR(50) NOT NULL, 
	
	-- URL
	url VARCHAR(5) NOT NULL
);

-- ---------------------------------------------------------------------------------------------------------------------------


CREATE UNIQUE INDEX u_Store_code ON box.Store (TRANSLATE(LOWER(TRIM(code))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

CREATE UNIQUE INDEX u_Store_name ON box.Store (TRANSLATE(LOWER(TRIM(name))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

CREATE UNIQUE INDEX u_Store_url ON box.Store (TRANSLATE(LOWER(TRIM(url))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.ftgFormatStore() CASCADE;

CREATE OR REPLACE FUNCTION box.ftgFormatStore() RETURNS TRIGGER AS $formatStore$
DECLARE
BEGIN
	 NEW.id := box.white_is_null(NEW.id);
	 NEW.code := box.white_is_null(NEW.code);
	 NEW.name := box.white_is_null(NEW.name);
	 NEW.url := box.white_is_null(NEW.url);

	RETURN NEW;
END;
$formatStore$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatStore ON box.Store CASCADE;

CREATE TRIGGER tgFormatStore BEFORE INSERT OR UPDATE
	ON box.Store FOR EACH ROW
	EXECUTE PROCEDURE box.ftgFormatStore();

-- ---------------------------------------------------------------------------------------------------------------------------



-- SELECT COUNT(*) FROM box.Store;

-- SELECT * FROM box.Store LIMIT 100 OFFSET 0;

-- SELECT * FROM box.Store;

-- SELECT * FROM box.Store WHERE id = 'xxx';


-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- //                                                                                                                        //
-- //          TABLA: Content                                                                                                //
-- //                                                                                                                        //
-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


-- Table: box.Content

-- ---------------------------------------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS box.Content CASCADE;

CREATE TABLE box.Content
(
	id VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4(),
	
	-- Code
	code VARCHAR(3) NOT NULL, 
	
	-- Name
	name VARCHAR(50) NOT NULL, 
	
	-- Kilo size
	kiloSize DOUBLE PRECISION, 
	
	-- Mime type
	mimeType VARCHAR(36)  NOT NULL  REFERENCES box.MimeType (id), 
	
	-- Store
	store VARCHAR(36)  NOT NULL  REFERENCES box.Store (id), 
	
	-- Created
	created TIMESTAMP NOT NULL, 
	
	-- Modified
	modified TIMESTAMP NOT NULL
);

-- ---------------------------------------------------------------------------------------------------------------------------


CREATE UNIQUE INDEX u_Content_code ON box.Content (TRANSLATE(LOWER(TRIM(code))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

CREATE UNIQUE INDEX u_Content_name ON box.Content (TRANSLATE(LOWER(TRIM(name))
	, '/\"'';,_-.âãäåāăąàáÁÂÃÄÅĀĂĄÀèééêëēĕėęěĒĔĖĘĚÉÈËÊìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőòÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮçÇñÑ'
	, '         aaaaaaaaaAAAAAAAAAeeeeeeeeeeEEEEEEEEEiiiiiiiiIIIIIIIIooooooooOOOOOOOOuuuuuuuuUUUUUUUUcCnN' ));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS box.ftgFormatContent() CASCADE;

CREATE OR REPLACE FUNCTION box.ftgFormatContent() RETURNS TRIGGER AS $formatContent$
DECLARE
BEGIN
	 NEW.id := box.white_is_null(NEW.id);
	 NEW.code := box.white_is_null(NEW.code);
	 NEW.name := box.white_is_null(NEW.name);
	 NEW.mimeType := box.white_is_null(NEW.mimeType);
	 NEW.store := box.white_is_null(NEW.store);

	RETURN NEW;
END;
$formatContent$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatContent ON box.Content CASCADE;

CREATE TRIGGER tgFormatContent BEFORE INSERT OR UPDATE
	ON box.Content FOR EACH ROW
	EXECUTE PROCEDURE box.ftgFormatContent();

-- ---------------------------------------------------------------------------------------------------------------------------



-- SELECT COUNT(*) FROM box.Content;

-- SELECT * FROM box.Content LIMIT 100 OFFSET 0;

-- SELECT * FROM box.Content;

-- SELECT * FROM box.Content WHERE id = 'xxx';