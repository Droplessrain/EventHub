INSERT INTO event (users_id, title, type_of_event, date, total_time, description) VALUES
    (2, 'Anna and Max Wedding', 'WEDDING', '2024-06-15', 8, 'Ceremony and banquet in country restaurant'),
    (3, 'IT Company Corporate', 'CORPORATE', '2024-12-20', 6, 'Annual New Year corporate party for employees'),
    (4, 'Graduation 2024', 'GRADUATIONEVENT', '2024-06-25', 5, 'University students graduation evening'),
    (5, 'Child Birthday Party', 'BIRTHDAY', '2024-08-10', 4, '5th birthday celebration with animators and show programs')
ON CONFLICT DO NOTHING;