--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;

CREATE TABLE `endereco` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(9) NOT NULL,
  `complemento` varchar(150) NOT NULL,
  `logradouro` varchar(150) NOT NULL,
  `numero` int(11) NOT NULL,
  `cidade_codigo` int(11) NOT NULL,
  `cliente_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_endereco_to_cidade` (`cidade_codigo`),
  KEY `fk_endereco_to_cliente` (`cliente_codigo`),
  CONSTRAINT `fk_endereco_to_cidade` FOREIGN KEY (`cidade_codigo`) REFERENCES `cidade` (`codigo`),
  CONSTRAINT `fk_endereco_to_cliente` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;

CREATE TABLE `produto` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  `foto` varchar(20) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_produto_to_categoria` (`categoria_id`),
  CONSTRAINT `fk_produto_to_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `status_pedido` varchar(20) NOT NULL,
  `sessao` varchar(20) NOT NULL,
  `cliente_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_pedido_to_cliente` (`cliente_codigo`),
  CONSTRAINT `fk_pedido_to_cliente` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;

CREATE TABLE `item_pedido` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `Sub_total` decimal(10,2) NOT NULL,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_item_pedido_to_pedido` (`pedido_id`),
  KEY `fk_item_pedido_to_produto` (`produto_id`),
  CONSTRAINT `fk_item_pedido_to_pedido` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`codigo`),
  CONSTRAINT `fk_item_pedido_to_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




