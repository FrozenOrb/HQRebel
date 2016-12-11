# HQRebel
JRebel plugin to hot-reload commands and listeners. HQRebel supplements JRebel's reloading by automatically rescanning Bukkit `Listener`s and qLib `@Command`s.

# Features

* Automatic registration/unregistration of new events in `Listener`s present on application startup
* Automatic registration/unregistration of new `@Command`s added anywhere in the JVM.
    * Modifications to aliases, permissions, parameters, etc. all work as expected.

# Usage

Nothing needs to be installed on the developer's computer or done by the developer to use HQRebel.

# Installation

In addition to the normal JRebel flags, the HQRebel binary must be present anywhere on the remote server and must be referenced
in the JVM startup flags as follows: `-Drebel.plugins=/root/HQRebel.jar -DRebel.hqrebel=true`. `/root/HQRebel.jar` should be replaced with the location of the binary.
