-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2015 at 07:40 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `smart_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `interest_table`
--

CREATE TABLE IF NOT EXISTS `interest_table` (
  `id` int(11) NOT NULL,
  `posts` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `key_table`
--

CREATE TABLE IF NOT EXISTS `key_table` (
  `id` int(11) NOT NULL,
  `keyword` varchar(100) NOT NULL,
  `posts` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `key_table`
--

INSERT INTO `key_table` (`id`, `keyword`, `posts`) VALUES
(1, 'Chelsea', 'I am a cool Chelsea fc fan.'),
(2, 'Love', 'She is in love with the guy.'),
(3, 'School', 'Tallinn University of Technology.'),
(4, 'Manchester United', 'I am not a fan of Man U but i love Wayne Rooney.'),
(5, 'Uzochukwu', 'My name is Uzochukwu Eddie Odozi'),
(6, 'Real madrid', 'Madrid Drew with Valencia today!!!'),
(7, 'liverpool', 'liverpool and chelsea');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `interest_table`
--
ALTER TABLE `interest_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `key_table`
--
ALTER TABLE `key_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `interest_table`
--
ALTER TABLE `interest_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `key_table`
--
ALTER TABLE `key_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
