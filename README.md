# Dashcraft

Minecraft Server-Plugin for storing v1.13+ (Sponge) structures as Non-Fungible-Tokens (NFT) on [Dash Platform](https://dashplatform.readme.io/docs/introduction-what-is-dash-platform) Blockchain. A demonstration for the creation and storage of NFT's in Data-Contracts. Anyone can use the default Minecraft game installation and connect to the [Dashcraft Creative Server](https://github.com/readme55/dashcraft-server) **185.141.27.111** running this plugin. After the structure is build, chat commands can be used to submit the NFT to the blockchain using [Dash Chrome Wallet](https://github.com/readme55/Dash-Chrome-Wallet) and a Dash username account.
This plugin will allow selecting and exporting the in-game build structures to Dash Platform and visualize them online on the [MineCraft-Explorer](http://readme.dashdevs.org/minecraft-explorer/)

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dashcraft-github1.PNG" width="1200" />
<!-- <img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dashcraft-github2.PNG" width="600" /> -->
<!-- <img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dashcraft-github3.PNG" width="600" /> -->
</p>

## Getting Started

- Install [Minecraft Java Edition](https://www.minecraft.net/en-us/store/minecraft-java-edition) (**NOT Windows 10 Bedrock Edition**)
- Install [Dash Chrome Wallet](https://github.com/readme55/Dash-Chrome-Wallet). Register identity and username. Then activate `Push Notifications` switch.
- Connect to the Minecraft Creative Server: Start Minecraft -> Multiplayer -> Connect to Server -> Insert `185.141.27.111`
- Type `t` to open chat (here you can chat and insert commands starting with `/` - all commands posted here must be typed in chat!)
- Claim a plot for building `/plot auto`
- Build your structure / pixel art: Type `e` to open your inventory and select building blocks. Drag them into the bottom inventory slots. Press `e` again to close inventory.
- Double tap `Space` to fly / not-fly
- Select the structure you want to upload as NFT within the game: (Also explained with images below)
  - Type `t` to open chat
  - Enter `//wand` to receive selection tool (wooden pickaxe)
  - Mark your structure inside a rectangle by selecting two outer coordinates, the first one with `left mouse button` and the second one with `right mouse button`
  - Enter `//copy` 
- Login to your Dash Platform username account and upload the structure as NFT
  - Setup a username in Dash Chrome Wallet and activate `Push Notifications`
  - In the Minecraft game type `t` to open chat
  - Enter `/dash login <dash-username>` eg. `/dash login readme` -> wait for the Notification from Chrome Wallet and confirm the request
  - Enter `/dash save <name> <title>` eg. `/dash save myNFT My NFT Example` -> wait for the Notification from Chrome Wallet and confirm the request
- View your NFT on the [MineCraft-Explorer](http://readme.dashdevs.org/minecraft-explorer/)

**NOTE:** the explorer currently cannot visualize non-full-blocks like doors, signs etc. For Minecraft v1.15 it can display 1800 from 2100 textures. But it is only a visualization issue, the schematic has all your blocks included and will display correctly when the explorer is updated in the future.

A detailed step-by-step instruction for connecting Dash Chrome Wallet with your Minecraft account on the Dashcraft server is [documented below](#Step-By-Step-instruction-for-Non-Fungible-Token-creation).


## Advanced Commands

- use command `//pos1` and `//pos2` to select the NFT using the players current position (instead of wand tool)
- Click `Middle Mouse Button` to equip the block type you are looking at
- `/spawn` Return to original spawn
- `/tp <player>` Teleport to a player
- `/plot claim` claim the plot you are standing on
- `/plot auto` claim the nearest free plot
- `/plot auto 2 2` claim a huge 2x2 plot
- `/plot add <player>` allow other players to build on your plot (only while you are online)
- `/plot trust <player>` allow other players to build on your plot (anytime)
- `/plot deny <player>` forbid other players to enter your plot


## Step-By-Step instruction for Non-Fungible-Token creation

This section will describe the process of creating a [DASH](https://www.dash.org/) Non-Fungible-Token from any Minecraft structure in more detail and with pictures. For a compact guide read [Getting Started](#getting-started)

First decide what structure you want to create a NFT from. In this example we will select and Tokenize the "Pixel Art Chicken".

Type `t` on the keyboard to open chat. Then enter `//wand` to receive the wooden pickaxe, which is used as a selection tool:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/13wandtool.png" width="900" />
</p>

Next we want to span a selection around the chicken. For this we place a block above the ground so the chicken is selected without the grass block it is standing on. Using `left mouse click` we can select the block and get a confirmation that `First position` is set:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/14selection-pos1.png" width="900" />
</p>

The second position is up in the air, you can either build blocks up to that position and destroy them after selection or use the players location to set the position using `//pos2` command:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/15selection-pos2.png" width="900" />
</p>

Execute `copy` command to save the selection into clipboard:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/16copy.png" width="900" />
</p>

Activate `Push Notifications` in [Dash Chrome Wallet](https://github.com/readme55/Dash-Chrome-Wallet)

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/18CW.png" width="900" />
</p>

Excute `/dash login <dash-username>` e.g. `/dash login readme`:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dash-login-readmee.png" width="700" />
</p>

Confirm Chrome Wallet Dapp Notification request:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/notification-login-cw.png" width="700" />
</p>

A confirmation inside the game will appear:

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dash-login-success.png" width="700" />
</p>

Execute `/dash save <schematic-name> <title>` e.g. `/dash save myChicken My Pixel Art Chicken` (do not use spaces for the schematic-name!)

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dash-save-chicken.png" width="700" />
</p>

Again you have to confirm a Dapp Request Notification inside Chrome Wallet. After confirmation the schematic will be saved with your identity ownership on the blockchain. You can view it on the [MineCraft-Explorer](http://readme.dashdevs.org/minecraft-explorer/):

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dash-explorer-chicken.png" width="700" />
</p>

<p align="center">
<img src="https://raw.githubusercontent.com/readme55/Dashcraft/main/img/dash-explorer-readme.png" width="700" />
</p>


## Requirements for Developers and Server Owners

### Server
- [Spigot](https://getbukkit.org/) / SpigotPaper Server 
- [Worldedit](https://enginehub.org/worldedit/) or [Fast Async Worldedit](https://wiki.intellectualsites.com/en/FastAsyncWorldEdit)

### Installation
- Download [Plugin](https://github.com/readme55/Dashcraft/raw/main/target/DashCraft.jar) and place into Spigot Server `/plugins` directory
- Add Permission creative.dash
- Restart server

## Development
- Run Spigot BuildTools and add `/BuildTools/Spigot/Spigot-API/target/spigot-api-1.xx-SNAPSHOT-shaded.jar` to Dashcraft Build Path
- Add Worldedit.jar to Build Path
- Add JSON-Simple library to Build Path
- Compile plugin using `build.xml` -> `Run As` -> `Ant Build`
