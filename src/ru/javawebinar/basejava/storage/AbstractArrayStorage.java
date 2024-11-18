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

        if (STORAGE_LIMIT <= size) {
            System.out.println("Storage overflow!");
            return;
        } else if (index >= 0) {
            System.out.println("Данный ID:" + resume.getUuid() + " уже существует!");
            return;
        }
        putResume(resume, index);
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.getUuid());
        } else {
            System.out.println("Такое резюме не найдено в массиве ID: " + resume.getUuid());
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не найден данный ID: " + uuid);
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Не найдено резюме ID:" + uuid);
            return;
        }
        shrinkArray(index);
        System.out.println("Резюме удалено! ID:" + uuid);
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
        System.out.println("Массив полностью очищен!");
    }

    protected abstract int getIndex(String uuid);

    protected abstract void putResume(Resume resume, int index);

    protected abstract void shrinkArray(int index);
}