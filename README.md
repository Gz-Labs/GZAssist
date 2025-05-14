
# <div align="center">GZAssist</div>

**<div align="center">Personal screen-reading assistant powered by AI — lightweight, fast, and focused.</div>**

> **Note:** The interface is currently under construction and in active development. Some features may be incomplete or
> subject to change.

<p align="center">
    <img src="https://img.shields.io/badge/java-21-blue.svg" alt="Java">
    <img src="https://img.shields.io/badge/javafx-21-green.svg" alt="JavaFX">
    <img src="https://img.shields.io/badge/build-maven-yellow.svg" alt="Maven">
    <img src="https://img.shields.io/badge/OS-Windows%2011-lightgrey.svg" alt="Windows">
</p>

---

## ✨ Overview

**GZAssist** is a personal-use desktop assistant for Windows that captures the visible screen and sends it directly to OpenAI's Vision API to summarize, translate, or explain its contents — all within a floating overlay that keeps your workflow uninterrupted.

Perfect for developers, readers, and learners who need quick insights from what’s on their screen.

> 🔄 Local OCR is not currently used — all visual understanding is handled by the AI itself.

---

## ✅ Current Features

- Global hotkey activation (Ctrl + Alt + K)
- Fullscreen or active window screenshot
- OpenAI GPT Vision integration (supports GPT-4o)
- Clean and minimal overlay with answer
- No need to switch tabs or copy/paste

---

## 💡 How It Works

```
[Hotkey Pressed] → [Screen Captured] → [Query AI] → [Display Result]
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
- Local OCR support for offline mode
- Local history of past queries
- Custom prompt templates
- Voice response (TTS)
- API key setup wizard (first launch)
- Secure local key storage (encrypted in Windows Registry)

---

**GZAssist** — The invisible assistant that understands your screen. 🚀
