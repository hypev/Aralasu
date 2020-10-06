-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 06, 2020 at 04:47 AM
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
(6, 16, 'DANK MEMES', '<p>xD</p>', '<p><img src=\"https://sun9-57.userapi.com/aH80nmEsL5ylJn3wyiL7LqJnKGIVN_uhS-1SpA/52jNKQvzfZM.jpg\" /></p>', '2020-10-05 21:03:33'),
(7, 16, 'As always', '<p>Some post</p>', '<p><img src=\"https://sun2.beeline-kz.userapi.com/Es0ZxndubyncznhuhEpuliIpi8r9mefP1ybTaw/XBjWhq5BUgM.jpg\" /></p>', '2020-10-06 02:36:06'),
(8, 16, 'ahahahaha', '<p><img src=\"https://sun2.beeline-kz.userapi.com/izx3Oz3lzCy2lL4UVJqdHVW2res4qkSLmowoYg/fRf7i-EBMNA.jpg\" /></p>', '<p>xD</p>', '2020-10-05 21:06:03'),
(9, 3, 'Деда смог', '<p><a class=\"mem_link\" style=\"cursor: pointer; font-family: -apple-system, BlinkMacSystemFont, Roboto, \'Open Sans\', \'Helvetica Neue\', \'Noto Sans Armenian\', \'Noto Sans Bengali\', \'Noto Sans Cherokee\', \'Noto Sans Devanagari\', \'Noto Sans Ethiopic\', \'Noto Sans Georgian\', \'Noto Sans Hebrew\', \'Noto Sans Kannada\', \'Noto Sans Khmer\', \'Noto Sans Lao\', \'Noto Sans Osmanya\', \'Noto Sans Tamil\', \'Noto Sans Telugu\', \'Noto Sans Thai\', sans-serif; font-size: 13px; background-color: #ffffff;\" href=\"https://vk.com/rhymes\">Дональд Трамп</a><span style=\"font-family: -apple-system, BlinkMacSystemFont, Roboto, \'Open Sans\', \'Helvetica Neue\', \'Noto Sans Armenian\', \'Noto Sans Bengali\', \'Noto Sans Cherokee\', \'Noto Sans Devanagari\', \'Noto Sans Ethiopic\', \'Noto Sans Georgian\', \'Noto Sans Hebrew\', \'Noto Sans Kannada\', \'Noto Sans Khmer\', \'Noto Sans Lao\', \'Noto Sans Osmanya\', \'Noto Sans Tamil\', \'Noto Sans Telugu\', \'Noto Sans Thai\', sans-serif; font-size: 13px; background-color: #ffffff;\">&nbsp;заявил о том, что выписывается из больницы после болезни коронавирусом</span></p>', '<p><img src=\"https://sun9-28.userapi.com/impg/fR-pa28e4q8_YtMZiuw-WNc38dDh2tgEUCaSRg/JVDv3Q9n4R8.jpg?size=1022x1020&amp;quality=90&amp;proxy=1&amp;sign=e4e684efd7422d7a5f33d60645dccadb\" /></p>', '2020-10-06 02:38:03');

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
(8, 'telegey.abdizhakipova@gmail.com', 'asdasd', 'Телегей Абдижакипова', '2000-09-19', 'https://sun1.beeline-kz.userapi.com/c854328/v854328169/1f74d9/IhxQ4L0lEAM.jpg'),
(9, 'amirkhanova.aruzhaaan@gmail.com', 'asdasd', 'Аружан Амирханова', '2002-02-07', 'https://sun9-39.userapi.com/Gxk-24mh8sai-718ESWm9B_UcRNKDAN261UwgQ/skfByy33ypo.jpg'),
(16, 'hypevstore@gmail.com', '', 'Руслан Курбанали', '2001-08-17', 'https://sun1.beeline-kz.userapi.com/impg/c858332/v858332585/229a1c/LZtqh2-hO5E.jpg?size=200x0&quality=90&crop=0,1,1188,1188&sign=a5347b6371133673c27cc9771e3f2fa3&c_uniq_tag=hnLNebbYq4NFrksrUX5paYXQcNw_VYN1Kb35TTP2Wvw&ava=1');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `auth_tokens`
--
ALTER TABLE `auth_tokens`
  ADD CONSTRAINT `ssuid_users__fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_users__fk` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
