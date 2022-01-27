use `hb-01-one-to-one-uni`;

create table `course` (
	`id` int not null auto_increment,
    `title` varchar(128) default null,
    `instructor_id` int default null,
    primary key (`id`),
    
    unique key (`title`),
    
    key `FK_INSTRUCTOR_idx` (`instructor_id`),
    
    constraint `FK_INSTRUCTOR` foreign key (`instructor_id`) references `instructor` (`id`)
		
    on delete no action on update no action
)engine=InnoDB auto_increment=10 default charset=latin1;
