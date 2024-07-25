create table if not EXISTS category(
    id integer primary key,
    description varchar(255) not null,
    name varchar(255) not null
);


create table if not exists product(
    id integer primary key,
    description varchar(255) not null,
    name varchar(255) not null,
    available_quantity double precision not null,
    price numeric(38, 2) not null,
    category_id integer
                constraint foreignkey_1 references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;


