create table category
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);

create unique index category_name_idx
    on category (name);
