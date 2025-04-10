# 💰 Personal Finance Tracker (Console-Based)

## 📌 Project Overview

Welcome to the **Personal Finance Tracker**—a console-based application developed collaboratively by our squad with a strong emphasis on **Object-Oriented Programming (OOP)** and adherence to **SOLID principles**. This project also aims to enforce proper **Git Flow** practices throughout the development lifecycle.

---

## ✅ Functional Requirements

- [x] **Add**, **view**, **edit**, and **delete** transactions (incomes and expenses).
- [x] Each transaction must be linked to a **category** (e.g., Food, Rent, Salary, etc.).
- [x] Transactions must include a **Date** field.
- [ ] Implement **search** functionality to filter transactions by date, category, or amount.
- [ ] Generate **monthly summary** and **balance report** based on transaction dates.
- [ ] Implement **test cases** for all major functionalities.
---

## ⚙️ Technical Requirements

- 🧪 All logic-heavy features must include **check functions for unit testing**.
- 🔁 Follow **Dependency Inversion Principle** to ensure loosely-coupled architecture.
- 🗃️ Use **file storage** with a clearly abstracted data layer, making it easy to swap in another storage mechanism.
- 🌱 All new features must be created in **dedicated branches**, merged into `develop` via **pull requests**.

---

## 🔍 Architecture

- Object-Oriented structure
- SOLID-compliant design
- Separation of concerns (e.g., Models, Services, Repositories, and Interfaces)
- Easily extendable and testable system

---

## 📂 File Structure

```
|- datasource  # Data source layer containing interfaces and implementations for data access
|   |- DataSource.kt  # Interface for data source
|   |- FileTransactionStorage.kt  # File data source implementation
|- models  # Data models representing transactions and categories
|   |- Transaction.kt  # Transaction model
|   |- Category.kt  # Enum for categories
|   | TransactionType.kt  # Enum for transaction types (income/expense)
|- utils  # Utility classes and functions
|   |- JsonUtil.kt  # Utility functions for JSON serialization/deserialization
|-Main.kt 
```

## 🛠️ Git Flow Guidelines

```plaintext
main       → Production-ready code
develop    → Ongoing development
feature/*  → New feature branches
hotfix/*   → Emergency fixes
```

1. Create a new branch from `develop`: `git checkout -b feature/feature-name`
2. Work on your feature.
3. Push and open a PR targeting `develop`.
4. Request review, ensure check functions pass.
5. Merge after approval.

---

## 🧪 Testing

- All major functions have corresponding `check*()` methods.
- Use your IDE or testing framework to run test cases before committing.
- Code must be unit tested before PR submission.

---

## 📃 License

This project is developed for educational purposes and follows best practices for team-based software engineering.