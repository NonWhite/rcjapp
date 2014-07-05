# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accion (
  idaccion                  integer auto_increment not null,
  nombreruta                varchar(255),
  descripcion               varchar(255),
  constraint pk_accion primary key (idaccion))
;

create table area_trabajo (
  idareatrabajo             integer auto_increment not null,
  nombreareatrabajo         varchar(255),
  constraint pk_area_trabajo primary key (idareatrabajo))
;

create table modulo (
  idmodulo                  integer auto_increment not null,
  nombremodulo              varchar(255),
  nombreruta                varchar(255),
  constraint pk_modulo primary key (idmodulo))
;

create table parametro_sistema (
  idparametrosistema        integer auto_increment not null,
  nombreparametro           varchar(255),
  valornumerico             integer,
  valortexto                varchar(255),
  tipoparametro             integer,
  constraint pk_parametro_sistema primary key (idparametrosistema))
;

create table perfil (
  idperfil                  integer auto_increment not null,
  nombreperfil              varchar(255),
  estado                    integer,
  constraint pk_perfil primary key (idperfil))
;

create table privilegio_seguridad (
  idperfil                  integer,
  idmodulo                  integer,
  idvista                   integer,
  idaccion                  integer,
  estado                    integer,
  constraint pk_privilegio_seguridad primary key (idperfil, idmodulo, idvista, idaccion))
;

create table resource_string (
  idresource                integer auto_increment not null,
  keystring                 varchar(255),
  esp                       varchar(255),
  eng                       varchar(255),
  constraint pk_resource_string primary key (idresource))
;

create table tipo_documento (
  idtipodocumento           integer auto_increment not null,
  nombretipodocumento       varchar(255),
  constraint pk_tipo_documento primary key (idtipodocumento))
;

create table usuario (
  idusuario                 integer auto_increment not null,
  nombreusuario             varchar(255),
  password                  varchar(255),
  nombre                    varchar(255),
  apellidopaterno           varchar(255),
  apellidomaterno           varchar(255),
  correo                    varchar(255),
  idperfil                  integer,
  idtipodocumento           integer,
  nrodocumento              varchar(255),
  idareatrabajo             integer,
  estado                    integer,
  estadoconexion            integer,
  esinterno                 integer,
  constraint pk_usuario primary key (idusuario))
;

create table vista (
  idvista                   integer auto_increment not null,
  nombrevista               varchar(255),
  nombreruta                varchar(255),
  constraint pk_vista primary key (idvista))
;


create table modulo_vista (
  idmodulo                       integer not null,
  idvista                        integer not null,
  constraint pk_modulo_vista primary key (idmodulo, idvista))
;

create table vista_accion (
  idvista                        integer not null,
  idaccion                       integer not null,
  constraint pk_vista_accion primary key (idvista, idaccion))
;
alter table usuario add constraint fk_usuario_perfil_1 foreign key (idperfil) references perfil (idperfil) on delete restrict on update restrict;
create index ix_usuario_perfil_1 on usuario (idperfil);
alter table usuario add constraint fk_usuario_tipodocumento_2 foreign key (idtipodocumento) references tipo_documento (idtipodocumento) on delete restrict on update restrict;
create index ix_usuario_tipodocumento_2 on usuario (idtipodocumento);
alter table usuario add constraint fk_usuario_areatrabajo_3 foreign key (idareatrabajo) references area_trabajo (idareatrabajo) on delete restrict on update restrict;
create index ix_usuario_areatrabajo_3 on usuario (idareatrabajo);



alter table modulo_vista add constraint fk_modulo_vista_modulo_01 foreign key (idmodulo) references modulo (idmodulo) on delete restrict on update restrict;

alter table modulo_vista add constraint fk_modulo_vista_vista_02 foreign key (idvista) references vista (idvista) on delete restrict on update restrict;

alter table vista_accion add constraint fk_vista_accion_vista_01 foreign key (idvista) references vista (idvista) on delete restrict on update restrict;

alter table vista_accion add constraint fk_vista_accion_accion_02 foreign key (idaccion) references accion (idaccion) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table accion;

drop table area_trabajo;

drop table modulo;

drop table modulo_vista;

drop table parametro_sistema;

drop table perfil;

drop table privilegio_seguridad;

drop table resource_string;

drop table tipo_documento;

drop table usuario;

drop table vista;

drop table vista_accion;

SET FOREIGN_KEY_CHECKS=1;

