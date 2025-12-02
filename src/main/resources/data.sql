INSERT INTO auth.tb_user (name, username, email, password, active, role, created_at)
VALUES ('Administrator user', 'admin', 'admin@local.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, 'ADM', CURRENT_TIMESTAMP),
('John Connor', 'john.connor', 'john.connor@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, 'USR', CURRENT_TIMESTAMP),
('Anne Lewis', 'anne.lewis', 'anne.lewis@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', false, 'USR', CURRENT_TIMESTAMP);
