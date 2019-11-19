-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2019 a las 09:31:39
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agricultural_field`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbol`
--

CREATE TABLE `arbol` (
  `ID_arb` int(11) NOT NULL,
  `edad` int(4) NOT NULL,
  `estado_actual` varchar(20) NOT NULL,
  `plaga_o_enfermedad` varchar(30) NOT NULL,
  `cantidad_de_arboles` int(20) NOT NULL,
  `cultivo` varchar(35) NOT NULL,
  `cuadro` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `arbol`
--

INSERT INTO `arbol` (`ID_arb`, `edad`, `estado_actual`, `plaga_o_enfermedad`, `cantidad_de_arboles`, `cultivo`, `cuadro`) VALUES
(1, 5, 'chido', 'ninguna', 2000, 'Nuez', '7A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `daños`
--

CREATE TABLE `daños` (
  `ID_dam` int(11) NOT NULL,
  `Lugar` varchar(50) NOT NULL,
  `Sintoma` varchar(50) NOT NULL,
  `Causa` varchar(50) NOT NULL,
  `cuadro` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_acciones`
--

CREATE TABLE `historial_acciones` (
  `ID_HA` int(11) NOT NULL,
  `accion` varchar(50) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `cuadro` varchar(10) NOT NULL,
  `descripcion` varchar(155) NOT NULL,
  `producto` varchar(50) NOT NULL,
  `receta` varchar(50) NOT NULL,
  `clima` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informacion_de_viajes`
--

CREATE TABLE `informacion_de_viajes` (
  `ID_IDV` int(11) NOT NULL,
  `destino` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `conductor` varchar(50) NOT NULL,
  `comentario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `ID_INV` int(11) NOT NULL,
  `categoria` varchar(30) NOT NULL,
  `producto` varchar(30) NOT NULL,
  `cantidad` int(20) NOT NULL,
  `Descripcion` varchar(155) NOT NULL,
  `Estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nuez`
--

CREATE TABLE `nuez` (
  `ID_nuez` int(11) NOT NULL,
  `variedad` varchar(30) NOT NULL,
  `de_primera` int(20) NOT NULL,
  `quebrada` int(20) NOT NULL,
  `germinada` int(20) NOT NULL,
  `vana` int(20) NOT NULL,
  `con_ruez` int(20) NOT NULL,
  `aceitosa` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `problema_en_cuadros`
--

CREATE TABLE `problema_en_cuadros` (
  `ID_PEC` int(100) NOT NULL,
  `cuadro` varchar(20) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `problema` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbol`
--
ALTER TABLE `arbol`
  ADD PRIMARY KEY (`ID_arb`);

--
-- Indices de la tabla `daños`
--
ALTER TABLE `daños`
  ADD PRIMARY KEY (`ID_dam`);

--
-- Indices de la tabla `historial_acciones`
--
ALTER TABLE `historial_acciones`
  ADD PRIMARY KEY (`ID_HA`);

--
-- Indices de la tabla `informacion_de_viajes`
--
ALTER TABLE `informacion_de_viajes`
  ADD PRIMARY KEY (`ID_IDV`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`ID_INV`);

--
-- Indices de la tabla `nuez`
--
ALTER TABLE `nuez`
  ADD PRIMARY KEY (`ID_nuez`);

--
-- Indices de la tabla `problema_en_cuadros`
--
ALTER TABLE `problema_en_cuadros`
  ADD PRIMARY KEY (`ID_PEC`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbol`
--
ALTER TABLE `arbol`
  MODIFY `ID_arb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `daños`
--
ALTER TABLE `daños`
  MODIFY `ID_dam` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_acciones`
--
ALTER TABLE `historial_acciones`
  MODIFY `ID_HA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `informacion_de_viajes`
--
ALTER TABLE `informacion_de_viajes`
  MODIFY `ID_IDV` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `ID_INV` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `nuez`
--
ALTER TABLE `nuez`
  MODIFY `ID_nuez` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `problema_en_cuadros`
--
ALTER TABLE `problema_en_cuadros`
  MODIFY `ID_PEC` int(100) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
