alter table doctors add active tinyint;
update vollmed_api.doctors set vollmed_api.doctors.active = 1; #todos os registos da tabela Doctor estão ativos