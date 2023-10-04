insert into guest (email, first_name, last_name)
values ('stas@mail.com', 'Stas', 'Efimtsev'),
    ('max@gmail.com', 'Maxim', 'Ivanov'),
    (
        'hopelessgeorge@hotmail.com',
        'George',
        'TheCoolest'
    );
insert into phone (phone_number)
values ('89999122745'),
    ('84952345637'),
    ('89124326711');
insert into position (title)
values ('Project Manager'),
    ('Tester'),
    ('Programmer');
insert into guest_position (guest_id, position_id) values(1, 2),
(1, 3), (2, 2), (3, 2), (3, 3);
inser into guest_phone(guest_id, phones_id)
values (1, )
create table guest (
        id bigserial not null,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        primary key (id)
    )
Hibernate: 
    create table guest_phones (
        guest_id bigint not null,
        phones_id bigint not null,
        primary key (guest_id, phones_id)
    )
Hibernate: 
    create table guest_position (
        guest_id bigint not null,
        position_id bigint not null,
        primary key (guest_id, position_id)
    )
Hibernate: 
    create table phone (
        id bigserial not null,
        phone_number varchar(255),
        primary key (id)
    )
Hibernate: 
    create table position (
        id bigserial not null,
        title varchar(255),
        primary key (id)
    )