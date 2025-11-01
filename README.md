# 🎟️ Ticket Booking System (CLI-Based)

The **Ticket Booking System** is a Java-based console application built to demonstrate strong **Object-Oriented Programming (OOP)** design and practical software engineering concepts.  
It offers a command-line platform for **user authentication, train search, seat booking, and ticket management**, following a modular and maintainable architecture.

A big thanks to **Lovepreet Singh** for his amazing tutorials and guidance that inspired me to bring this project to life! 🙌  

---

## 🚀 Features

✔️ **User Authentication** – Secure sign-up and login using hashed passwords (BCrypt)  
✔️ **Train Search** – Search trains by source and destination  
✔️ **Seat Availability Check** – View seat layout and availability before booking  
✔️ **Ticket Booking & Cancellation** – Reserve and manage your train tickets easily  
✔️ **Data Persistence** – User and train data stored in JSON files  
✔️ **Unique Ticket IDs** – Every booking is assigned a unique, time-based ID  

---

## 🛠️ Technologies Used

🔹 **Java** – Core logic and application structure  
🔹 **Gradle** – Build automation and dependency management  
🔹 **Jackson Library** – JSON serialization and deserialization  
🔹 **BCrypt** – Password hashing for secure authentication  
🔹 **Collections & Streams API** – Efficient data filtering and processing  
🔹 **OOP Principles** – Encapsulation, Abstraction, Inheritance, and Polymorphism  

---

## 📦 Setup & Run

Step 1: Clone the Repository  
git clone https://github.com/Pillapavan/TicketBookingSystem.git  
cd TicketBookingSystem  

Step 2: Build the Project  
./gradlew build  

Step 3: Run the Application  
./gradlew run  

---

## 📌 How to Use

1️⃣ **Sign Up** – Create a new user account  
2️⃣ **Login** – Log in securely using your credentials  
3️⃣ **Search Trains** – Enter source and destination stations  
4️⃣ **View Seats** – Check seat availability for a specific train  
5️⃣ **Book a Seat** – Select row and seat number to confirm your booking  
6️⃣ **Cancel Booking** – Cancel an existing reservation if needed  
7️⃣ **Exit** – Safely close the application  

---

## 🧩 Project Structure

TicketBookingSystem/  
│  
├── src/main/java/Ticket/booking/  
│   ├── Entities/        # User, Train, and Ticket classes  
│   ├── Service/         # TrainService, UserService, and related logic  
│   ├── Utils/           # Utility classes (e.g., password hashing, ID generation)  
│   └── Main.java        # Application entry point  
│  
├── LocalDb/  
│   ├── User.json        # Stores user data  
│   └── Train.json       # Stores train details  
│  
├── build.gradle          # Gradle build file  
└── README.md             # Project documentation  

---

## 🏗️ Future Enhancements

🚀 **Graphical User Interface (GUI)** – Add an interactive front-end using JavaFX or Swing  
💾 **Database Integration** – Replace JSON files with MySQL or PostgreSQL for better scalability  
🔧 **Admin Panel** – Allow admin users to manage trains, schedules, and bookings  
📅 **Ticket History & Date-based Search** – Support bookings for specific dates  

---

## 🙌 Acknowledgements

Special thanks to **Lovepreet Singh** for his in-depth Java tutorials and practical project guidance.  
His work motivated me to explore real-world software design using OOP principles.

---

## 🧠 Author

👤 **Pavan Kumar Pilla**  
📍 Passionate Java Developer | Exploring AI & Full Stack Development  
🔗 [GitHub Profile](https://github.com/Pillapavan)
