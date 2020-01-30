-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-01-2020 a las 20:00:01
-- Versión del servidor: 5.7.17
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_festival`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_event`
--

CREATE TABLE `t_event` (
  `id_events` int(3) UNSIGNED NOT NULL,
  `id_users_events` int(3) UNSIGNED NOT NULL,
  `date_events` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `place_events` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `name_events` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `price` float UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_event`
--

INSERT INTO `t_event` (`id_events`, `id_users_events`, `date_events`, `place_events`, `name_events`, `price`) VALUES
(1, 1, '2020-07-23', 'Madrid', 'Rock in Rio', 30),
(2, 2, '2019-04-14', 'Da’anshan', 'Brassicaceae', 40.9),
(3, 3, '2019-09-28', 'Bennäs', 'Orthotrichaceae', 66),
(4, 4, '2019-11-19', 'Anta', 'Ophioglossaceae', 72.1),
(7, 1, '2019-12-25', 'otro lugar', 'insercion de prueba', 56.9),
(8, 1, '2019-12-27', 'Madrid Rio', 'Navidad 2019', 23.56);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_events_favorites`
--

CREATE TABLE `t_events_favorites` (
  `id_ev_fav` int(3) UNSIGNED NOT NULL,
  `id_user_ev_fav` int(3) UNSIGNED NOT NULL,
  `id_ev` int(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_events_favorites`
--

INSERT INTO `t_events_favorites` (`id_ev_fav`, `id_user_ev_fav`, `id_ev`) VALUES
(1, 1, 1),
(5, 1, 2),
(17, 1, 3),
(3, 2, 1),
(2, 2, 4),
(4, 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_events_groups`
--

CREATE TABLE `t_events_groups` (
  `id_ev_group` int(11) NOT NULL,
  `id_group` int(3) UNSIGNED NOT NULL,
  `id_event` int(3) UNSIGNED NOT NULL,
  `hour_ev_group` varchar(20) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_events_groups`
--

INSERT INTO `t_events_groups` (`id_ev_group`, `id_group`, `id_event`, `hour_ev_group`) VALUES
(2, 8, 1, '19:30'),
(3, 16, 1, '19:30'),
(4, 10, 2, '21:00'),
(5, 8, 2, '20:00'),
(6, 16, 7, '23:00'),
(7, 10, 1, '20:00'),
(8, 11, 8, '19:00'),
(9, 8, 8, '21:00'),
(10, 16, 8, '23:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_group`
--

CREATE TABLE `t_group` (
  `id_group` int(3) UNSIGNED NOT NULL,
  `name_group` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `logo_group` varchar(200) COLLATE latin1_general_ci NOT NULL,
  `obs_group` varchar(200) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_group`
--

INSERT INTO `t_group` (`id_group`, `name_group`, `logo_group`, `obs_group`) VALUES
(8, 'Kiss', '/images/kiss.jpg', 'Música Rock and Roll'),
(10, 'Marea', '/images/marea2.jpg', 'Grupo de música Español.\nMúsica Rock'),
(11, 'Estopa', '/images/Estopa.jpg', 'Grupo Español.\nJosé Manuel y David Muñoz\nConocidos por la raja de tu falda y \npor estrellar un seat panda'),
(16, 'Metallica', '/images/metallica.jpg', 'Lo más mejor'),
(26, 'The Killers', '/images/Killers.jpg', 'Rock'),
(28, 'Four Liars', '/images/marea.jpg', 'VVVV');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_purchase_ev`
--

CREATE TABLE `t_purchase_ev` (
  `id_purchase_ev` bigint(20) UNSIGNED NOT NULL,
  `id_event_purchase_ev` int(11) NOT NULL,
  `name_purchase_ev` text COLLATE latin1_general_ci NOT NULL,
  `email_purchase_ev` text COLLATE latin1_general_ci NOT NULL,
  `pay_purchase_ev` text COLLATE latin1_general_ci NOT NULL,
  `address_purchase_ev` text COLLATE latin1_general_ci NOT NULL,
  `cp_purchase_ev` int(5) NOT NULL,
  `nif_purchase_ev` char(9) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci COMMENT='Tabla de compras de entradas a eventos';

--
-- Volcado de datos para la tabla `t_purchase_ev`
--

INSERT INTO `t_purchase_ev` (`id_purchase_ev`, `id_event_purchase_ev`, `name_purchase_ev`, `email_purchase_ev`, `pay_purchase_ev`, `address_purchase_ev`, `cp_purchase_ev`, `nif_purchase_ev`) VALUES
(1, 1, 'Paco', 'cliente1@gmai.com', 'Paypal', 'Calle pinocho,8', 28040, '45693256L'),
(2, 2, 'Pedro', 'cliente2@gmail.com', 'paypal', 'calle menor 14', 25040, '12457896B'),
(3, 3, 'Alvaro', 'alvaro@gmail.com', 'PayPal', 'Calle calle', 87654, '09876543K');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_types_users`
--

CREATE TABLE `t_types_users` (
  `id_types_user` int(3) UNSIGNED NOT NULL,
  `types` varchar(20) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_types_users`
--

INSERT INTO `t_types_users` (`id_types_user`, `types`) VALUES
(1, 'Administrador'),
(2, 'Normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_users`
--

CREATE TABLE `t_users` (
  `id_user` int(3) UNSIGNED NOT NULL,
  `nick_user` varchar(15) COLLATE latin1_general_ci NOT NULL,
  `pass_user` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `cod_types_user` int(3) UNSIGNED NOT NULL,
  `nombre_user` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `apellidos_user` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `nif_user` char(9) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Volcado de datos para la tabla `t_users`
--

INSERT INTO `t_users` (`id_user`, `nick_user`, `pass_user`, `cod_types_user`, `nombre_user`, `apellidos_user`, `nif_user`) VALUES
(1, '123456', '123456', 1, 'pepe', 'gonzalez', '12345678A'),
(2, 'david', 'david', 2, 'david', 'david', '45693256L'),
(3, 'mari', 'mari', 2, 'mari', 'mari', '12345678F'),
(4, 'ASD', 'ASD', 2, 'ASD', 'ASD', '12345678D'),
(20, 'loli', 'loli', 2, 'loli', 'loli', '1'),
(23, 'pepi', 'pepi', 2, 'pepi', 'pepi', '5'),
(24, 'oli', 'oli', 2, 'oli', 'oli', '65'),
(25, 'juan', 'juan', 2, 'juan', 'juan', '85214796G'),
(27, 'ASDASD', 'ASD', 2, 'ASDASD', 'ASD', '63985214A'),
(28, 'qwer', 'qwer', 2, 'qwer', 'qwer', '45665445R'),
(29, 'po', 'po', 2, 'po', 'po', '45454545P'),
(31, 'oi', 'oi', 2, 'oi', 'oi', '65656565O'),
(32, 'ASDASDASD', 'ASDASDASD', 2, 'ASDASDASD', 'ASDASDASD', '77788899D'),
(33, 'juanCarlos', '123456', 2, 'Juan', 'Carlos', '12345987L'),
(34, 'luis', 'luis', 2, 'Luis', 'Fernandez Martin', '01010101U');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `t_event`
--
ALTER TABLE `t_event`
  ADD PRIMARY KEY (`id_events`),
  ADD KEY `FK_USERS` (`id_users_events`);

--
-- Indices de la tabla `t_events_favorites`
--
ALTER TABLE `t_events_favorites`
  ADD PRIMARY KEY (`id_ev_fav`),
  ADD UNIQUE KEY `useryevent` (`id_user_ev_fav`,`id_ev`),
  ADD KEY `eventoFavorito` (`id_ev`);

--
-- Indices de la tabla `t_events_groups`
--
ALTER TABLE `t_events_groups`
  ADD PRIMARY KEY (`id_ev_group`),
  ADD UNIQUE KEY `grupoyevent` (`id_group`,`id_event`),
  ADD KEY `FK_t_group` (`id_group`),
  ADD KEY `FK_t_event` (`id_event`);

--
-- Indices de la tabla `t_group`
--
ALTER TABLE `t_group`
  ADD PRIMARY KEY (`id_group`),
  ADD UNIQUE KEY `name_group` (`name_group`);

--
-- Indices de la tabla `t_purchase_ev`
--
ALTER TABLE `t_purchase_ev`
  ADD PRIMARY KEY (`id_purchase_ev`),
  ADD UNIQUE KEY `id_purchase` (`id_purchase_ev`),
  ADD UNIQUE KEY `eventynif` (`id_event_purchase_ev`,`nif_purchase_ev`);

--
-- Indices de la tabla `t_types_users`
--
ALTER TABLE `t_types_users`
  ADD PRIMARY KEY (`id_types_user`);

--
-- Indices de la tabla `t_users`
--
ALTER TABLE `t_users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `nick_user` (`nick_user`),
  ADD UNIQUE KEY `uniq` (`nif_user`),
  ADD KEY `cod_types_user` (`cod_types_user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `t_event`
--
ALTER TABLE `t_event`
  MODIFY `id_events` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `t_events_favorites`
--
ALTER TABLE `t_events_favorites`
  MODIFY `id_ev_fav` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `t_events_groups`
--
ALTER TABLE `t_events_groups`
  MODIFY `id_ev_group` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `t_group`
--
ALTER TABLE `t_group`
  MODIFY `id_group` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `t_purchase_ev`
--
ALTER TABLE `t_purchase_ev`
  MODIFY `id_purchase_ev` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `t_types_users`
--
ALTER TABLE `t_types_users`
  MODIFY `id_types_user` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `t_users`
--
ALTER TABLE `t_users`
  MODIFY `id_user` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `t_event`
--
ALTER TABLE `t_event`
  ADD CONSTRAINT `FK_USERS_events` FOREIGN KEY (`id_users_events`) REFERENCES `t_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `t_events_favorites`
--
ALTER TABLE `t_events_favorites`
  ADD CONSTRAINT `eventoFavorito` FOREIGN KEY (`id_ev`) REFERENCES `t_event` (`id_events`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuarioFavorito` FOREIGN KEY (`id_user_ev_fav`) REFERENCES `t_users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `t_events_groups`
--
ALTER TABLE `t_events_groups`
  ADD CONSTRAINT `FK_t_event` FOREIGN KEY (`id_event`) REFERENCES `t_event` (`id_events`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_t_group` FOREIGN KEY (`id_group`) REFERENCES `t_group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `t_users`
--
ALTER TABLE `t_users`
  ADD CONSTRAINT `type` FOREIGN KEY (`cod_types_user`) REFERENCES `t_types_users` (`id_types_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
