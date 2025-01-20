# Classroom Vacancy Notifier

**Classroom Vacancy Notifier** is an Android application designed to notify users about the availability of classrooms. The app uses a MySQL backend to store classroom availability data, a Flask API to serve as an intermediary, and a simple and intuitive Android frontend developed in Java using Android Studio.

## Features
- **Real-Time Notifications**: The app notifies users when classrooms become available or occupied.
- **MySQL Backend**: Stores classroom availability information.
- **Flask API Integration**: Acts as a bridge between the backend and the Android application.
- **User-Friendly Interface**: Built with Android Studio, ensuring smooth and efficient user interaction.

## Tech Stack
- **Frontend**: Android (Java)
- **Backend**: MySQL
- **API**: Flask (Python)

## Project Architecture
1. **MySQL Backend**: Contains a database table with classroom availability details.
2. **Flask API**: Handles requests from the app, checks classroom availability, and returns the status.
3. **Android Application**: Periodically queries the API to check classroom status and notifies users about any changes.

## Installation

### Prerequisites
- Android Studio installed on your computer.
- Python 3 installed along with Flask.
- MySQL installed and running.

### Steps to Set Up
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/erenyeager98/Schedule_Alerts
