# 📊 Coding Profile Analyzer

A Spring Boot web application that aggregates coding activity from multiple competitive programming platforms and presents unified analytics, including solved problems, difficulty distribution, and platform-wise insights.

## 🚀 Features

- 🔍 Search coding profiles using usernames
- 📊 View coding statistics across supported platforms
- 🧠 Unified difficulty classification: Easy, Medium, Hard, and Unknown
- 🌐 Multi-platform support:
  - Codeforces
  - LeetCode
- 🎯 Filter problems by difficulty
- 📈 Interactive coding statistics dashboard
- 🔗 Direct links to problems where available
- 🧩 Clean MVC architecture with separated controllers, services, and DTOs

## 🛠️ Tech Stack

| Category | Technologies |
|---|---|
| Language | Java |
| Backend | Spring Boot, Spring MVC |
| Frontend | Thymeleaf, HTML, CSS |
| API Integration | Spring WebClient, REST, GraphQL |
| Architecture | MVC, DTO-based design |
| Build Tool | Maven |

## 📡 APIs Used

- **Codeforces API:** Retrieves user submission data and problem information.
- **LeetCode GraphQL:** Retrieves available user submission statistics and coding activity.

## 📊 Features Breakdown

### 1. Profile Analysis
- Fetches coding data from multiple platforms using usernames.
- Aggregates available information into a unified dataset.
- Presents platform-specific and combined statistics.

### 2. Difficulty Classification

Problems are classified into the following categories:

- Easy
- Medium
- Hard
- Unknown, when difficulty information is unavailable

### 3. Difficulty Filtering
- Filter problems by difficulty.
- View the relevant problems without navigating through separate platform dashboards.

### 4. Statistics Dashboard
- Total solved problems based on the available data.
- Difficulty-wise breakdown.
- Platform-wise aggregation.
- Direct navigation to individual problems when problem-level data is available.

## 🧠 Key Learnings

- Integrating external APIs using Spring WebClient
- Consuming REST APIs and GraphQL endpoints
- Aggregating data from multiple sources
- Designing DTOs for structured data transfer
- Building server-rendered interfaces using Thymeleaf
- Handling missing, inconsistent, and incomplete API data
- Organizing backend logic using the MVC architecture

## ⚠️ Limitations

- LeetCode does not provide a stable, officially supported public API for all user and problem-level statistics.
- Available LeetCode data may be limited to aggregated submission statistics rather than a complete problem history.
- Difficulty classification depends on the metadata returned by each platform.
- API availability, rate limits, and response formats may affect data retrieval.

## 📂 Project Structure

```text
coding-profile-analyzer/
├── src/
│   └── main/
│       ├── java/
│       │   └── .../
│       │       ├── controller/
│       │       ├── service/
│       │       ├── dto/
│       │       └── config/
│       └── resources/
│           ├── templates/
│           ├── static/
│           │   └── css/
│           └── application.properties/
├── images/
│   ├── home.png
│   ├── dashboard.png
│   └── filterssection.png
├── pom.xml
└── README.md
```

*The package names and directory structure above are illustrative. Adjust them to match the actual project.*

## 📸 Screenshots

### 🏠 Home Page

![Home Page](images/home.png.png)

### 📊 Dashboard

![Dashboard](images/dashboard.png.png)

### 🎯 Difficulty Filters

![Difficulty Filters](images/filterssection.png.png)

## 🔮 Future Improvements

- 📊 Add charts to visualize difficulty distribution.
- 🏆 Build a platform comparison dashboard.
- 👥 Enable side-by-side coding profile comparisons.
- 💾 Integrate a database to store profiles and historical statistics.
- 🔐 Add user authentication and profile management.
- 📱 Improve responsive design for mobile devices.
- 📈 Track coding progress over time.

## ⚙️ How to Run

### Prerequisites

- Java installed and configured
- Maven
- Access to the required external APIs

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/coding-profile-analyzer.git
cd coding-profile-analyzer
```

### 2. Configure the Application

Update `src/main/resources/application.properties` with any required configuration, API settings, and server port.

### 3. Run the Application

```bash
mvn spring-boot:run
```

### 4. Open in Your Browser

```text
http://localhost:8080
```

## 👩‍💻 Author

**Khushnuma Parween**

A project focused on strengthening backend development skills, external API integration, data aggregation, and practical system design.

---

*Built with Java and Spring Boot.*
