这是一款用于监控、调控本地缓存的组件，用于对缓存的功能增强：状态监控，不重启服务的情况下进行缓存实例热替换
添加在配置类中的缓存上@Watch注解，即可完成对缓存的监控。
WatchersManager是api入口，依赖注入该类就可以访问相应的功能。
