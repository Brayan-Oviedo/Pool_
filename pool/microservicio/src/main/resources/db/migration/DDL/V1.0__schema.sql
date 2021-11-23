create table cliente (
 id int(11) not null auto_increment,
 identificacion varchar(10) not null unique,
 fecha_nacimiento date not null,
 primary key(id)
);

create table orden (
 id int(11) not null auto_increment,
 tiempo_extra int(2) null,
 cliente_identificacion varchar(10) not null,
 primary key(id),
 foreign key(cliente_identificacion) references cliente(identificacion)
  on update cascade
);

create table ticket (
 id int(11) not null auto_increment,
 costo_total float not null,
 fecha_vencimiento datetime not null,
 orden_id int(11) not null,
 primary key(id),
 foreign key(orden_id) references orden(id)
  on delete cascade
  on update cascade
);