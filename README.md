# <div align="center">GZAssist</div>

**<div align="center">Your invisible assistant — fast, smart, and screenshot-powered.</div>**

> **Note:** GZAssist is in active development. Some features may be incomplete or change frequently.

<div style="text-align: center;">
    <img src="https://img.shields.io/badge/java-21-blue.svg" alt="Java" />
    <img src="https://img.shields.io/badge/javafx-21-green.svg" alt="JavaFX" />
    <img src="https://img.shields.io/badge/build-maven-yellow.svg" alt="Maven" />
    <img src="https://img.shields.io/badge/OS-Windows%2011-lightgrey.svg" alt="Windows" />
</div>

---

## ✨ Overview

**GZAssist** is a smart and lightweight desktop utility for Windows that uses screen captures as input for AI-powered
insights — such as solving questions, explaining code, summarizing content, translating, and more.

No tab switching, no manual copy-paste — just **hotkey, capture, and get answers** directly in a floating overlay.

Ideal for developers, students, readers, or anyone needing fast context-aware assistance.

---

## ✅ Current Features

- Global hotkey activation (Ctrl + Alt + K)
- Fullscreen or active window capture
- AI modes (exam questions, explanation, summary, etc.)
- Clean floating overlay for quick responses
- Works without disrupting your workflow

---

## 🗂️ Supported Modes

| Mode              | Description                                            | Status         |
|-------------------|--------------------------------------------------------|----------------|
| 📘 Exam Question  | Solves multiple-choice questions with justification    | ✅ Implemented  |
| 💻 Code Debugger  | Explains errors or code snippets                       | ✅ Implemented  |
| 📖 Summarizer     | Summarizes long texts or slides                        | ✅ Implemented  |
| 🌍 Translator     | Translates visible content                             | ✅ Implemented  |
| 🔍 Auto Mode      | AI decides the best action based on the captured image | ✅ Implemented  |
| 🕵️‍♂️ Extra Mode | Hidden feature for programming challenges (explore!)   | 🚧 In Progress |

> You can add more modes or customize prompts as needed. There is at least one hidden "extra mode" focused on
> programming challenges.

---

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/gzlabs/gzassist.git
   cd gzassist
   ```

2. To build the project with all dependencies and create an executable "fat" JAR:
   ```bash
   mvn clean package
   ```
   The resulting file will be at `target/GZAssist.jar`.

3. Run the application:
   ```bash
   java -jar target/GZAssist.jar
   ```
   > **Note:** Make sure you have Java 21 installed. Recommended JDK distributions with JavaFX included:
   > - Azul Zulu JDK with JavaFX 21 (https://www.azul.com/downloads)
   > - BellSoft Liberica Full JDK 21 (https://bell-sw.com)
   > - Liberica NIK 21 (Native Image Kit with JavaFX)
       > Alternatively, you can use any JDK 21 and Maven will handle the JavaFX dependencies.

---

## 📦 Release

Installers and binaries are already available under [Releases](https://github.com/gzlabs/gzassist/releases).

---

## 🧪 Testing

Automated testing functionality will be available soon.

---

## 🌱 Roadmap & Ideas

- 🧩 Action picker UI (choose AI mode)
- 🎨 Configurable overlay themes
- 📸 Local OCR support for offline mode
- 🕓 Local and searchable query history
- 🧠 Custom prompt templates per mode
- 🔐 API key wizard and local secure storage
- 🔊 Text-to-speech response (optional)

---

**GZAssist** — The invisible assistant that understands your screen. 🚀