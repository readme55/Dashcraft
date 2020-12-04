package me.readme.DashCraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Base64;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class CreativeCommand implements CommandExecutor {

	FileConfiguration config;
	String nfaTitle;
	String nfaDesc;

	public CreativeCommand(FileConfiguration config) {
		this.config = config;
	}

	public static String encode_sha256base64(final String clearText) throws NoSuchAlgorithmException {
		return new String(Base64.getEncoder()
				.encode(MessageDigest.getInstance("SHA-256").digest(clearText.getBytes(StandardCharsets.UTF_8))));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (label.equalsIgnoreCase("dash")) {
			if (!(sender instanceof Player)) {
				// console
				sender.sendMessage("The console cannot run this command");
				return true;
			}

			int numArgs = args.length;

			Player player = (Player) sender;
			String playerName = player.getName();
			String uuid = player.getUniqueId().toString();
			String playerLogin = playerName + ".login";
			String playerDashUser = playerName + ".dashUser";

			String absPath = new File("").getAbsolutePath();
			// System.out.println(absPath);
			String relConfigPath = "/plugins/DashCraft/config.yml";
			Boolean useFAWE = config.getBoolean("useFAWE");

			// LOGIN command
			String configPath = absPath + relConfigPath;
			File configFile = new File(configPath);

			if (numArgs == 0) {
				// dash
//				player.sendMessage(ChatColor.RED + "Usage: /dash {login/logout/tip/save");
//				player.sendMessage(ChatColor.YELLOW + "Usage: /dash < login | logout | tip | save >");
				player.sendMessage(ChatColor.YELLOW + "Usage: /dash login <username> | logout | save <schematic-name> <title>");
				return true;
			}

			if (args[0].equalsIgnoreCase("login")) {

				if (config.getBoolean(playerLogin)) {
					player.sendMessage(ChatColor.AQUA + "You are already logged into Dash Platform as "
							+ config.getString(playerDashUser)); //
					return true;
				}

				if (numArgs == 2) {

					// TODO: do some CW/dashevo login request - response
					if (true) {
						// if entry for player already exists
						if (config.isSet(playerName)) {
							config.set(playerLogin, true);
							config.set(playerDashUser, args[1]);
						} else {
							config.addDefault(playerDashUser, args[1]);
							config.addDefault(playerLogin, true);
						}

						try {
							config.save(configFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						player.sendMessage(ChatColor.GREEN + "Successfully logged into Dash Platform as " + args[1]);
						return true;
					}
				} else {
					player.sendMessage(ChatColor.AQUA + "You are not logged in!");
					player.sendMessage(ChatColor.RED + "Usage: /dash login < username >");
				}
				return true;
			}

			// LOGOUT command
			else if (args[0].equalsIgnoreCase("logout")) {
				String logoutUser = config.getString(playerDashUser);
				config.set(playerLogin, false);
				config.set(playerDashUser, "");
				try {
					config.save(configFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				player.sendMessage(ChatColor.GREEN + "Logged out as user " + logoutUser);
				return true;
			}

			// TIP command
			else if (args[0].equalsIgnoreCase("tip")) {
				if (numArgs == 3) {
//					String tipUser = args[1];
//					String amount = args[2];
					// send CW payment request

					player.sendMessage(ChatColor.AQUA + "Tipping not implemented yet");
					// player.sendMessage("You tipped user " + tipUser + " " + amount + " eDash");
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /dash tip < username > < amount eDash >");
				}

				return true;
			}

			// SAVE command
			else if (args[0].equalsIgnoreCase("save") && config.getBoolean(playerLogin)) {

				if (numArgs >= 3) {

					String relUserSchemPath = "";
					if (useFAWE == true)
						relUserSchemPath = "/plugins/FastAsyncWorldEdit/schematics/" + uuid + "/" + args[1] + ".schem";
					else
						relUserSchemPath = "/plugins/WorldEdit/schematics/" + args[1] + ".schem";

					String userSchemPath = absPath + relUserSchemPath;
					Path pathFilename = Path.of(userSchemPath);
					this.nfaTitle = "";

					// title is defined as everything behind and including second argument
					for (int i = 2; i < numArgs; i++) {
						this.nfaTitle = this.nfaTitle + args[i] + " ";
					}
					this.nfaTitle.trim();

					this.nfaDesc = "<no description>"; // name and title, no description
					
					
					// DEBUGGING
					// if (numArgs >= 4) {
					// this.nfaDesc = args[3];
					// }
					// Path pathFilename2 = Paths.get(userSchemPath);
					// System.out.println(userSchemPath);
					// System.out.println(pathFilename.toString());
					// System.out.println(pathFilename2.toString());

					// player.sendMessage("Command1: " + command + "\nLabel: " + label + "\nArgs: "
					// + args[1]);

					// player.sendMessage("Copy data using /copy");
					// System.out.println("schem clear start execute");
					// Bukkit.getServer().dispatchCommand(player, "/schem clear");
					// Bukkit.getServer().dispatchCommand(player, "/copy");
					
					// player.sendMessage("Save data to server using /schematic save");
					Bukkit.getServer().dispatchCommand(player, "/schematic save " + args[1]);

					// player.sendMessage("Saving data to Dash Platform...");
					Thread thread = new Thread() {
						@SuppressWarnings("unchecked")	// TODO: Check alternative when removed
						public void run() {
							try {
								Thread.sleep(1000); // wait until parent command + dispatched one finish
								System.out.println("Starting Thread");

								File myObj = new File(userSchemPath);
								// TODO: test and remove, shouldnt be needed even with several players
								if (!myObj.exists()) {
									Thread.sleep(2000);
									System.out.println("Sleep another 2sec");
									System.out.println("Object written: " + myObj.exists());
								}
								
								// String actual = Files.readString(pathFilename, Charset.forName("UTF-8"));
								// working with FAWE saved file, try which works on different platform!
								String actual = Files.readString(pathFilename, Charset.forName("ISO-8859-1")); 

								// String actual = Files.readString(pathFilename, Charset.forName("US-ASCII"));
								// String actual = Files.readString(pathFilename);
								// System.out.println(actual);	

								// String actual2 = new String(actual.getBytes(), StandardCharsets.US_ASCII);
								// System.out.println(actual2);

								// String actual3 = new String(actual.getBytes(), StandardCharsets.UTF_8);
								// System.out.println(actual3);

								// need to specify charset from above
								String encodedString = Base64.getEncoder()
										.encodeToString(actual.getBytes(StandardCharsets.ISO_8859_1));
								// all good, compared with NPP "base 64 with padding" and equal
								// System.out.println("Result Encoded String: " + encodedString); 

								////////////////////////////////////////////////////////////////////////////
								// send schem as document - save as JSON and call node as temporary solution

								JSONObject nfaObject = new JSONObject();
								nfaObject.put("date", java.time.Clock.systemUTC().instant().toString());
								// nfaObject.put("date", LocalDateTime.now(ZoneId.of("GMT+00:00")));
								nfaObject.put("timestamp", Long.toString(Clock.systemUTC().millis()));
								nfaObject.put("game_username", playerName);
								nfaObject.put("username", config.getString(playerDashUser));
								nfaObject.put("title", nfaTitle);
								nfaObject.put("description", nfaDesc);
								nfaObject.put("schematic", encodedString);
								nfaObject.put("schematic_hash", encode_sha256base64(encodedString));
								nfaObject.put("version", "Sponge");
								System.out.println("Json string: " + nfaObject.toJSONString());
//								System.out.println("toString: " + nfaObject.toString());
//								System.out.println("normal: " + nfaObject.get("schematic"));

								JSONObject msgObject = new JSONObject();
								msgObject.put("header", "Request Document ST");
								msgObject.put("dappname", "MyNFA Dapp");
								msgObject.put("reference", config.get(playerDashUser));
								msgObject.put("status", "0");
								msgObject.put("timestamp", Long.toString(Clock.systemUTC().millis()));
								// mynfa contract id
								msgObject.put("STcontract", "Faggf8rA3GuoEoSo8ACaqtY72iok6DY5D77dr2B7vFw1"); 
								msgObject.put("STdocument", "mynfa");
								// msgObject.put("STcontent", nfaObject.toJSONString()); // TODO: fix, build one after an other
								String strNfaObject = "{ \"date\" : \"" + nfaObject.get("date") + "\",\"timestamp\":\""
										+ nfaObject.get("timestamp") + "\",\"game_username\":\""
										+ nfaObject.get("game_username") + "\",\"username\":\""
										+ nfaObject.get("username") + "\",\"title\":\"" + nfaObject.get("title")
										+ "\",\"description\":\"" + nfaObject.get("description") + "\",\"schematic\":\""
										+ nfaObject.get("schematic") + "\",\"schematic_hash\":\""
										+ nfaObject.get("schematic_hash") + "\",\"version\":\""
										+ nfaObject.get("version") + "\"}";
								// System.out.println("strNfaObject: " + strNfaObject);
								msgObject.put("STcontent", strNfaObject);

								// System.out.println(msgObject.toJSONString());

								//////////// USE JS-NODE until DAPI JAVA-Bindings /////////////

								// create/overwrite and write to filename.txt
								String relUserTmpPath = "/plugins/DashCraft/" + playerName + ".txt";
								String filepath = absPath + relUserTmpPath;
								File myObj1 = new File(filepath);
								if (myObj1.createNewFile()) {
									System.out.println("File created: " + myObj1.getName());
								} else {
									System.out.println("File already exists, overwriting it.");
								}
								FileWriter myWriter = new FileWriter(filepath);
								myWriter.write(msgObject.toJSONString());
								myWriter.close();
								System.out.println("Successfully wrote to the file " + filepath);
								
								player.sendMessage(ChatColor.GREEN + "Created Non-Fungible-Token from your build Structure. Sending to Wallet now, please wait.");
								////////////////////////////////////////////////////////////////////////////
								
								// execute system command
								String s = null;
								String osname = System.getProperty("os.name");
								System.out.println("osname: " + osname);
								Process p = null;
								if (osname.startsWith("Windows"))
									p = Runtime.getRuntime().exec("cmd /c node sendNFT-DSmsg.js " + filepath); // Windows
								else
									p = Runtime.getRuntime().exec("node sendNFT-DSmsg.js " + filepath); // Linux

								BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

								BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

								// read the output from the command
								System.out.println("Here is the standard output of the command:\n");
								while ((s = stdInput.readLine()) != null) {
									System.out.println(s);
								}

								// read any errors from the attempted command
								System.out.println("Here is the standard error of the command (if any):\n");

								boolean error = false;
								while ((s = stdError.readLine()) != null) {
									System.out.println(s);
									error = true;
								}

								//////////////////////////////////////////////////////////////////////////
								if (!error) {
									player.sendMessage(ChatColor.GREEN + "Success sending Request to Wallet. Confirm inside your Dash-Wallet!");
								} else {
									player.sendMessage(ChatColor.AQUA + "Error occured sending Request to Wallet.");
								}

							} catch (NoSuchAlgorithmException | InterruptedException | IOException e) {
								System.out.println("An error occurred.");
								e.printStackTrace();
							}
						}
					};

					thread.start();

					// player.sendMessage(ChatColor.GREEN + "dash save " + args[1] + " command
					// executed successful");

				} else {
					player.sendMessage(ChatColor.RED + "Usage: /dash save < schematic-name > < title >");
				}
				return true;
			}

			// File file = new File("plugins\\SpigotBlankPlugin\\schem\\filename.txt"); /*
			// figure out where to save the clipboard */;
			// if (file.createNewFile()) {
			// System.out.println("File created: " + file.getName());
			// } else {
			// System.out.println("File already exists.");
			// }
			//

			///////// testing ////////////////

			// World world = BukkitAdapter.adapt(player.getWorld());
			// try (EditSession editSession =
			// WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
			// // use the edit session here ...
			// } // it is automatically closed/flushed when the code exits the block
			//

			//////////////////////////////////

			// World world = (World) player.getWorld(); // exception here

			// World world = BukkitAdapter.adapt(player.getWorld());
			// BlockVector3 min;
			// BlockVector3 max;
			// try (EditSession editSession =
			// WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
			// min = editSession.getMinimumPoint();
			// max = editSession.getMaximumPoint();
			//
			//
			// // use the edit session here ...
			// } // it is automatically closed/flushed when the code exits the block

			// CuboidRegion region = new CuboidRegion(world, min , max);
			// BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
			//
			// try (EditSession editSession =
			// WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
			// ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(
			// editSession, region, clipboard, region.getMinimumPoint()
			// );
			// // configure here
			// Operations.complete(forwardExtentCopy);
			// player.sendMessage("Operations.complete(forwardExtentCopy)");
			// }
			//
			// try (ClipboardWriter writer =
			// BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new
			// FileOutputStream(file))) {
			// writer.write(clipboard);
			// player.sendMessage("filename.txt");
			// }

			// } catch (IOException e) {
			// System.out.println("An error occurred.");
			// e.printStackTrace();
			// }

			////////////////////////////////////////////////////////////////////////////
			// From Discord help
			// https://matthewmiller.dev/blog/updating-worldedit-to-minecraft-113/#selections
			// get clipboard from /wand + /copy command
			// BukkitPlayer bk = BukkitAdapter.adapt(player);
			// World world = BukkitAdapter.adapt(player.getWorld());
			// LocalSession ls = WorldEdit.getInstance().getSessionManager().get(bk);
			// Region reg = ls.getSelection(world);
			////////////////////////////////////////////////////////////////////////////

		}
		return false;
	}
}
