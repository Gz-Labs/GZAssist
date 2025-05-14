package br.com.gabrizord.gzedusolve.ai;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public final class AiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AiService.class);
    private static final String PROMPT = """
            Você é um tutor especializado em Engenharia de Software, com experiência em resolver questões de provas. Ao receber a imagem da questão (incluindo enunciado e alternativas), siga este fluxo:

            1. Extraia o enunciado e todas as opções A, B, C, D, E.
            2. Analise criticamente cada alternativa, descartando passo a passo as incorretas.
            3. Selecione a resposta correta e justifique seu raciocínio em até 2 linhas.
            4. Apresente apenas:
               • “Resposta: <Letra>”
               • “Justificativa: <duas linhas>”

            Não inclua nada além desses dois itens.""";

    private final OpenAIClient client;

    public AiService() {
        this.client = OpenAIOkHttpClient.fromEnv();
        LOGGER.debug("OpenAIClient initialized from environment variables.");
    }

    public Optional<String> solve(Rectangle captureArea) {
        try {
            LOGGER.debug("Creating screenshot in area: {}", captureArea);
            BufferedImage screenshot = new Robot().createScreenCapture(captureArea);

            String dataUrl = toBase64DataUrl(screenshot);
            LOGGER.debug("Image converted to data URL, length: {} characters", dataUrl.length());

            ChatCompletionContentPart textPart =
                    ChatCompletionContentPart.ofText(
                            ChatCompletionContentPartText.builder()
                                    .text(PROMPT)
                                    .build());

            ChatCompletionContentPart imagePart =
                    ChatCompletionContentPart.ofImageUrl(
                            ChatCompletionContentPartImage.builder()
                                    .imageUrl(ChatCompletionContentPartImage.ImageUrl.builder()
                                            .url(dataUrl)
                                            .build())
                                    .build());

            ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                    .model(ChatModel.GPT_4_1)
                    .maxCompletionTokens(800)
                    .addUserMessageOfArrayOfContentParts(List.of(textPart, imagePart))
                    .build();

            LOGGER.info("Requesting AI answer for image ({}x{}) with prompt: {}", screenshot.getWidth(), screenshot.getHeight(), PROMPT);

            return client.chat().completions().create(params)
                    .choices().stream()
                    .findFirst()
                    .flatMap(choice -> {
                        LOGGER.debug("AI response received successfully");
                        return choice.message().content().stream().findFirst();
                    });

        } catch (Exception ex) {
            LOGGER.error("Error during AI solve: {}", ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    private static String toBase64DataUrl(BufferedImage img) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", baos);
            String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
            return "data:image/png;base64," + base64;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}