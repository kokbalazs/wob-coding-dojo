create table if not exists person
(
    id  serial not null constraint person_pk PRIMARY KEY,
    first_name varchar(200) not null,
    last_name  varchar(200) not null,
    age        integer not null
)