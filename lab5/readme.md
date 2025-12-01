# Сборка и запуск
nasm -f bin boot.asm -o boot.bin
qemu-system-i386 -drive file=boot.bin,format=raw,if=floppy
![photo_2025-12-01_21-34-34](https://github.com/user-attachments/assets/a772da1b-a8e1-4a7a-ae2a-1a2a56dd7a81)


# Проверка на соответствие заданию

## Базовое решение 
- Выводится  ASCII-логотип
- Выводится название
![photo_2025-12-01_21-34-32](https://github.com/user-attachments/assets/224890d2-e7fd-4a0b-b0db-1c751d148e22)


## Для получения хорошей оценки
- Осуществлено взаимодействие с пользователем - запрос имени, приветсвие, запрос возраста
- Выполняется вычисление года рождения на основе вводимых с клавиатуры данных
![photo_2025-12-01_21-34-32 (2)](https://github.com/user-attachments/assets/c5d5251a-92cb-4d18-9a9f-8cfe7da50388)


## Для получения отличной оценки
- Выполнено особое цветовое и стилевое оформление интерфейса - белый фон + красный текст, центрирование лого и приветствия
![photo_2025-12-01_21-34-32](https://github.com/user-attachments/assets/224890d2-e7fd-4a0b-b0db-1c751d148e22)
