-- this is where you can load data in the DB
insert into employee (name) values ('Alice'), ('Bob'), ('Carla'), ('Denis');

-- add some tasks
insert into task (description, due_date, assignee_id, creation_date, last_update)
values 
	('Landing page', current_timestamp, null, current_timestamp, null),
	('Tasks list', current_timestamp, null, current_timestamp, null),
	('Task component', current_timestamp, null, current_timestamp, null),
	('Assignee change', current_timestamp, 1, current_timestamp, null),
	('Due date change', current_timestamp, null, current_timestamp, null);
	
