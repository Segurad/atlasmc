package de.atlasmc.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.atlasmc.chat.Chat;
import de.atlasmc.chat.ChatColor;
import de.atlasmc.chat.component.ChatComponent;
import static de.atlasmc.chat.component.ChatComponent.*;
import de.atlasmc.command.argparser.VarArgParser;
import de.atlasmc.command.suggestion.SuggestionType;
import de.atlasmc.plugin.Plugin;
import de.atlasmc.registry.ClassRegistry;
import de.atlasmc.registry.Registries;
import de.atlasmc.registry.Registry;
import de.atlasmc.util.configuration.Configuration;
import de.atlasmc.util.configuration.ConfigurationSection;
import de.atlasmc.util.configuration.InvalidConfigurationException;
import de.atlasmc.util.configuration.file.YamlConfiguration;
import de.atlasmc.util.map.ConcurrentLinkedListMultimap;
import de.atlasmc.util.map.Multimap;

public class Commands {
	
	private static final Map<String, Command> commands = new ConcurrentHashMap<>();
	private static final Map<String, Command> commandByAlias = new ConcurrentHashMap<>();
	private static final Multimap<Plugin, Command> pluginCommands = new ConcurrentLinkedListMultimap<>();
	
	public static void register(Command cmd, Plugin plugin) {
		if (cmd == null)
			throw new IllegalArgumentException("Command can not be null!");
		if (plugin == null)
			throw new IllegalArgumentException("Plugin can not be null!");
		if (commands.containsKey(cmd.getName()))
			throw new IllegalArgumentException("Command with the given name already registered: " + cmd.getName());
		commands.putIfAbsent(cmd.getName(), cmd);
		pluginCommands.put(plugin, cmd);
		Collection<String> aliases = cmd.getAliases();
		if (!aliases.isEmpty()) {
			for (String alias : aliases)
				commandByAlias.putIfAbsent(alias, cmd);
		}
 	}
	
	public static void register(Collection<Command> commands, Plugin plugin) {
		if (commands == null)
			throw new IllegalArgumentException("Commands can not be null!");
		if (commands.isEmpty())
			return;
		for (Command cmd : commands) {
			register(cmd, plugin);
		}
	}
	
	/**
	 * Loads a command from the given {@link ConfigurationSection}
	 * @param config 
	 * @return command
	 * @throws InvalidConfigurationException
	 */
	public static Command loadCommand(ConfigurationSection config) {
		if (config == null)
			throw new IllegalArgumentException("config can not be null!");
		String cmdName = config.getString("name");
		if (cmdName == null)
			throw new InvalidConfigurationException("\"name\" is not defined!", config);
		Command command = new Command(cmdName);
		String cmdDescription = config.getString("cmd-description");
		command.setCommandDescription(cmdDescription);
		List<String> aliaes = config.getStringList("aliases");
		Map<String, CommandArg> templates = Map.of();
		List<ConfigurationSection> templateCfgs = config.getListOfType("templates", ConfigurationSection.class);
		if (templateCfgs != null) {
			templates = new HashMap<>();
			for (ConfigurationSection argCfg : templateCfgs) {
				String key = argCfg.getString("template-name");
				if (key == null)
					continue;
				CommandArg template = loadArg(argCfg, templates);
				if (template == null)
					continue;
				templates.put(key, template);
			}
		}
		if (aliaes != null)
			command.getAliases().addAll(aliaes);
		loadArg(config, command, templates);
		return command;
	}
	
	/**
	 * Loads a commands from the given file.
	 * @param file
	 * @return commands or empty collection
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	public static Collection<Command> loadCommands(File file) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("File can not be null!");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		List<ConfigurationSection> rawCommands = config.getListOfType("commands", ConfigurationSection.class);
		List<Command> commands = new ArrayList<>();
		for (ConfigurationSection rawCommand : rawCommands) {
			Command command = loadCommand(rawCommand);
			if (command != null)
				commands.add(command);
		}
		return commands;
	}
	
	private static CommandArg loadArg(ConfigurationSection config, Map<String, CommandArg> templates) {
		String tempKey = config.getString("template");
		if (tempKey != null)
			return templates.get(tempKey);
		String type = config.getString("type");
		if (type == null)
			return null;
		CommandArg arg = null;
		switch (type) {
		case "literal":
			arg = loadLiteralArg(config, templates);
			break;
		case "var":
			arg = loadVarArg(config, templates);
			break;
		}
		return arg;
	}
	
	private static void loadArg(ConfigurationSection config, CommandArg arg, Map<String, CommandArg> templates) {
		arg.setPermission(config.getString("permission"));
		String executorKey = config.getString("executor");
		if (executorKey != null) {
			Registry<CommandExecutor> registry = Registries.getInstanceRegistry(CommandExecutor.class);
			CommandExecutor executor = registry.get(executorKey);
			arg.setExecutor(executor);
			if (executor == null)
				throw new InvalidConfigurationException("No executor with name: " + executorKey);
		}
		List<ConfigurationSection> literalArgs = config.getListOfType("args", ConfigurationSection.class);
		if (literalArgs != null) {
			for (ConfigurationSection argCfg : literalArgs) {
				CommandArg childArg = loadArg(argCfg, templates);
				if (childArg != null) {
					arg.setArg(childArg);
				}
			}
		}
		String description = config.getString("description");
		arg.setDescription(description);
		String allowedSource = config.getString("allowed-source");
		if (allowedSource != null) {
			switch (allowedSource) {
			case "ANY":
				break;
			case "PLAYER":
				arg.setSenderValidator(CommandArg.PLAYERS_ONLY);
				break;
			case "CONSOLE":
				arg.setSenderValidator(CommandArg.CONSOLE_ONLY);
				break;
			}
		}
	}
	
	private static LiteralCommandArg loadLiteralArg(ConfigurationSection config, Map<String, CommandArg> templates) {
		String argName = config.getString("name");
		if (argName == null)
			throw new InvalidConfigurationException("\"name\" is not defined!", config);
		LiteralCommandArg arg = new LiteralCommandArg(argName);
		List<String> aliaes = config.getStringList("aliases");
		if (aliaes != null)
			arg.getAliases().addAll(aliaes);
		arg.setArgKey(config.getString("arg-key"));
		loadArg(config, arg, templates);
		return arg;
	}
	
	@SuppressWarnings("rawtypes")
	private static VarCommandArg loadVarArg(ConfigurationSection config, Map<String, CommandArg> templates) {
		String argName = config.getString("name");
		if (argName == null)
			throw new InvalidConfigurationException("\"name\" is not defined!", config);
		VarCommandArg arg = new VarCommandArg(argName);
		loadArg(config, arg, templates);
		// load parser
		Object rawParser = config.get("parser");
		if (rawParser == null)
			throw new InvalidConfigurationException("\"parser\" is not defined!", config);
		ClassRegistry<? extends VarArgParser> parserRegistry = Registries.getClassRegistry(VarArgParser.class);
		Class<? extends VarArgParser> parserClass = null;
		ConfigurationSection parserCfg = null;
		if (rawParser instanceof String parserKey) {
			parserClass = parserRegistry.get(parserKey);
			if (parserClass == null)
				throw new InvalidConfigurationException("Unable to find the defined parser: " + parserKey);
			parserCfg = Configuration.of();
		} else if (rawParser instanceof ConfigurationSection parserConfig) {
			String parserKey = parserConfig.getString("type");
			if (parserKey != null) {
				parserClass = parserRegistry.get(parserKey);
				if (parserClass == null)
					throw new InvalidConfigurationException("Unable to find the defined parser: " + parserKey);
			} else
				throw new InvalidConfigurationException("\"type\" is not defined!", config);
			parserCfg = parserConfig;
		} else {
			throw new InvalidConfigurationException("\"parser\" must be a String or Object!", config);
		}
		try {
			VarArgParser<?> parser = parserClass.getConstructor(ConfigurationSection.class).newInstance(parserCfg);
			arg.setParser(parser);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new InvalidConfigurationException("Error while instaciating VarArgParser", e, config);
		}
		// Load suggestion type
		Object rawSuggestionType = config.getString("suggestion");
		if (rawSuggestionType != null) {
			String suggestionKey = null;
			ConfigurationSection suggestionCfg = null;
			if (rawParser instanceof String key) {
				suggestionKey = key;
				suggestionCfg = Configuration.of();
			} else if (rawParser instanceof ConfigurationSection suggestionConfig) {
				suggestionKey = suggestionConfig.getString("type");
				suggestionCfg = suggestionConfig;
			}
			if (suggestionKey != null) {
				switch (suggestionKey) {
				case "minecraft:all_recipes":
					arg.setSuggestion(SuggestionType.ALL_RECIPES);
					break;
				case "minecraft:available_sounds":
					arg.setSuggestion(SuggestionType.AVAILABLE_SOUNDS);
					break;
				case "minecraft:summonable_entities":
					arg.setSuggestion(SuggestionType.SUMMONABLE_ENTITIES);
					break;
				default:
					ClassRegistry<? extends SuggestionType> registry = Registries.getClassRegistry(SuggestionType.class);
					Class<? extends SuggestionType> suggestionClass = registry.get(suggestionKey);
					if (suggestionClass != null) {
						try {
							SuggestionType suggestion = suggestionClass.getConstructor(ConfigurationSection.class).newInstance(suggestionCfg);
							arg.setSuggestion(suggestion);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							throw new InvalidConfigurationException("Error while instaciating SuggestionType", e, config);
						}
					} else {
						throw new InvalidConfigurationException("Unable to find the defined suggestion type: " + suggestionKey, config);
					}
					break;
				}
			}
		}
		return arg;
	}
	
	public static Command getCommand(String name) {
		return commands.get(name);
	}
	
	public static Command getByAlias(String alias) {
		return commandByAlias.get(alias);
	}
	
	public static Collection<Command> getCommands() {
		return commands.values();
	}
	
	public static boolean unregister(Command cmd) {
		if (cmd == null)
			return false;
		if (!commands.remove(cmd.getName(), cmd))
			return false;
		Collection<String> aliases = cmd.getAliases();
		if (!aliases.isEmpty()) {
			for (String alias : aliases)
				commandByAlias.remove(alias, cmd);
		}
		return true;
	}
	
	public static CommandContext parseCommand(CommandSender sender, String command) {
		if (sender == null)
			throw new IllegalArgumentException("Sender can not be null!");
		if (command == null)
			throw new IllegalArgumentException("Command can not be unll!");
		CommandStringReader reader = new CommandStringReader(command);
		String rawCommand = reader.readUnquotedString();
		Command cmd = commands.get(rawCommand);
		if (cmd == null)
			cmd = commandByAlias.get(rawCommand);
		if (cmd == null)
			throw new CommandNotFoundException(rawCommand);
		CommandContextBuilder builder = new CommandContextBuilder();
		builder.setSender(sender);
		builder.setCommand(cmd);
		builder.setRawCommand(rawCommand);
		CommandArg currentArg = cmd;
		while (currentArg != null) {
			reader.skipWhitespaces();
			// lookup literals
			if (currentArg.hasLiteralArgs()) {
				final int cursor = reader.getCursor();
				String rawArg = reader.readUnquotedString();
				LiteralCommandArg next = currentArg.getLiteralArg(rawArg);
				if (next != null) {
					String key = next.getArgKey();
					if (key != null)
						builder.addArgument(key, next.getName());
					builder.addParsedArg(next);
					currentArg = next;
					continue;
				}
				reader.setCursor(cursor);
			}
			// lookup var args
			if (currentArg.hasVarArgs()) {
				Collection<VarCommandArg> varArgs = currentArg.getVarArgs();
				for (VarCommandArg varArg : varArgs) {
					if (!varArg.canUse(sender))
						continue;
					final int cursor = reader.getCursor();
					try {
						VarArgParser<?> parser = varArg.getParser();
						if (parser == null)
							continue;
						Object arg = parser.parse(reader);
						if (arg == null)
							continue;
						builder
						.addArgument(varArg.getName(), arg)
						.addParsedArg(varArg);
						currentArg = varArg;
						break;
					} catch (CommandSyntaxException e) {
						// TODO handle exception
						reader.setCursor(cursor);
						continue;
					}
				}
			}
			// TODO implement redirects
			break; // break no node found
		}
		builder.setLastArg(currentArg);
		return builder.build();
	}
	
	public static List<Chat> buildHelp(Command command) {
		if (command == null)
			throw new IllegalArgumentException("Command can not be null!");
		List<Chat> help = new ArrayList<>();
		buildHelp(help, text("/").color(ChatColor.RED), command);
		return help;
	}
	
	private static void buildHelp(List<Chat> help, ChatComponent parent, CommandArg arg) {
		parent = parent.clone();
		if (arg instanceof VarCommandArg) {
			parent.extra(
					text("<").color(ChatColor.RED),
					text(arg.getName()).color(ChatColor.DARK_GRAY),
					text(">").color(ChatColor.RED));
		} else {
			parent.extra(text(arg.getName()).color(ChatColor.DARK_GRAY));
		}
		if (arg.hasExecutor())
			help.add(parent);
		if (!arg.hasArguments())
			return;
		for (CommandArg child : arg.getArguments()) {
			buildHelp(help, parent, child);
		}
	}

}