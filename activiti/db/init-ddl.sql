drop table if exists leave_info;
create table leave_info (
id varchar(64) primary key,
status varchar(32),
leave_msg varchar(32)
);