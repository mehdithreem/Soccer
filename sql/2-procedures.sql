create or replace function update_players_amadegi (
	game_id int
) returns void as $$
declare
	cur_players CURSOR(_host varchar(20), _guest varchar(20)) FOR
		select player, role
		from plays_in pi
		where (pi.team = _host) or (pi.team = _guest);
	target_player record;
	target_team record;
begin
	select g.host_team, g.guest_team into target_team
		from game g
		where g.id = game_id;

	open cur_players(target_team.host_team, target_team.guest_team);
	loop
		fetch cur_players into target_player;
		exit when not found;

		if target_player.role = 'bench' then
			update player pt
				set amadegi = amadegi + amadegi * 0.05
				where pt.name = target_player.player;
		else
			update player pt
				set amadegi = amadegi - amadegi * 0.05
				where pt.name = target_player.player;
		end if;
	end loop;
	close cur_players;
end; $$
language PLPGSQL;

create or replace view stadium as
	select st.name,
		st.capacity,
		st.capacity * 10000 as price
	from stadium_data st
;

create or replace function calculate_team_satisfaction (
	in_team varchar(20)
) returns real as $$
declare
	g record;
	satisfaction real;
	toilet_q real;
begin
	satisfaction = 0;

	for g in 
		select is_host_win, host_team, guest_team
			from game
			where host_team = in_team or guest_team = in_team
		order by start_time desc limit 10
	loop
		if (in_team = g.host_team and g.is_host_win = 1)
			or (in_team = g.guest_team and g.is_host_win = -1) then
			satisfaction = satisfaction + 10;
		elsif (g.is_host_win = 0) then
			satisfaction = satisfaction + 5;
		elsif (g.is_host_win is not null) then
			satisfaction = satisfaction + 1;
		end if;
	end loop;

	select toilet_quality into toilet_q
		from team_stadium ts
		where ts.team_name = in_team;

	if (toilet_q < 0) then
		satisfaction = satisfaction - 20;
	end if;

	if (satisfaction < 0) then
		satisfaction = 0;
	end if;

	return satisfaction /100;
end; $$
language PLPGSQL;

create or replace view team_satisfaction as
	select team.name,
		calculate_team_satisfaction(team.name) as satisfaction
	from team;

create or replace function calculate_game_income(
	game_id int,
	target_team varchar(20)
) returns int as $$
declare
	income int;
	target_game record;
	target_team_stadium record;
	stadium_capacity int;
begin
	income = 0;
	select * into target_game from game g where g.id = game_id;
	select * into target_team_stadium from team_stadium ts where ts.team_name = target_game.host_team;
	select capacity into stadium_capacity from stadium_data sd where sd.name = target_team_stadium.stadium_name;
	if (target_game.host_team = target_team) then
		income = calculate_team_satisfaction(target_team) * target_game.ticket_price * 0.9 * stadium_capacity * target_team_stadium.seat_quality;
	elsif (target_game.guest_team = target_team) then
		income = calculate_team_satisfaction(target_team) * target_game.ticket_price * 0.1 * stadium_capacity * target_team_stadium.seat_quality;
	end if;

	return income;
end; $$
language PLPGSQL;

create or replace function update_stadium_quality(
	game_id int
) returns void as $$
declare
	target_game record;
	seat_coeff int;
begin
	select * into target_game from game g where g.id = game_id;
	seat_coeff = 1;

	if (target_game.is_host_win = -1) then
		seat_coeff = 0.8;
	end if;

	update team_stadium
		set seat_quality = seat_quality * seat_coeff,
			toilet_quality = toilet_quality - 0.15,
			grass_quality = grass_quality * 0.85
		where team_name = target_game.host_team;
end; $$
language PLPGSQL;

create or replace function buy_dealer(
	in_dealer varchar(20),
	in_year int,
	in_team varchar(20)
) returns void as $$
declare
	current_contract record;
	dealer_price int;
	current_dealer record;
begin

    select * into current_dealer from dealer d, contract c where c.team = in_team AND c.person = d.name  AND c.year = in_year;

    with upsert as (update contract
                    set person = in_dealer
                    where year =in_year AND team = in_team AND person = current_dealer.name returning *)
                    insert into contract (team, person, year)
                                select in_team , in_dealer , in_year
                                where not exists (
                                select 1
                                from upsert
                                where upsert.person = in_dealer AND upsert.year = in_year);


	select price into dealer_price from person where person.name = in_dealer;
	update team
		set money = money - dealer_price
		where name = in_team;

end; $$
language PLPGSQL;

create or replace function buy_coach(
	in_coach varchar(20),
	in_year int,
	in_team varchar(20)
) returns void as $$
declare
	coach_price int;
begin
	select price into coach_price from person where person.name = in_coach;
	insert into contract values
		(in_team, in_coach, in_year);
	update team
		set money = money - coach_price
		where name = in_team;
end; $$
language PLPGSQL;

create or replace function buy_player(
	in_player varchar(20),
	in_year int,
	in_team varchar(20)
) returns void as $$
declare
	dealer_leverage real;
	player_price int;
begin
	dealer_leverage = 1;
	select leverage into dealer_leverage from dealer d
		where exists(
			select * from contract where 
				person = d.name and
				team = in_team and
				year = in_year
		);
	
	select price into player_price from person where person.name = in_player;
	insert into contract values
		(in_team, in_player, in_year);
	update team
		set money = money - (player_price * dealer_leverage)
		where name = in_team;

end; $$
language PLPGSQL;

create or replace function influence_player(
	in_coach varchar(20),
	in_player varchar(20)
) returns void as $$
declare
	target_player record;
	target_coach record;

	ghodrat_badani_coeff int;
	ghodrat_pass_coeff int;
	toop_giri_coeff int;
	ghodrat_golzani_coeff int;
	ghodrat_shoot_coeff int;
	sorat_coeff int;
	darvazebani_coeff int;
begin
	select * into target_coach from coach where coach.name = in_coach;

	ghodrat_badani_coeff = 0;
	ghodrat_pass_coeff = 0;
	toop_giri_coeff = 0;
	ghodrat_golzani_coeff = 0;
	ghodrat_shoot_coeff = 0;
	sorat_coeff = 0;
	darvazebani_coeff = 0;

	case target_coach.duty
	when 'goalkeeping' then
		darvazebani_coeff = 1;
		ghodrat_badani_coeff = 1;
		toop_giri_coeff = 1;
	when 'tactical' then
		toop_giri_coeff = 1;
		ghodrat_pass_coeff = 1;
		ghodrat_golzani_coeff = 1;
	when 'fitness' then
		ghodrat_badani_coeff = 1;
		ghodrat_shoot_coeff = 1;
	end case;

    raise notice '%' ,in_player;

	update player p
		set
			ghodrat_badani = ghodrat_badani + target_coach.exprience * ghodrat_badani_coeff,
			ghodrat_pass = ghodrat_pass + target_coach.exprience * ghodrat_pass_coeff,
			toop_giri = toop_giri + target_coach.exprience * toop_giri_coeff,
			ghodrat_golzani = ghodrat_golzani + target_coach.exprience * ghodrat_golzani_coeff,
			ghodrat_shoot = ghodrat_shoot + target_coach.exprience * ghodrat_shoot_coeff,
			sorat = sorat + target_coach.exprience * sorat_coeff,
			darvazebani = darvazebani + target_coach.exprience * darvazebani_coeff
		where
			p.name = in_player;
end; $$
language PLPGSQL;

create or replace function influence_all(
	in_team varchar(20),
	in_coach varchar(20)
) returns void as $$
declare
	pi record;
begin
	for pi in
		select player from plays_in
		where plays_in.team = in_team
	loop
		PERFORM influence_player(in_coach, pi.player);
	end loop;
end; $$
language PLPGSQL;

create or replace function recursive_league_timeset(
	teams varchar(20)[],
	time_level int,
	league_id int,
	OUT time_levelo int

)as $$
declare
	arr_size int;
	t1 varchar(20)[];
	t2 varchar(20)[];
	a1 int;
	a2 int;	
	host varchar(20);
	guest varchar(20);
	i int;
	j int;
begin
	SELECT array_length(teams,1) into arr_size;
	-- raise notice 'arr_size : %',arr_size;	
	IF arr_size = 2 then
		-- raise notice 'HERE : %, %',teams[1], teams[2];			
		INSERT INTO game (host_team, guest_team, start_time, league, ticket_price)
					VALUES (teams[1], teams[2], time_level, league_id, 100 );
		INSERT INTO game (host_team, guest_team, start_time, league, ticket_price)
					VALUES (teams[2], teams[1], 0-(time_level+1), league_id, 100 );
		time_levelo = time_level;
	ELSE
		-- raise notice 'arr_size : %',arr_size;
		
		SELECT recursive_league_timeset(teams[0:(arr_size/2)], time_level, league_id) into a1;
		SELECT recursive_league_timeset(teams[(arr_size/2)+1:arr_size], time_level, league_id) into a2;
		
		time_levelo = a1+1;
		FOR i IN 0..(arr_size/2-1) LOOP
			FOR j IN 1..arr_size/2 LOOP
				INSERT INTO game (host_team, guest_team, start_time, league, ticket_price)
					VALUES ( teams[(i+j)%(arr_size/2)+1], teams[ (arr_size/2)+j], time_levelo, league_id, 100 );
				INSERT INTO game (host_team, guest_team, start_time, league, ticket_price)
					VALUES ( teams[ (arr_size/2)+j],  teams[(i+j)%(arr_size/2)+1], 0-(time_levelo+1), league_id, 100 );
			END LOOP;
			time_levelo = time_levelo +1;			
		END LOOP;
	END IF;
end; $$
language PLPGSQL;

create or replace function league_timeset(
	league_id int
) returns void as $$ 
declare
	teams varchar(20)[];
	res int;
begin
	select array_agg(team::varchar(20)) into teams from league_team where league = league_id;
	select recursive_league_timeset(teams, 0, league_id) into res;
	-- raise notice 'res : %',res;

end; $$
language PLPGSQL;

create or replace function match(
	game_id int
) returns void as $$
declare
	target_game record;
	host_score int;
	guest_score int;
	o_is_host_win int;
begin
	select * into target_game from game where game.id = game_id;

	host_score = 0;
	guest_score = 0;

	select
		COALESCE(sum(amadegi),0) +
		COALESCE(sum(ghodrat_badani),0) +
		COALESCE(sum(ghodrat_pass),0) +
		COALESCE(sum(toop_giri),0) +
		COALESCE(sum(ghodrat_golzani),0) +
		COALESCE(sum(ghodrat_shoot),0) +
		COALESCE(sum(sorat),0) +
		COALESCE(sum(darvazebani),0)
		into host_score
		from player p
		where exists(
			select role from plays_in pi
			where (pi.player = p.name) and
				(pi.team = target_game.host_team) and
				(role != 'bench')
		);

	select
		COALESCE(sum(amadegi),0) +
		COALESCE(sum(ghodrat_badani),0) +
		COALESCE(sum(ghodrat_pass),0) +
		COALESCE(sum(toop_giri),0) +
		COALESCE(sum(ghodrat_golzani),0) +
		COALESCE(sum(ghodrat_shoot),0) +
		COALESCE(sum(sorat),0) +
		COALESCE(sum(darvazebani),0)
		into guest_score
		from player p
		where exists(
			select role from plays_in pi
			where (pi.player = p.name) and
				(pi.team = target_game.guest_team) and
				(role != 'bench')
		);

	if (host_score > guest_score) then
		o_is_host_win = 1;
	elsif (host_score < guest_score) then
		o_is_host_win = -1;
	else
		o_is_host_win = 0;
	end if;

	update game
		set is_host_win = o_is_host_win
		where game.id = target_game.id;
end; $$
language PLPGSQL;

create or replace function batch_match(
	league_id int,
	match_series int
) returns void as $$
declare
	g record;
begin
	for g in 
		select game.id, game.host_team, game.guest_team
			from game
			where game.league = league_id and game.start_time = match_series
	loop
		PERFORM match(g.id);
		PERFORM update_players_amadegi(g.id);
		PERFORM update_stadium_quality(g.id);

		update team
			set money = money + calculate_game_income(g.id, g.host_team)
			where team.name = g.host_team;

		update team
			set money = money + calculate_game_income(g.id, g.guest_team)
			where team.name = g.guest_team;
	end loop;
end; $$
language PLPGSQL;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO soccer_access;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to soccer_access;