package org.game.Engine.Classes;

import org.game.Engine.Classes.Components.EntityComponent;
import org.game.Engine.Classes.Components.Transform;
import org.game.Engine.Systems.JECEngine;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Entity {
    public Set<EntityComponent> components = new HashSet<>();
    public Transform transform;
    private boolean enabled = true;

    public Entity(Vec2 initialPos) {
        this(initialPos, true);
    }

    public Entity(Vec2 initialPos, boolean enabledAtStart) {
        this.transform = new Transform();
        this.transform.attach(this);
        this.transform.position = initialPos;
        this.enabled = enabledAtStart;
        try {
            JECEngine.instantiate(this);
        } catch (NullPointerException e) {
            JECEngine.init();
            JECEngine.instantiate(this);
        }
        start();
    }

    public <T extends EntityComponent> void addComponent(Class<T> componentClass) {
        try {
            T componentInstance = componentClass.getDeclaredConstructor().newInstance();
            componentInstance.attach(this);
            this.components.add(componentInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        for (EntityComponent component : components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null; // Return null if the component is not found
    }


    public void start() {

    }

    public void update(Graphics g) throws Exception {
        if (!enabled) return;
        for (EntityComponent comp : components) {
            if (comp.getEnabled()) comp.update(g);
        }
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
