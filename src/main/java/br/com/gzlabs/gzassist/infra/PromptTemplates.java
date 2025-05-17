package br.com.gzlabs.gzassist.infra;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.*;

import java.util.List;

public final class PromptTemplates {

    private static final String PROMPT = """
        Você é um tutor especializado em Engenharia de Software, com experiência em resolver questões de provas. Ao receber a imagem da questão (incluindo enunciado e alternativas), siga este fluxo:

        1. Extraia o enunciado e todas as opções A, B, C, D, E.
        2. Analise criticamente cada alternativa, descartando passo a passo as incorretas.
        3. Selecione a resposta correta e justifique seu raciocínio em até 2 linhas.
        4. Apresente apenas:
           • “Resposta: <Letra>”
           • “Justificativa: <duas linhas>”

        Não inclua nada além desses dois itens.""";

    public ChatCompletionCreateParams forExamQuestion(String imageUrl) {
        ChatCompletionContentPart textPart = ChatCompletionContentPart.ofText(
                ChatCompletionContentPartText.builder().text(PROMPT).build()
        );

        ChatCompletionContentPart imagePart = ChatCompletionContentPart.ofImageUrl(
                ChatCompletionContentPartImage.builder()
                        .imageUrl(ChatCompletionContentPartImage.ImageUrl.builder().url(imageUrl).build())
                        .build()
        );

        return ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_4_1)
                .maxCompletionTokens(800)
                .addUserMessageOfArrayOfContentParts(List.of(textPart, imagePart))
                .build();
    }
}
