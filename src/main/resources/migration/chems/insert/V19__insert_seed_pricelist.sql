INSERT INTO pricelist (contractor_id, service_type, quantity_of_hours, price, experience) VALUES
    (1, 'PHOTOGRAPHER', 2, 5000, '10 years in wedding photography'),
    (1, 'PHOTOGRAPHER', 4, 9000, 'Over 200 completed shoots'),
    (1, 'PHOTOGRAPHER', 8, 15000, 'Professional equipment'),
    (2, 'VIDEOGRAPHER', 4, 12000, 'Full day shooting and editing'),
    (2, 'VIDEOGRAPHER', 6, 18000, 'Multi-camera shooting'),
    (3, 'CHEF', 3, 25000, 'Catering for 50 people'),
    (3, 'CHEF', 5, 40000, 'European cuisine buffet'),
    (4, 'LEAD', 4, 8000, 'Event host since 2015'),
    (4, 'LEAD', 6, 12000, 'Interactive programs and games')
ON CONFLICT DO NOTHING;