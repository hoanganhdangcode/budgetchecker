# 💰 Budget Checker App

Một ứng dụng quản lý chi tiêu cá nhân dành cho Android, được xây dựng dựa trên các tiêu chuẩn và best practice hiện đại nhất của Mobile Development.

## 🛠 Tech Stack & Cốt lõi kỹ thuật

Dự án áp dụng các công nghệ và thư viện phổ biến:
- **Ngôn ngữ:** Kotlin
- **Kiến trúc:** Clean Architecture kết hợp MVVM (Model-View-ViewModel)
- **Dependency Injection:** [Hilt / Koin - *Điền DI framework bạn đang dùng*]
- **Local Data:** Room Database (Hỗ trợ truy xuất offline)
- **Bất đồng bộ:** Kotlin Coroutines & Flow
- **UI:** [Jetpack Compose / XML View Binding]

## 🏗 Kiến trúc hệ thống (Architecture)

Dự án tuân thủ nghiêm ngặt **Clean Architecture**, phân tách rõ ràng các mối quan tâm (Separation of Concerns) để code dễ bảo trì, dễ scale và dễ viết Unit Test. Chiều phụ thuộc (Dependency Rule) luôn hướng từ ngoài (Presentation, Data) vào trong (Domain).

```mermaid
graph TD
    subgraph Presentation["1. Presentation Layer (MVVM)"]
        UI[UI: Splash, Auth, Mainboard]
        VM[ViewModels & States]
        UI -->|Observe State| VM
    end

    subgraph Domain["2. Domain Layer (Core Business)"]
        UC[Use Cases: InitSettings...]
        RepoInt[Repository Interfaces]
        Model[Domain Models: LoggedUser]
        
        VM -->|Execute| UC
        UC -->|Get/Save Data| RepoInt
        UC -->|Return| Model
    end

    subgraph Data["3. Data Layer"]
        RepoImp[Repository Implementations]
        Local[Local Data Source]
        Remote[Remote / API]
        
        RepoImp -.->|Implement| RepoInt
        RepoImp --> Local
        RepoImp --> Remote
    end

    subgraph CoreAndDI["Cross-cutting Concerns"]
        DI[DI: AuthModule, DatabaseModule]
        Core[Core: utils, constants, uimanager]
    end

    %% Luồng Dependency Injection
    DI -.->|Inject| Presentation
    DI -.->|Inject| Domain
    DI -.->|Inject| Data