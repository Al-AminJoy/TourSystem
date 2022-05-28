

INSERT INTO `role` (`id`, `role`) VALUES (2, 'ADMIN'), (1, 'USER');


INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `num1`, `num2`, `primary_num`, `user_address`, `user_dob`, `user_email`, `user_gender`, `password`, `user_name`, `email`) VALUES
(1, 'alamin ', 'joy', '01234567891', '01234567891', '01234567891', 'badda', '1995-05-12', 'joy@email.com', 'MALE', '$2a$12$TrFzVIxfnjmliF7ZRT4bKu3hsZVE9esv/Pw1kBLxeawDvUs5Un1GK', 'joy', 'joy@email.com');


INSERT INTO `user_roles` (`users_user_id`, `roles_id`) VALUES (1, 2);


