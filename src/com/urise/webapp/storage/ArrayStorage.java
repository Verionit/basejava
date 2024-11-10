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
        int index = getIndex(resume.uuid);

        if (STORAGE_LIMIT == size) {
            System.out.println("В массиве нет места для хранения!");
        } else if (isExisting(index)) {
            System.out.println("Данный ID:" + resume.uuid + " уже есть в массиве!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме успешно сохранено! ID:" + resume.uuid);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.uuid);

        if (isExisting(index)) {
            storage[index] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.uuid);
        } else {
            System.out.println("Такое резюме не найдено в массиве ID: " + resume.uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Не найден данный ID: " + uuid);
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (isExisting(index)) {
            storage[index] = storage[size - 1];
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

    protected boolean isExisting(int index) {
        return index >= 0;
    }
}
