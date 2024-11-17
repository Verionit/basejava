package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void clear() {
        if (size == 0) {
            System.out.println("Массив пуст!");
            return;
        }
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
        System.out.println("Массив полностью очищен!");
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не найден данный ID: " + uuid);
            return null;
        }
        return storage[index];
    }

    protected boolean isExisting(int index) {
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);
}