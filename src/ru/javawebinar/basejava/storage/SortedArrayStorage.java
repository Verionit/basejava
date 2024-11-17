package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

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
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (STORAGE_LIMIT <= size) {
            System.out.println("Storage overflow!");
        } else if (isExisting(index)){
            System.out.println("Данный ID:" + resume.getUuid() + " уже существует!");
        } else if (size == 0){
            storage[0] = resume;
            System.out.println("Резюме успешно сохранено в первую ячейку! ID:" + resume.getUuid());
            return;
        }

        while (true) {
            int lowerBound = 0;
            int upperBound = size - 1;
            int currentIndex = 0;
            while (true) {
                currentIndex = (upperBound + lowerBound) / 2;
                if (storage[currentIndex].compareTo(resume) < 0) {
                    lowerBound = currentIndex + 1; // its in the upper
                    if (lowerBound > upperBound)
                        currentIndex += 1;
                    storage[currentIndex] = resume;

                } else {
                    upperBound = currentIndex - 1; // its in the lower
                    if (lowerBound > upperBound)
                        storage[currentIndex] = resume;
                    size++;
                    return;
                }
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index < 0 ) {
            System.out.println("Не найдено резюме ID:" + uuid);
            return;
        } else {
            Resume[] tempArray = new Resume[size - index - 1];
            System.arraycopy(storage, index + 1, tempArray, 0, size - index - 1);
            System.out.println(Arrays.toString(tempArray));
            System.arraycopy(tempArray, 0, storage, index, size - 1);
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