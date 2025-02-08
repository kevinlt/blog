CREATE TABLE post (
    uuid                UUID  PRIMARY KEY,
    title               VARCHAR(255) NOT NULL,
    content             TEXT         NOT NULL,
    status              VARCHAR(64)  NOT NULL,
    creation_date       TIMESTAMP    NOT NULL,
    updated_date        TIMESTAMP,
    publication_date    TIMESTAMP
);