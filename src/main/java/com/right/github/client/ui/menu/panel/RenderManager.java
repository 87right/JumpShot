package main.java.com.right.github.client.ui.menu.panel;


import java.awt.*;

public class RenderManager {
    private final MainPanel panel;

    private Graphics2D currentGraBackground;
    private Graphics2D currentGraEntities;
    private Graphics2D currentGraBlocks;
    private Graphics2D currentGraParticles;
    private Graphics2D currentGraUI;
    private Graphics2D currentGraEffects;

    RenderManager(MainPanel panel){
        this.panel = panel;
    }

    public Graphics2D getGraBackground() {
        disposeALL();
        currentGraBackground = panel.getLayerBackground().createGraphics();
        return currentGraBackground;
    }
    public Graphics2D getGraEntities() {
        disposeALL();
        currentGraEntities = panel.getLayerEntities().createGraphics();
        return currentGraEntities;
    }
    public Graphics2D getGraBlocks() {
        disposeALL();
        currentGraBlocks = panel.getLayerBlocks().createGraphics();
        return currentGraBlocks;
    }
    public Graphics2D getGraParticles() {
        disposeALL();
        currentGraParticles = panel.getLayerParticles().createGraphics();
        return currentGraParticles;
    }
    public Graphics2D getGraUI() {
        disposeALL();
        currentGraUI = panel.getLayerUI().createGraphics();
        return currentGraUI;
    }
    public Graphics2D getGraEffects() {
        disposeALL();
        currentGraEffects = panel.getLayerEffects().createGraphics();
        return currentGraEffects;
    }

    // Rustの所有権っぽい
    private void disposeALL(){
        if (currentGraBackground != null){currentGraBackground.dispose();}
        if (currentGraEntities != null){currentGraEntities.dispose();}
        if (currentGraBlocks != null){currentGraBlocks.dispose();}
        if (currentGraParticles != null){currentGraParticles.dispose();}
        if (currentGraUI != null){currentGraUI.dispose();}
        if (currentGraEffects != null){currentGraEffects.dispose();}
    }
}
