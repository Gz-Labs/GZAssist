package br.com.gzlabs.gzassist.adapters;

import br.com.gzlabs.gzassist.core.HotkeyBinder;
import br.com.gzlabs.gzassist.errors.HotkeyException;
import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GlobalHotkeyBinder implements HotkeyBinder {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalHotkeyBinder.class);

    private static final int HOTKEY_ID = 1;
    private static final int MODIFIERS = JIntellitypeConstants.MOD_CONTROL + JIntellitypeConstants.MOD_ALT;
    private static final int KEY = 'K';

    @Override
    public void register(Runnable onTrigger) throws HotkeyException {
        try {
            JIntellitype jintellitype = JIntellitype.getInstance();
            jintellitype.addHotKeyListener(id -> {
                if (id == HOTKEY_ID) {
                    LOG.info("Atalho global pressionado: Ctrl+Alt+K");
                    Platform.runLater(onTrigger);
                }
            });
            jintellitype.registerHotKey(HOTKEY_ID, MODIFIERS, KEY);
            LOG.debug("Hotkey registrada com sucesso.");
        } catch (Exception e) {
            throw new HotkeyException("Falha ao registrar hotkey global", e);
        }
    }

    @Override
    public void close() {
        try {
            JIntellitype.getInstance().unregisterHotKey(HOTKEY_ID);
            JIntellitype.getInstance().cleanUp();
            LOG.info("Hotkey desregistrada e recursos liberados.");
        } catch (Exception e) {
            LOG.warn("Erro ao desregistrar hotkey", e);
        }
    }
}
