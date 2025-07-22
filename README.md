# QuizzzApp 🧠📱

A native Android quiz application built using **Kotlin** and **Firebase Realtime Database**. This app allows users to take topic-based quizzes with a timer, scoring system, and result summary.

## 🚀 Features

- 📚 Topic-wise quizzes (e.g., Programming, GK, Politics)
- ⏱️ Countdown timer per quiz
- ✅ Multiple-choice questions
- 📊 Score summary with pass/fail dialog
- 🔄 Dynamic quiz data from Firebase Realtime Database
- 🔐 Secure data access with Firebase Anonymous Auth
- 🖼️ Clean UI with RecyclerView and ViewBinding

## 🛠️ Tech Stack

- **Kotlin**
- **Firebase Realtime Database**
- **Firebase Authentication (Anonymous)**
- **ViewBinding**
- **RecyclerView**
- **AlertDialog**

## 📷 Screenshots

_Add screenshots here of your main screen and quiz screen for better visuals._

## 🔧 Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/quizzzapp.git
   ```

2. **Open in Android Studio**

3. **Add your `google-services.json`** to the `/app` directory

4. **Configure Firebase Rules:**

   ```json
   {
     "rules": {
       ".read": "auth != null",
       ".write": "auth != null"
     }
   }
   ```

5. **Build and Run the app** on your emulator or Android device

## 🔒 Notes

- For local testing, you can temporarily use public Firebase rules (`.read: true`)
- For production, secure your rules and use proper Firebase Auth methods

## ✨ Author

Made with ❤️ by [Harshit Shekhar](https://github.com/your-username)

---

_This project is for learning purposes. You can freely fork, use, or extend it!_
