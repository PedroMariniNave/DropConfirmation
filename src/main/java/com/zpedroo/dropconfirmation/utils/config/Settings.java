package com.zpedroo.dropconfirmation.utils.config;

import com.zpedroo.dropconfirmation.utils.FileUtils;

public class Settings {

    public static final int EXPIRE_AFTER = FileUtils.get().getInt(FileUtils.Files.CONFIG, "Settings.expire-after");
}