-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-10-2020 a las 05:03:50
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bodegas`
--

CREATE TABLE `bodegas` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `fecha_actualizacion` date DEFAULT NULL,
  `id_compuesto` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `sucursal_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `bodegas`
--

INSERT INTO `bodegas` (`id`, `cantidad`, `create_at`, `fecha_actualizacion`, `id_compuesto`, `nombre`, `precio_compra`, `precio_venta`, `producto_id`, `sucursal_id`) VALUES
(1, 10, '2020-10-07', NULL, '12', 'Bulevar', 1200, 2000, 2, 1),
(2, 10, '2020-10-08', NULL, '22', 'Alejandria', 1700, 2000, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `celular1` varchar(255) DEFAULT NULL,
  `celular2` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `apellido`, `celular1`, `celular2`, `create_at`, `direccion`, `documento`, `email`, `fecha`, `foto`, `nombre`, `telefono`, `region_id`) VALUES
(1, 'rodriguez', '9876543210', NULL, '2020-09-28', 'calle3', '1093738019', 'manuelyivanrc@ufps.edu.co', NULL, NULL, 'manuel', NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `numero_factura` int(11) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `total_factura` double DEFAULT NULL,
  `total_ganancia` double DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `sucursal_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_items`
--

CREATE TABLE `facturas_items` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `des_dinero` double DEFAULT NULL,
  `des_porcentaje` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `precio_comprado` double DEFAULT NULL,
  `precio_vendido` double DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `factura_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(5) NOT NULL,
  `create_at` date DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `codigo`, `create_at`, `descripcion`, `foto`, `nombre`) VALUES
(1, '555', '2020-09-04', 'sdfsd', NULL, 'zapatos'),
(2, '550', '2020-09-05', 'sdfsd', NULL, 'sandalias');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regiones`
--

CREATE TABLE `regiones` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `regiones`
--

INSERT INTO `regiones` (`id`, `create_at`, `nombre`) VALUES
(1, '2020-01-01', 'No Especifica'),
(2, '2020-01-01', 'La Ceiba'),
(3, '2020-01-01', 'Comuneros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_modificado` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `bodega_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `nombre`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `id` bigint(20) NOT NULL,
  `celular1` varchar(255) DEFAULT NULL,
  `celular2` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `geoposicion` varchar(255) DEFAULT NULL,
  `instagram` varchar(255) DEFAULT NULL,
  `nit` varchar(255) DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `numero_factura` int(11) DEFAULT NULL,
  `propietario` varchar(255) DEFAULT NULL,
  `regimen` varchar(255) DEFAULT NULL,
  `sede` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`id`, `celular1`, `celular2`, `create_at`, `direccion`, `facebook`, `geoposicion`, `instagram`, `nit`, `nombre`, `numero_factura`, `propietario`, `regimen`, `sede`, `telefono`) VALUES
(1, '300 392 2227', '55555555', '2018-01-01', 'Calle 11 Av.0 Centro Comercial Gran Bulevar. Local 143', 'Joiner Candado', 'Cucuta - Colombia', 'Joiner Candado', '1.090.363.231-0', 'Bulevar', 10, 'Joiner Obando', 'Simplificado', 'CLUB GALLERY AIRGUNS COLOMBIA', '5833938'),
(2, '300 392 2227', '55555555', '2018-01-01', 'Calle 11 Av.0 Centro Comercial Gran Bulevar. Local 143', 'Joiner Candado', 'Cucuta - Colombia', 'Joiner Candado', '1.090.363.231-0', 'Alejandria', 20, 'Joiner Obando', 'Simplificado', 'CLUB GALLERY AIRGUNS COLOMBIA', '5833938');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `celular1` varchar(10) NOT NULL,
  `celular2` varchar(10) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `direccion` varchar(50) NOT NULL,
  `documento` varchar(10) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `fecha` date NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(7) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `region_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `apellido`, `celular1`, `celular2`, `create_at`, `direccion`, `documento`, `email`, `enabled`, `fecha`, `foto`, `nombre`, `password`, `telefono`, `username`, `region_id`) VALUES
(1, 'admin', '3194335815', NULL, '2019-01-01', 'calle alguna # alguno', '0123456789', 'administrador@bolsadeideas.com', b'1', '2018-03-05', NULL, 'Administrador', '$2a$10$0mE/eqTisi6Gqr0falzenOd3klFZ9TGUJUAOFiiF37F6W4w2SZko6', NULL, 'admin1', 1),
(2, 'usuario1', '3194335819', NULL, '2019-01-01', 'calle alguna # alguno', '0123456788', 'usuario1@bolsadeideas.com', b'1', '2018-03-05', NULL, 'Usuario1', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', NULL, 'admin2', 1),
(3, 'usuario2', '3194335818', NULL, '2019-01-01', 'calle alguna # alguno', '0123456787', 'usuario2@bolsadeideas.com', b'1', '2018-03-05', NULL, 'Usuario2', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', NULL, 'usuario1', 1),
(4, 'usuario3', '3194335817', NULL, '2019-01-01', 'calle alguna # alguno', '0123456786', 'usuario3@bolsadeideas.com', b'1', '2018-03-05', NULL, 'Usuario3', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', NULL, 'usuario2', 1),
(5, 'usuario4', '3194335816', NULL, '2019-01-01', 'calle alguna # alguno', '0123456785', 'usuario4@bolsadeideas.com', b'1', '2018-03-05', NULL, 'Usuario4', '$2a$10$2ZdRmg7MtYWhgD0ZZYlXFeEtCLa17fi9z/zdAffEk.Szski7yBsze', NULL, 'usuario3', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT INTO `usuarios_roles` (`usuario_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(5, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bodegas`
--
ALTER TABLE `bodegas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_iuro8oopb49aqm5m9yrvbighd` (`id_compuesto`),
  ADD KEY `FKcml84e93anq183l2msu06egen` (`producto_id`),
  ADD KEY `FKk08frrg678mq6fjqpefd7khjl` (`sucursal_id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_47fj0e5fbq2l3b80ij3rn1qss` (`documento`),
  ADD KEY `FKf1kwxd4whuje3nv9yxddld4c3` (`region_id`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1qiuk10rfkovhlfpsk7oic0v8` (`cliente_id`),
  ADD KEY `FK10v6t9ep0k5k97e023jenib16` (`sucursal_id`),
  ADD KEY `FK7h3qmligsy2a1mp3ei41rdqag` (`usuario_id`);

--
-- Indices de la tabla `facturas_items`
--
ALTER TABLE `facturas_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdumnm9x14hjfp9fufn7q8389r` (`producto_id`),
  ADD KEY `FKnv8ijya20df661b0p6drqcx7u` (`factura_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_h04wpyqwddobltuqq56cp6s05` (`codigo`),
  ADD UNIQUE KEY `UK_mlgw7js72hh2xtd4mvpdqfsbe` (`nombre`);

--
-- Indices de la tabla `regiones`
--
ALTER TABLE `regiones`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_2utq0pi16tcaxyu29eqyk3olp` (`nombre`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9hw2vjdkd90ovo91p9txp4dvu` (`bodega_id`),
  ADD KEY `FKjb16uevkap6vb4wd2kimdomuc` (`usuario_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8bv0gcsxqwmc5r68oriq9l759` (`nombre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6wvbyr2qay8bvsw98feitxu5u` (`celular1`),
  ADD UNIQUE KEY `UK_51x567hg32si9nj9gjcbabcnm` (`documento`),
  ADD UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  ADD UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`),
  ADD KEY `FKpge15oc1vesms1wlrilcujtcm` (`region_id`);

--
-- Indices de la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD UNIQUE KEY `UKqjaspm7473pnu9y4jxhrds8r2` (`usuario_id`,`role_id`),
  ADD KEY `FKihom0uklpkfpffipxpoyf7b74` (`role_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bodegas`
--
ALTER TABLE `bodegas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturas_items`
--
ALTER TABLE `facturas_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `regiones`
--
ALTER TABLE `regiones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bodegas`
--
ALTER TABLE `bodegas`
  ADD CONSTRAINT `FKcml84e93anq183l2msu06egen` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `FKk08frrg678mq6fjqpefd7khjl` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`id`);

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FKf1kwxd4whuje3nv9yxddld4c3` FOREIGN KEY (`region_id`) REFERENCES `regiones` (`id`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `FK10v6t9ep0k5k97e023jenib16` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`id`),
  ADD CONSTRAINT `FK1qiuk10rfkovhlfpsk7oic0v8` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `FK7h3qmligsy2a1mp3ei41rdqag` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `facturas_items`
--
ALTER TABLE `facturas_items`
  ADD CONSTRAINT `FKdumnm9x14hjfp9fufn7q8389r` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `FKnv8ijya20df661b0p6drqcx7u` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`);

--
-- Filtros para la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD CONSTRAINT `FK9hw2vjdkd90ovo91p9txp4dvu` FOREIGN KEY (`bodega_id`) REFERENCES `bodegas` (`id`),
  ADD CONSTRAINT `FKjb16uevkap6vb4wd2kimdomuc` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FKpge15oc1vesms1wlrilcujtcm` FOREIGN KEY (`region_id`) REFERENCES `regiones` (`id`);

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `FKihom0uklpkfpffipxpoyf7b74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
