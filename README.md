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

**GZAssist** is a smart and lightweight desktop utility for Windows that uses screen captures as input for AI-powered insights — such as solving exam questions, explaining code, summarizing content, translating, and more.

No switching tabs, no manual copy-paste — just **hotkey, capture, and get answers** directly in a floating overlay.

Ideal for developers, students, readers, or anyone needing fast context-aware assistance.

---

## ✅ Current Features

- Global hotkey activation (Ctrl + Alt + K)
- Fullscreen or active window capture
- AI modes (exam questions, explanation, summary, etc.)
- Clean floating overlay for quick responses
- Works without disrupting your current workflow

---

## 🧠 How It Works

```
[Hotkey Pressed] → [Screen Captured] → [AI Mode Selected] → [Display Result]
```

Simple. Fast. Contextual.

---

## 🗂️ Supported Modes

| Mode             | Description                                         |
|------------------|-----------------------------------------------------|
| 📘 Exam Question | Solves multiple-choice questions with justification |
| 💻 Code Debugger | Explains errors or code snippets                    |
| 📖 Summarizer    | Summarizes long texts or slides                     |
| 🌍 Translator    | Translates visible content                          |
| 🔍 Auto Mode     | AI decides best action based on image               |
| 🔒 LeetCode Mode | Hidden mode for solving LeetCode problems           |

> You can add more modes or customize prompts as needed.

---

## 🔧 Installation

1. Clone this repository
2. Package the app with Maven:
```bash
mvn clean package
```

---

## 🧪 Testing

- Unit tests: hotkey listener, capture flow, AI call, overlay rendering
- Manual tests: full end-to-end screenshot-to-answer experience

---

## 📦 Download

Installers and binaries will be available soon under [Releases](https://github.com/gzlabs/gzassist/releases).

---

## 🌱 Roadmap & Ideas

- 🧩 Action picker UI (choose AI mode)
- 🎨 Configurable overlay themes
- 📸 Local OCR support for offline mode
- 🕓 Query history (local, searchable)
- 🧠 Custom prompt templates per mode
- 🔐 API key wizard & local secure storage
- 🔊 Text-to-speech response (optional)

---

**GZAssist** — The invisible assistant that understands your screen. 🚀