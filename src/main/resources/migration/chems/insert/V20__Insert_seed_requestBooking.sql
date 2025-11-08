INSERT INTO requestBooking (dateTime, usersId, contractorId, eventId, status, title, description) VALUES
    ('2024-05-20', 2, 1, 1, 'PENDING', 'Wedding Photography', 'Need photographer for entire ceremony and banquet'),
    ('2024-05-18', 3, 2, 2, 'ACCEPTED', 'Corporate Video Shooting', 'Videographer required for event coverage'),
    ('2024-05-15', 4, 3, 3, 'COMPLETED', 'Graduation Catering', 'Buffet organization for 100 people'),
    ('2024-05-22', 5, 4, 4, 'REJECTED', 'Birthday Party Host', 'Host needed for children party')
ON CONFLICT DO NOTHING;