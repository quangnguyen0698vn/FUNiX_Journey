use master;
go
drop DATABASE QLBanhang;
go

create database QLBanhang;
go
use QLBanhang;

create table KHACHHANG 
(
    MAKH NVARCHAR(5),
    TENKH NVARCHAR(30),
    DIACHI NVARCHAR(300),
    DT VARCHAR(10),
    EMAIL VARCHAR(30)
    CONSTRAINT pk_khachhang PRIMARY KEY(MAKH)
)

create table VATTU 
(
    MAVT NVARCHAR(5) PRIMARY KEY,
    TENVT NVARCHAR(30),
    DVT NVARCHAR(20),
    GIAMUA MONEY,
    SLTON INT
)

CREATE TABLE HOADON 
(
    MAHD NVARCHAR(10) PRIMARY KEY,
    NGAY DATETIME,
    MAKH NVARCHAR(5),
    TONGTG MONEY
)

-- DROP TABLE CHITTIETHOADON
-- DROP TABLE CHITIETHOADON
CREATE TABLE CHITIETHOADON
(
    MAHD NVARCHAR(10),
    MAVT NVARCHAR(5),
    SL INT,
    KHUYENMAI DECIMAL,
    GIABAN MONEY
    CONSTRAINT PK_CHITIETHOADON PRIMARY KEY (MAHD, MAVT)
)


alter table KHACHHANG
alter column TENKH NVARCHAR(30) not null

alter table KHACHHANG
add constraint chk_KHACHHANG_DT check(LEN(DT) <= 10)

alter table VATTU
alter column TENVT NVARCHAR(30) NOT NULL

alter table VATTU
add constraint chk_VATTU_GIAMUA check(GIAMUA > 0)

alter table VATTU
add constraint chk_SLTON check (SLTON >= 0)

alter table hoadon
add constraint fk_MAKH FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH)

alter table CHITIETHOADON
add constraint fk_CHITIETHOADON_MAHD FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD)

alter table CHITIETHOADON
add constraint fk_CHITIETHOADON_MAVT FOREIGN KEY (MAVT) REFERENCES VATTU(MAVT)

alter table CHITIETHOADON
drop constraint df_SL


alter table CHITIETHOADON
add constraint df_CHITIETHOADON_SL
DEFAULT 1 for SL

alter table CHITIETHOADON
add constraint chk_CHITIETHOADON_SL check (SL > 0)

insert into VATTU(MAVT, TENVT, DVT, GIAMUA, SLTON) VALUES
('VT01','XI MANG','BAO',50000,5000),
('VT02','CAT','KHOI',45000,50000),
('VT03','GACH ONG','VIEN',120,800000),
('VT04','GACH THE','VIEN',110,800000),
('VT05','DA LON','KHOI',25000,100000),
('VT06','DA NHO','KHOI',33000,100000),
('VT07','LAM GIO','CAI',15000,50000);

insert into KHACHHANG(MAKH, TENKH, DIACHI, DT, EMAIL) VALUES 
('KH01','NGUYEN THI BE','TAN BINH',8457895,'bnt@yahoo.com'),
('KH02','LE HOANG NAM','BINH CHANH',9878987,'namlehoang@abc.com.vn'),
('KH03','TRAN THI CHIEU','TAN BINH',8457895,null),
('KH04','MAI THI QUE ANH','BINH CHANH',null, null),
('KH05','LE VAN SANG','QUAN 10', null,'sanglv@hcm.vnn.vn'),
('KH06','TRAN HOANG KHAI','TAN BINH',8457897, null);

