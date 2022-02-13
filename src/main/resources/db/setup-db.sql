create database booklibrarydb;
create user if not exists 'library_user'@'localhost' identified by 'Library123';
grant all privileges on booklibrarydb.* to 'library_user'@'localhost';
flush privileges;