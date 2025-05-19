package br.com.gzlabs.gzassist.adapters;

import br.com.gzlabs.gzassist.core.AnswerProvider;
import br.com.gzlabs.gzassist.core.Mode;
import br.com.gzlabs.gzassist.errors.AiException;
import br.com.gzlabs.gzassist.util.ImageUtils;
import br.com.gzlabs.gzassist.util.PromptTemplates;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.services.blocking.chat.ChatCompletionService;

import java.awt.image.BufferedImage;
import java.util.Optional;

public final class OpenAiAnswerProvider implements AnswerProvider {

    private final ChatCompletionService chat;
    private final PromptTemplates templates;

    public OpenAiAnswerProvider(ChatCompletionService chat, PromptTemplates templates) {
        this.chat = chat;
        this.templates = templates;
    }

    @Override
    public Optional<String> answer(BufferedImage screenshot, Mode mode) throws AiException {
        try {
            String dataUrl = ImageUtils.toDataUrl(screenshot);
            ChatCompletionCreateParams params = switch (mode) {
                case EXAM_QUESTION -> templates.forExamQuestion(dataUrl);
                case CODE_EXPLAIN -> templates.forCodeExplain(dataUrl);
                case SUMMARIZE -> templates.forSummarize(dataUrl);
                case TRANSLATE -> templates.forTranslate(dataUrl);
                case AUTO_DETECT -> templates.forAutoDetect(dataUrl);
            };
            return chat.create(params)
                    .choices().stream()
                    .findFirst()
                    .flatMap(choice -> choice.message().content().stream().findFirst());
        } catch (Exception e) {
            throw new AiException("Erro ao obter resposta da IA", e);
        }
    }
}
