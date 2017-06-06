\connect postgres;
drop database if exists soccer;
create database soccer;

DROP USER IF EXISTS soccer_access;
CREATE USER soccer_access UNENCRYPTED PASSWORD 'soccerpass';
GRANT ALL PRIVILEGES ON DATABASE soccer TO soccer_access;

\connect soccer;

create table info (
	key varchar(20),
	value varchar(20),

	primary key (key, value)
);

create table league (
	key serial primary key,
	closed boolean default false,
	done boolean default false,
	done_rounds int default 0,
	year int
);

create table team (
	name varchar(20) not null,
	money int not null check (money >= 0),

	primary key (name)
);

create table league_team (
	league serial references league(key),
	team varchar(20) references team(name),

	primary key (league, team)
);

create table stadium_data (
	name varchar(20) not null,
	capacity int not null,
	primary key (name)
);

create table team_stadium (
	stadium_name varchar(20) not null references stadium_data(name),
	team_name  varchar(20) references team(name),
	grass_quality real not null default 1,
	toilet_quality real not null default 1,
	seat_quality real not null default 1,

	primary key (team_name)	
);

create table game (
	id serial primary key,
	host_team varchar(20) references team(name),
	guest_team varchar(20) references team(name),
	start_time int not null,
	league serial references league(key),
	ticket_price int,
	is_host_win int,
 
	unique (host_team, guest_team, league)
);

create table person(
	name varchar(20) not null,
	price int,
	primary key (name)
);

create table coach(
	name varchar(20) not null references person(name),
	duty varchar(20) not null,
	exprience int not null,
	primary key (name)
);

create table dealer(
	name varchar(20) not null references person(name),
	leverage real not null,
	primary key (name)
);

create table player(
	name varchar(20) not null references person(name),
	expertise varchar(20) not null,
	amadegi int not null,
	ghodrat_badani int not null,
	ghodrat_pass int not null,
	toop_giri int not null,
	ghodrat_golzani int not null,
	ghodrat_shoot int not null,
	sorat int not null,
	darvazebani int not null,
	
	primary key (name)		
);

create table plays_in (
	player varchar(20) references player(name),
	team varchar(20) references team(name),
	shirt_number int,
	role varchar(20),

	primary key (player, team)
);

create table contract (
	team varchar(20) references team(name),
	person varchar(20) references person(name),
	year int,
	sellable boolean default false,
	sold boolean default false,

	primary key (person, year)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO soccer_access;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to soccer_access;
