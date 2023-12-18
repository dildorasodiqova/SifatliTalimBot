INSERT INTO profile (id, created_date, visible, full_name, password, login, role)
VALUES (1, '2023-12-11 16:16:12.000000', true, 'AdminJon',
        '$2a$10$n6Q6/ONcMvOLanohMIaj0O/N0maBwvHCuQNMCaqvlMCbiWZ.1/u1W', 'admin', 'ROLE_ADMIN')
on conflict (id) do nothing;

SELECT setval('profile_id_seq', max(id))
FROM profile;
