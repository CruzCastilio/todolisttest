# schema Tasks

# --- !Ups

CREATE SEQUENCE task_id_seq;
CREATE TABLE task (
    id integer NOT NULL DEFAULT nextval('task_id_seq'),
    label varchar(2000),
    who varchar(40),
    mytime varchar(100),
    ready integer
);

# --- !Downs

DROP TABLE task;
DROP SEQUENCE task_id_seq;