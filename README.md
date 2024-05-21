# Discord for J2ME
Discord client for Java ME (MIDP 2.0) devices, inspired by [Discord for Symbian](https://github.com/uwmpr/discord-symbian-fixed). Uses a [proxy server](/proxy/) (dscjava.uwmpr.online is the default).

![Screenshots](img/screenshots.png)

* [Download](https://github.com/gtrxAC/discord-j2me/releases/latest)
* [FAQ](https://github.com/gtrxAC/discord-j2me/wiki/FAQ)
* [Discord server](https://discord.gg/2GKuJjQagp) (#apps-and-games)
* [Telegram group](https://t.me/dscforsymbian)

## Status
### Working
* Server list
* Channel list
* Message reading
* Message sending
* Reading older messages
* Direct messages and group DMs

### Experimental
* Gateway/WebSocket (live message updates)
  * The gateway proxy uses TLS 1.0, meaning it will only work on devices with the proper certificates installed (Let's Encrypt).

### Not implemented
* Replying to messages
* Ping/unread indicators
* Pretty much everything else

## How to build
1. Create an `sdk` folder inside the repo with the following contents:
    * `jdk1.6.0_45` folder containing that version of the Java Development Kit.
        * If you have another JDK installation that supports Java 1.3, you can change the `OLD_JAVA_HOME` variable in `build.sh` to point to it.
    * `jdk-22.0.1` folder containing that version of the JDK.
        * If you have another modern JDK installation, change the `JAVA_HOME` variable in `build.sh`.
        * On Linux, you may be able to use the `OLD_JAVA_HOME` in place of the modern JDK.
    * `cldcapi11.jar` and `midpapi20.jar` from the Sun Wireless Toolkit.
    * `proguard.jar` (can be found in the `lib` folder inside the ZIP available [here](https://github.com/Guardsquare/proguard/releases))
2. Run `build.sh`.

## Thanks
* [@uwmpr](https://github.com/uwmpr) for hosting the default proxy server
* [@shinovon](https://github.com/shinovon) for their Java ME [JSON library](https://github.com/shinovon/NNJSON)
