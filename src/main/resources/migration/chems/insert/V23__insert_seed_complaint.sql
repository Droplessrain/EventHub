INSERT INTO complaint (dateTime, title, description, user_id, contractor_id, status) VALUES
    ('2024-05-10', 'Late arrival', 'Contractor was 2 hours late without notification', 2, 1, 'PENDING'),
    ('2024-05-11', 'Poor quality service', 'Video was poorly edited, important moments missed', 3, 2, 'ACCEPTED'),
    ('2024-05-12', 'Agreement violation', 'Menu differed from agreed one', 4, 3, 'REJECTED')
ON CONFLICT DO NOTHING;