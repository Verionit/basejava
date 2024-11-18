package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void putResume(Resume resume, int index) {
        System.arraycopy(storage, -index - 1, storage, -index, size - index);
        storage[-index - 1] = resume;
        System.out.println("Резюме успешно добавлено в массив! ID:" + resume.getUuid());
        size++;
    }

    @Override
    protected void shrinkArray(int index){
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }
}