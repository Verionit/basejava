package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractArrayStorageTest {


    private static final Storage storage = new ArrayStorage();

    @BeforeEach
    void beforeEach() {
        storage.clear();
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void getAll() {
        Resume[] testResume = {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")};
        assertArrayEquals(testResume, storage.getAll());
    }

    @Test
    void save() {
        Resume[] resumes = {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3"), new Resume("uuid4")};
        storage.save(new Resume("uuid4"));
        assertArrayEquals(resumes, storage.getAll());
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume("uuid4")));
    }

    @Test
    void update() {
        Resume updatedResume = new Resume("uuid1");
        storage.update(updatedResume);
        assertEquals(updatedResume, storage.get("uuid1"));
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid55")));
    }

    @Test
    void get() {
        Resume updatedToGet = new Resume("uuid2");
        assertEquals(updatedToGet, storage.get("uuid2"));
    }

    @Test
    void delete() {
        Resume[] testResume = {new Resume("uuid3"), new Resume("uuid2")};
        storage.delete("uuid1");
        assertArrayEquals(testResume, storage.getAll());
    }

    @Test
    void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }
}