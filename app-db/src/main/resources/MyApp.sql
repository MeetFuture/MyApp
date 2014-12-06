
--服务配置--
insert into app_server_config (id, CREATETIME, DELSTATUS, UPDATETIME, ENABLE, SERVERBEAN, SERVERNAME, SPARESTR, TITLE) values (1, '2013-08-03 01:05:11', 0, '2013-08-03 01:05:11', 1, 'MinaServer', 'MinaServer', null, 'Mina后台通信服务');
insert into app_server_config (id, CREATETIME, DELSTATUS, UPDATETIME, ENABLE, SERVERBEAN, SERVERNAME, SPARESTR, TITLE) values (2, '2013-08-03 01:05:11', 0, '2013-08-03 01:05:11', 1, 'SocketServer', 'SocketServer', null, 'Socket后台通信服务');

--系统参数配置---
insert into app_system_params (id, CREATETIME, DELSTATUS, UPDATETIME, PARAMID, PARAMNAME, PARAMVALUE) values (1, '2013-08-03 10:27:52', 0, '2013-08-03 10:27:52', 1, 'Mina服务器开启端口', '8095');
insert into app_system_params (id, CREATETIME, DELSTATUS, UPDATETIME, PARAMID, PARAMNAME, PARAMVALUE) values (2, '2013-08-03 10:27:52', 0, '2013-08-03 10:27:52', 2, 'Socket服务器开启端口', '8094');
insert into app_system_params (id, CREATETIME, DELSTATUS, UPDATETIME, PARAMID, PARAMNAME, PARAMVALUE) values (3, '2013-08-03 10:27:52', 0, '2013-08-03 10:27:52', 3, 'Kugou程序运行路径', 'F:\KGMusic\KuGou.exe');


