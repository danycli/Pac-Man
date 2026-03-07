# 🎮 Pac-Man JavaFX Game

A fully functional Pac-Man-style desktop game built using **JavaFX**.  
This project is open-source and includes a ready-to-run `.exe` file for users who do not have programming knowledge.

---

## About The Game

This is a classic arcade-style Pac-Man game where the player:

- Controls Pac-Man using keyboard arrow keys
- Collects points and diamonds
- Avoids enemies
- Tracks score and stats
- Saves game data locally

The game includes:
- Score tracking
- Diamond collection system
- Persistent save file
- Custom UI styling
- Smooth movement system
- Enemy AI behavior

## Features

- In-Game Store: Collect diamonds during gameplay to purchase custom Pac-Man skins.
- Custom Skins: Unlock unique cosmetic items including Multicolor, Undercover, Badass, Teeth, Female, and Pixelated.
- Skin Collection: Access a dedicated menu to view and manage your unlocked items.
- Persistent Save System: Your progress, diamond counts, and skin collections are automatically saved locally to your Windows AppData directory, ensuring you never lose your progress.
- Custom UI & Aesthetics: Features a stylized interface utilizing a custom Minecrafter_Alt font and transparent, undecorated game windows for a modern arcade feel.

## Architecture Highlights

- UI Design: Built entirely with JavaFX, utilizing StackPane and Scene layouts to structure menus seamlessly.
- Data Management: The SaveManager class handles file I/O operations dynamically, creating a dedicated PacMan folder in the system environment to read and write stats.txt and collection.txt.
- Event Handling: Clean separation of UI elements and game logic, utilizing lambda expressions for efficient button actions and keyboard inputs.

---

# For Non-Programmers (Easy Installation)

You do NOT need to know programming to play this game.

## Option 1 – Run the Installer (Recommended)

1. Go to the **Releases** section of this repository.
2. Download the latest `.exe` file.
3. Double-click the installer.
4. Follow the installation steps.
5. Launch the game from desktop shortcut.
(If you are facing issue from window side of thing just go to window security look for the App and browser Control, then click on smart app control settings and trun the enhance protection Off)

That’s it 

---

## If Windows Shows "Unknown Publisher"

Because this is an independent open-source project:

1. Click **More Info**
2. Click **Run Anyway**

This is normal for unsigned indie applications.

---

# For Developers (Run From Source)

If you want to build or modify the project:

## Requirements

- Java JDK 17 or higher
- JavaFX SDK
- Any IDE (IntelliJ, Eclipse, VS Code)

---

## How To Run

1. Clone the repository:

```bash
git clone https://github.com/danycli/Pac-Man.git
```

Open the project in your IDE.
Configure the JavaFX library path.

Run the AppLauncher Class.

## Save File Location

The game automatically creates a save file on first launch.

Default location:
C:\Users\YourName\AppData\Roaming\PacMan\

Stored data includes:

- Current Score
- Total Diamonds
- Player settings
- Purchased Skins

## Built With

- Java
- JavaFX
- Object-Oriented Programming
- File I/O system

## Contributing

- Pull requests are welcome.
- If you would like to:
- Improve enemy AI
- Enhance UI
- Optimize performance
- Add new features
- Fork the repository and submit a Pull Request.

Author

- Danial Ahmed
  (One of the cherry is not picking up)

## Support

- If you like the project:
- Star the repository
- Share it
- Contribute improvements
