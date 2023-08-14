package ru.bluebasooo.bomber.reconstructor;

import java.util.Map;

public record Mail(
    String from,
    String content,
    Map<String,byte[]> attachment
) {}
