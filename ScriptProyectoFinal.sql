

create table Estado(
  IDEstado serial primary key,
  Nombre varchar(40) not null,
  Clave varchar(40) not null,
  Abrev varchar(40) not null,
  NombreUsuarioEncargado varchar(50)not null,
  Contra varchar(50) not null);
 
 /*delete from estado;*/
/* drop table estado;*/

 create table Municipio(
  IDMunicipio serial primary key,
  IDEstado int not null,
  Nombre varchar(100) not null,
  Clave  varchar (40) not null,
  Codigo varchar (40) not null,
  foreign key (IDEstado)
  references Estado(IDEstado)
  on update cascade on delete cascade
 );

select * from municipio;
/*drop table municipio;*/
 
create table Apoyos (
  IDApoyo serial primary key,
  Componente varchar(50)
);

/*drop table apoyos;*/

CREATE table Beneficiados(
   IDBeneficiado serial primary key,
   NombreUsaurio varchar (30) not null,
   Contra varchar (50) not null,
   Calle varchar (60) not null,
   Edad int not null,
   Correo varchar (90),
   IDMunicipio int not null,
   Imagen bytea not null,
   foreign key (IDMunicipio)
  references Municipio(IDMunicipio)
  on update cascade on delete cascade
 );

/*drop table Beneficiados;*/

create table Pedidos(
  IDPedido serial not null,
  IDBeneficiado int not null,
  NombreComercial varchar(30) not null,
  Laboratorio varchar(30) not null,
  Cantidad int not null,
  FechaPedido date not null,
  FechaEntrega date not null,
  foreign key (IDBeneficiado)
  references Beneficiados( IDBeneficiado )
  on update cascade on delete cascade
);

/*drop table pedidos;*/

create table VariantesApoyos(
  IDMunicipio int not null,
  IDApoyo int not null,
  NombreComercial varchar(30) not null,
  Laboratorio varchar(30) not null,
  primary key(IDMunicipio,IDApoyo),
  foreign key (IDApoyo)
  references Apoyos( IDApoyo )
  on update cascade on delete cascade,
  foreign key (IDMunicipio)
  references Municipio( IDMunicipio )
  on update cascade on delete cascade
);

/*drop table variantesApoyos;*/

insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Aguascalientes','1234','Aguas.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Baja California','2345','Baja Cal.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Baja California Sur','3456','Baja Cal. Sur.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Campeche','4567','Campe.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Chiapas','5678','Chiap.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Chihuahua','6789','Chihua.','erick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Coahuila','7891','Coahil.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Colima','8912','Colim.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Durango','9123','Duran.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Estado de México','2468','Estado M.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Guanajuato','4682','Guana.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Guerrero','6824','Guerr.','frida','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Hidalgo','8246','Hida.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Jalisco','1357','Jali.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Michoacán','3571','Michoa.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Morelos','5713','More.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Nayarit','7135','Nayar.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Nuevo León','1111','Nuevo Le.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Oaxaca','2222','Oaxac.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Puebla','3333','Puebl.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Querétaro','4444','Quer.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Quintana Roo','5555','Quint.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('San Luis Potosí','6666','San Lu.','paola','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Sinaloa','7777','Sina.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Sonora','8888','Sono.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Tabasco','9999','Taba.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Tamaulipas','1122','Tam.','rick','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Tlaxcala','3344','Tlax.','ulises','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Veracruz','5566','Verac.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Yucatán','7788','Yuca.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Zacateca','9911','Zac.','luis','1234');
insert into estado(Nombre,Clave,Abrev,NombreUsuarioEncargado,Contra)
values ('Distrito Federal','3334','D.F.','luis','1234');
select * from estado;
/*Municipios aguascalientes*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (1,'Asientos','11234','12341');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (1,'Calvillo','12341','23411');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (1,'Cosío','13412','34121');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (1,'Aguascalientes','14123','41231');
/*Municipios baja california*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (2,'Ensenada','21234','12342');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (2,'Mexicali','22341','23412');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (2,'Tecate','23412','34122');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (2,'Tijuana','24123','41232');
/*Municipios baja california sur*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (3,'La paz','31234','12343');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (3,'Loreto','32341','23413');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (3,'Comondú','33412','34123');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (3,'Mulegé','34123','41233');
/*Municipios baja campeche*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (4,'Carmen','41234','12344');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (4,'Palizada','42341','23414');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (4,'Tenabo','43412','34124');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (4,'Candelaria','44123','41234');
/*Municipios baja chiapas*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (5,'Acala','51234','12345');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (5,'Arriaga','52341','23415');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (5,'Cintalapa','53412','34125');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (5,'Pantepec','54123','41235');
/*Municipios baja chihuahua*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (6,'Ahumada','61234','12346');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (6,'Aldama','62341','23416');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (6,'Coronado','63412','34126');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (6,'Delicias','64123','41236');
/*Municipios baja coahila*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (7,'Allende','71234','12347');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (7,'Candela','72341','23417');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (7,'Escobedo','73412','34127');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (7,'Nava','74123','41237');
/*Municipios baja colima*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (8,'Colima','81234','12348');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (8,'Tecomán','82341','23418');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (8,'Armería','83412','34128');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (8,'Comala','84123','41238');
/*Municipios durango*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (9,'Canelas','91234','12349');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (9,'Lerdo','92341','23419');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (9,'Nazas','93412','34129');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (9,'Ocampo','94123','41239');
/*Municipios estado de méxico*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (10,'Acambay','10234','12310');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (10,'Acolman','10341','23410');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (10,'Lerma','10412','34110');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (10,'Ozumba','10123','41210');
/*Municipios guanajuato*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (11,'Abasolo','11234','12311');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (11,'Allende','11341','23411');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (11,'Victoria','11412','34111');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (11,'Yuriria','11123','41211');
/*Municipios Guerrero*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (12,'Alpoyeca','12234','12312');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (12,'Apaxtla','12341','23412');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (12,'Atlixtac','12412','34112');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (12,'Tlalchapa','12123','41212');
/*Municipios Hidalgo*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (13,'Actopan','13234','12313');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (13,'Ajacuba','13341','23413');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (13,'Lolotla','13412','34113');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (13,'Pacula','13123','41213');
/*Municipios Jalisco*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (14,'Acatic','14234','12314');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (14,'Amacueca','14341','23414');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (14,'Arandas','14412','34114');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (14,'Atengo','14123','41214');
/*Municipios Michoacán*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (15,'Ario','15234','12315');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (15,'Coeneo','15341','23415');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (15,'Chilchota','15412','34115');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (15,'Quiroja','15123','41215');
/*Municipios Morelos*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (16,'Axochiapan','16234','12316');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (16,'Mazatepec','16341','23416');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (16,'Temixco','16412','34116');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (16,'Temoac','16123','41216');
/*Municipios Nayarit*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (17,'Jala','17234','12317');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (17,'Huajicori','17341','23417');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (17,'Ruiz','17412','34117');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (17,'Tepic','17123','41217');
/*Municipios Nuevo Leon*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (18,'Abasolo','18234','12318');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (18,'Apodaca','18341','23418');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (18,'Arramberri','18412','34118');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (18,'Carmen','18123','41218');
/*Municipios Oxaca*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (19,'Abejones','19234','12319');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (19,'Natividad','19341','23419');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (19,'Zoogocho','19412','34119');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (19,'Ozotepec','19123','41219');
/*Municipios Puebla*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (20,'Acajete','20234','12320');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (20,'Ajalpan','20341','23420');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (20,'Axutla','20412','34120');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (20,'Calpan','20123','41220');
/*Municipios Quéretaro*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (21,'Corregidora','21234','12321');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (21,'Bonfil','21341','23421');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (21,'Amoles','21412','34121');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (21,'Panamiller','21123','41221');
/*Municipios Quintana Roo*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (22,'Cozumel','22234','12322');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (22,'Bacalar','22341','23422');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (22,'Tulum','22412','34122');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (22,'Solidaridad','22123','41222');
/*Municipios San Luis Potosi*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (23,'Ahualulco','23234','12323');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (23,'Catorce','23341','23423');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (23,'Cedral','23412','34123');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (23,'Cerritos','23123','41223');
/*Municipios Sinaloa*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (24,'Ahome','24234','12324');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (24,'Elota','24341','23424');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (24,'Mocorito','24412','34124');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (24,'Navolato','24123','41224');
/*Municipios Sonora*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (25,'Aconchi','25234','12325');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (25,'Altar','25341','23425');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (25,'Arizpe','25412','34125');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (25,'Caborca','25123','41225');
/*Municipios Tabasco*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (26,'Centro','26234','12326');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (26,'Centla','26341','23426');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (26,'Jonuta','26412','34126');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (26,'Teapa','26123','41226');
/*Municipios Tamaulipas*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (27,'Aldamo','27234','12327');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (27,'Altamira','27341','23427');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (27,'Burgos','27412','34127');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (27,'Casas','27123','41227');
/*Municipios Tlaxcala*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (28,'Apizaco','28234','12328');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (28,'Ixtenco','28341','23428');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (28,'Panotla','28412','34128');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (28,'Terrenate','28123','41228');
/*Municipios Veracruz*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (29,'Actopan','29234','12329');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (29,'Acajete','29341','23429');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (29,'Alvarado','29412','34129');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (29,'Catemaco','29123','41229');
/*Municipios Yucatán*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (30,'Akil','30234','12330');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (30,'Baca','30341','23430');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (30,'Conkal','30412','34130');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (30,'Dzan','30123','41230');
/*Municipios Zacatecas*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (31,'Apulco','31234','12331');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (31,'Calera','31341','23431');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (31,'Apozol','31412','34131');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (31,'Fresnillo','31123','41231');
/*Municipios Distrito Federal*/
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (32,'Iztacalco','32234','12332');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (32,'Iztapalapa','32341','23432');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (32,'Azcapotzalco','32412','34132');
insert into municipio (IDEstado,Nombre,Clave,Codigo)
values (32,'Tlalpan','32123','41232');

select *from municipio;

insert into municipio (idestado,nombre,clave,codigo)
values (3,'ddfsdf','ds','sd');

select * from municipio;
insert into apoyos(componente) values ('diclofenaco');
select * from apoyos;
select * from beneficiados;
insert into Beneficiados(nombreUsaurio,contra,calle,edad,CURP,idmunicipio)
values('rick','1234','dsds',12,'ddsds',3);