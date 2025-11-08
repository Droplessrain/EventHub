INSERT INTO contractorEvent (contractorId, eventId, dateTime, totalTime, cost, serviceType, description) VALUES
    (1, 1, '2024-06-15', 8, 15000, 'PHOTOGRAPHER', 'Full day wedding photography'),
    (2, 2, '2024-12-20', 6, 18000, 'VIDEOGRAPHER', 'Corporate event shooting with editing'),
    (3, 3, '2024-06-25', 5, 40000, 'CHEF', 'Graduation evening catering'),
    (4, 4, '2024-08-10', 4, 8000, 'LEAD', 'Children birthday party hosting')
ON CONFLICT DO NOTHING;