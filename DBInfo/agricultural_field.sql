-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-11-2019 a las 14:55:40
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `ID_cat` int(10) NOT NULL,
  `categoria` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`ID_cat`, `categoria`) VALUES
(1, 'Herramientas'),
(2, 'Fertilizantes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuadros`
--

CREATE TABLE `cuadros` (
  `ID_cuadro` int(10) NOT NULL,
  `cuadro` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuadros`
--

INSERT INTO `cuadros` (`ID_cuadro`, `cuadro`) VALUES
(1, '7A'),
(2, '7B'),
(3, '8A'),
(4, '8B');

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

--
-- Volcado de datos para la tabla `daños`
--

INSERT INTO `daños` (`ID_dam`, `Lugar`, `Sintoma`, `Causa`, `cuadro`) VALUES
(1, 'Hoja', 'mielosa', 'Humedad', '7A'),
(2, 'raiz', 'rota', 'topos', '7B'),
(4, 'Hoja', 'Pulgon blanco', 'Clima', '7A'),
(5, 'Hoja', 'Pulgon blanco', 'No se', '7A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(10) NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `estado`) VALUES
(1, 'Pendiente'),
(2, 'Completo'),
(3, 'Otro');

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

--
-- Volcado de datos para la tabla `historial_acciones`
--

INSERT INTO `historial_acciones` (`ID_HA`, `accion`, `fecha`, `cuadro`, `descripcion`, `producto`, `receta`, `clima`) VALUES
(1, 'agregar', 'ayer', '7B', 'Pos que te puedo decir? al chile no se', 'agua', 'ninguna', 'Seco'),
(2, 'Se agrego', 'Fecha actual', '7A', 'Rodado', 'Producto: Austar', 'nombre de receta', 'ASD'),
(3, 'a', '2019-11-22', '7A', 'a', 'a', 'Ninguna', 'a'),
(4, 'a', '2019-11-22', '7A', 'a', 'a', 'Ninguna', 'a'),
(5, 'a', '2019-11-22', '7B', 'a', 'a', 'Ninguna', 'a');

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

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`ID_INV`, `categoria`, `producto`, `cantidad`, `Descripcion`, `Estado`) VALUES
(1, 'Herramientas', 'Martillo', 7, 'Martillos normales', 'Disponible'),
(2, 'Fertilizantes', 'Acadian', 15, 'Fertilizante en litros', 'No disponible');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recetas`
--

CREATE TABLE `recetas` (
  `ID_re` int(11) NOT NULL,
  `recetas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `recetas`
--

INSERT INTO `recetas` (`ID_re`, `recetas`) VALUES
(1, 'Austar'),
(2, 'Kendo Heretic'),
(3, 'Dap Plus, Amistar gold'),
(4, 'Cabrio C'),
(5, 'Go zinc 17'),
(6, 'Urea');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terreno`
--

CREATE TABLE `terreno` (
  `ID_terreno` int(30) NOT NULL,
  `fertilidad` varchar(30) NOT NULL,
  `estado_actual` varchar(30) NOT NULL,
  `tipo_riego` varchar(30) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `cuadro` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `terreno`
--

INSERT INTO `terreno` (`ID_terreno`, `fertilidad`, `estado_actual`, `tipo_riego`, `fecha`, `cuadro`) VALUES
(1, 'musha', 'bueno', 'goteo', 'hoy', '7B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipe_de_riego`
--

CREATE TABLE `tipe_de_riego` (
  `ID_riego` int(10) NOT NULL,
  `tipoRiego` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipe_de_riego`
--

INSERT INTO `tipe_de_riego` (`ID_riego`, `tipoRiego`) VALUES
(1, 'Rodado'),
(2, 'Goteo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

CREATE TABLE `viajes` (
  `ID_IDV` int(11) NOT NULL,
  `destino` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `conductor` varchar(50) NOT NULL,
  `comentario` varchar(100) NOT NULL
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
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`ID_cat`);

--
-- Indices de la tabla `cuadros`
--
ALTER TABLE `cuadros`
  ADD PRIMARY KEY (`ID_cuadro`);

--
-- Indices de la tabla `daños`
--
ALTER TABLE `daños`
  ADD PRIMARY KEY (`ID_dam`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `historial_acciones`
--
ALTER TABLE `historial_acciones`
  ADD PRIMARY KEY (`ID_HA`);

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
-- Indices de la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD PRIMARY KEY (`ID_re`);

--
-- Indices de la tabla `terreno`
--
ALTER TABLE `terreno`
  ADD PRIMARY KEY (`ID_terreno`);

--
-- Indices de la tabla `tipe_de_riego`
--
ALTER TABLE `tipe_de_riego`
  ADD PRIMARY KEY (`ID_riego`);

--
-- Indices de la tabla `viajes`
--
ALTER TABLE `viajes`
  ADD PRIMARY KEY (`ID_IDV`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbol`
--
ALTER TABLE `arbol`
  MODIFY `ID_arb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `ID_cat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cuadros`
--
ALTER TABLE `cuadros`
  MODIFY `ID_cuadro` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `daños`
--
ALTER TABLE `daños`
  MODIFY `ID_dam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id_estado` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `historial_acciones`
--
ALTER TABLE `historial_acciones`
  MODIFY `ID_HA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `ID_INV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `nuez`
--
ALTER TABLE `nuez`
  MODIFY `ID_nuez` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `problema_en_cuadros`
--
ALTER TABLE `problema_en_cuadros`
  MODIFY `ID_PEC` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `recetas`
--
ALTER TABLE `recetas`
  MODIFY `ID_re` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `terreno`
--
ALTER TABLE `terreno`
  MODIFY `ID_terreno` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipe_de_riego`
--
ALTER TABLE `tipe_de_riego`
  MODIFY `ID_riego` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `viajes`
--
ALTER TABLE `viajes`
  MODIFY `ID_IDV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
