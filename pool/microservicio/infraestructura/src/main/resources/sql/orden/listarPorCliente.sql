select o.id, tiempo_extra, cliente_identificacion, t.id as ticket_id, t.costo_total, t.fecha_vencimiento, c.fecha_nacimiento
from orden o join ticket t
on t.orden_id = o.id
join cliente c
on c.identificacion = cliente_identificacion

where cliente_identificacion = :identificacion