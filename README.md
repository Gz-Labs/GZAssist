
# <div align="center">GZAssist</div>

**<div align="center">Personal screen-reading assistant powered by AI — lightweight, fast, and focused.</div>**

<p align="center">
    <img src="https://img.shields.io/badge/java-21-blue.svg" alt="Java">
    <img src="https://img.shields.io/badge/javafx-21-green.svg" alt="JavaFX">
    <img src="https://img.shields.io/badge/build-maven-yellow.svg" alt="Maven">
    <img src="https://img.shields.io/badge/OS-Windows%2011-lightgrey.svg" alt="Windows">
</p>

---

## ✨ Overview

**GZAssist** is a personal-use desktop assistant for Windows that captures the visible screen, performs OCR, and sends the content to OpenAI to summarize, translate, or explain it — all in a non-intrusive floating overlay.

Perfect for developers, readers, and learners who need quick insights from what’s on their screen.

---

## ✅ Current Features

- Global hotkey activation (Ctrl + Alt + K)
- Fullscreen or active window screenshot
- OpenAI GPT integration (supports GPT-4o)
- Clean and minimal overlay with answer
- No need to switch tabs or copy/paste

---

## 💡 How It Works

```
[Hotkey Pressed] → [Screen Captured] → [OCR Applied] → [Query AI] → [Display Result]
```

Simple, fast, and designed to stay out of your way.

---

## 🔧 Installation

1. Clone this repository
2. Package the app with Maven:
```bash
mvn clean package
```
3. Launch the app and enter your OpenAI API key on first run

---

## 🧪 Testing

- Unit tests: hotkey listener, capture flow, API call, overlay render
- Manual tests: end-to-end interaction with AI

---

## 📦 Download

Installer and releases will be available soon in the [Releases](https://github.com/gzlabs/gzassist/releases) section.

---

## 🌱 Possible Future Features

- Action picker (choose between summarize, translate, explain, etc.)
- Configurable overlay theme (light/dark/compact)
- Local history of past queries
- Custom prompt templates
- Voice response (TTS)
- API key setup wizard (first launch)
- Secure local key storage (encrypted in Windows Registry)

---

**GZAssist** — The invisible assistant that understands your screen. 🚀
