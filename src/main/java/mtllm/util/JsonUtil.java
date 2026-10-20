package mtllm.util;

/**
 * Minimal JSON helper methods for the OpenAI request and response shape.
 *
 * <p>In simple terms, this escapes prompt text for JSON and extracts the assistant message from
 * the OpenAI response without adding a full JSON library yet.</p>
 */
public final class JsonUtil {
    private JsonUtil() {
    }

    public static String quote(String value) {
        return "\"" + escape(value == null ? "" : value) + "\"";
    }

    public static String escape(String value) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\\':
                    out.append("\\\\");
                    break;
                case '"':
                    out.append("\\\"");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\r':
                    out.append("\\r");
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                default:
                    if (c <= 0x1F) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                    break;
            }
        }
        return out.toString();
    }

    /**
     * Reads one integer field out of the usage object of an OpenAI chat completion response,
     * or 0 when it is absent.
     *
     * <p>Every non-streaming completion carries a usage object, so this needs no extra request.
     * Streaming responses omit it unless stream_options.include_usage is set.</p>
     */
    public static int extractUsageField(String json, String field) {
        if (json == null || field == null) {
            return 0;
        }
        int usageIdx = json.indexOf("\"usage\"");
        if (usageIdx < 0) {
            return 0;
        }
        String quotedField = "\"" + field + "\"";
        int keyIdx = json.indexOf(quotedField, usageIdx);
        if (keyIdx < 0) {
            return 0;
        }
        int i = keyIdx + quotedField.length();
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        if (i >= json.length() || json.charAt(i) != ':') {
            return 0;
        }
        i++;
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        int start = i;
        while (i < json.length() && Character.isDigit(json.charAt(i))) {
            i++;
        }
        if (i == start) {
            return 0;
        }
        try {
            return Integer.parseInt(json.substring(start, i));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String extractOpenAiContent(String json) {
        int messageIdx = json.indexOf("\"message\"");
        if (messageIdx < 0) {
            return null;
        }
        int keyIdx = json.indexOf("\"content\"", messageIdx);
        if (keyIdx < 0) {
            return null;
        }
        int i = keyIdx + "\"content\"".length();
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        if (i >= json.length() || json.charAt(i) != ':') {
            return null;
        }
        i++;
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) {
            i++;
        }
        if (i >= json.length() || json.charAt(i) != '"') {
            return null;
        }
        return parseStringLiteral(json, i + 1);
    }

    private static String parseStringLiteral(String json, int startIndex) {
        int i = startIndex;
        boolean escaping = false;
        StringBuilder out = new StringBuilder();
        while (i < json.length()) {
            char c = json.charAt(i++);
            if (escaping) {
                switch (c) {
                    case '"':
                        out.append('"');
                        break;
                    case '\\':
                        out.append('\\');
                        break;
                    case '/':
                        out.append('/');
                        break;
                    case 'b':
                        out.append('\b');
                        break;
                    case 'f':
                        out.append('\f');
                        break;
                    case 'n':
                        out.append('\n');
                        break;
                    case 'r':
                        out.append('\r');
                        break;
                    case 't':
                        out.append('\t');
                        break;
                    case 'u':
                        if (i + 4 > json.length()) {
                            return null;
                        }
                        String hex = json.substring(i, i + 4);
                        i += 4;
                        try {
                            out.append((char) Integer.parseInt(hex, 16));
                        } catch (NumberFormatException e) {
                            return null;
                        }
                        break;
                    default:
                        out.append(c);
                        break;
                }
                escaping = false;
            } else if (c == '\\') {
                escaping = true;
            } else if (c == '"') {
                return out.toString();
            } else {
                out.append(c);
            }
        }
        return null;
    }
}
