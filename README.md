<<<<<<< HEAD
# 📊 Coding Profile Analyzer

A Spring Boot-based web application that aggregates coding activity from multiple competitive programming platforms and provides unified analytics like difficulty distribution, solved problems, and platform-wise insights.

---

## 🚀 Features

* 🔍 Search coding profiles using usernames
* 📊 View solved problems across platforms
* 🧠 Unified difficulty analysis (Easy / Medium / Hard / Unknown)
* 🌐 Multi-platform support:

    * Codeforces
    * LeetCode
* 🎯 Filter problems by difficulty
* 📈 Real-time stats dashboard
* 🔗 Direct links to problems
* 🧩 Clean MVC architecture

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Frontend:** Thymeleaf, HTML, CSS
* **API Integration:** WebClient (REST + GraphQL)
* **Architecture:** MVC (Controller → Service → View)

---

## 📡 APIs Used

* Codeforces API → problem submission data
* LeetCode GraphQL → user submission statistics

---

## 📊 Features Breakdown

### 1. Profile Analysis

* Fetches user data from multiple platforms
* Merges results into a single dataset

### 2. Difficulty Classification

* Easy
* Medium
* Hard
* Unknown (for missing data)

### 3. Filtering System

* Filter problems by difficulty in real time

### 4. Statistics Dashboard

* Total problems solved
* Difficulty-wise breakdown
* Platform aggregation (future extension ready)

---

## 🧠 Key Learnings

* REST API integration using WebClient
* GraphQL API consumption
* Data aggregation from multiple sources
* DTO-based clean architecture
* Server-side rendering using Thymeleaf
* Handling missing / inconsistent API data

---

## ⚠️ Limitations

* LeetCode API does not provide full problem-level data publicly
* Uses aggregated submission statistics instead of full problem history

---

## 📂 Project Structure

```
src/
 ├── controller
 ├── service
 ├── dto
 ├── config
 ├── resources/templates (Thymeleaf UI)
 └── resources/static (CSS)
```

---

## 🔮 Future Improvements

* 📊 Add charts (Easy vs Medium vs Hard visualization)
* 🏆 Platform comparison dashboard
* 👥 Compare two users
* 💾 Database integration for storing profiles
* 🔐 Authentication system
* 📱 Responsive UI improvements

---

## 🧑‍💻 How to Run

```bash
git clone https://github.com/your-username/coding-profile-analyzer.git
cd coding-profile-analyzer
mvn spring-boot:run
```

Open:

```
http://localhost:8081
```

---

## 👨‍🎓 Author

Built by Khushnuma Parween
A project focused on improving backend skills, API integration, and real-world system design understanding.

---
=======
## 📸 Screenshots

### Home Page
![Home Page](images/home.png)

### Dashboard
![Dashboard](images/dashboard.png)


### Filterssection
![Dashboard](images/filterssection.png)