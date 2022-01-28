drop schema if exists `hb-05-many-to-many`;
create schema `hb-05-many-to-many`;
use `hb-05-many-to-many`;
drop table if exists  `instructor_detail`;
create table `instructor_detail` (
                                     `id` int not null auto_increment,
                                     `youtube_channel` varchar(128) default null,
                                     `hobby` varchar(64) default null,
                                     primary key (`id`)
) engine=InnoDB auto_increment=1 default charset=latin1;

drop table if exists `instructor`;
create table `instructor`(
                             `id` int not null auto_increment,
                             `first_name` varchar(64) default null,
                             `last_name` varchar(64) default null,
                             `email` varchar(64) default null,
                             `instructor_detail_id` int default null,
                             primary key (`id`),
                             key `FK_DETAIL_idx` (`instructor_detail_id`),
                             constraint `FK_DETAIL` foreign key (`instructor_detail_id`)
                                 references `instructor_detail` (`id`) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=latin1;

drop table if exists `course`;
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

drop table if exists `student`;
create table `student` (
                           `id` int not null auto_increment,
                           `first_name` varchar(64) default null,
                           `last_name` varchar(64)  default null,
                           `email` varchar(128)  default null,
                           primary key (`id`)
)engine=InnoDB auto_increment=10 default charset=latin1;


drop table if exists `review`;
create table `review` (
                          `id` int not null auto_increment,
                          `comment` varchar(256) default null,
                          `course_id` int default null,
                          primary key (`id`),

                          key `FK_COURSE_idx` (`course_id`),

                          constraint `FK_COURSE` foreign key (`course_id`) references `course` (`id`)

                              on delete no action on update no action
)engine=InnoDB auto_increment=10 default charset=latin1;

drop table if exists `course_student`;
create table `course_student` (
                                  `course_id` int not null,
                                  `student_id` int not null,

                                  primary key (`course_id`, `student_id`),
                                  KEY `FK_STUDENT_idx` (`student_id`),
                                  constraint `FK_COURSE_05` foreign key (`course_id`) references course (`id`)
                                      on delete no action on update no action,

                                  constraint `FK_STUDENT_05` foreign key (`student_id`) references student (`id`)
                                      on delete no action on update no action

)engine=InnoDB auto_increment=10 default charset=latin1;





