create table if not exists person_source
(
    id  serial not null constraint person_source_pk PRIMARY KEY,
    last_name  text not null,
    first_name text not null,
    age        integer not null
);

TRUNCATE person_source;

INSERT INTO person_source (last_name, first_name, age) VALUES ('Teszt',' Elek', 30);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Nyom',' Réka', 21);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Para',' Zita', 32);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Patta',' Nóra', 43);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Pár',' Zoltán', 51);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Pop',' Simon', 12);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Remek',' Elek', 35);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Beviz',' Elek', 8);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Szalmon',' Ella', 11);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Techno',' Kolos', 83);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Trab',' Antal', 92);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Ultra',' Viola', 43);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Am',' Erika', 12);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Bac',' Ilus', 23);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Citad',' Ella', 31);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Dil',' Emma', 72);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Eszte',' Lenke', 81);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Feles',' Elek', 68);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Füty',' Imre', 34);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Git',' Áron', 99);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Har',' Mónika', 1);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Heu',' Réka', 52);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Hü',' Jenő', 44);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Kukor',' Ica', 22);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Kala',' Pál', 59);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Külö',' Nóra', 79);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Körm',' Ödön', 33);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Kér',' Ede', 54);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Major',' Anna', 82);
INSERT INTO person_source (last_name, first_name, age) VALUES ('Meg',' Győző', 26);
