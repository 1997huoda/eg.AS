create table book  
(  
   ISBN varchar(20) primary key,  
   B_Name varchar(50),  
   B_Author varchar(50),     
   B_Publishment varchar(80),  
   B_BuyTime varchar(50)  
);  
insert into book values('9787302164289','3ds max 9��ά��ģ','�̷��','�廪��ѧ������','2010-02-01');  
insert into book values('9787121060953','photoshop cs3 ͼ����','׿Խ�Ƽ�','���ӹ�ҵ������','2003-02-01');  
insert into book values('9787121102462','Java����Աְ��ȫ����-��С����ר��','���Ƿ�','���ӹ�ҵ������','2004-04-05');  
insert into book values('9787115227508','Android 2.0��Ϸ����ʵս����','���Ƿ�','�����ʵ������','2003-06-07');  
insert into book values('9787030236630','PowerBuilder_10.5ʵ�ý̳�','������','��ѧ������','2005-07-05');  
insert into book values('9787121079528','PowerBuilder ʵ�ý̳̣���3�棩','֣����','���ӹ�ҵ������','2005-07-09');  
insert into book values('9787302244158','��ѧ�����������21������ͨ��У����������γ̹滮�̲ģ�','��ޱ�������� ','�廪��ѧ������','2005-0-09');  
insert into book values('9787562324560','΢�ͼ����ԭ��Ӧ��','���ٹ⣬������','��������ѧ������','2005-07-09');  
insert into book values('9787111187776','�㷨����','������������Cormen,T.H.�� �������˽�� ����','��е��ҵ������','2005-07-09');  
  
create table student  
(  
  S_Num varchar(20) primary key,  
  S_Name varchar(50),  
  S_Age varchar(10),  
  S_Sex varchar(50),  
  S_Class varchar(50),  
  S_Department varchar(50),  
  S_Phone varchar(11),  
  S_Permitted varchar(50),  
  S_Pwd varchar(20)  
);  
  
insert into student values('10001','����','20','Ů','�����1��','�����ϵ','15176536034','��','001');  
insert into student values('10002','����',21,'Ů','�����1��','�����ϵ','13730220123','��','002');  
insert into student values('10003','���',20,'��','�����1��','�����ϵ','13633654578','��','003');  
insert into student values('10004','�ι�',22,'��','�����1��','�����ϵ','2578975','��','004');  
insert into student values('10005','����',21,'Ů','�����1��','�����ϵ','13936968956','��','005');  
insert into student values('10006','�����',20,'��','�����2��','�����ϵ','1234667','��','006');  