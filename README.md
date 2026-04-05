# 💸 KharchaSnap – WhatsApp Expense Tracker

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![Database](https://img.shields.io/badge/SQLite-Embedded-lightgrey)
![Status](https://img.shields.io/badge/Status-Active-success)

---

## 🚀 Overview

**KharchaSnap** is a real-time, WhatsApp-based expense tracking system that enables users to log and track daily expenses using simple text messages.

It intelligently parses messages, categorizes expenses, and generates structured outputs along with a consolidated daily bill.

---

## ✨ Key Features

* 📩 Add expenses via WhatsApp messages
* 🧠 Smart parsing (quantity + item + amount)
* 🏷️ Automatic category detection (Dairy, Food, Grocery, Fuel, General)
* 📊 Clean tabular response format
* 🧾 **BILL command** → consolidated expenses for the day
* 💾 Persistent storage using SQLite
* 👥 Multi-user support (based on WhatsApp number)

---

## 🎬 Demo Flow

1️⃣ User sends message:

```
10 vadapav 200, 2 milk 100
```

2️⃣ System responds:

```
✅ Added:

Quantity   Item Name      Category      Amount
----------------------------------------------
10         vadapav        🍔 Food       ₹200
2          milk           🥛 Dairy      ₹100
----------------------------------------------
TOTAL                      ₹300
```

---

## 🧾 BILL Feature

Send:

```
bill
```

📊 System returns **full day consolidated expenses**:

```
🧾 Today's Bill

Quantity   Item Name      Category      Amount
----------------------------------------------
10         vadapav        🍔 Food       ₹200
2          milk           🥛 Dairy      ₹100
1          chai           🍔 Food       ₹20
----------------------------------------------
TOTAL                      ₹320
```

---

## 🏗️ Architecture

```
📱 WhatsApp User
        │
        ▼
Twilio WhatsApp API
        │
        ▼
Spring Boot Application
        │
        ▼
Controller Layer (Webhook)
        │
        ▼
Parser Engine (Regex आधारित parsing)
        │
        ▼
Service Layer (Business Logic)
        │
        ▼
Repository Layer (JPA)
        │
        ▼
🗄️ SQLite Database (kharcha.db)
```

---

## 🧠 Architecture Highlights

* Event-driven communication via WhatsApp webhook
* Stateless REST API design
* Regex-based intelligent parsing engine
* Layered architecture (Controller → Service → Repository)
* Embedded database (SQLite) for persistence
* Multi-user data isolation using phone number

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* SQLite (Embedded Database)
* Twilio WhatsApp API
* Maven

---

## 📸 Screenshots

### 📩 Input Message

![Input](screenshots/input.png)

### ✅ Parsed Output

![Output](screenshots/output.png)

### 🧾 Daily Bill

![Bill](screenshots/bill.png)

---

## 📦 Setup & Run

### 1️⃣ Clone Repository

```
git clone https://github.com/yourusername/kharchasnap.git
cd kharchasnap
```

---

### 2️⃣ Configure Application

Ensure `application.properties`:

```
spring.datasource.url=jdbc:sqlite:kharcha.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Run Application

```
mvn clean install
mvn spring-boot:run
```

---

## 🔗 Twilio Webhook Setup

* Configure WhatsApp Sandbox in Twilio
* Set webhook URL to:

```
https://<your-ngrok-url>/whatsapp
```

* Use **ngrok** to expose local server

---

## 📁 Project Structure

```
controller/      → WhatsApp API handling
service/         → Business logic
repository/      → Database operations
model/           → Entity classes
util/            → Message parsing logic
```

---

## 💡 Future Enhancements

* 📄 Export bill as PDF
* 📊 Monthly analytics dashboard
* 🤖 AI-based spending insights
* ☁️ Cloud deployment (AWS/Azure)
* 📈 Category-wise spending trends

---

## 🎯 Use Case

Designed as a **personal finance assistant** for quick, frictionless expense tracking without needing a dedicated mobile app.

---

## 👨‍💻 Author

**Anand Nerurkar**
Enterprise Technology Leader | AI & Cloud Architect

---

## ⭐ Resume Highlight

> Developed a WhatsApp-based expense tracking system with real-time parsing, category classification, and persistent storage using Spring Boot, SQLite, and Twilio APIs.

---
