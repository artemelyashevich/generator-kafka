create table if not exists data
(
    id          int primary key auto_increment,
    sensor_id   int         not null,
    timestamp   timestamp   not null,
    measurement float       not null,
    type        varchar(50) not null
)