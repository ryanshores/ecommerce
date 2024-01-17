create table order_line_items
(
    order_id   bigint not null,
    product_id bigint,
    quantity   bigint
);

create table cart
(
    created_dt  timestamptz not null,
    updated_dt  timestamptz,
    id          bigserial
        primary key,
    coupon_code text,
    modified_by text
);

create table authority
(
    name text not null primary key
);

create table account
(
    created_dt  timestamptz not null,
    updated_dt  timestamptz,
    cart_id     bigint
        unique
        constraint fk_cart
            references cart,
    id          bigserial
        primary key,
    email       text not null,
    modified_by text,
    password    text not null
);

create table account_authority
(
    account_id     bigint      not null
        constraint fk_account
            references account,
    authority_name text not null
        constraint fk_authority
            references authority,
    primary key (account_id, authority_name)
);

create table product
(
    created_dt  timestamptz not null,
    price       double precision check ( price > 0 ),
    updated_dt  timestamptz,
    id          bigserial
        primary key,
    quantity    bigint check ( quantity > 0 ),
    description text not null,
    modified_by text,
    name        text not null,
    sku         text not null
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
    created_dt  timestamptz not null,
    discount    integer
        check ( discount >= 0 and discount <= 100 ),
    status      smallint
        constraint sale_status_check
            check (status >= 0 AND status <= 4),
    updated_dt  timestamptz,
    account_id  bigint
        constraint fk_account
            references account,
    id          bigserial
        primary key,
    modified_by text
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

