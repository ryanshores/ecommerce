create table order_line_items
(
    order_id   bigint not null,
    product_id bigint,
    quantity   bigint
);

create table cart
(
    created_dt  date,
    updated_dt  date,
    id          bigserial
        primary key,
    coupon_code varchar(32),
    modified_by varchar(64)
);

create table authority
(
    name varchar(16) not null primary key
);

create table account
(
    created_dt  date,
    updated_dt  date,
    cart_id     bigint
        unique
        constraint fk_cart
            references cart,
    id          bigserial
        primary key,
    email       varchar(64),
    modified_by varchar(64),
    password    varchar(64)
);

create table account_authority
(
    account_id     bigint      not null
        constraint fk_account
            references account,
    authority_name varchar(16) not null
        constraint fk_authority
            references authority,
    primary key (account_id, authority_name)
);

create table product
(
    created_dt  date,
    price       double precision,
    updated_dt  date,
    id          bigserial
        primary key,
    quantity    bigint,
    description varchar(255) not null,
    modified_by varchar(64),
    name        varchar(64) not null,
    sku         varchar(32) not null
);

create table cart_line_items
(
    line_items_order integer not null,
    cart_id          bigint  not null
        constraint fk_cart
            references cart,
    product_id       bigint
        constraint fk_product
            references product,
    quantity         bigint,
    primary key (line_items_order, cart_id)
);

create table sale
(
    created_dt  date,
    discount    integer,
    status      smallint
        constraint sale_status_check
            check ((status >= 0) AND (status <= 4)),
    updated_dt  date,
    account_id  bigint
        constraint fk_account
            references account,
    id          bigserial
        primary key,
    modified_by varchar(64)
);

create table sale_line_items
(
    line_items_order integer not null,
    product_id       bigint
        constraint fk_product
            references product,
    quantity         bigint,
    sale_id          bigint  not null
        constraint fl_sale
            references sale,
    primary key (line_items_order, sale_id)
);

