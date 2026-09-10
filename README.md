# X-PRIVATEMINES API
Official API for X-PrivateMines plugin.

**Links:**
- [SpigotMC (Download)](https://www.spigotmc.org/resources/126985/)
- [Discord (Support)](https://discord.gg/ZeSkmEC6mG)
- [Wiki (Documentation)](https://github.com/Drawethree/X-PrivateMines/wiki)
- [Javadocs](https://www.drawethree.dev/plugins/x-privatemines/javadoc/index.html)
## Dependency

Artifacts are published to [repo.drawethree.dev](https://repo.drawethree.dev). Every build,
including per-commit snapshots, is listed at
[ci.drawethree.dev/x-privatemines](https://ci.drawethree.dev/x-privatemines/).

### Maven
```xml
<repositories>
    <repository>
        <id>drawethree</id>
        <url>https://repo.drawethree.dev/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.drawethree</groupId>
        <artifactId>XPrivateMinesAPI</artifactId>
        <version>1.4</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
    maven { url 'https://repo.drawethree.dev/releases' }
}

dependencies {
    compileOnly 'dev.drawethree:XPrivateMinesAPI:1.4'
}
```

Pin a real version. Maven 3 dropped `LATEST` and `RELEASE` for dependency resolution, so a build
that asks for one resolves differently depending on who runs it, or not at all. The development
head is published to `https://repo.drawethree.dev/snapshots` as `1.4-SNAPSHOT` if you want it.

Sources and javadoc jars are published alongside each release, so your IDE shows the contract and
its comments rather than decompiled bytecode.

Always `provided` / `compileOnly` — X-PrivateMines supplies these classes at runtime, and shading them
into your own jar puts a second copy on the server.

