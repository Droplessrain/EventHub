INSERT INTO contractor (name, lastName, birthdate, usersId, description, tower) VALUES
    ('Alex', 'Johnson', '1985-03-15', 2, 'Experienced photographer with 10 years of expertise. Specializing in wedding and portrait photography.', 'Photographers'),
    ('Maria', 'Garcia', '1990-07-22', 3, 'Professional videographer. Shooting corporate events, weddings and children parties.', 'Videographers'),
    ('David', 'Smith', '1988-11-30', 4, 'Chef with European experience. Organizing catering for events of any scale.', 'Chefs'),
    ('Anna', 'Brown', '1992-05-10', 5, 'Event host. Creating holiday atmosphere and comfort for guests.', 'Hosts')
ON CONFLICT DO NOTHING;