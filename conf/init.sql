DELETE FROM privilegio_seguridad;
DELETE FROM vista_accion;
DELETE FROM modulo_vista;
DELETE FROM accion;
DELETE FROM vista;
DELETE FROM modulo;
DELETE FROM usuario;
DELETE FROM perfil;
DELETE FROM area_trabajo;
DELETE FROM tipo_documento;
DELETE FROM parametro_sistema;

INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 1 , "Usuario" , 1 , "nombreusuario" , 1 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 2 , "Contraseña" , 1 , "password" , 1 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 3 , "Nro Documento" , 0 , "nrodocumento" , 1 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 4 , "Correo" , 0 , "correo" , 1 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 5 , "Nombre General" , null , "Proyecto PIPEI" , 2 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 6 , "Imagen sistema" , null , "/assets/images/logo.jpg" , 2 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 7 , "Maximo atenciones" , 10 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 8 , "Min. tiempo de sesión" , 180 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 9 , "Min. tiempo respuesta SE" , 0 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 10 , "No respuesta SE (LI)" , 25 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 11 , "No respuesta SE (LS)" , 40 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 12 , "No respuesta usuario (LI)" , 25 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 13 , "No respuesta usuario (LS)" , 40 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 14 , "Alerta atención (LI)" , 25 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 15 , "Alerta atención (LS)" , 40 , null , 3 ) ;
INSERT INTO parametro_sistema ( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 16 , "Numero de conectados" , 0 , null , 4 ) ;
 INSERT INTO parametro_sistema( idparametrosistema , nombreparametro , valornumerico , valortexto , tipoparametro ) VALUES ( 17 , "Tiempo de bloqueo login" , 5 , null , 3 ) ;

INSERT INTO tipo_documento ( idtipodocumento , nombretipodocumento ) VALUES ( 1 , "DNI" ) ;
INSERT INTO tipo_documento ( idtipodocumento , nombretipodocumento ) VALUES ( 2 , "Pasaporte" ) ;
INSERT INTO tipo_documento ( idtipodocumento , nombretipodocumento ) VALUES ( 3 , "Carnet Extranjería" ) ;

INSERT INTO area_trabajo ( idareatrabajo , nombreareatrabajo ) VALUES ( 1 , "Administración" ) ;
INSERT INTO area_trabajo ( idareatrabajo , nombreareatrabajo ) VALUES ( 2 , "Finanzas" ) ;
INSERT INTO area_trabajo ( idareatrabajo , nombreareatrabajo ) VALUES ( 3 , "Contabilidad" ) ;

INSERT INTO perfil ( idperfil , nombreperfil , estado ) VALUES ( 1 , "administrador" , 1 ) ;
INSERT INTO perfil ( idperfil , nombreperfil , estado ) VALUES ( 2 , "experto" , 1 ) ;
INSERT INTO perfil ( idperfil , nombreperfil , estado ) VALUES ( 3 , "agente" , 1 ) ;
INSERT INTO perfil ( idperfil , nombreperfil , estado ) VALUES ( 4 , "cliente" , 1 ) ;

INSERT INTO usuario ( idusuario , nombreusuario , password , idperfil , estado , estadoconexion , nombre , apellidopaterno , apellidomaterno , correo , idtipodocumento , nrodocumento , idareatrabajo , esinterno ) VALUES ( 1 , "admin" , "admin" , 1 , 1 , 0 , "number" , "one" , "" , "admin@infobox.com" , 1 , 11111111 , 1 , 1 ) ;
INSERT INTO usuario ( idusuario , nombreusuario , password , idperfil , estado , estadoconexion , nombre , apellidopaterno , apellidomaterno , correo , idtipodocumento , nrodocumento , idareatrabajo , esinterno ) VALUES ( 2 , "experto" , "experto" , 2 , 1 , 0 , "rule" , "master" , "" , "experto@infobox.com" , 2 , 22222222 , 2 , 0 ) ;
INSERT INTO usuario ( idusuario , nombreusuario , password , idperfil , estado , estadoconexion , nombre , apellidopaterno , apellidomaterno , correo , idtipodocumento , nrodocumento , idareatrabajo , esinterno ) VALUES ( 3 , "agente" , "agente" , 3 , 1 , 0 , "anti" , "bots" , "" , "agente@infobox.com" , 3 , 33333333 , 3 , 1 ) ;
INSERT INTO usuario ( idusuario , nombreusuario , password , idperfil , estado , estadoconexion , nombre , apellidopaterno , apellidomaterno , correo , idtipodocumento , nrodocumento , idareatrabajo , esinterno ) VALUES ( 4 , "cliente" , "cliente" , 4 , 1 , 0 , "alejandro" , "bello" , "" , "cliente@infobox.com" , 1 , 44444444 , 3 , 0 ) ;

INSERT INTO modulo ( idmodulo , nombremodulo , nombreruta ) VALUES ( 1 , "Administración" , "administracion" ) ; -- 1

INSERT INTO vista ( idvista , nombrevista , nombreruta ) VALUES ( 1 , "Perfiles" , "perfil" ) ; -- 1. ver, creacion, edicion, desactivación
INSERT INTO vista ( idvista , nombrevista , nombreruta ) VALUES ( 2 , "Usuarios" , "usuario" ) ; -- 2. ver, creacion, edicion, desactivación, buscar

INSERT INTO accion ( idaccion , nombreruta , descripcion ) VALUES ( 1 , "ver" , "Ver vista" ) ; -- 1
INSERT INTO accion ( idaccion , nombreruta , descripcion ) VALUES ( 2 , "crear" , "Crear" ) ; -- 2
INSERT INTO accion ( idaccion , nombreruta , descripcion ) VALUES ( 3 , "editar" , "Editar" ) ; -- 3
INSERT INTO accion ( idaccion , nombreruta , descripcion ) VALUES ( 4 , "eliminar" , "Eliminar" ) ; -- 4

INSERT INTO modulo_vista ( idmodulo , idvista ) VALUES ( 1 , 1 ) ;
INSERT INTO modulo_vista ( idmodulo , idvista ) VALUES ( 1 , 2 ) ;

INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 1 , 1 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 1 , 2 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 1 , 3 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 1 , 4 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 2 , 1 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 2 , 2 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 2 , 3 ) ;
INSERT INTO vista_accion ( idvista , idaccion ) VALUES ( 2 , 4 ) ;

INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 1 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 1 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 1 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 1 , 4 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 2 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 2 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 2 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 1 , 1 , 2 , 4 , 1 ) ;

INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 1 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 1 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 1 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 1 , 4 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 2 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 2 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 2 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 2 , 1 , 2 , 4 , 1 ) ;

INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 1 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 1 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 1 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 1 , 4 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 2 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 2 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 2 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 3 , 1 , 2 , 4 , 1 ) ;

INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 1 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 1 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 1 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 1 , 4 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 2 , 1 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 2 , 2 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 2 , 3 , 1 ) ;
INSERT INTO privilegio_seguridad ( idperfil , idmodulo , idvista , idaccion , estado ) VALUES ( 4 , 1 , 2 , 4 , 1 ) ;