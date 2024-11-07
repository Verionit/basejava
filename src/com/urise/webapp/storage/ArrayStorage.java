package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;
    int foundedIndex;

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
            return;
        }

        if (isResumePresent(resume)) {
            System.out.println("Данный ID:" + resume.uuid + " уже есть в массиве!");
            return;
        }

        storage[size] = resume;
        size++;
        System.out.println("Резюме успешно сохранено! ID:" + resume.uuid);
    }

    public void update(Resume resume) {
        if (isResumePresent(resume)) {
            storage[foundedIndex] = resume;
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

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }

        System.out.println("Не найден данный ID: " + uuid);
        return null;
    }

    public void delete(String uuid) {
        Resume tempResume = new Resume();
        tempResume.uuid = uuid;

        if (isResumePresent(tempResume)) {
            storage[foundedIndex] = storage[size - 1];
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

    boolean isResumePresent(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.uuid.equals(storage[i].uuid)) {
                foundedIndex = i;
                return true;
            }
        }
        return false;
    }
}
