package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;

import java.awt.*;

/**
 * An abstract base class for components that can be attached to entities in the game engine.
 */
public abstract class EntityComponent {

    /**
     * Indicates whether the component is currently enabled.
     */
    private boolean enabled = true;

    /**
     * The entity to which this component is attached.
     *
     * @see #attach(Entity)
     */
    public Entity entity;

    /**
     * Attaches the component to a specific entity and invokes the start method.
     *
     * @param parentObj The entity to which the component will be attached.
     */
    public void attach(Entity parentObj) {
        this.entity = parentObj;
        start();
    }

    /**
     * Initialization method called when the component is attached to an entity.
     * Override this method to perform setup actions.
     */
    public abstract void start();

    /**
     * Update method called during the game loop to update the component's state.
     *
     * @param g The graphics context for rendering (may be null if rendering is not needed).
     * @throws Exception If an error occurs during the update process.
     */
    public abstract void update(Graphics g) throws Exception;

    /**
     * Method called when the component is disabled.
     * Override this method to perform cleanup actions when the component is turned off.
     *
     * @see #setEnabled(boolean)
     * @see #onStop()
     */
    public abstract void onStop();

    /**
     * Sets the enabled state of the component.
     * If the component is being disabled, the onStop method is called before changing the state.
     *
     * @param state The new enabled state of the component.
     */
    public final void setEnabled(boolean state) {
        if (!state) {
            onStop();
        }
        enabled = state;
    }

    /**
     * Gets the current enabled state of the component.
     *
     * @return True if the component is enabled, false otherwise.
     */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Gets the simple name of the component's class.
     *
     * @return The simple name of the component's class.
     */
    public final String getName() {
        return this.getClass().getSimpleName();
    }
}
