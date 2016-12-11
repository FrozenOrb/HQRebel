package net.frozenorb.hqrebel.listeners;

import net.frozenorb.qlib.command.FrozenCommandHandler;

import org.zeroturnaround.javarebel.ClassEventListener;

public final class CommandClassReloadListener implements ClassEventListener {

    @Override
    public void onClassEvent(int ignored, Class<?> type) {
        FrozenCommandHandler.unregisterClass(type);
        FrozenCommandHandler.registerClass(type);
    }

    @Override
    public int priority() {
        return 1;
    }

}