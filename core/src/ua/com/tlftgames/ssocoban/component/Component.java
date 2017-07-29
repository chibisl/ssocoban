package ua.com.tlftgames.ssocoban.component;

import ua.com.tlftgames.ssocoban.object.GameObject;

public abstract class Component {
    private GameObject object;

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }

}
