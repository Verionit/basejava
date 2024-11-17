package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (STORAGE_LIMIT <= size) {
            System.out.println("Storage overflow!");
        } else if (isExisting(index)) {
            System.out.println("Данный ID:" + resume.getUuid() + " уже существует!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме успешно сохранено! ID:" + resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (isExisting(index)) {
            storage[index] = resume;
            System.out.println("Резюме успешно обновлено ID:" + resume.getUuid());
        } else {
            System.out.println("Такое резюме не найдено в массиве ID: " + resume.getUuid());
        }
    }

    @Override
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

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
