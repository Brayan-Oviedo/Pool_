insert into cliente(id, identificacion, fecha_nacimiento) values(1, '123', now());

insert into orden(id, tiempo_extra, cliente_identificacion) values(1, 1, '123');

insert into ticket(id, costo_total, fecha_vencimiento, orden_id) values(1, 5000, now(), 1)