# 💸 KharchaSnap – WhatsApp Expense Tracker with AI Insights

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![Database](https://img.shields.io/badge/SQLite-Embedded-lightgrey)
![Status](https://img.shields.io/badge/Status-Active-success)

---

## 🚀 Overview

**KharchaSnap** is a real-time, WhatsApp-based expense tracking system that enables users to log and track daily expenses using simple text messages.

It not only captures expenses but also provides **AI-driven spending insights**, helping users understand their spending patterns instantly.

---
💬 SIMPLE TAGLINE
👉 “बस WhatsApp पे खर्च लिखो, बाकी हम संभालेंगे”
## ✨ Key Features

* 📩 Add expenses via WhatsApp messages
* 🧠 Smart parsing (quantity + item + amount)
* 🏷️ Automatic category detection
* 📊 Clean tabular response format
* 🧾 **BILL command** → consolidated expenses for the day
* 🤖 **INSIGHTS command** → AI-ready insights (rule-based with GenAI extensibility)”
* 💾 Persistent storage using SQLite
* 👥 Multi-user support (based on WhatsApp number)

---

## 🎬 Demo Flow

### 📩 Add Expense

```id="a1"
10 vadapav 200, 2 milk 100
```

### ✅ Response

```id="a2"
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

### 📩 User:

```id="a3"
bill
```

### 📊 Response:

```id="a4"
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

## 🤖 AI-ready insights (rule-based with GenAI extensibility)”

### 📩 User:

```id="a5"
insights
```

### 📊 Response:

```id="a6"
📊 Spending Insights

💰 Total Spend Today: ₹3200  
🔥 Top Category: 🥛 Dairy (₹1350)  
📦 Most Bought Item: milk (3 times)  
⚠️ High Expense Item: ghee ₹1200  

💡 Suggestion:
You are spending more on Dairy. Consider optimizing or reducing this category.
```

---

## 🧠 How Insights Work

* Aggregates daily expenses
* Performs category-wise analysis
* Identifies spending patterns
* Detects highest expense items
* Generates intelligent recommendations

👉 Designed as a **lightweight AI engine (rule-based + extensible to GenAI)**

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
Service Layer (Business Logic + Insights Engine)
        │
        ▼
Repository Layer (JPA)
        │
        ▼
🗄️ SQLite Database (kharcha.db)
```

---

## 🧠 Architecture Highlights

* Event-driven WhatsApp webhook integration
* Stateless REST API design
* Modular service layer with analytics engine
* Real-time insights generation
* Embedded database for persistence
* Multi-user isolation using phone number

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* SQLite
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
service/         → Business logic + insights engine  
repository/      → Database operations  
model/           → Entity classes  
util/            → Message parsing logic  
```

---

## 💡 Future Enhancements

* 📄 Export bill as PDF
* 📊 Monthly analytics dashboard
* 🤖 GenAI-based conversational insights
* ☁️ Cloud deployment (AWS/Azure)
* 📈 Budget alerts & anomaly detection

---

## 🎯 Use Case

A **personal AI finance assistant** that enables frictionless expense tracking and intelligent spending awareness directly from WhatsApp.

---

## 👨‍💻 Author

**Anand Nerurkar**
Enterprise Technology Leader | AI & Cloud Architect

---

## ⭐ Resume Highlight

> Developed a WhatsApp-based expense tracking system with AI-driven spending insights, real-time parsing, category classification, and persistent storage using Spring Boot, SQLite, and Twilio APIs.

---
