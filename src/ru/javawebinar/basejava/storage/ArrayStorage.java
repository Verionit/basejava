package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (!overflowOrExist(resume, index)) {
            putResume(resume, index);
        }
    }

    @Override
    protected void putResume(Resume resume, int index){
        storage[size] = resume;
        size++;
        System.out.println("Резюме успешно сохранено! ID:" + resume.getUuid());
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Не найдено резюме ID:" + uuid);
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
        System.out.println("Резюме удалено! ID:" + uuid);
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
