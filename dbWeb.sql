CREATE DATABASE HeThongDichVuSinhVien;
use HeThongDichVuSinhVien;
CREATE TABLE NienKhoa (
    SoLopHoc INT,
    SoSinhVien INT,
    NienKhoa INT,
    NamBatDau INT,
    NamKetThuc INT,
    PRIMARY KEY (NienKhoa)
);
CREATE TABLE NamHoc (
    NamHoc INT PRIMARY KEY,
    NgayBatDau DATE,
    NgayKetThuc DATE
);
CREATE TABLE HocKy (
    MaHocKy varchar(10) PRIMARY KEY,
    HocKy int,
    NgayBatDau DATE,
    NgayKetThuc DATE,
    NamHoc INT,
    FOREIGN KEY (NamHoc) REFERENCES NamHoc(NamHoc)
);
CREATE TABLE TaiKhoan (    
    TenDangNhap VARCHAR(255),
    MatKhau VARCHAR(255),
	NgayTao DATE,
    Email VARCHAR(255) primary key,
    LoaiTaiKhoan VARCHAR(50)
);

-- Bảng CongTacVien
CREATE TABLE CongTacVien (
    MaCTV int NOT NULL auto_increment PRIMARY KEY,
    HoTen VARCHAR(255) CHARACTER SET utf8mb4,
    NgaySinh DATE,
    GioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    DiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    Email VARCHAR(255),
    SoDienThoai VARCHAR(20),
    NgayThamGia DATE,
    TrangThai BIT
);

-- Bảng QuanTriVien
CREATE TABLE QuanTriVien (
    MaQTV int NOT NULL auto_increment PRIMARY KEY,
    HoTen VARCHAR(255) CHARACTER SET utf8mb4,
    NgaySinh DATE,
    GioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    DiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    Email VARCHAR(255),
    SoDienThoai VARCHAR(20),
    NgayNhanChuc DATE
);
-- Bảng SinhVien
CREATE TABLE SinhVien (
    MaSV int NOT NULL auto_increment PRIMARY KEY,
    HoTen VARCHAR(255) CHARACTER SET utf8mb4,
    NgaySinh DATE,
    DiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    GioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    Email VARCHAR(255),
	CCCD varchar (50),
    SoDienThoai VARCHAR(20),
    NgayNhapHoc DATE,
    Lop varchar(10),
	NienKhoa int,
	TrangThai bit, 
    Khoa VARCHAR(255) CHARACTER SET utf8mb4
);
-- Bảng Lop
CREATE TABLE Lop (
    MaLop varchar(10) PRIMARY KEY,
    MaKhoa varchar(10),
	NienKhoa int
);


-- Bảng Khoa
CREATE TABLE Khoa (
    MaKhoa varchar(10) PRIMARY KEY,
    TenKhoa VARCHAR(255) CHARACTER SET utf8mb4,
    TruongKhoa VARCHAR(255) CHARACTER SET utf8mb4,
    SDTTruongKhoa VARCHAR(10),
    EmailKhoa VARCHAR(255)
);
;
-- Bảng HoatDong
CREATE TABLE HoatDong (
    MaHoatDong int NOT NULL auto_increment PRIMARY KEY,
    TenHoatDong VARCHAR(255) CHARACTER SET utf8mb4,
    MoTa VARCHAR(255) CHARACTER SET utf8mb4,
    NgayBatDau DATE,
	Diem int,
    TrangThai bit,
    NgayKetThuc DATE,
	ToChuc varchar(10),
    MaNguoiTao int
);
-- Bảng ToChuc
CREATE TABLE ToChuc (
    MaToChuc varchar(10) PRIMARY KEY,
    TenToChuc VARCHAR(255) CHARACTER SET utf8mb4,
    DiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    SoDienThoai VARCHAR(20),
    Email VARCHAR(255)
);
;
-- Bảng DiemCTXH
CREATE TABLE DiemCTXH (    
    MaSV int,
    MaHD int,
	NgayDangKy date,
	primary key (MaSV, MaHD)
);
-- Bảng HoiDap
CREATE TABLE HoiDap (
    MaCauHoi int NOT NULL auto_increment PRIMARY KEY,
    MaSV int,
    NoiDung VARCHAR(255) CHARACTER SET utf8mb4,
    NgayGui DATETIME,
    TrangThai BIT
);
create table PhanHoi(
	MaPhanHoi int NOT NULL auto_increment PRIMARY KEY,
	NoiDungPhanHoi VARCHAR(255) CHARACTER SET utf8mb4,
	NgayPhanHoi DATETIME,
	MaCTV int
);

-- Bảng XinGiayXacNhan
CREATE TABLE XinGiayXacNhan (
    MaLoaiGiay varchar(10),
	SoThuTu int auto_increment primary key,
    SoLuong int,
    MaSV int,
    LyDo VARCHAR(255) CHARACTER SET utf8mb4,
    MaHocKy varchar(10),
    ThoiGian DATETIME,
    TrangThai VARCHAR(255) CHARACTER SET utf8mb4
);
;
Create table XacNhanXinGiay(
	MaLoaiGiay varchar(10) ,
	SoThuTu int auto_increment primary key,
    SoLuong int,
    MaHocKy varchar(10),
	TrangThai VARCHAR(255) CHARACTER SET utf8mb4,
	ThoiGianNhan datetime,
    MaNguoiXacNhan int,
    LyDo VARCHAR(255) CHARACTER SET utf8mb4
)
;
create table LoaiGiay(
	MaLoaiGiay varchar(10) primary key,
	ChiPhi DECIMAL(19,2),
	TenLoaiGiay VARCHAR(255) CHARACTER SET utf8mb4,
    MaNguoiTao int,
    MoTa VARCHAR(255) CHARACTER SET utf8mb4,
    TrangThai bit
);

alter table Lop add foreign key (MaKhoa)references Khoa(MaKhoa);
alter table Lop add foreign key (NienKhoa)references NienKhoa(NienKhoa);
alter table SinhVien add foreign key (Lop) references Lop(MaLop);
alter table XinGiayXacNhan add foreign key (MaSV) references SinhVien(MaSV);
alter table XinGiayXacNhan add foreign key (MaLoaiGiay) references LoaiGiay(MaLoaiGiay);
alter table XinGiayXacNhan add foreign key (MaHocKy) references HocKy(MaHocKy);
alter table XacNhanXinGiay add foreign key (MaLoaiGiay) references LoaiGiay(MaLoaiGiay);
alter table XacNhanXinGiay add foreign key (MaNguoiXacNhan) references CongTacVien(MaCTV);
alter table XacNhanXinGiay add foreign key (MaHocKy) references HocKy(MaHocKy);
alter table HoiDap add foreign key (MaSV) references SinhVien(MaSV);
alter table PhanHoi add foreign key (MaCTV) references CongTacVien(MaCTV);
alter table PhanHoi add foreign key (MaPhanHoi) references HoiDap(MaCauHoi);
alter table HoatDong add foreign key (ToChuc) references ToChuc(MaToChuc);
alter table HoatDong add foreign key (MaNguoiTao) references CongTacVien(MaCTV);
alter table DiemCTXH add foreign key (MaHD) references HoatDong(MaHoatDong);
alter table DiemCTXH add foreign key (MaSV) references SinhVien(MaSV);
alter table SinhVien add foreign key (Email) references TaiKhoan(Email);
alter table CongTacVien add foreign key (Email) references TaiKhoan(Email);
alter table QuanTriVien add foreign key (Email) references TaiKhoan(Email);
alter table LoaiGiay add foreign key(MaNguoiTao) references QuanTriVien(MaQTV);
-----------------------------------------------------------------------------
-- Nhập dữ liệu cho bảng NienKhoa
INSERT INTO NienKhoa (SoLopHoc, SoSinhVien, NienKhoa, NamBatDau, NamKetThuc)
VALUES
(1, 2, 2021, 2021, 2029),
(1, 2, 2022, 2022, 2030);

-- Nhập dữ liệu cho bảng NamHoc
INSERT INTO NamHoc (NamHoc, NgayBatDau, NgayKetThuc)
VALUES
(2021, '2021-09-01', '2022-05-31'),
(2022, '2022-09-02', '2023-06-01'),
(2023, '2023-08-28', '2024-05-26'),
(2024, '2024-09-04', '2025-06-03'),
(2025, '2025-09-01', '2026-05-31'),
(2026, '2026-09-02', '2027-06-01'),
(2027, '2027-08-28', '2028-05-26'),
(2028, '2028-09-04', '2029-06-03'),
(2029, '2029-09-03', '2030-06-02'),
(2030, '2030-09-03', '2031-06-02');

-- Nhập dữ liệu cho bảng HocKy
INSERT INTO HocKy (MaHocKy, HocKy, NgayBatDau, NgayKetThuc, NamHoc)
VALUES
('2101', 1, '2021-09-01', '2021-12-31', 2021),
('2102', 2, '2022-01-01', '2022-05-31', 2021),
('2201', 1, '2022-09-02', '2023-01-01', 2022),
('2202', 2, '2023-01-02', '2023-06-01', 2022),
('2301', 1, '2023-08-28', '2023-12-26', 2023),
('2302', 2, '2023-12-27', '2024-05-26', 2023),
('2401', 1, '2024-09-04', '2025-01-03', 2024),
('2402', 2, '2025-01-04', '2025-06-03', 2024),
('2501', 1, '2025-09-01', '2025-12-31', 2025),
('2502', 2, '2026-01-01', '2026-05-31', 2025),
('2601', 1, '2026-09-02', '2027-01-01', 2026),
('2602', 2, '2027-01-02', '2027-06-01', 2026),
('2701', 1, '2027-08-28', '2027-12-26', 2027),
('2702', 2, '2027-12-27', '2028-05-26', 2027),
('2801', 1, '2028-09-04', '2029-01-03', 2028),
('2802', 2, '2029-01-04', '2029-06-03', 2028),
('2901', 1, '2029-09-03', '2030-01-02', 2029),
('2902', 2, '2030-01-03', '2030-06-02', 2029),
('3001', 1, '2030-09-03', '2030-01-02', 2030),
('3002', 2, '2031-01-03', '2031-06-02', 2030);
-- Nhập dữ liệu cho bảng TaiKhoan
INSERT INTO TaiKhoan (TenDangNhap, MatKhau, NgayTao, Email, LoaiTaiKhoan)
VALUES
('congtacvien1', 'congtacvien1', '2018-01-01', 'ctv1@gmail.com', 'ctv'),
('congtacvien2', 'congtacvien2', '2018-01-02', 'tuannguyen23823@gmail.com', 'ctv'),
('quantrivien1', 'quantrivien1', '2022-01-03', 'qtv1@gmail.com', 'qtv'),
('quantrivien2', 'quantrivien2', '2020-01-04', 'qtv2@gmail.com', 'qtv'),
('sinhvien1', 'sinhvien1', '2023-09-02', 'yasuozed2382003@gmail.com', 'sv'),
('sinhvien2', 'sinhvien2', '2023-09-02', 'sv2@gmail.com', 'sv'),
('sinhvien3', 'sinhvien3', '2023-09-03', 'sv3@gmail.com', 'sv'),
('sinhvien4', 'sinhvien4', '2023-09-02', 'sv4@gmail.com', 'sv'),
('sinhvien5', 'sinhvien5', '2023-09-02', 'sv5@gmail.com', 'sv'),
('sinhvien6', 'sinhvien6', '2023-09-03', 'sv6@gmail.com', 'sv');
INSERT INTO Khoa (MaKhoa, TenKhoa, TruongKhoa, SDTTruongKhoa, EmailKhoa)
VALUES
('K01', 'Khoa CNTT', 'Lê Văn Vinh', '0123456780', 'HCMUTEFIT@gmail.com'),
('k02', 'Khoa Kinh Tế', 'Đàng Quang Vắng', '0147852369', 'HCMUTEFE@gmail.com'),
('k03', 'Khoa CLC', 'Nguyễn Đăng Quang', '0123654987', 'HCMUTEFIT@gmail.com');
-- Tạo 4 lớp
INSERT INTO Lop (MaLop, MaKhoa, NienKhoa)
VALUES
('211103', 'K01', 2021),
('221102', 'K01', 2022),
('211201', 'K02', 2021),
('221204', 'K02', 2022),
('221301', 'K03', 2022),
('221303', 'K03', 2022);
-- Tạo 2 quản trị viên với email giống như bảng TaiKhoan
INSERT INTO QuanTriVien (MaQTV, HoTen, NgaySinh, GioiTinh, DiaChi, Email, SoDienThoai, NgayNhanChuc)
VALUES
(1, 'Nguyễn Thị Nhật Vy', '2003-01-01', 'Nữ', 'Thủ Đức', 'qtv1@gmail.com', '0101020304', '2023-01-01'),
(2, 'Nguyễn Linh Duyên', '2003-02-02', 'Nữ', 'Thủ Đức', 'qtv2@gmail.com', '0373314113', '2023-01-02');

-- Tạo 2 sinh viên với email giống như bảng TaiKhoan
INSERT INTO SinhVien (MaSV, HoTen, NgaySinh, GioiTinh, DiaChi, Email, CCCD, SoDienThoai, NgayNhapHoc, Lop, NienKhoa, TrangThai, Khoa)
VALUES
(1, 'Lê Thị Thùy Duyên', '2003-01-01', 'Nữ', 'Thủ Đức', 'yasuozed2382003@gmail.com', '123456789012', '0368812345', '2023-09-01', '211103', 2021, 1, 'Khoa CNTT'),
(2, 'Phan Ngọc Hân', '2003-02-02', 'Nữ', 'Thủ Đức', 'sv2@gmail.com', '987654321012', '0765348133', '2023-09-02', '221102', 2022, 1, 'Khoa CNTT'),
(3, 'Trần Văn An', '2003-03-03', 'Nam', 'Quận 1', 'sv3@gmail.com', '111222333444', '0987654321', '2023-09-03', '211201', 2021, 1, 'Khoa Kinh Tế'),
(4, 'Nguyễn Thị Bình', '2003-04-04', 'Nữ', 'Quận 2', 'sv4@gmail.com', '555666777888', '0123456789', '2023-09-04', '221204', 2022, 1, 'Khoa Kinh Tế'),
(5, 'Hoàng Văn Cường', '2003-05-05', 'Nam', 'Quận 3', 'sv5@gmail.com', '999000111222', '0345678901', '2023-09-05', '221301', 2022, 1, 'Khoa CLC'),
(6, 'Lê Thị Thảo', '2003-06-06', 'Nữ', 'Quận 4', 'sv6@gmail.com', '333444555666', '0678901234', '2023-09-06', '221303', 2022, 1, 'Khoa CLC');
-- Tạo 2 cộng tác viên với email giống như bảng TaiKhoan
INSERT INTO CongTacVien (MaCTV, HoTen, NgaySinh, GioiTinh, DiaChi, Email, SoDienThoai, NgayThamGia, TrangThai)
VALUES
(1, 'Bùi Trọng Trí', '2003-01-01', 'Nam', 'Thủ Đức', 'ctv1@gmail.com', '0123456789', '2023-01-05', 1),
(2, 'Nguyễn Ngọc Tuấn', '2003-08-23', 'Nam', 'Thủ Đức', 'tuannguyen23823@gmail.com', '0987654321', '2023-01-06', 1);
-- Tạo 1 tổ chức
INSERT INTO ToChuc (MaToChuc, TenToChuc, DiaChi, SoDienThoai, Email)
VALUES
('TC001', 'FPT', 'Đồng Nai', '0373123456', 'FPT@gmail.com'),
('TC002', 'Viettel', 'Thủ Đức', '0373123484', 'Viettel@gmail.com');
-- Tạo 2 hoạt động
INSERT INTO HoatDong (MaHoatDong, TenHoatDong, MoTa, NgayBatDau, Diem, TrangThai, NgayKetThuc, ToChuc, MaNguoiTao)
VALUES
(1, 'Hiến máu tình nguyện', 'Đi hiến máu', '2023-11-27', 5, 1, '2023-11-28', 'TC001', 1),
(2, 'Nhạc hội', 'Cổ vũ nhạc hội', '2023-11-24', 5, 1, '2023-12-27', 'TC001', 1),
(3, 'Bảo vệ môi trường', 'Dọn dẹp cỏ', '2023-12-07', 10, 1, '2023-12-14', 'TC002', 2),
(4, 'Hội thao', 'HCMUTE RUNNING', '2023-11-27', 15, 1, '2023-11-30', 'TC002', 2); 
-- Tạo 4 điểm Cộng tác xã hội
INSERT INTO DiemCTXH (MaSV, MaHD, NgayDangKy)
VALUES
(1, 1, '2023-11-27'),
(2, 1, '2023-11-28'),
(1, 2, '2023-11-24'),
(2, 2, '2023-11-25');
-- Tạo 3 câu hỏi đáp
INSERT INTO HoiDap (MaCauHoi, MaSV, NoiDung, NgayGui, TrangThai)
VALUES
(1, 1, 'Trường mình IT có mấy chuyên ngành ạ', '2023-11-27 10:00:00', 1),
(2, 2, 'Trường mình có những phương thức đóng học phí nào ạ', '2023-09-10 11:00:00', 1),
(3, 1, 'Làm thế nào để em có thể chuyển lớp ạ', '2023-10-20 12:00:00', 0);

-- Tạo 2 phản hồi
INSERT INTO PhanHoi (MaPhanHoi, NoiDungPhanHoi, NgayPhanHoi, MaCTV)
VALUES
(1, '4 chuyên ngành em nhé', '2023-11-28 14:00:00', 1),
(2, 'Em có thể tìm hiểu thêm trên trang web trường', '2023-09-15 15:00:00', 2),
(3, null, null, null);
-- Tạo 2 loại giấy
INSERT INTO LoaiGiay (MaLoaiGiay, ChiPhi, TenLoaiGiay, MaNguoiTao, MoTa, TrangThai)
VALUES
('G001', 0, 'Giấy Xác Nhận Sinh Viên', 2, 'Dùng để xác nhận bạn đang là sinh viên tại trường UTE', 1),
('G002', 15, 'Giấy Vay Vốn', 2, 'Dùng để vay vốn cho sinh viên', 1),
('G003', 10, 'Đơn Xin Ở - DDQH TP.HCM', 1, 'Xin lại giấy tạm trú', 1),
('G004', 20, 'Giấy Bảng Điểm', 1, 'Để xem thông tin bảng điểm của sinh viên', 1),
('G005', 14, 'Giấy Xác Nhận Ngành Nghề', 1, 'Dùng để xác nhận ngành nghề', 1);

-- Tạo 3 xin giấy và 3 xác nhận xin giấy
INSERT INTO XinGiayXacNhan (MaLoaiGiay, SoThuTu, SoLuong, MaSV, LyDo, ThoiGian, TrangThai)
VALUES
('G001', 1, 1, 1, 'Cần để xác nhận đang là sinh viên', '2023-11-27 10:00:00', 'Đã Duyệt'),
('G001', 2, 4, 2, 'Cần để xác nhận đang là sinh viên', '2023-11-28 11:00:00', 'Chờ Xác Nhận'),
('G002', 3, 3, 1, 'Không đủ tiền đóng học phí', '2023-11-29 12:00:00', 'Bị Từ Chối'),
('G003', 4, 1, 4, 'Em hết tiền ở trọ', '2023-12-05 12:11:35', 'Chờ Xác Nhận'),
('G003', 5, 1, 5, 'Em hết tiền ở trọ', '2023-12-05 12:11:35', 'Chờ Xác Nhận'),
('G003', 6, 1, 6, 'Em hết tiền ở trọ', '2023-12-05 12:11:35', 'Chờ Xác Nhận');

INSERT INTO XacNhanXinGiay (MaLoaiGiay, SoThuTu, SoLuong, TrangThai, ThoiGianNhan, MaNguoiXacNhan, LyDo)
VALUES
('G001', 1, 1, 'Đã Xác Nhận', '2023-11-28 14:00:00', 1, 'Chấp Nhận'),
('G001', 2, 4, 'Chờ Xác Nhận', NULL, NULL, NULL),
('G002', 3, 3, 'Đã Xác Nhận', '2023-11-30 15:00:00', 2, 'Cần Giấy Hộ Nghèo'),
('G003', 4, 1, 'Chờ Xác Nhận', NULL, NULL, NULL),
('G003', 5, 1, 'Chờ Xác Nhận', NULL, NULL, NULL),
('G003', 6, 1, 'Chờ Xác Nhận', NULL, NULL, NULL);
DELIMITER $$
CREATE PROCEDURE `ThemTaiKhoanSinhVien`(
    IN pTenDangNhap VARCHAR(255),
    IN pMatKhau VARCHAR(255),
    IN pEmail VARCHAR(255),
    IN pLoaiTaiKhoan VARCHAR(255),
    IN pHoTen VARCHAR(255) CHARACTER SET utf8mb4,
    IN pNgaySinh DATE,
    IN pGioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pCanCuocCongDan VARCHAR(255),
    IN pSoDienThoai VARCHAR(255),
    IN pNgayNhapHoc DATE,
    IN pLop VARCHAR(255),
    IN pNienKhoa int,
    IN pKhoa VARCHAR(255) CHARACTER SET utf8mb4
)
BEGIN
    -- Thêm vào bảng TaiKhoan
    INSERT INTO taikhoan (TenDangNhap, MatKhau, NgayTao, Email, LoaiTaiKhoan)
    VALUES (pTenDangNhap, pMatKhau, pNgayNhapHoc, pEmail, pLoaiTaiKhoan);

    -- Thêm vào bảng SinhVien
    INSERT INTO sinhvien (HoTen, NgaySinh, GioiTinh, DiaChi, Email, CCCD, SoDienThoai, NgayNhapHoc, Lop, NienKhoa, TrangThai, Khoa)
    VALUES (pHoTen, pNgaySinh, pGioiTinh, pDiaChi, pEmail, pCanCuocCongDan, pSoDienThoai, pNgayNhapHoc, pLop, pNienKhoa, 1, pKhoa);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `SuaThongTinLienLac`(
	IN pEmailOld VARCHAR(255),
    IN pEmailNew VARCHAR(255),
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pCanCuocCongDan VARCHAR(255),
    IN pSoDienThoai VARCHAR(255)
)
BEGIN
    SET foreign_key_checks = 0;
    UPDATE sinhvien
	SET Email = pEmailNew, DiaChi = pDiaChi, CCCD = pCanCuocCongDan, SoDienThoai = pSoDienThoai WHERE Email = pEmailOld;
	UPDATE taikhoan
	SET Email = pEmailNew WHERE Email = pEmailOld;
    SET foreign_key_checks = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `DangKyGiayXacNhan`(
	IN pMaLoaiGiay VARCHAR(255),
    IN pSoLuong int,
    IN pLyDo VARCHAR(255) CHARACTER SET utf8mb4,
    IN pMaSV int,
    IN pThoiGian datetime,
    IN pMaHocKy varchar(10)
)
BEGIN
	INSERT INTO XinGiayXacNhan (MaLoaiGiay, SoLuong, MaSV, MahocKy, LyDo, ThoiGian, TrangThai)
	VALUES
	(pMaLoaiGiay, pSoLuong, pMaSV, pMaHocKy, pLyDo, pThoiGian, 'Chờ Xác Nhận');
    INSERT INTO XacNhanXinGiay (MaLoaiGiay, SoLuong, TrangThai, MaHocKy)
	VALUES
	(pMaLoaiGiay, pSoLuong, 'Chờ Xác Nhận', pMaHocKy);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `DuyetGiayXacNhan`(
    IN pSoThuTu int,
    IN pThoiGian datetime,
    IN pMaNguoiXacNhan int,
    IN pTrangThai VARCHAR(255) CHARACTER SET utf8mb4,
    IN pLyDo VARCHAR(255) CHARACTER SET utf8mb4
)
BEGIN
	UPDATE XinGiayXacNhan
    SET TrangThai = pTrangThai WHERE SoThuTu = pSoThuTu;
    UPDATE XacNhanXinGiay
    SET TrangThai = pTrangThai, LyDo = pLyDo, ThoiGianNhan = pThoiGian, MaNguoiXacNhan = pMaNguoiXacNhan WHERE SoThuTu = pSoThuTu;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `XoaGiayXacNhan`(
    IN pSoThuTu int
)
BEGIN
	UPDATE XinGiayXacNhan
    SET TrangThai = 'Đã Xóa' WHERE SoThuTu = pSoThuTu;
    UPDATE XacNhanXinGiay
    SET TrangThai = 'Đã Xóa' WHERE SoThuTu = pSoThuTu;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `GetGiayXacNhanByMaSV`(IN pMaSV INT)
BEGIN
    SELECT X.ThoiGian, L.TenLoaiGiay, X.MahocKy, X.SoThuTu, X.SoLuong, L.ChiPhi, G.ThoiGianNhan, X.TrangThai
    FROM XinGiayXacNhan AS X 
    INNER JOIN LoaiGiay AS L ON X.MaLoaiGiay = L.MaLoaiGiay
    INNER JOIN XacNhanXinGiay AS G ON X.SoThuTu = G.SoThuTu
    WHERE X.MaSV = pMaSV;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `XemDanhSachCTXH`(IN pMaSV INT)
BEGIN
    SELECT H.MaHoatDong, H.TenHoatDong, H.MoTa, H.NgayBatDau, H.NgayKetThuc, H.Diem, T.TenToChuc 
	FROM HoatDong AS H
	INNER JOIN ToChuc AS T ON H.ToChuc = T.MaToChuc
	WHERE H.MaHoatDong IN (SELECT MaHD FROM DiemCTXH WHERE MaSV = pMaSV);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `XemDanhSachHoatDongConLai`(IN pMaSV INT)
BEGIN
    SELECT H.MaHoatDong, H.TenHoatDong, H.MoTa, H.NgayBatDau, H.NgayKetThuc, H.Diem, T.TenToChuc 
	FROM HoatDong AS H
	INNER JOIN ToChuc AS T ON H.ToChuc = T.MaToChuc
	WHERE H.MaHoatDong NOT IN (SELECT MaHD FROM DiemCTXH WHERE MaSV = pMaSV);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `ThamGiaHoatDong`(
IN pMaSV INT,
IN pMaHD INT,
IN pNgayDangKy date
)
BEGIN
    INSERT INTO DiemCTXH(MaSV, MaHD, NgayDangKy)VALUES(pMaSV, pMaHD, pNgayDangKy);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `GuiHoiDap`(
IN pMaSV INT,
IN pNoiDung VARCHAR(255) CHARACTER SET utf8mb4,
IN pNgayGui datetime
)
BEGIN
    INSERT INTO HoiDap(MaSV, NoiDung, NgayGui, TrangThai)
    VALUES
    (pMaSV, pNoiDung, pNgayGui, 0);
    INSERT INTO PhanHoi(NoiDungPhanHoi) VALUES(null);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `GuiPhanHoi`(
IN pMaCauHoi INT,
IN pNoiDung VARCHAR(255) CHARACTER SET utf8mb4,
IN pNgayPhanHoi datetime,
IN pMaCTV INT
)
BEGIN
    UPDATE HoiDap
    SET TrangThai = 1 WHERE MaCauHoi = pMaCauHoi;
    UPDATE PhanHoi
    SET NoiDungPhanHoi = pNoiDung, NgayPhanHoi = pNgayPhanHoi, MaCTV = pMaCTV WHERE MaPhanHoi = pMaCauHoi;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `SuaThongTinLienLacCongTacVien`(
	IN pEmailOld VARCHAR(255),
    IN pEmailNew VARCHAR(255),
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pSoDienThoai VARCHAR(255)
)
BEGIN
    SET foreign_key_checks = 0;
    UPDATE congtacvien
	SET Email = pEmailNew, DiaChi = pDiaChi, SoDienThoai = pSoDienThoai WHERE Email = pEmailOld;
	UPDATE taikhoan
	SET Email = pEmailNew WHERE Email = pEmailOld;
    SET foreign_key_checks = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `SuaThongTinLienLacQuanTriVien`(
	IN pEmailOld VARCHAR(255),
    IN pEmailNew VARCHAR(255),
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pSoDienThoai VARCHAR(255)
)
BEGIN
    SET foreign_key_checks = 0;
    UPDATE quantrivien
	SET Email = pEmailNew, DiaChi = pDiaChi, SoDienThoai = pSoDienThoai WHERE Email = pEmailOld;
	UPDATE taikhoan
	SET Email = pEmailNew WHERE Email = pEmailOld;
    SET foreign_key_checks = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `SuaTaiKhoanSinhVien`(
    IN pTenDangNhap VARCHAR(255),
    IN pMatKhau VARCHAR(255),
    IN pEmail VARCHAR(255),
	IN pOldEmail VARCHAR(255),
    IN pHoTen VARCHAR(255) CHARACTER SET utf8mb4,
    IN pNgaySinh DATE,
    IN pGioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pCanCuocCongDan VARCHAR(255),
    IN pSoDienThoai VARCHAR(255),
    IN pLop VARCHAR(255),
    IN pNienKhoa int,
    IN pKhoa VARCHAR(255) CHARACTER SET utf8mb4,
    IN pMaSV INT
)
BEGIN
	SET foreign_key_checks = 0;
    UPDATE sinhvien
    SET HoTen = pHoTen, NgaySinh = pNgaySinh, GioiTinh = pGioiTinh, DiaChi = pDiaChi, Email = pEmail, 
    CCCD = pCanCuocCongDan, SoDienThoai = pSoDienThoai, Lop = pLop, NienKhoa = pNienKhoa, Khoa = pKhoa
    WHERE MaSV = pMaSV;
     
    UPDATE TaiKhoan
    SET TenDangNhap = pTenDangNhap, MatKhau = pMatKhau, Email = pEmail
    WHERE Email = pOldEmail;
	SET foreign_key_checks = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `ThemTaiKhoanCongTacVien`(
    IN pTenDangNhap VARCHAR(255),
    IN pMatKhau VARCHAR(255),
    IN pEmail VARCHAR(255),
    IN pLoaiTaiKhoan VARCHAR(255),
    IN pHoTen VARCHAR(255) CHARACTER SET utf8mb4,
    IN pNgaySinh DATE,
    IN pGioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pSoDienThoai VARCHAR(255),
    IN pNgayThamGia DATE
)
BEGIN
    -- Thêm vào bảng TaiKhoan
    INSERT INTO taikhoan (TenDangNhap, MatKhau, NgayTao, Email, LoaiTaiKhoan)
    VALUES (pTenDangNhap, pMatKhau, pNgayThamGia, pEmail, pLoaiTaiKhoan);

    -- Thêm vào bảng Cộng Tác Viên
    INSERT INTO CongTacVien (HoTen, NgaySinh, GioiTinh, DiaChi, Email, SoDienThoai, NgayThamGia, TrangThai)
    VALUES (pHoTen, pNgaySinh, pGioiTinh, pDiaChi, pEmail, pSoDienThoai, pNgayThamGia, 1);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `SuaTaiKhoanCongTacVien`(
    IN pTenDangNhap VARCHAR(255),
    IN pMatKhau VARCHAR(255),
    IN pEmail VARCHAR(255),
	IN pOldEmail VARCHAR(255),
    IN pHoTen VARCHAR(255) CHARACTER SET utf8mb4,
    IN pNgaySinh DATE,
    IN pGioiTinh VARCHAR(255) CHARACTER SET utf8mb4,
    IN pDiaChi VARCHAR(255) CHARACTER SET utf8mb4,
    IN pSoDienThoai VARCHAR(255),
    IN pMaCTV INT
)
BEGIN
	SET foreign_key_checks = 0;
    UPDATE CongTacVien
    SET HoTen = pHoTen, NgaySinh = pNgaySinh, GioiTinh = pGioiTinh, DiaChi = pDiaChi, Email = pEmail, 
    SoDienThoai = pSoDienThoai
    WHERE MaCTV = pMaCTV;
     
    UPDATE TaiKhoan
    SET TenDangNhap = pTenDangNhap, MatKhau = pMatKhau, Email = pEmail
    WHERE Email = pOldEmail;
	SET foreign_key_checks = 1;
END$$
DELIMITER ;

CREATE VIEW CTVXemGiayXacNhan AS
SELECT X.ThoiGian, L.TenLoaiGiay, X.SoThuTu, X.SoLuong, L.ChiPhi,
		X.MaSV, G.MaHocKy, G.ThoiGianNhan, X.TrangThai, G.MaNguoiXacNhan, L.TrangThai AS TinhTrangGiay
FROM XinGiayXacNhan AS X 
INNER JOIN LoaiGiay AS L ON X.MaLoaiGiay = L.MaLoaiGiay
INNER JOIN XacNhanXinGiay AS G ON X.SoThuTu = G.SoThuTu;

CREATE VIEW XemPhanHoi AS 
SELECT H.MaCauHoi, H.MaSV, H.NoiDung, H.NgayGui, P.NoiDungPHanHoi, P.NgayPhanHoi, P.MaCTV
FROM HoiDap AS H INNER JOIN PhanHoi AS P ON H.MaCauHoi = P.MaPhanHoi;