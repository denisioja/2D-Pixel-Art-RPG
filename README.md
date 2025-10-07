# 2D Pixel Art RPG

This project is a complete 2D Role-Playing Game (RPG) developed from the ground up entirely in **Java**, without relying on any external game engines (such as Unity or Godot). It serves as a comprehensive demonstration of fundamental game development principles, custom graphics programming, and algorithmic application.

The narrative is deeply inspired by Dacian mythology and history, offering a unique cultural backdrop for an immersive exploration and combat experience.

## Custom Engine & Technical Highlights

The game is built upon a proprietary, native Java game loop and rendering pipeline, showcasing the implementation of several core concepts:

* **Custom Tile-Based Rendering:** Efficient system for rendering large, complex maps using dynamic tile sheets.
* **A\* Pathfinding:** Implemented for Non-Player Character (NPC) navigation, allowing for intelligent movement and patrol patterns across complex environments.
* **Custom Collision Detection:** Precise, lightweight collision system optimized for tile-based environments.
* **Dynamic Day-Night Cycle:** A visual and time-based system that influences game lighting and NPC behavior.
* **Multithreaded Architecture:** Utilizes the `Runnable` interface for concurrent execution of the game logic and rendering, ensuring smooth performance.

## Game Features

The project integrates all core mechanics expected in a classic RPG:

* **Exploration:** Navigate an overworld and various environments rendered in pixel art.
* **Combat System:** Turn-based or real-time combat utilizing player stats and equipment.
* **Inventory & Equipment:** Functional system for managing items, weapons, and armor.
* **Dialogue System:** Custom architecture for NPC interaction and progressing the story.
