package br.com.gabrizord.gzedusolve.hotkey;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HotkeyService implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HotkeyService.class);
    private static final int HOTKEY_ID = 1;

    public HotkeyService(Runnable onTrigger) {
        LOG.debug("Inicializando JIntellitype");
        JIntellitype.getInstance();

        JIntellitype.getInstance().addHotKeyListener(identifier -> {
            LOG.trace("WM_HOTKEY recebido (id={})", identifier);
            if (identifier == HOTKEY_ID) {
                LOG.info("Hotkey global pressionada (Ctrl+Alt+K)");
                Platform.runLater(onTrigger);
            } else {
                LOG.warn("Hotkey desconhecida recebida (id={})", identifier);
            }
        });

        int modifiers = JIntellitypeConstants.MOD_CONTROL + JIntellitypeConstants.MOD_ALT;
        LOG.debug("Registrando hotkey id={} mods={} key='K'...", HOTKEY_ID, modifiers);
        JIntellitype.getInstance().registerHotKey(HOTKEY_ID, modifiers, 'K');
        LOG.info("Hotkey global registrada com sucesso");
    }

    @Override
    public void close() {
        try {
            LOG.debug("Desregistrando hotkey id={}...", HOTKEY_ID);
            JIntellitype.getInstance().unregisterHotKey(HOTKEY_ID);
            JIntellitype.getInstance().cleanUp();
            LOG.info("JIntellitype finalizado e recursos liberados");
        } catch (Exception e) {
            LOG.error("Erro ao liberar JIntellitype", e);
        }
    }
}
