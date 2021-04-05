-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 31 Mar 2021 pada 14.30
-- Versi server: 10.4.8-MariaDB
-- Versi PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user-management`
--
DROP DATABASE IF EXISTS `user-management`;
CREATE DATABASE IF NOT EXISTS `user-management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `user-management`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_user`
--

CREATE TABLE `master_user` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `employee_number` bigint(20) DEFAULT NULL,
  `birth_place` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `active` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `master_user`
--

INSERT INTO `master_user` (`user_id`, `email`, `password`, `employee_number`, `birth_place`, `birth_date`, `active`) VALUES
(1, 'aris@atk.id', 'e10adc3949ba59abbe56e057f20f883e', 2021001, 'Jambi', '1992-01-21', b'0'),
(2, 'aris@atk.id', 'e10adc3949ba59abbe56e057f20f883e', 2021002, 'Riau', '1992-01-21', b'1'),
(3, 'aris@atx.id', 'e10adc3949ba59abbe56e057f20f883e', 2021003, 'Kebumen', '1992-12-21', b'1'),
(4, 'aris@atk.id', 'e10adc3949ba59abbe56e057f20f883e', 2021004, 'Kebumen', '1992-01-21', b'1'),
(5, 'aris@atk.id', 'c33367701511b4f6020ec61ded352059', 2021005, 'Samarinda', '1992-01-21', b'1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_jwt_token`
--

CREATE TABLE `mst_jwt_token` (
  `id` bigint(20) NOT NULL,
  `approved` bit(1) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `grant_types` varchar(255) DEFAULT NULL,
  `has_scope` bit(1) DEFAULT NULL,
  `has_secret` bit(1) DEFAULT NULL,
  `redirect_uris` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `expired` bigint(20) DEFAULT NULL,
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mst_jwt_token`
--

INSERT INTO `mst_jwt_token` (`id`, `approved`, `authorities`, `client_id`, `created`, `grant_types`, `has_scope`, `has_secret`, `redirect_uris`, `scope`, `client_secret`, `expired`, `updated`) VALUES
(1, b'1', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 'my-client-id', '2020-09-04 15:00:00', 'password,authorization_code,refresh_token,implicit', b'1', b'1', '', 'read,write,trust', '5f4dcc3b5aa765d61d8327deb882cf99', 3600, '2020-09-04 15:00:00'),
(2, b'1', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 'mobile-client-id', '2020-09-04 15:00:00', 'password,authorization_code,refresh_token,implicit', b'1', b'1', '', 'read,write,trust', '5f4dcc3b5aa765d61d8327deb882cf99', -1, '2020-09-04 15:00:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_role`
--

CREATE TABLE `mst_role` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mst_role`
--

INSERT INTO `mst_role` (`id`, `created`, `name`, `status`, `updated`) VALUES
(1, '2021-03-31 15:55:55', 'ROLE_ADMIN', 1, '2021-03-31 15:55:55'),
(2, '2021-03-31 15:55:55', 'ROLE_USER', 1, '2021-03-31 15:55:55');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_user_access`
--

CREATE TABLE `mst_user_access` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mst_user_access`
--

INSERT INTO `mst_user_access` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `master_user`
--
ALTER TABLE `master_user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `email` (`email`),
  ADD KEY `employee_number` (`employee_number`);

--
-- Indeks untuk tabel `mst_jwt_token`
--
ALTER TABLE `mst_jwt_token`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `mst_role`
--
ALTER TABLE `mst_role`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `mst_user_access`
--
ALTER TABLE `mst_user_access`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKq0c8f6fap3qq781swkqp7l2l9` (`role_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `master_user`
--
ALTER TABLE `master_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `mst_jwt_token`
--
ALTER TABLE `mst_jwt_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `mst_role`
--
ALTER TABLE `mst_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `mst_user_access`
--
ALTER TABLE `mst_user_access`
  ADD CONSTRAINT `FKbbh1ghsp9ibthcok9s50ck6q1` FOREIGN KEY (`user_id`) REFERENCES `master_user` (`user_id`),
  ADD CONSTRAINT `FKq0c8f6fap3qq781swkqp7l2l9` FOREIGN KEY (`role_id`) REFERENCES `mst_role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
