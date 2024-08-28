
use pos;

create table customer(
                         id          varchar(255) primary key,
                         name        varchar(255) not null,
                         address     varchar(255) not null,
                         contact     varchar(255) not null
);

create table item(
                     itemId              varchar(255) primary key,
                     itemName     varchar(255) not null,
                     itemPrice      decimal(10,2) not null,
                     itemQty             varchar(255) not null
);

create table orders(
                       orderId              varchar(255) primary key,
                       date            date not null,
                       discount       decimal(10,2) not null,
                       total       decimal(10,2) not null,
                       customerId     varchar(255) not null,
                       constraint foreign key (customerId) references customer(id)
                           on update cascade on delete cascade
);

create table order_details(
                              orderId            varchar(255) not null ,
                              itemId             varchar(255) not null,
                              qty                 int not null,
                              unitPrice          decimal(10,2) not null,
                              total               decimal(10,2) not null,
                              constraint foreign key (orderId) references orders(orderId)
                                  on update cascade on delete cascade,
                              constraint foreign key (itemId) references item(itemId)
                                  on update cascade on delete cascade
);

