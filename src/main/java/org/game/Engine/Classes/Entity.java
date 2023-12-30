package org.game.Engine.Classes;

import org.game.Engine.Classes.Components.EntityComponent;
import org.game.Engine.Classes.Components.Transform;
import org.game.Engine.Systems.FileSystem.Serializable;
import org.game.Engine.Systems.JECEngine;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an entity in the game engine.
 */
public class Entity {

    /**
     * Set of components attached to the entity.
     */
    public Set<EntityComponent> components = new HashSet<>();

    /**
     * Transform component representing the position and orientation of the entity.
     */
    public Transform transform;

    /**
     * Indicates whether the entity is enabled.
     */
    private boolean enabled = true;

    /**
     * Unique identifier for the entity.
     *
     * @see #getID()
     */
    @Serializable
    private final int ID;

    /**
     * Parent identifier for hierarchical relationships between entities.
     *
     * @see #getParentID()
     */
    private int parentID = -1;

    /**
     * Set of identifiers for child entities.
     *
     * @see #getChildrenIDs()
     */
    private final Set<Integer> childrenIDs;

    /**
     * Gets the unique identifier of the entity.
     *
     * @return The entity's ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Constructs an entity with an initial position.
     *
     * @param initialPos        The initial position of the entity.
     * @see #Entity(Vec2, boolean)
     */
    public Entity(Vec2 initialPos) {
        this(initialPos, true);
    }

    /**
     * Constructs an entity with an initial position and specified initial state.
     *
     * @param initialPos        The initial position of the entity.
     * @param enabledAtStart    The initial state of the entity (enabled or disabled).
     */
    public Entity(Vec2 initialPos, boolean enabledAtStart) {
        this.childrenIDs = new HashSet<>();
        this.ID = this.hashCode();
        this.transform = new Transform();
        this.transform.attach(this);
        this.transform.setPosition(initialPos);
        this.enabled = enabledAtStart;
        try {
            JECEngine.instantiate(this);
        } catch (NullPointerException e) {
            JECEngine.init();
            JECEngine.instantiate(this);
        }
        start();
    }

    /**
     * Adds a component of the specified class to the entity.
     *
     * @param componentClass    The class of the component to be added.
     * @param <T>               The type of the component.
     */
    public <T extends EntityComponent> void addComponent(Class<T> componentClass) {
        try {
            T componentInstance = componentClass.getDeclaredConstructor().newInstance();
            componentInstance.attach(this);
            this.components.add(componentInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a component of the specified class attached to the entity.
     *
     * @param componentClass    The class of the component to be retrieved.
     * @param <T>               The type of the component.
     * @return                  The component instance, or null if not found.
     */
    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        for (EntityComponent component : components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null; // Return null if the component is not found
    }

    /**
     * Method called when the entity is started.
     */
    public void start() {
        // Implementation specific to each entity.
    }

    /**
     * Updates the entity, invoking the update method of each enabled component.
     *
     * @param g     The graphics context for rendering.
     * @throws Exception    If an exception occurs during the update.
     */
    public void update(Graphics g) throws Exception {
        if (!enabled) return;
        for (EntityComponent comp : components) {
            if (comp.getEnabled()) comp.update(g);
        }
    }

    /**
     * Gets the enabled state of the entity.
     *
     * @return  True if the entity is enabled, false otherwise.
     */
    public boolean getEnabled() {
        return this.enabled;
    }

    /**
     * Sets the enabled state of the entity.
     *
     * @param enabled   The desired enabled state.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Adds a child entity to the entity, establishing a parent-child relationship.
     *
     * @param child     The child entity to be added.
     */
    public void addChild(Entity child) {
        childrenIDs.add(child.getID());
        child.parentID = this.parentID;
    }

    /**
     * Gets the parent identifier of the entity.
     *
     * @return  The parent identifier.
     */
    public int getParentID() {
        return this.parentID;
    }

    /**
     * Gets an array of identifiers for the child entities.
     *
     * @return  An array of child entity identifiers.
     */
    public Object[] getChildrenIDs() {
        return childrenIDs.toArray();
    }
}
