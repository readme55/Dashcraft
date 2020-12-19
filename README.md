# Dashcraft (WIP)

Minecraft Server-Plugin for storing v1.13+ (Sponge) structures as Non-Fungible-Tokens on Dash Platform. A demonstration for the creation and storage of NFT's in Data-Contracts. Anyone can use the default Minecraft game installation and connect to the [Dashcraft Creative Server](https://github.com/readme55/dashcraft-server) **185.141.27.111** running this plugin. After the structure is build, chat commands can be used to submit the NFT to the blockchain using [Dash Chrome Wallet](https://github.com/readme55/Dash-Chrome-Wallet) and a Dash username account.
This plugin will allow selecting and exporting the in-game build structures to Dash Platform and visualize them online on the [MineCraft-Explorer](http://readme.dashdevs.org/minecraft-explorer/)

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/master/dashcraft-github1.PNG" width="700" />
<!-- <img src="https://raw.githubusercontent.com/readme55/Dashcraft/master/dashcraft-github2.PNG" width="600" /> -->
<!-- <img src="https://raw.githubusercontent.com/readme55/Dashcraft/master/dashcraft-github3.PNG" width="600" /> -->
</p>

## Getting Started

- Install [Minecraft Java Edition](https://www.minecraft.net/en-us/store/minecraft-java-edition) (**NOT Windows 10 Bedrock Edition**)
- Install [Dash Chrome Wallet](https://github.com/readme55/Dash-Chrome-Wallet). Register identity and username. Then activate `Push Notifications` switch.
- Connect to the Minecraft Creative Server: Start Minecraft -> Multiplayer -> Connect to Server -> Insert `185.141.27.111`
- Build your structure / pixel art: Type `e` to open your inventory and select building blocks. Drag them into the bottom inventory slots. Press `e` again to close inventory.
- Double tap `Space` to fly / not-fly.
- Select the structure you want to upload as NFT within the game:
  - Type `t` to open chat
  - Enter `//wand` to receive selection tool (wooden pickaxe)
  - Mark your structure inside a rectangle by selecting two outer coordinates, the first one with left and the second one with right mouse button
  - Enter `//copy` 
- Login to your Dash Platform username account and upload the structure as NFT
  - Setup a username in Dash Chrome Wallet and activate `Push Notifications`
  - In the Minecraft game type `t` to open chat
  - Enter `/dash login <dash-username>` eg. `/dash login readme` -> wait for the Notification from Chrome Wallet and confirm the request
  - Enter `/dash save <name> <title>` eg. `/dash save myNFT My NFT Example` -> wait for the Notification from Chrome Wallet and confirm the request
- View your NFT on the [MineCraft-Explorer](http://readme.dashdevs.org/minecraft-explorer/)

A detailed step-by-step instruction for connecting Dash Chrome Wallet with your Minecraft account on the Dashcraft server is documented below.

## Step-By-Step instruction for Non-Fungible-Token creation






## Requirements for Developers and Server Owners

### Server
- Spigot / SpigotPaper Server (https://getbukkit.org/)
- Worldedit (https://enginehub.org/worldedit/) **OR** Fast Async Worldedit (https://wiki.intellectualsites.com/en/FastAsyncWorldEdit)

### Installation
- Download .jar plugin and place into Spigot Server Plugin directory
- Add Permission creative.dash
- Restart server

## Development
- Run Spigot BuildTools and add `/BuildTools/Spigot/Spigot-API/target/spigot-api-1.xx-SNAPSHOT-shaded.jar` to Dashcraft Build Path
- Add Worldedit.jar to Build Path
- Add JSON-Simple library to Build Path
- Compile plugin using `build.xml` -> `Run As` -> `Ant Build`
