public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        if (size == 0) {
            System.out.println("Массив чист!");
        } else {
            for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
            System.out.println("Массив чист!");
        }
        size = 0;
    }

    void save(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.uuid.equals(storage[i].uuid)) {
                System.out.println("Данный ID уже есть в массиве!");
                return;
            }
        }
        storage[size] = resume;
        size++;
    }

    Resume get(String uuid) {
        if (size == 0) {
            System.out.println("Массив пуст!");
            return null;
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Не найден данный ID");
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("Не найдено резюме " + uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    int size() {
        return size;
    }
}
