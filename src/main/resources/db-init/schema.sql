DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE IF NOT EXISTS ARTICLE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TITLE VARCHAR(255) NOT NULL,
    CONTENT VARCHAR(255) NOT NULL,
    AUTHOR_ID BIGINT NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);