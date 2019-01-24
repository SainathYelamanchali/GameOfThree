package com.takeaway.gameofthree.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the pojo which encapsulates the value for messaging
 */
public class QueueMessage implements Serializable {

    private static final long SerialVersionUID = 123576767;
    private String uuid;
    private long value;

    public static QueueMessage newInstance() {
        return new QueueMessage();
    }

    public String getUuid() {
        return uuid;
    }

    public QueueMessage setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public long getValue() {
        return value;
    }

    public QueueMessage setValue(long value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueMessage that = (QueueMessage) o;
        return value == that.value &&
                Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, value);
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "uuid='" + uuid + '\'' +
                ", value=" + value +
                '}';
    }
}
