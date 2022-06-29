package com.zpedroo.dropconfirmation.utils.config;

import com.zpedroo.dropconfirmation.utils.FileUtils;
import com.zpedroo.dropconfirmation.utils.color.Colorize;

import java.util.List;

public class Messages {

    public static final List<String> DROP_AGAIN = Colorize.getColored(FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Messages.drop-again"));
}