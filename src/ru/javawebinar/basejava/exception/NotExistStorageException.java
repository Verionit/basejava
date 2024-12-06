package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super("Не найдено резюме ID:" + uuid, uuid);
    }
}
