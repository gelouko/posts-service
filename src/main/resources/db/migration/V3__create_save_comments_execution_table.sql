CREATE TABLE `save_comments_execution` (
    `id` bigint(20) NOT NULL,
    `identifier` binary(16) NOT NULL,
    `status` enum('PENDING', 'FETCHING_COMMENTS', 'SAVING_COMMENTS', 'FETCHING_POSTS',
    'SAVING_POSTS', 'FINISHED') NOT NULL DEFAULT 'PENDING',
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
