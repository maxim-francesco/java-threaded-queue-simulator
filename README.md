# 🧵 Java Queue Management System – PT Assignment 2

This is a **Java multithreaded queue simulation application** developed for the **Fundamental Programming Techniques** course (Assignment 2). The system manages multiple queues in parallel using **Java Threads and Synchronization**, with the goal of minimizing client waiting time through dynamic queue assignment.

---

## 🚦 Features

- Generate N clients with:
  - Unique ID
  - Random arrival time within user-defined interval
  - Random service time within user-defined interval
- Assign clients dynamically to the queue with the **shortest waiting time**
- Run a **simulation over a user-defined time period**
- Use **Q threads** (one per queue) + one simulation manager thread
- Real-time **log output** showing:
  - Time step
  - Clients waiting
  - Clients in each queue
- Final statistics:
  - **Average waiting time**
  - **Peak time**
  - **Service duration per queue**

---

## 🔧 Technical Details

- **Java Concurrency**:
  - Threads per queue
  - One manager thread controlling simulation time and client assignment
  - `synchronized` blocks to ensure thread-safe data structures
- **UI Interface**:
  - Input panel for simulation parameters
  - Real-time queue state visualization
  - Log and stats display
- **Modular Design**:
  - Classes under 300 lines
  - Methods under 30 lines
- **Input Parameters**:
  - Number of clients and queues
  - Simulation duration
  - Min/Max arrival and service times

---

## 📊 Example Input (Test Case)

- Clients: 4  
- Queues: 2  
- Simulation Time: 60 seconds  
- Arrival Time Range: [2, 30]  
- Service Time Range: [2, 4]  

The application randomly generates 4 clients and assigns them to the best available queue as simulation time progresses. Logs track every step.

---

## 📁 Project Structure

```
src/
├── model/
│   ├── Client.java
│   ├── Queue.java
│   └── EventLogger.java
├── controller/
│   ├── Scheduler.java
│   └── SimulationManager.java
├── ui/
│   └── MainFrame.java
├── utils/
│   └── RandomGenerator.java
├── resources/
│   └── test_log.txt
└── diagrams/
    ├── use_case.drawio
    ├── class_diagram.drawio
    └── package_diagram.drawio
```

---

## ✅ What I Learned

- Implementing a multithreaded system in Java
- Using synchronization to prevent race conditions
- Designing real-time simulations
- Logging and calculating metrics like average waiting time
- Applying OOP and concurrency principles in practice

---

## 🏆 Grade

✔️ **10/10** – Full functionality and technical requirements met.

---

## 👨‍💻 Author

**Maxim Francesco**  
**University**: Technical University of Cluj-Napoca  
**Course**: Fundamental Programming Techniques  
**Email**: maaximfrancesco@gmail.com  
**LinkedIn**: [linkedin.com/in/francescomaxim](https://www.linkedin.com/in/francescomaxim/)
