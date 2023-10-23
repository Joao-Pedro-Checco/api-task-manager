create table tasks
(
    id bigint not null auto_increment,
    title varchar(100) not null,
    description varchar(100),
    completed tinyint,

    primary key(id)
);
