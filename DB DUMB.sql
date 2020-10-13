-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2020 at 04:17 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `task5`
--

-- --------------------------------------------------------

--
-- Table structure for table `auth_tokens`
--

CREATE TABLE `auth_tokens` (
  `id` int(11) NOT NULL,
  `selector` varchar(12) NOT NULL,
  `validator` varchar(64) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auth_tokens`
--

INSERT INTO `auth_tokens` (`id`, `selector`, `validator`, `user_id`) VALUES
(104, '6Nl8dDfWWJhB', 'udcM9m0RpjwshkzBsgVEMNMLQcp2q4UHJpqICcXBZbhgIfmVtwIo6tD4wTX08Njc', 17),
(105, 'ohbyz6eNJidR', 'l53CXzsTFvfVIJZOvRLw7U66sykx7KVSXfN1Xio3Hi9ZrgUtQChm3CuV1lw6KS3w', 3),
(106, 'JbFFrfEXXfjd', 'FHN3lX6cuVlmjs1rRRdWTK0xvlxsWQN7PlrgWPOQATUyWTVPI8CcLYvKDKSReBz3', 7);

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `opponent_user_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `latest_message_text` text DEFAULT NULL,
  `latest_message_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`id`, `user_id`, `opponent_user_id`, `created_date`, `latest_message_text`, `latest_message_time`) VALUES
(9, 2, 17, '2020-10-10 21:03:18', 'd', '2020-10-10 21:03:18'),
(10, 3, 17, '2020-10-11 15:53:14', 'oh hi', '2020-10-11 15:53:14'),
(11, 4, 17, '2020-10-10 21:11:33', 'dwq', '2020-10-10 21:11:33'),
(12, 7, 17, '2020-10-13 02:15:49', 'dwqkmdlkqw', '2020-10-13 02:15:49');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `added_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`id`, `user_id`, `friend_id`, `added_time`) VALUES
(3, 3, 4, '2020-10-10 10:00:02'),
(4, 17, 4, '2020-10-10 10:00:03'),
(5, 3, 2, '2020-10-10 10:00:21'),
(6, 17, 2, '2020-10-10 10:00:23'),
(7, 3, 7, '2020-10-10 10:00:38'),
(9, 3, 5, '2020-10-10 10:00:58'),
(11, 3, 6, '2020-10-10 10:01:21'),
(12, 17, 6, '2020-10-10 10:01:23'),
(13, 17, 3, '2020-10-10 21:06:29');

-- --------------------------------------------------------

--
-- Table structure for table `friend_request`
--

CREATE TABLE `friend_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `request_sender_id` int(11) NOT NULL,
  `sent_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friend_request`
--

INSERT INTO `friend_request` (`id`, `user_id`, `request_sender_id`, `sent_time`) VALUES
(15, 2, 6, '2020-10-10 10:01:47');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `message_text` text NOT NULL,
  `read_by_receive` tinyint(1) NOT NULL,
  `sent_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `chat_id`, `user_id`, `sender_id`, `message_text`, `read_by_receive`, `sent_date`) VALUES
(17, 9, 2, 17, 'Hello', 1, '2020-10-10 20:42:34'),
(18, 9, 2, 17, 'So how are you?', 1, '2020-10-10 20:46:18'),
(19, 9, 17, 2, 'im fine, what about you?', 1, '2020-10-10 20:50:34'),
(20, 9, 2, 17, 'me too', 1, '2020-10-10 20:53:25'),
(21, 9, 17, 2, 'Some spam', 1, '2020-10-10 20:54:02'),
(22, 9, 17, 2, 'Some spam', 1, '2020-10-10 20:54:05'),
(23, 9, 17, 2, 'Some spam', 1, '2020-10-10 20:54:07'),
(24, 9, 17, 2, 'dkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsndkamsdjlnaldjsn', 1, '2020-10-10 20:54:14'),
(25, 9, 2, 17, 'd', 1, '2020-10-10 21:03:18'),
(26, 10, 3, 17, 'bez bab? lul', 1, '2020-10-10 21:05:00'),
(27, 10, 3, 17, 'gl hf', 1, '2020-10-10 21:05:47'),
(28, 10, 17, 3, 'ty', 1, '2020-10-10 21:06:09'),
(30, 10, 3, 17, 'Some spam', 1, '2020-10-10 21:09:54'),
(31, 10, 3, 17, 'dwqdq', 1, '2020-10-10 21:11:22'),
(32, 11, 4, 17, 'dwq', 0, '2020-10-10 21:11:33'),
(33, 10, 17, 3, 'hi', 1, '2020-10-11 15:53:06'),
(34, 10, 3, 17, 'oh hi', 1, '2020-10-11 15:53:14'),
(35, 12, 7, 17, 'hello', 1, '2020-10-13 02:15:33'),
(36, 12, 17, 7, 'dqwljndqjlw', 1, '2020-10-13 02:15:46'),
(37, 12, 17, 7, 'dwqkmdlkqw', 1, '2020-10-13 02:15:49');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `title` text NOT NULL,
  `short_content` text NOT NULL,
  `content` text NOT NULL,
  `pub_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `author_id`, `title`, `short_content`, `content`, `pub_date`) VALUES
(13, 17, 'То ли еще будет', '<p><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://sun9-62.userapi.com/iYowN_x2hj8R1NzTvqN8I4aFCJwLvK0V0Gmqfw/cMoueRa3l5k.jpg\" width=\"399\" height=\"522\" /></p>', '', '2020-10-09 17:48:39'),
(14, 17, ':]', '<p><img src=\"https://sun2.beeline-kz.userapi.com/RE8gKNuGyIpAYcWTWRWx1Ue_BW4U5YNSS3qc4w/5AUWfTnH7M4.jpg\" /></p>', '', '2020-10-09 17:49:29'),
(15, 17, '', '<p><img src=\"https://sun9-35.userapi.com/I-XkWYwZ23HqJsuVpawB9jxkB-o-UQQ4wi8yLA/1X1f-0yqnJc.jpg\" /></p>', '', '2020-10-09 17:50:23'),
(16, 5, 'hello world', '', '', '2020-10-09 18:46:03'),
(17, 3, '', '<p style=\"text-align: center;\"><img src=\"https://avatanplus.com/files/resources/original/5c16425682533167b6f32206.png\" alt=\"Наклейка без баб PNG - AVATAN PLUS\" /></p>', '', '2020-10-10 13:59:40');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `picture_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `full_name`, `birthdate`, `picture_url`) VALUES
(2, 'alisher.ayaganov@gmail.com', 'asdasd', 'Alisher Ayaganov', '2001-07-27', 'https://sun9-25.userapi.com/rebfvxLaaL2FTePzU6Ooqy6ZUGX_8BDbp5bLVg/TQSnB5ppJoA.jpg'),
(3, 'temirlan.assanov@gmail.com', 'asdasd', 'Temirlan Assanov', '2001-08-13', ''),
(4, 'azamat.zhybandykov@gmail.com', 'asdasd', 'Азамат Жубандыков', '1999-07-01', 'https://sun9-32.userapi.com/lZIlcQFco8iu_1bZ8dC_xOe4JGwH8-CbfzfjDQ/fujds_LVCvQ.jpg'),
(5, 'asselya.kasymzhanov@gmail.com', 'asdasd', 'Aselya Kassymzhanova', '2001-06-17', 'https://sun2.beeline-kz.userapi.com/c848524/v848524880/d4b96/-jjHHBKVVKc.jpg'),
(6, 'kasiyet.erkin@gmail.com', 'asdasd', 'Kasiyet Erkin', '2000-01-31', 'https://sun9-47.userapi.com/hwrHDKy2ndeEXTH5C2PVz9Gcj2vL7kVVmSdHTA/gWUkI5-bGu8.jpg'),
(7, 'alisher.ibraimov@gmail.com', 'asdasd', 'Алишер Ибраимов', '2001-01-02', 'https://sun9-24.userapi.com/ixcJfBLTrMXy61-flUC3Zbdgs-Qwrg8U5mZlzQ/RK0JJtcJvrw.jpg'),
(17, 'hypevstore@gmail.com', '', 'Руслан Курбанали', '2001-08-17', 'https://sun1.beeline-kz.userapi.com/c858332/v858332585/229a1c/LZtqh2-hO5E.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auth_tokens`
--
ALTER TABLE `auth_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ssuid_users__fk` (`user_id`);

--
-- Indexes for table `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chats_users__fk` (`user_id`),
  ADD KEY `chats_users__fk_2` (`opponent_user_id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id`),
  ADD KEY `friends_users__fk` (`user_id`),
  ADD KEY `friends_users__fk_2` (`friend_id`);

--
-- Indexes for table `friend_request`
--
ALTER TABLE `friend_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `friend_request_users__fk` (`user_id`),
  ADD KEY `friend_request_users__fk_2` (`request_sender_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `messages_chats__fk` (`chat_id`),
  ADD KEY `messages_users__fk` (`user_id`),
  ADD KEY `messages_users__fk_2` (`sender_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `posts_users__fk` (`author_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auth_tokens`
--
ALTER TABLE `auth_tokens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `friends`
--
ALTER TABLE `friends`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `friend_request`
--
ALTER TABLE `friend_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `auth_tokens`
--
ALTER TABLE `auth_tokens`
  ADD CONSTRAINT `ssuid_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `chats`
--
ALTER TABLE `chats`
  ADD CONSTRAINT `chats_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `chats_users__fk_2` FOREIGN KEY (`opponent_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `friends_users__fk_2` FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `friend_request`
--
ALTER TABLE `friend_request`
  ADD CONSTRAINT `friend_request_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `friend_request_users__fk_2` FOREIGN KEY (`request_sender_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_chats__fk` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`id`),
  ADD CONSTRAINT `messages_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `messages_users__fk_2` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_users__fk` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
