create table if not exists listing_status
(
    id  serial not null constraint listing_status_pk PRIMARY KEY,
    status_name  text not null
);

create table if not exists marketplace
(
    id  serial not null constraint marketplace_pk PRIMARY KEY,
    marketplace_name  text not null
);
