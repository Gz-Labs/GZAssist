package br.com.gzlabs.gzassist.core.impl;

import br.com.gzlabs.gzassist.core.AnswerProvider;
import br.com.gzlabs.gzassist.errors.AiException;
import br.com.gzlabs.gzassist.infra.ImageUtils;
import br.com.gzlabs.gzassist.infra.PromptTemplates;
import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import java.awt.image.BufferedImage;
import java.util.Optional;

public final class OpenAiAnswerProvider implements AnswerProvider {

    private final OpenAIClient client;
    private final PromptTemplates templates;

    public OpenAiAnswerProvider(OpenAIClient client, PromptTemplates templates) {
        this.client = client;
        this.templates = templates;
    }

    @Override
    public Optional<String> answer(BufferedImage screenshot) throws AiException {
        try {
            String dataUrl = ImageUtils.toDataUrl(screenshot);
            ChatCompletionCreateParams params = templates.forExamQuestion(dataUrl);
            return client.chat().completions().create(params)
                    .choices().stream()
                    .findFirst()
                    .flatMap(choice -> choice.message().content().stream().findFirst());
        } catch (Exception e) {
            throw new AiException("Erro ao obter resposta da IA", e);
        }
    }
}
