# Dashcraft (WIP)

## Minecraft Plugin for storing Build Structures as Non-Fungible-Tokens on the Dash Blockchain

This Minecraft Server-Plugin stores v1.13+ (SPONGE) structures as NFT's on Dash Platform. A demonstration for the creation of Non-Fungible-Tokens (or Non-Fungible-Assets) and storage on Dash Drive. The client will not need any special software and can connect to the Server running the plugin with the default Minecraft game installation.
This plugin will allow selecting and exporting the in-game build structures to Dash Platform and viewing them online on a MineCraft-Explorer website.

## Requirements
- Spigot / SpigotPaper Server (https://getbukkit.org/)
- Worldedit (https://enginehub.org/worldedit/)
- Fast Async Worldedit (https://wiki.intellectualsites.com/en/FastAsyncWorldEdit)

## Installation
- Download .jar plugin and place into Spigot Server Plugin directory
- Add Permission creative.dash
- Start server

## Usage
- Build your structure
- Type `t` to open chat
- Enter `//wand` to receive selection tool (wooden pickaxe)
- Mark your structure inside a rectangle by selecting two outer coordinates, the first one with left and the second one with right mouse button
- Enter `//copy` 
- Enter `/dash save <name> <title>` eg. `/dash save myNFT my nft title`
