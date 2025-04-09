alter table vollmed_api.patients add active tinyint;
update vollmed_api.patients set vollmed_api.patients.active = 1;