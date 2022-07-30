
/* Giá bán của một vật tư bất kỳ cần lớn hơn hoặc bằng giá mua của nó. Ràng buộc này cần được đảm bảo khi insert/update một HOADON hay khi tạo một CHITIETHOADON hoặc update giá bán. */

ALTER TRIGGER trg_CheckGiaBan on CHITIETHOADON after insert
as 
begin
    IF EXISTS (
        select 1
        from 
            inserted inner join CHITIETHOADON 
            on inserted.MAHD = CHITIETHOADON.MAHD 
            inner join VATTU on CHITIETHOADON.MAVT = VATTU.MAVT
        where
            CHITIETHOADON.GIABAN < VATTU.GIAMUA
    )
    PRINT 'WRONG CONDUCT'
    ELSE PRINT 'OK THAO TAC NAY CO THE THUC HIEN'
end

/*
    khi mua hang thi nhung thu sau se bi anh huong
    hoa don
    chi tiet hoa don
*/
begin tran
    insert into HOADON VALUES('Q001','2022-01-01','KH01',0);
    insert into CHITIETHOADON VALUES('Q001','VT01',5,NULL,1000);
rollback

begin tran
    insert into HOADON VALUES('Q001','2022-01-01','KH01',0);
    insert into CHITIETHOADON VALUES('Q001','VT01',5,NULL,100000000);
rollback

SELECT * FROM HOADON
WHERE MAHD = 'Q001'

SELECT 'HOADON'
SELECT * FROM HOADON; -- MAHD, NGAY, MAKH, TONGTG
go
SELECT 'VATTU'
SELECT * FROM VATTU; -- MAVT, TENVT, DVT, GIAMUA, SLTON
go
SELECT 'CHITIETHOADON'
SELECT * FROM CHITIETHOADON; --MAHD, MAVT, SL, KHUYENMAI, GIABAN
go
SELECT 'KHACHHANG' --MAKH, TENKH, DIACHI, DT, EMAIL
SELECT * FROM KHACHHANG

.sql