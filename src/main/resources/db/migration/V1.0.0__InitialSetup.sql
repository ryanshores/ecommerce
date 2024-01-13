create table order_line_items
(
    order_id   bigint not null,
    product_id bigint,
    quantity   bigint
);

alter table order_line_items
    owner to admin;

create table cart
(
    created_dt  date,
    updated_dt  date,
    id          bigserial
        primary key,
    coupon_code varchar(255),
    modified_by varchar(255)
);

alter table cart
    owner to admin;

create table account
(
    created_dt  date,
    updated_dt  date,
    cart_id     bigint
        unique
        constraint fkf1aw1a2b2qrnxhuqupim0yblq
            references cart,
    id          bigserial
        primary key,
    email       varchar(255),
    modified_by varchar(255),
    password    varchar(255)
);

alter table account
    owner to admin;

create table product
(
    created_dt  date,
    price       double precision,
    updated_dt  date,
    id          bigserial
        primary key,
    quantity    bigint,
    description varchar(255) not null,
    modified_by varchar(255),
    name        varchar(255) not null,
    sku         varchar(255) not null
);

alter table product
    owner to admin;

create table cart_line_items
(
    line_items_order integer not null,
    cart_id          bigint  not null
        constraint fkwels3wu8sxqxxa9t6bgxdxa3
            references cart,
    product_id       bigint
        constraint fk8gplms50tca43f37vov0fhd9d
            references product,
    quantity         bigint,
    primary key (line_items_order, cart_id)
);

alter table cart_line_items
    owner to admin;

create table sale
(
    created_dt  date,
    discount    integer,
    status      smallint
        constraint sale_status_check
            check ((status >= 0) AND (status <= 4)),
    updated_dt  date,
    account_id  bigint
        constraint fkd9v2kefsda5bh0r2hvo9ke17h
            references account,
    id          bigserial
        primary key,
    modified_by varchar(255)
);

alter table sale
    owner to admin;

create table sale_line_items
(
    line_items_order integer not null,
    product_id       bigint
        constraint fk6kn71bxrl59a6br95erums180
            references product,
    quantity         bigint,
    sale_id          bigint  not null
        constraint fkdj1ruabwvn981h82c7gao3skn
            references sale,
    primary key (line_items_order, sale_id)
);

alter table sale_line_items
    owner to admin;

