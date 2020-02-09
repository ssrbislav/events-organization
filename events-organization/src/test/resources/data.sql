insert into location(city, country, deleted, name, number, street_name, zip_code) values("Novi Sad", "Srbija", false, "Sajam", 30,"Kralja Petra", "21000");
insert into location(city, country, deleted, name, number, street_name, zip_code) values("Beograd", "Srbija", false, "Sumice", 30,"Bulevar", "11000");
insert into location(city, country, deleted, name, number, street_name, zip_code) values("Sabac", "Srbija", false, "Bazen", 30,"Mile Jovanovica", "23000");

insert into hall(deleted, name, location_id) values(false, "Master centar", 1);
insert into hall(deleted, name, location_id) values(false, "Hala Sumice", 2);
insert into hall(deleted, name, location_id) values(false, "Hala Pionir Sabac", 3);


insert into sector(deleted, num_of_columns, num_of_rows, sector_mark, hall_id) values(false, 4, 4, "SEC444", 1);
insert into sector(deleted, num_of_columns, num_of_rows, sector_mark, hall_id) values(false, 2, 6, "SEC266", 2);
insert into sector(deleted, num_of_columns, num_of_rows, sector_mark, hall_id) values(false, 3, 4, "SEC344", 3);

insert into event(deleted, end_date, event_type, name, start_date, location_id) values(false, "2020-02-08", 1, "VIP Fest", "2020-02-02", 2);
insert into event(deleted, end_date, event_type, name, start_date, location_id) values(false, "2020-05-08", 2, "Sajam poljoprivrede", "2020-05-02", 1);
insert into event(deleted, end_date, event_type, name, start_date, location_id) values(false, "2020-10-15", 1, "Sabacki festival", "2020-10-10", 2);

insert into event_sector(price, sector_type, event_id, sector_id) values(200.0, 1, 1, 2);
insert into event_sector(price, sector_type, event_id, sector_id) values(330.0, 2, 1, 2);
insert into event_sector(price, sector_type, event_id, sector_id) values(500.0, 0, 2, 3);
insert into event_sector(price, sector_type, event_id, sector_id) values(500.0, 0, 2, 1);

insert into user(id, address, date_of_birth, email, first_name, last_name, password, phone_number, username) values 
(1, "Adresa", "1990-01-01", "visitor@visitor.com", "Ime", "Prezime", "visitor", "060123456", "visitor");	

insert into visitor(active, blocked, id) values (1, 0, 1);

insert into reservation(id, canceled, deleted, expire_date, reservation_date, visitor_id, price) values (1, 0, 0, "2020-05-03", "2020-05-02", "1", 200);

insert into ticket(id, bought, reserved, seat_column, seat_row, visitor_id, event_sector_id, reservation_id) values (1, 0, 1, 1, 1, 1, 1, 1);

insert into user(address, date_of_birth, email, first_name, last_name, password, phone_number, username) values 
("Bulevar Oslobodjenja", "1990-02-02", "user@user.com", "Pera", "Zderic", "sifra123", "060123456", "korisnik");

