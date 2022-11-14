-- This is where your DML goes
create table employee(
	id bigint not null auto_increment primary key,
	name varchar(20) not null
);

-- create the task table with id, description, due date and employee columns
create table task(
	id bigint not null auto_increment primary key,
	description varchar(100) not null,
	due_date date,
	assignee_id bigint,
	creation_date timestamp not null,
	last_update timestamp,
	constraint fk_task_employee foreign key (assignee_id) references employee(id)
);
