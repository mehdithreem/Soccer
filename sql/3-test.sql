--stadium
INSERT INTO stadium_data VALUES
	('azadi', 10);
INSERT INTO stadium_data VALUES
	('foolad', 20);
INSERT INTO stadium_data VALUES
	('kazemi', 15);
INSERT INTO stadium_data VALUES
	('emam', 10);
INSERT INTO stadium_data VALUES
	('jahan', 20);
INSERT INTO stadium_data VALUES
	('shiroodi', 10);
INSERT INTO stadium_data VALUES
	('takhti', 10);

--team
INSERT INTO team VALUES
	('esteghlal', 4000000);
INSERT INTO team VALUES
	('perspolic', 3000000);
INSERT INTO team VALUES
	('naft', 2000000);
INSERT INTO team VALUES
	('peikan', 1500000);

--team_stadium
INSERT INTO team_stadium VALUES
	('azadi', 'esteghlal');
INSERT INTO team_stadium VALUES
	('takhti', 'perspolic');
INSERT INTO team_stadium VALUES
	('jahan', 'naft');
INSERT INTO team_stadium VALUES
	('kazemi', 'peikan');

--person
INSERT INTO person VALUES
	('Ehsan Hajsafi', 200);
INSERT INTO person VALUES
	('Mehdi Taremi', 220);
INSERT INTO person VALUES
	('Ramin Rezaeian', 240);
INSERT INTO person VALUES
	('Alireza Beiranvand', 260);
INSERT INTO person VALUES
	('Jalal Hosseini', 280);
INSERT INTO person VALUES
	('Omid Ebrahimi', 300);
INSERT INTO person VALUES
	('Vahid Amiri', 320);
INSERT INTO person VALUES
	('Ezzatollah Pourghaz', 340);
INSERT INTO person VALUES
	('Masoud Shojaei', 360);
INSERT INTO person VALUES
	('Morteza Pouraliganji', 380);
INSERT INTO person VALUES
	('Vouria Ghafouri', 400);
INSERT INTO person VALUES
	('Pejman Montazeri', 200);
INSERT INTO person VALUES
	('Milad Mohammadi', 220);
INSERT INTO person VALUES
	('Mehdi Torabi', 240);
INSERT INTO person VALUES
	('Sardar Azmoun', 260);
INSERT INTO person VALUES
	('Andranik Teymourian', 280);
INSERT INTO person VALUES
	('Reza Ghoochannejhad', 300);
INSERT INTO person VALUES
	('Ashkan Dejagah', 320);
INSERT INTO person VALUES
	('Alireza Jahanbakhsh', 340);
INSERT INTO person VALUES
	('Karim Ansarifard', 360);
INSERT INTO person VALUES
	('Saeid Ezatolahi', 380);
INSERT INTO person VALUES
	('Alireza Haghighi', 400);
INSERT INTO person VALUES
	('Soroush Rafiei', 200);
INSERT INTO person VALUES
	('ghein gaf', 220);
INSERT INTO person VALUES
	('mim mim mim', 240);
INSERT INTO person VALUES
	('noon ye', 260);
INSERT INTO person VALUES
	('be lam', 280);
INSERT INTO person VALUES
	('ali parvin', 300);
INSERT INTO person VALUES
	('ali daie', 320);
INSERT INTO person VALUES
	('afshin ghotbi', 340);
INSERT INTO person VALUES
	('amir ghalenoee', 360);

--player
INSERT INTO player VALUES
	('Ehsan Hajsafi', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Mehdi Taremi', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Ramin Rezaeian', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Alireza Beiranvand', 'goalkeeper', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Jalal Hosseini', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Omid Ebrahimi', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Vahid Amiri', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Ezzatollah Pourghaz', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Masoud Shojaei', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Morteza Pouraliganji', 'defender', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Vouria Ghafouri', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Pejman Montazeri', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Milad Mohammadi', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Mehdi Torabi', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Sardar Azmoun', 'forward', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Andranik Teymourian', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Reza Ghoochannejhad', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Ashkan Dejagah', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Alireza Jahanbakhsh', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Karim Ansarifard', 'midfielder', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Saeid Ezatolahi', 'goalkeeper', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Alireza Haghighi', 'goalkeeper', 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO player VALUES
	('Soroush Rafiei', 'goalkeeper', 100, 100, 100, 100, 100, 100, 100, 100);

--dealer
INSERT INTO dealer VALUES
	('ghein gaf', 0.8);
INSERT INTO dealer VALUES
	('mim mim mim', 1);
INSERT INTO dealer VALUES
	('noon ye', 0.5);
INSERT INTO dealer VALUES
	('be lam', 0.3);

--coach
INSERT INTO coach VALUES
	('ali parvin', 'tactical', 50);
INSERT INTO coach VALUES
	('afshin ghotbi', 'fitness', 25);
INSERT INTO coach VALUES
	('ali daie', 'tactical', 33);
INSERT INTO coach VALUES
	('Soroush Rafiei','goalkeeping', 14);
INSERT INTO coach VALUES
	('amir ghalenoee', 'fitness', 41);

--buy dealer
select buy_dealer('ghein gaf', 1396, 'esteghlal');
select buy_dealer('mim mim mim', 1396, 'perspolic');
select buy_dealer('noon ye', 1396, 'naft');
select buy_dealer('be lam', 1396, 'peikan');

--buy coach
select buy_coach('ali parvin', 1396, 'esteghlal');
select buy_coach('afshin ghotbi', 1396, 'perspolic');
select buy_coach('Soroush Rafiei', 1396, 'naft');
select buy_coach('ali daie', 1396, 'peikan');

--buy player
select buy_player('Ehsan Hajsafi', 1396, 'esteghlal');
select buy_player('Mehdi Taremi', 1396, 'esteghlal');
select buy_player('Ramin Rezaeian', 1396, 'esteghlal');
select buy_player('Alireza Beiranvand', 1396, 'esteghlal');
select buy_player('Jalal Hosseini', 1396, 'esteghlal');

insert into plays_in values
	('Ehsan Hajsafi', 'esteghlal', 10, 'goalkeeper'),
	('Mehdi Taremi', 'esteghlal', 13, 'forward'),
	('Ramin Rezaeian', 'esteghlal', 9, 'defender'),
	('Alireza Beiranvand', 'esteghlal', 1, 'defender'),
	('Jalal Hosseini', 'esteghlal', 23, 'bench');

select buy_player('Omid Ebrahimi', 1396, 'perspolic');
select buy_player('Vahid Amiri', 1396, 'perspolic');
select buy_player('Ezzatollah Pourghaz', 1396, 'perspolic');
select buy_player('Masoud Shojaei', 1396, 'perspolic');
select buy_player('Morteza Pouraliganji', 1396, 'perspolic');

insert into plays_in values
	('Omid Ebrahimi', 'perspolic', 10, 'goalkeeper'),
	('Vahid Amiri', 'perspolic', 13, 'forward'),
	('Ezzatollah Pourghaz', 'perspolic', 9, 'defender'),
	('Masoud Shojaei', 'perspolic', 1, 'defender'),
	('Morteza Pouraliganji', 'perspolic', 23, 'bench');

select buy_player('Vouria Ghafouri', 1396, 'naft');
select buy_player('Pejman Montazeri', 1396, 'naft');
select buy_player('Milad Mohammadi', 1396, 'naft');
select buy_player('Mehdi Torabi', 1396, 'naft');
select buy_player('Sardar Azmoun', 1396, 'naft');

insert into plays_in values
	('Vouria Ghafouri', 'naft', 10, 'goalkeeper'),
	('Pejman Montazeri', 'naft', 13, 'forward'),
	('Milad Mohammadi', 'naft', 9, 'defender'),
	('Mehdi Torabi', 'naft', 1, 'defender'),
	('Sardar Azmoun', 'naft', 23, 'bench');

select buy_player('Andranik Teymourian', 1396, 'peikan');
select buy_player('Reza Ghoochannejhad', 1396, 'peikan');
select buy_player('Ashkan Dejagah', 1396, 'peikan');
select buy_player('Alireza Jahanbakhsh', 1396, 'peikan');
select buy_player('Karim Ansarifard', 1396, 'peikan');

insert into plays_in values
	('Andranik Teymourian', 'peikan', 10, 'goalkeeper'),
	('Reza Ghoochannejhad', 'peikan', 13, 'forward'),
	('Ashkan Dejagah', 'peikan', 9, 'defender'),
	('Alireza Jahanbakhsh', 'peikan', 1, 'defender'),
	('Karim Ansarifard', 'peikan', 23, 'bench');

--influence by coach
select influence_all('esteghlal', 'ali parvin');
select influence_all('perspolic', 'afshin ghotbi');
select influence_all('naft', 'Soroush Rafiei');
select influence_all('peikan', 'ali daie');

--league
INSERT INTO league(year) VALUES (1396);

--league_team
INSERT INTO league_team(league, team) VALUES
	(1, 'esteghlal'),
	(1, 'perspolic'),
	(1, 'naft'),
	(1, 'peikan');

select league_timeset(1);

select batch_match(1, 0);
select batch_match(1, 1);
select batch_match(1, 2);
select batch_match(1, -1);
select batch_match(1, -2);
select batch_match(1, -3);



