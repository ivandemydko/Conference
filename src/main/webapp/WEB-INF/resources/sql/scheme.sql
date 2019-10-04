drop database if exists conferenceDb;
create database conferenceDb;
use conferenceDb;

create table address
(
	id int auto_increment primary key,
	city char(30) not null,
	street char(50) not null,
	building char(10) not null,
	room char(5) not null
);

create table positions
(
	id int auto_increment primary key,
	position char(50)
);

create table language
(
	id int auto_increment primary key,
	language char(3)
);


create table users
(
	id int auto_increment primary key,
	name char(50) not null,
	surname char(50) not null,
	email char(100) not null,
    password char(50) not null,
	position int,
    language int,
	foreign key (position) references positions (id)
	on update cascade on delete set null,
	foreign key (language) references language (id)
	on update cascade on delete set null
);

create table speakerratings
(
	speakerId int not null primary key,
    rating int default 0,
    bonuses int default 0,
	foreign key (speakerId) references users (id)
			on update cascade on delete cascade
);

create table reports
(
	id int auto_increment primary key,
	name char(255) not null,
	addressId int null,
	date date null,
    time time null,
	speakerId int null,
	foreign key (speakerId) references speakerratings (speakerId)
			on update cascade on delete set null,
	foreign key (addressId) references address (id)
    on update cascade on delete set null
);

create table presence
(
	reportId int not null,
	count int null,
	foreign key (reportId) references reports (id)
			on update cascade on delete cascade
);



create table registeredlist
(
	reportId int not null,
	userId int not null,
	foreign key (reportId) references reports (id)
			on update cascade on delete cascade,
	foreign key (userid) references users (id)
			on update cascade on delete cascade
);
