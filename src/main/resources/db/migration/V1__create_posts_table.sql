CREATE TABLE `post` (
    `id` bigint(20) NOT NULL,
    `title` varchar(255) NOT NULL,
    `body` text NOT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
