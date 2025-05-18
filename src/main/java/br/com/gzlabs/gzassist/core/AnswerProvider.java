package br.com.gzlabs.gzassist.core;

import java.awt.image.BufferedImage;
import java.util.Optional;
import br.com.gzlabs.gzassist.errors.AiException;

public interface AnswerProvider {
    Optional<String> answer(BufferedImage screenshot, Mode mode) throws AiException;
}
