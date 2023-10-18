package com.piggygaming.ezmapdl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.piggygaming.ezmapdl.FileUtils.unzipFile;

public class unzipThread extends Thread {

        String fileZip;
        File destDir;
        MinecraftClient client;

        public unzipThread(String fileZip, File destDir, MinecraftClient client) {
            this.fileZip = fileZip;
            this.destDir = destDir;
            this.client = client;
        }

        public void run() {
                try {
                        unzipFile(this.fileZip, this.destDir);
                        new File(this.fileZip).delete();
                        this.client.execute(() -> client.setScreen(new SelectWorldScreen(new TitleScreen())));
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

}
