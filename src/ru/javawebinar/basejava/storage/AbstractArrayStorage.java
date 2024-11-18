package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if(!overflowOrExist(resume, index)){
            putResume(resume, index);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.getUuid());
        } else {
            System.out.println("Такое резюме не найдено в массиве ID: " + resume.getUuid());
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
        System.out.println("Массив полностью очищен!");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не найден данный ID: " + uuid);
            return null;
        }
        return storage[index];
    }

    protected boolean overflowOrExist(Resume resume, int index) {
        if (STORAGE_LIMIT <= size) {
            System.out.println("Storage overflow!");
            return true;
        } else if (index >= 0) {
            System.out.println("Данный ID:" + resume.getUuid() + " уже существует!");
            return true;
        }
        return false;
    }

    protected abstract int getIndex(String uuid);
    protected abstract void putResume(Resume resume, int index);
}