import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        if (size == 0) {
            System.out.println("Список чист!");
        } else {
            for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
            System.out.println("Список чист!");
        }
        size = 0;
    }

    void save(Resume r) {
        if (size == 0) {
            storage[size] = r;
            size++;
        } else {
            for (int i = 0; i < size; i++) {
                if (r.uuid.equals(storage[i].uuid)) {
                    System.out.println("Данный ID уже есть в списке!");
                    return;
                }
            }
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {

        if (size == 0) {
            System.out.println("Список пуст!");
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
        int tempPosition;

        for (int i = 0; i < size; i++) {
            assert storage[i] != null;
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                tempPosition = i;

                Resume[] temp = new Resume[size - tempPosition - 1];
                System.arraycopy(storage, i + 1, temp, 0, size - i - 1);
                System.out.println(Arrays.toString(temp));
                System.arraycopy(temp, 0, storage, i, size - 1);
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
        Resume[] temp = new Resume[size];
        System.arraycopy(storage, 0, temp, 0, size);
        return temp;
    }

    int size() {
        return size;
    }
}
