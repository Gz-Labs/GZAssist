package br.com.gzlabs.gzassist.util;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.*;

import java.util.List;

public final class PromptTemplates {

    private static final String PROMPT_EXAM_QUESTION = """
            You are a tutor specialized in Software Engineering, experienced in solving exam questions. Upon receiving the image of the question (including statement and alternatives), follow this flow:
            
            1. Extract the statement and all options A, B, C, D, E.
            2. Critically analyze each alternative, step-by-step discarding the incorrect ones.
            3. Select the correct answer and justify your reasoning in up to 2 lines.
            4. Present only:
               • "Answer: <Letter>"
               • "Justification: <two lines>"
            
            Do not include anything beyond these two items.""";

    private static final String PROMPT_CODE_EXPLAIN = """
            You are an experienced programming tutor. Analyze the provided image, which may contain a code snippet, an error message, or both.
            Provide a clear and concise explanation of the code or error. If it's an error, suggest possible causes and solutions.
            If it's a code snippet, explain its functionality, logic, and, if applicable, suggest improvements or potential issues.
            Keep the explanation focused and didactic.""";

    private static final String PROMPT_SUMMARIZE = """
            You are an efficient summarization assistant. Analyze the content of the provided image, which could be text, an article, slides, or any other informational material.
            Create a concise and accurate summary of the main points of the content.
            The summary should capture the essence of the material, omitting secondary details but preserving crucial information.
            Present only the summary.""";

    private static final String PROMPT_TRANSLATE = """
            You are a multilingual translator. Analyze the provided image and identify the language of the visible text.
            Translate the text to English.
            If there are multiple languages in the image, translate the predominant or most relevant language.
            Present only the translation.""";

    private static final String PROMPT_AUTO_DETECT = """
            You are an intelligent multifunctional assistant. Analyze the provided image and determine the nature of its content (e.g., multiple-choice question, code snippet, text to summarize, text to translate).
            Based on your analysis, perform the most appropriate task (solve question, explain code, summarize, translate).
            If it's a question, follow the answer and justification format.
            If it's code, explain it.
            If it's text to summarize, provide the summary.
            If it's for translation, provide the translation into English.
            Act autonomously to provide the most useful result.""";

    private ChatCompletionCreateParams createChatCompletionParams(String promptText, String imageUrl) {
        ChatCompletionContentPart textPart = ChatCompletionContentPart.ofText(
                ChatCompletionContentPartText.builder().text(promptText).build()
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

    public ChatCompletionCreateParams forExamQuestion(String imageUrl) {
        return createChatCompletionParams(PROMPT_EXAM_QUESTION, imageUrl);
    }

    public ChatCompletionCreateParams forCodeExplain(String imageUrl) {
        return createChatCompletionParams(PROMPT_CODE_EXPLAIN, imageUrl);
    }

    public ChatCompletionCreateParams forSummarize(String imageUrl) {
        return createChatCompletionParams(PROMPT_SUMMARIZE, imageUrl);
    }

    public ChatCompletionCreateParams forTranslate(String imageUrl) {
        return createChatCompletionParams(PROMPT_TRANSLATE, imageUrl);
    }

    public ChatCompletionCreateParams forAutoDetect(String imageUrl) {
        return createChatCompletionParams(PROMPT_AUTO_DETECT, imageUrl);
    }
}