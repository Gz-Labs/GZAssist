package br.com.gzlabs.gzassist.core;

import br.com.gzlabs.gzassist.errors.HotkeyException;

public interface HotkeyBinder extends AutoCloseable {
    void register(Runnable onTrigger) throws HotkeyException;
}
