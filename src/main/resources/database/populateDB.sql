INSERT INTO universal_user VALUES
('6cc23ff9-79ac-4ee4-be0f-22d6679fc2e4',
 'SUPERUSER',
 '$2a$08$Rt0AsbUU4ELmvezc0M45OuZiHNwDxhewyRI3Q47dsVZosY7atzqv2', --m_32_fq_222_fp!pa
 NULL,
 NULL
) ON CONFLICT (id) DO NOTHING;

INSERT INTO administrator_key_code VALUES
(
 '37fa0b65-ef61-43ad-b632-770ce95615de',
 '6cc23ff9-79ac-4ee4-be0f-22d6679fc2e4',
 '9ae6f7ad`42e3!4f30?a6e8-091b24f6cff1'
) ON CONFLICT (id) DO NOTHING;
