-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-02-2025 a las 06:23:23
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `moviesdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movie`
--

CREATE TABLE `movie` (
  `id_movie` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `release_year` int(11) DEFAULT NULL,
  `synopsis` text DEFAULT NULL,
  `image_poster` blob DEFAULT NULL,
  `id_category` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `created_date` date NOT NULL,
  `user_rate` int(11) DEFAULT NULL,
  `date_rate` date DEFAULT NULL,
  `rate` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movie`
--

INSERT INTO `movie` (`id_movie`, `name`, `release_year`, `synopsis`, `image_poster`, `id_category`, `id_user`, `created_date`, `user_rate`, `date_rate`, `rate`) VALUES
(11, 'Rambo I', 1982, 'El veterano de Vietnam, John Rambo, envuelve a la policía en una cacería en el bosque tras escapar de un vil comisario.', NULL, 7, 2, '2025-02-21', NULL, NULL, NULL),
(13, 'Titanic', 1997, 'Una joven de la alta sociedad abandona a su arrogante pretendiente por un artista humilde en el trasatlántico que se hundió durante su viaje inaugural', NULL, 10, 2, '2025-02-21', NULL, NULL, NULL),
(16, 'Mi primer beso', 2000, 'A woman gets kissed by first time', NULL, 10, 2, '2025-02-22', 2, '2025-02-22', 'Excellent Movie'),
(20, 'Love in university', 2001, 'two youngs in love in the university', NULL, 10, 2, '2025-02-25', NULL, NULL, NULL),
(21, 'Mi villano favorito 2', 2005, 'One family goes for a trip and forget his little kid', NULL, 8, 14, '2025-02-25', 14, '2025-02-25', 'This movie was really amazing! Better than first.'),
(22, 'Mi villano favorito 1: First Adventure!', 2007, 'this movie is related to yellow kids, first version called first adventure!', NULL, 8, 14, '2025-02-25', 14, '2025-02-25', 'I think this is a really funny movie for kids');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movie_category`
--

CREATE TABLE `movie_category` (
  `id_category` int(11) NOT NULL,
  `category_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movie_category`
--

INSERT INTO `movie_category` (`id_category`, `category_name`) VALUES
(7, 'action'),
(8, 'comedy'),
(9, 'science fiction'),
(10, 'Romance'),
(12, 'Drama');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `rol_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `rol_name`) VALUES
(1, 'admin'),
(2, 'user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_user`, `name`, `lastname`, `username`, `password`, `id_rol`) VALUES
(1, 'Gerardo', 'Campos', 'gerardocampos2112@gmail.com', '$2a$10$BAbM8tIaMMaU3DOkmXcpteK1GD6aXwPvvniyET3AQpyfZcX.0hvHW', 1),
(2, 'Abigail', 'Juarez', 'rosalesabigail267@gmail.com', '$2a$10$/bEZm4XaFcLI535lljXDR.FvErJtinou5M3UbbfAeoq6WfkTMdjiC', 1),
(14, 'Luis', 'Martinez', 'lamartinez@gmail.com', '$2a$10$TtTRdT9nRXk.B.RPMkj/X.8v.V3DbJuySFnhkyeRMAf.b0uNSDlGK', 1),
(17, 'Melissa', 'Campos', 'mcampos25@gmail.com', '$2a$10$btQEzGy2JcqFBaPgLIROTO/zIidKfj75DD6sJTqFeQCLI909M7DtG', 2),
(18, 'Maria', 'Juarez', 'mjuarez25@gmail.com', '$2a$10$q9GNAXGiuYxTOFR371ywve1Dh1.CiuGFfMXf5rDuCZ5cGwj7316OS', 2),
(19, 'Victor', 'Perez', 'vperez25@gmail.com', '$2a$10$heP.4tJux3qbrO3P9T3RmOqWcWcttAukoek0G2LENB5csvdbsGESi', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id_movie`),
  ADD KEY `movie_category` (`id_category`),
  ADD KEY `movie_user` (`id_user`),
  ADD KEY `movie_user_rate` (`user_rate`);

--
-- Indices de la tabla `movie_category`
--
ALTER TABLE `movie_category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username_unique` (`username`),
  ADD KEY `user_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `movie`
--
ALTER TABLE `movie`
  MODIFY `id_movie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `movie_category`
--
ALTER TABLE `movie_category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `movie_category` FOREIGN KEY (`id_category`) REFERENCES `movie_category` (`id_category`),
  ADD CONSTRAINT `movie_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `movie_user_rate` FOREIGN KEY (`user_rate`) REFERENCES `user` (`id_user`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
