# Echoing Utility ⚡

A Minecraft Fabric mod that adds the **Modified Amethyst Shard** - a powerful utility item with temporal manipulation and death-prevention abilities.

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.8-brightgreen)
![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## ✨ Features

### Modified Amethyst Shard
A rare, epic-tier item with two powerful abilities:

#### 🕐 **Temporal Echo** (Active Ability)
- **Activation**: Right-click while holding the item
- **Effect**: Creates a temporal anchor at your current position
- **Duration**: Returns you to the anchor point after 10 seconds
- **Visual**: Purple portal particles at echo location
- **Cooldown**: One echo at a time per player
- **Usage**: Strategic retreat, exploration safety, parkour recovery

#### 🛡️ **Void Anchor** (Passive Ability)
- **Trigger**: Automatic when you would die
- **Effect**: Prevents death and restores 50% of max health
- **Bonus**: Resistance V for 5 seconds
- **Visual**: Burst of portal particles
- **Consumption**: Item is consumed on use
- **Usage**: Last-chance survival, dangerous exploration

### Visual Effects
- **Idle Animation**: Soul fire flames and portal particles when held
- **Epic Rarity**: Enchantment glint effect
- **Particle Trails**: Warped spores for mystical appearance

## 📦 Installation

### Requirements
- Minecraft 1.21.8
- Fabric Loader 0.17.0+
- Fabric API 0.136.0+
- Java 21+

### Steps
1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest release of Echoing Utility
4. Place both JAR files in your `.minecraft/mods` folder
5. Launch Minecraft with Fabric profile

## 🎮 How to Obtain

### Villager Trading
Trade with a **Master Level Cleric** (Level 5):
- **Cost**: 48 Emeralds 💎
- **Result**: 1 Modified Amethyst Shard ✨
- **Max Uses**: 1 trade per villager restock

#### How to Get a Master Cleric:
1. Find or place a **Brewing Stand** near an unemployed villager
2. The villager will become a **Cleric** (purple robe)
3. Trade with the Cleric repeatedly to level them up:
   - Novice (1) → Apprentice (2) → Journeyman (3) → Expert (4) → **Master (5)**
4. Once they reach Master level, the Modified Amethyst Shard trade will be available

**Note**: The trade may not appear immediately at Master level. You can reset trades by breaking and replacing the Brewing Stand.

## 🛠️ Technical Details

### Architecture
- **Event-driven design**: Uses Fabric API lifecycle events
- **Thread-safe**: ConcurrentHashMap for pending echoes
- **Server-side logic**: All abilities handled server-side for multiplayer compatibility
- **Client particle system**: Custom particle effects using client tick events

### Key Components
- `CondensedCatalystItem`: Main item class with temporal echo logic
- `ModItems`: Item registration with proper registry keys
- `EchoingUtility`: Main mod initialization and death prevention handler
- `EchoingUtilityClient`: Client-side particle effects

## 🔧 For Developers

### Building from Source
```bash
git clone https://github.com/YOUR_USERNAME/EchoingUtility.git
cd EchoingUtility
./gradlew build
```

The compiled JAR will be in `build/libs/`

### Project Structure
```
EchoingUtility/
├── src/main/
│   ├── java/com/sprakiles/echoingutility/
│   │   ├── EchoingUtility.java           # Main mod class
│   │   ├── EchoingUtilityClient.java     # Client initialization
│   │   └── item/
│   │       ├── CondensedCatalystItem.java  # Item implementation
│   │       ├── ModItems.java               # Item registration
│   │       └── ModItemGroup.java           # Creative tab
│   └── resources/
│       ├── fabric.mod.json               # Mod metadata
│       ├── assets/echoingutility/        # Textures, models, lang
│       └── data/echoingutility/          # Recipes, loot tables
└── gradle/                               # Gradle wrapper
```

## 📝 Changelog

### v1.0.0 (Current)
- Initial release
- Added Modified Amethyst Shard item
- Implemented Temporal Echo ability
- Implemented Void Anchor ability
- Added custom particle effects
- Added `/getecho` command

## 🐛 Known Issues

- Smithing Table recipe not implemented yet (coming in future update)
- Trade may require villager restock to appear

## 🚀 Planned Features

- [ ] Alternative crafting methods (Smithing Table recipe)
- [ ] Sound effects for abilities
- [ ] Configurable echo duration
- [ ] Multiple echo points
- [ ] Echo cooldown system
- [ ] Additional villager trades
- [ ] Custom enchantments

## 📸 Screenshots

*Coming soon!*

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest features
- Submit pull requests

## 📄 License

This project is licensed under the [MIT License](https://github.com/sprakiles/EchoingUtility/tree/main?tab=MIT-1-ov-file).

## 👤 Author

**Sprakiles**
- GitHub: [sprakiles](https://github.com/sprakiles)

## 🙏 Acknowledgments

- Built with [Fabric](https://fabricmc.net/)
- Inspired by temporal mechanics in various games
- Thanks to the Minecraft modding community

---

⭐ **If you enjoy this mod, consider giving it a star!**
