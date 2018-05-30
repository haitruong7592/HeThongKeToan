--<<<<<<< HEAD:HTTTKT.sql
Create database HTKT
GO
use HTKT
GO

Create TABLE CuaHang(
	MSCH varchar(6) primary key,
	TenCH nvarchar(200),
	DiaChi nvarchar(200)
)
GO

Create TABLE TaiKhoan(
	MaTK varchar(15) primary key,
	TenTK nvarchar(200)
)

GO
Create TABLE KhachHang(
	MSKH varchar(6) primary key,
	HoTenKhach nvarchar(200),
	DiaChiKhach nvarchar(200),
	MaSoThue varchar(20)
)
GO

Create TABLE HangHoa(
	MSHH varchar(6) primary key,
	TenHang nvarchar(200), 
	DonViTinh nvarchar(200)
)
Go

Create TABLE PhieuNhap(
	MaPN varchar(6) primary key,
	NgayNhap DateTime,
	ThueSuatMua float,
	MSKH varchar(6) references KhachHang(MSKH),
	MSCH varchar(6) references CuaHang(MSCH),
	MaTK varchar(15) references TaiKhoan(MaTK)
)
GO

Create TABLE Mua(
	MaPN varchar(6),
	MSHH varchar(6),
	SoLuong int,
	DonGiaNhap money,
	primary key(MaPN,MSHH),
	foreign key(MaPN) references PhieuNhap(MaPN),
	foreign key(MSHH) references HangHoa(MSHH)
)
GO

Create TABLE HoaDon(
	MaHD varchar(6) primary key,
	SoHD varchar(15),
	NgayBan DateTime,
	LyDo nvarchar(MAX),	
	ThueSuatBan float,
	NgayThanhToan DateTime,
	MSCH varchar(6) references CuaHang(MSCH),
	MSKH varchar(6) references KhachHang(MSKH),
	MaTK varchar(15) references TaiKhoan(MaTK),
	Soseri varchar(100),
	TongTien float
	
)
GO

Create TABLE Ban(
	MaHD varchar(6),
	MSHH varchar(6),
	SoLuong int,
	DonGiaBan float
	primary key(MaHD,MSHH),
	foreign key(MaHD) references HoaDon(MaHD),
	foreign key(MSHH) references HangHoa(MSHH)
)
GO

Create TABLE PhieuThu(
	MaPT varchar(6) primary key,
	NgayThu DateTime,
	SoTienThu money,
	MSKH varchar(6) references KhachHang(MSKH)
	
)
GO

Create TABLE PhieuChi(
	MaPC varchar(6) primary key,
	NgayChi DateTime,
	SoTienChi money,
	MSKH varchar(6) references KhachHang(MSKH)
)

GO



Create function func_NextID(@lastUserID varchar(6), @prefix varchar(2), @size int)
	returns varchar(6)
as 
	begin
		if(@lastUserID='')
			set @lastUserID=@prefix + REPLICATE(0,@size - Len(@prefix))
		declare @num_nextUserID int, @nextUserID varchar(6)
		set @lastUserID=LTRIM(RTRIM(@lastUserID))
		set @num_nextUserID= REPLACE(@lastUserID, @prefix,'')+1
		set @size = @size - len(@prefix)
		set @nextUserID= @prefix + REPLICATE(0,@size-len(@prefix))
		set @nextUserID= @prefix + RIGHT(REPLICATE(0,@size)+ convert(varchar(max),@num_nextUserID ), @size)
		return @nextUserID
	end

GO

Create trigger tr_NextID_CuaHang on CuaHang
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MSCH  from CuaHang order by MSCH  desc)
		update CuaHang set MSCH  = dbo.func_NextID ( @lastUserID, 'CH', 6) where MSCH =''
	end

GO

Create trigger tr_NextID_KhachHang on KhachHang
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MSKH  from KhachHang order by MSKH  desc)
		update KhachHang set MSKH  = dbo.func_NextID ( @lastUserID, 'KH', 6) where MSKH =''
	end

GO

Create trigger tr_NextID_HangHoa on HangHoa
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MSHH  from HangHoa order by MSHH  desc)
		update HangHoa set MSHH  = dbo.func_NextID ( @lastUserID, 'HH', 6) where MSHH =''
	end

GO

Create trigger tr_NextID_PhieuNhap on PhieuNhap
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MaPN   from PhieuNhap order by MaPN   desc)
		update PhieuNhap set MaPN   = dbo.func_NextID ( @lastUserID, 'PN', 6) where MaPN  =''
	end

GO

Create trigger tr_NextID_HoaDon on HoaDon
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MaHD   from HoaDon order by MaHD   desc)
		update HoaDon set MaHD   = dbo.func_NextID ( @lastUserID, 'HD', 6) where MaHD  =''
	end

GO

Create trigger tr_NextID_PhieuThu on PhieuThu
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MaPT   from PhieuThu order by MaPT   desc)
		update PhieuThu set MaPT   = dbo.func_NextID ( @lastUserID, 'PT', 6) where MaPT  =''
	end

GO

Create trigger tr_NextID_PhieuChi on PhieuChi
for insert 
as 
	begin 
		declare @lastUserID varchar(6)
		set @lastUserID = (select TOP 1 MaPC   from PhieuChi order by MaPC   desc)
		update PhieuChi set MaPC   = dbo.func_NextID ( @lastUserID, 'PC', 6) where MaPC  =''
	end

Go
/**Cửa hàng**/
INSERT INTO dbo.CuaHang(MSCH,TenCH,DiaChi) Values('',N'Ministop - Bình Thạnh',N'449 Lê Quang Đinh - Bình Thạnh')
GO
INSERT INTO dbo.CuaHang(MSCH,TenCH,DiaChi) Values('',N'Ministop - Tân Bình',N'449 Cộng Hòa - Tân Bình')
GO
INSERT INTO dbo.CuaHang(MSCH,TenCH,DiaChi) Values('',N'Ministop - Thủ Đức',N'449 Võ Văn Ngân - Thủ Đức')
GO
/**Tài Khoản**/
Insert into dbo.TaiKhoan values('N131', N'Phải thu của khách')
Insert into dbo.TaiKhoan values('N133', N'Thuế GTGT được khấu trừ')
Insert into dbo.TaiKhoan values('C331', N'Phải trả cho người bán')
Go

/**Khách Hàng**/
INSERT INTO dbo.KhachHang Values('',N'Bùi Anh Hào',N'449/32/6 Lê Quang Ðịnh - Bình Thạnh','212326466')
GO
INSERT INTO dbo.KhachHang Values('',N'Bùi Anh Tuấn',N'449/32/6 Cộng Hòa - Tân Bình','212326467')
GO
INSERT INTO dbo.KhachHang Values('',N'Bùi Anh Khoa',N'449/32/6 Võ Văn Ngân- Thủ Đức','212326468')
GO
INSERT INTO dbo.KhachHang Values('',N'CocaVietNam',N'449/32/6 Võ Van Ngân - Thủ Đức','2215454652164')
GO
INSERT INTO dbo.KhachHang Values('',N'Mì Gói Vietnam',N'449/32/6 Võ Văn Ngân - Thủ Đức','2215454652164')
GO

/**Hàng Hóa**/
INSERT INTO dbo.HangHoa(MSHH,TenHang,DonViTinh) Values('',N'Cocacola Cà phê',N'chai')
GO
INSERT INTO dbo.HangHoa(MSHH,TenHang,DonViTinh) Values('',N'Cocacola',N'chai')
GO
INSERT INTO dbo.HangHoa(MSHH,TenHang,DonViTinh) Values('',N'Cocacola Cà phê Lon',N'Lon')
GO


/**Hóa Ðon**/
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('', 18728,convert(datetime,'18-06-12 10:34:09 PM',5), N'Xuất Bán',0.1,convert(datetime,'18-06-12 10:34:09 PM',5),'CH0001','KH0001','N131','c123o123c123a',4000000)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',72628,convert(datetime,'18-06-14 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-06-14 10:36:09 PM',5),'CH0001','KH0001','N131','c123o123c123a',100000000)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',42827,convert(datetime,'18-06-17 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-06-17 10:36:09 PM',5),'CH0001','KH0002','N131','c123o123c123a',49999444)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',38738,convert(datetime,'18-02-18 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-02-18 10:38:09 PM',5),'CH0002','KH0002','N131','c123o123c123a',28822000)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',383838,convert(datetime,'18-01-18 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-06-18 10:36:09 PM',5),'CH0003','KH0003','N131','c123o123c123a',90003333)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',38483,convert(datetime,'18-02-18 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-06-18 10:36:09 PM',5),'CH0003','KH0003','N131','c123o123c123a',888888333)
GO
INSERT INTO dbo.HoaDon(MaHD,SoHD,NgayBan,LyDo,ThueSuatBan,NgayThanhToan,MSCH,MSKH,MaTK,Soseri,TongTien) Values('',09494,convert(datetime,'18-03-18 10:34:09 PM',5),N'Xuất Bán',0.1,convert(datetime,'18-06-18 10:36:09 PM',5),'CH0003','KH0002','N131','c123o123c123a',888888890)
GO

/**Bán**/
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0001','HH0001',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0001','HH0002',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0001','HH0003',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0002','HH0001',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0002','HH0002',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0002','HH0003',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0003','HH0001',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0003','HH0002',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0004','HH0001',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0004','HH0002',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0004','HH0003',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0005','HH0001',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0005','HH0002',2,250000)
GO
Insert INTO dbo.Ban(MaHD,MSHH,SoLuong,DonGiaBan) Values('HD0005','HH0003',2,250000)

/**Phiếu Thu**/
INSERT INTO dbo.PhieuThu values('',convert(datetime,'18-06-14 10:34:09 PM',5),1000000,'KH0001')
GO
INSERT INTO dbo.PhieuThu values('',convert(datetime,'18-06-17 10:34:09 PM',5),1000000,'KH0002')
GO


/**Phiếu nhập**/
Insert Into dbo.PhieuNhap Values('',convert(datetime,'18-03-18 10:34:09 PM',5),0.1,'KH0001','CH0001', 'C331')
GO
Insert Into dbo.PhieuNhap Values('',convert(datetime,'18-04-18 10:34:09 PM',5),0.1,'KH0002','CH0002','C331')
GO

/**Mua**/
Insert Into dbo.Mua values('PN0001','HH0001',10,100000)
GO
Insert Into dbo.Mua values('PN0001','HH0002',10,200000)
GO
Insert Into dbo.Mua values('PN0002','HH0001',10,100000)
GO
Insert Into dbo.Mua values('PN0002','HH0002',30,200000)
GO

/**Phiếu Chi**/
Insert into dbo.PhieuChi values('',convert(datetime,'18-04-18 10:34:09 PM',5),3000000,'KH0001')
GO
Insert into dbo.PhieuChi values('',convert(datetime,'18-04-18 10:34:09 PM',5),7000000,'KH0002')
GO

Create PROC sp_statistic_avg(
	@store_id	VARCHAR(6),
	@date_from	DATE,
	@date_to	DATE
)
AS
BEGIN
	SELECT Sell.MSHH,dbo.HangHoa.TenHang, Sell.count_sell, Purchase.avg_price, Sell.count_sell * Purchase.avg_price AS total_money 
	FROM 
		(SELECT Ban.MSHH,SUM(dbo.Ban.SoLuong) AS count_sell FROM dbo.HoaDon JOIN dbo.Ban ON Ban.MaHD = HoaDon.MaHD 
		WHERE (dbo.HoaDon.NgayBan BETWEEN @date_from AND @date_to)  AND dbo.HoaDon.MSCH = @store_id
		GROUP BY Ban.MSHH) AS Sell
		JOIN
		(SELECT Mua.MSHH,(SUM(SoLuong*DonGiaNhap)/SUM(SoLuong)) AS avg_price FROM dbo.PhieuNhap JOIN dbo.Mua ON Mua.MaPN = PhieuNhap.MaPN 
		WHERE (PhieuNhap.NgayNhap BETWEEN @date_from AND @date_to) AND dbo.PhieuNhap.MSCH = @store_id
		GROUP BY Mua.MSHH) AS Purchase
		ON Sell.MSHH = Purchase.MSHH
		JOIN 
		dbo.HangHoa ON HangHoa.MSHH = Sell.MSHH
END
GO
﻿