[Doc](doc.md) > [Development](doc.md#development) > [Node Startup]()

# Node Startup

Plugin Resourcepaths: 
- [`/META-INF/atlas/startup-handler.yml`](#startup-handleryml)

---

For lounching atlas the goal is to only provide a minimal system that will load `AtlasModul` plugins.

These modules are the foundation of all of Atlas Core functionality. To freely controll how atlas works and to be able to replace single implementations Atlas has a startup process consisting of multiple stages. This system itself is also used by the default implementation of Atlas so functionality can be added or replaced without the need to recompile the whole Software. Simply drop in or replace a Modul.

The default stages are:
- `load-extra-plugins` modules can register new plugin loaders and load extra modules at this stage
- `init-stages` modules can register new stages at this stage
- `init-master` initialization of the master
- `load-master-data` loading of all configurations
- `connect-master` builing master connection
- `init-node` initialization of node
- `load-node-data` loading of all configurations
- `finalize-startup` sould be always the last stage can be used for last changes before the node will be marked as online

For interaction with the startup process your Plugin need to implement the `AtlasModul` interface. After loading the `initStartupHandler(StartupContext context)` method will be called.

New stages may be added at any time. The only requirement is they must be added after the current stage or later.

The startup pocess provides a context `Map<String, Object>` used to share data between handlers. It will be cleared each stage. A stage has 3.
- `prepare` used to provide stage relevant context e.g. provide builder.
- `handle` used to populate builders and prepare data.
- `finalize` used for initialization of populated builder.

Stage handler may be automatically registered with `startup-handler.yml`. Auto register stage handler must have a constructor without arguments.

## startup-handler.yml
```yaml
somestage: 
- my.stage.Handler
- my.other.stage.Handler
```