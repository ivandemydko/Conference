use conferencedb;

   insert positions (position) 
value('Admin'),
     ('Moderator'),
     ('Speaker'),
     ('User');
     
	insert language (language) 
value('UA'),
     ('EN'),
     ('RU');
     
     insert address (city, street, building, room) 
value('Киев','Донца', '25','18'),
     ('Львов','Шевченко', '5','77');
     
     
    insert users (name, surname, email, password, position, language) 
value('Юій','Юдильцев', 'userudilcev@gmail.com','user1111',4,1),
     ('Ulia','Ulieva', 'userulieva@gmail.com','user2222',4,2),
     ('Святослав','Семин', 'speakersemin@gmail.com','speaker1111',3,1),
     ('Савелий','Синий', 'sinijsavelij@gmail.com','speaker2222',3,3),
     ('admin','admin', 'admin@ukr.net','admin',1,3),
     ('moder','moder', 'moder@ukr.net','moder',2,1);
     
      insert speakerratings (speakerId, rating, bonuses) 
value(3,0,0),
     (4,3,25);
    
    
       insert reports (name, addressId, date, time, speakerId) 
  value('Java Boot',1, '2019-11-11','10:00:00',3),
       ('Java Boot',1, '2019-11-11','10:00:00',3),
       ('Java Boot',1, '2019-11-11','23:18:00',3),
       ('Java Boot',1, '2019-11-11','10:00:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java Boot',1, '2019-11-11','15:47:00',3),
       ('Java Boot',1, '2019-11-11','15:47:00',4),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',4),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',4),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',4),
       ('Java 13',1, '2019-09-09','15:47:00',3),
       ('Java 13',1, '2019-09-09','15:47:00',4),
       ('Java 8',null, null,null,3),
       ('Java 8',null, null,null,4),
       ('Java 8',null, null,null,3),
       ('Java 8',null, null,null,4),
       ('Java 8',null, null,null,3),
       ('Java 8',null, null,null,4),
       ('Java 8',null, null,null,3),
       ('Java 8',null, null,null,3);
     