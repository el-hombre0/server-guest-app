insert into position (title)
values ('Project Manager'),
    ('Tester'),
    ('Programmer');

insert into guest (email, first_name, last_name)
values ('stas@mail.com', 'Stas', 'Efimtsev'),
    ('max@gmail.com', 'Maxim', 'Ivanov'),
    (
        'hopelessgeorge@hotmail.com',
        'George',
        'TheCoolest'
    );

insert into phone (phone_number, guest_id)
values ('89999122745', 1),
    ('84952345637', 1),
    ('89124326711', 2),
    ('89115643217', 3);
    
insert into guest_position (guest_id, position_id)
values(1, 2),
    (1, 3),
    (2, 2),
    (3, 2),
    (3, 3);