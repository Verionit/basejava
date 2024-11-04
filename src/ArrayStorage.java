/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++){
            storage[i] = null;
            if (storage[i+1] == null && i < (storage.length-1)){
                break;
            }
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i <= size; i++) {
            if(storage[i].uuid.equals(uuid)){
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= size; i++) {
            assert storage[i] != null;
            if(storage[i].uuid.equals(uuid)){
                storage[i] = null;


                size--;
                break;
            }
        }
        System.out.println("Не найдено резюме " + uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] temp = new Resume[size];
        for(int i = 0; i < size; i++){
            temp[i] = storage[i];
        }
        return temp;
    }

    int size() {
        return size;
    }
}
