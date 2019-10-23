CREATE TABLE `comment` (
    `id` bigint(20) NOT NULL,
    `post_id` bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `body` text NOT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
    INDEX (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
