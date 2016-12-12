package net.frozenorb.hqrebel;

import net.frozenorb.hqrebel.listeners.CommandClassReloadListener;
import net.frozenorb.hqrebel.listeners.ListenerClassReloadListener;
import net.frozenorb.hqrebel.listeners.StaffMessageClassReloadListener;

import org.zeroturnaround.javarebel.ClassEventListener;
import org.zeroturnaround.javarebel.ClassResourceSource;
import org.zeroturnaround.javarebel.Plugin;
import org.zeroturnaround.javarebel.Reloader;
import org.zeroturnaround.javarebel.ReloaderFactory;

import java.util.Arrays;

public final class HqRebel implements Plugin {

    @Override
    public void preinit() {
        Reloader reloader = ReloaderFactory.getInstance();

        for (ClassEventListener listener : Arrays.asList(
            new CommandClassReloadListener(),
            new ListenerClassReloadListener(),
            new StaffMessageClassReloadListener()
        )) {
            reloader.addClassLoadListener(listener);
            reloader.addClassReloadListener(listener);
        }
    }

    @Override
    public boolean checkDependencies(ClassLoader loader, ClassResourceSource resourceSource) {
        // PluginClassLoader doesn't have public access :(
        return loader.getClass().getName().equals("org.bukkit.plugin.java.PluginClassLoader");
    }

    @Override
    public String getId() {
        return "hqrebel";
    }

    @Override
    public String getName() {
        return "HQRebel";
    }

    @Override
    public String getDescription() {
        return "MineHQ JRebel integration to hot-reload commands and listeners";
    }

    @Override
    public String getAuthor() {
        return "@colinmcdonald22";
    }

    @Override
    public String getWebsite() {
        return "MineHQ.com";
    }

    @Override
    public String getSupportedVersions() {
        return "N/A";
    }

    @Override
    public String getTestedVersions() {
        return "N/A";
    }

}