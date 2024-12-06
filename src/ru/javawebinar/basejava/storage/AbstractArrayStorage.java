package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
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
            throw new StorageException("Storage overflow!", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        size++;
        putResume(resume, index);
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.getUuid());
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        shrinkArray(index);
        size--;
        System.out.println("Резюме удалено! ID:" + uuid);
    }

    public void clear() {

        if(size > 0){
            Arrays.fill(storage, 0, size - 1, null);
            size = 0;
            System.out.println("Массив полностью очищен!");
        }

    }

    protected abstract int getIndex(String uuid);

    protected abstract void putResume(Resume resume, int index);

    protected abstract void shrinkArray(int index);
}