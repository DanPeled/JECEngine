package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;

import java.awt.*;

public abstract class EntityComponent {
    private boolean enabled = true;
    public Entity entity;

    public EntityComponent() {
        start();
    }

    public void attach(Entity parentObj) {
        this.entity = parentObj;
    }

    public abstract void start();

    public abstract void update(Graphics g);

    public abstract void onStop();

    public final void setEnabled(boolean state) {
        if (!state) {
            onStop();
        }
        enabled = state;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getName() {
        return this.getClass().getSimpleName();
    }
}
