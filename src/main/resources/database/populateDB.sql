INSERT INTO universal_user VALUES
    (
        '6cc23ff9-79ac-4ee4-be0f-22d6679fc2e4',
        NULL,
        NULL,
        'SUPERUSER',
        1
    ) ON CONFLICT (id) DO NOTHING;

INSERT INTO administrator_key_code VALUES
    (
        '37fa0b65-ef61-43ad-b632-770ce95615de',
        '6cc23ff9-79ac-4ee4-be0f-22d6679fc2e4',
        '9ae6f7ad`42e3!4f30?a6e8-091b24f6'
    ) ON CONFLICT (id) DO NOTHING;

INSERT INTO user_group_roles VALUES
    (
        'f83d1d02-b2d1-4546-9f59-8997d1157f65',
        'Администратор',
        'Роль администратора приложения',
        true
    ) ON CONFLICT (id) DO NOTHING;

INSERT INTO user_group_roles VALUES
    (
        '932bc241-73ac-4664-a3b9-569ab9a167ed',
        'Слушатель',
        'Роль слушателя',
        true
    ) ON CONFLICT (id) DO NOTHING;

INSERT INTO user_group_roles VALUES
    (
        'd5803e83-dfe2-40cc-84e9-076ef97f7453',
        'Исполнитель',
        'Роль исполнителя',
        true
    ) ON CONFLICT (id) DO NOTHING;
