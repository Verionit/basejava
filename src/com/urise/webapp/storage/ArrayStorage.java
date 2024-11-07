package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        if (size == 0) {
            System.out.println("Массив пуст!");
            return;
        }
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
        System.out.println("Массив полностью очищен!");
    }


    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("В массиве нет места для хранения!");
        } else if (isExisting(resume.uuid)) {
            System.out.println("Данный ID:" + resume.uuid + " уже есть в массиве!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме успешно сохранено! ID:" + resume.uuid);
        }
    }

    public void update(Resume resume) {
        if (isExisting(resume.uuid)) {
            storage[getIndex(resume.uuid)] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.uuid);
        } else {
            System.out.println("Такое резюме не найдено в массиве ID: " + resume.uuid);
        }
    }

    public Resume get(String uuid) {
        if (size == 0) {
            System.out.println("Массив пустой!");
            return null;
        }

        if (getIndex(uuid) < 0) {
            System.out.println("Не найден данный ID: " + uuid);
            return null;
        } else {
            return storage[getIndex(uuid)];
        }
    }

    public void delete(String uuid) {
        if (isExisting(uuid)) {
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме удалено! ID:" + uuid);
            return;
        }
        System.out.println("Не найдено резюме ID:" + uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExisting(String uuid) {
        return getIndex(uuid) >= 0;
    }
}
