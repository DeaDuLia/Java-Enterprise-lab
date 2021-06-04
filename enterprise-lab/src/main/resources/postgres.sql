
create table train (
    id serial primary key,
    speedkoeff integer default 1,
    waitingkoeff integer default 1
);
select * from train;
create table station (
    id serial primary key,
    station_name varchar (100),
    km integer default 0
);
select * from station;
create table trains_on_stations (
    train_id integer references train(id),
    station_id integer references station(id)
);
select * from trains_on_stations;
create table route (
    id serial primary key,
    route_name varchar(100)
)
select * from route;
create table trains_table (
    id serial primary key,
    train_id integer references train(id),
    route_id integer references route(id)
);

create table stations_on_route (
    route_id integer references route(id),
    station_id integer references station(id)
);

create table accident (
    id serial primary key,
    ac_name varchar (100),
    repair_time integer default 0
)

create table accidents_on_stations (
    ac_id integer references accident(id),
    station_id integer references station(id)
)



