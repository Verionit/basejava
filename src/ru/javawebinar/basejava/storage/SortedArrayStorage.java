package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (overflowOrExist(resume, index)) {
            return;
        }

        System.arraycopy(storage, -index - 1, storage, -index, size - index);
        storage[-index - 1] = resume;
        System.out.println("Резюме успешно добавлено в массив! ID:" + resume.getUuid());
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Не найдено резюме ID:" + uuid);
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
            System.out.println("Резюме удалено из сортированного массива! ID:" + uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}