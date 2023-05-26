/*drop table if exists comic;
drop table if exists comic_marvel_characters;
drop table if exists comic_date;
drop table if exists comic_price;
drop table if exists marvel_character;
*/


/*
create table comic (id bigint not null auto_increment, description varchar(255), format varchar(255), full_image longblob, modified TIMESTAMP, page_count integer, thumbnail longblob, title varchar(255), primary key (id));
create table comic_marvel_characters (comic_id bigint not null, marvel_characters_id bigint not null, primary key (comic_id, marvel_characters_id));
create table comic_date (id bigint not null auto_increment, date TIMESTAMP, type varchar(255), comic_id bigint, primary key (id));
create table comic_price (id bigint not null auto_increment, price decimal(19,2), type varchar(255), comic_id bigint, primary key (id));
create table marvel_character (id bigint not null auto_increment, description varchar(255), full_image longblob, modified TIMESTAMP, name varchar(255), thumbnail longblob, primary key (id));
alter table comic_marvel_characters add constraint fk_marvel_character foreign key (marvel_characters_id) references marvel_character (id)
alter table comic_marvel_characters add constraint fr_comic foreign key (comic_id) references comic (id)
alter table comic_date add constraint fk_comic foreign key (comic_id) references comic (id)
alter table comic_price add constraint fk_comic foreign key (comic_id) references comic (id)
*/
