# 💰 Budget Checker System

Hệ thống quản lý chi tiêu cá nhân toàn diện, bao gồm cả Backend API và Mobile App (Android), được tổ chức theo mô hình **Monorepo**.

Dự án này được thiết kế với mục tiêu cung cấp trải nghiệm ghi chép và phân tích chi tiêu nhanh chóng, đồng bộ dữ liệu real-time và áp dụng các best practice về Clean Architecture, CI/CD và Containerization.

## 📂 Cấu trúc Monorepo

Dự án được chia thành 2 module chính. Vui lòng xem tài liệu chi tiết ở từng thư mục:

| Thư mục | Khái quát | Tài liệu chi tiết |
| --- | --- | --- |
| 📱 [`/android`](./android) | Mobile App xây dựng bằng Kotlin, Clean Architecture & MVVM. | [📖 Android Docs](./android/README.md) |
| ⚙️ [`/backend`](./backend) | RESTful API server cung cấp dữ liệu cho ứng dụng. | [📖 Backend Docs](./backend/README.md) |

## 🏗 Tổng quan Kiến trúc Hệ thống

Hệ thống hoạt động theo mô hình Client-Server cơ bản:
* **Client:** Ứng dụng Android giao tiếp với Backend thông qua các RESTful API. Dữ liệu được cache ở Local Database (Room) để hỗ trợ Offline-first.
* **Server:** Xử lý logic nghiệp vụ, xác thực người dùng (Authentication) và lưu trữ dữ liệu (Database). Toàn bộ Backend và Database được đóng gói (Dockerized) để dễ dàng triển khai.

## 🚀 Khởi chạy hệ thống (Quick Start)

Cách nhanh nhất để khởi chạy toàn bộ môi trường Backend (API + Database) ở dưới local là sử dụng Docker.

### Yêu cầu
* [Docker](https://www.docker.com/) & Docker Compose đã được cài đặt.

### Các bước chạy Backend Local
1. Clone repository:
   ```bash
   git clone [https://github.com/hoanganhdangcode/budgetchecker.git](https://github.com/hoanganhdangcode/budgetchecker.git)
   cd budgetchecker