package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Резюме уже существует ID:" + uuid, uuid);
    }
}
